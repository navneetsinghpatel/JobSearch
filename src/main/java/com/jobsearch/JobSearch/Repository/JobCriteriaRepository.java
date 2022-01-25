package com.jobsearch.JobSearch.Repository;

import com.jobsearch.JobSearch.Model.Job;
import com.jobsearch.JobSearch.Model.JobPage;
import com.jobsearch.JobSearch.Model.JobSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
public class JobCriteriaRepository {

private final EntityManager entityManager;
private final CriteriaBuilder criteriaBuilder;

    public JobCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Job> findAllWithFilters(JobPage jobPage, JobSearchCriteria jobSearchCriteria){
        CriteriaQuery<Job> criteriaQuery = criteriaBuilder.createQuery(Job.class);
        Root<Job> jobRoot = criteriaQuery.from(Job.class);

        Predicate predicate = getPredicate(jobSearchCriteria, jobRoot);
        criteriaQuery.where(predicate);
        setOrder(jobPage, criteriaQuery, jobRoot);

        TypedQuery<Job> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(jobPage.getPageNumber() * jobPage.getPageSize());
        typedQuery.setMaxResults(jobPage.getPageSize());

        Pageable pageable = getPageable(jobPage);
        long jobCount = getJobCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, jobCount);
    }

    private long getJobCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Job> countRoot = countQuery.from(Job.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private Pageable getPageable(JobPage jobPage) {

        Sort sort = Sort.by(jobPage.getSortDirection(), jobPage.getSortBy());
        return PageRequest.of(jobPage.getPageNumber(), jobPage.getPageSize(), sort);
    }

    private void setOrder(JobPage jobPage, CriteriaQuery<Job> criteriaQuery, Root<Job> jobRoot) {

        if(jobPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(jobRoot.get(jobPage.getSortBy())));
        }else{
            criteriaQuery.orderBy(criteriaBuilder.desc(jobRoot.get(jobPage.getSortBy())));
        }
    }

    private Predicate getPredicate(JobSearchCriteria jobSearchCriteria, Root<Job> jobRoot) {

        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(jobSearchCriteria.getCompany())){
            if(jobSearchCriteria.getCompany().equalsIgnoreCase("any")){
                predicates.add(
                        criteriaBuilder.like(jobRoot.get("company"),
                                "%"
                        )
                );
            }else {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(jobRoot.get("company")),
                                "%" + jobSearchCriteria.getCompany().toLowerCase() + "%"
                        )
                );
            }
        }
        if(Objects.nonNull(jobSearchCriteria.getDescription())){
            if(jobSearchCriteria.getDescription().equalsIgnoreCase("any")){
                predicates.add(
                        criteriaBuilder.like(jobRoot.get("description"),
                                "%"
                        )
                );
            }else {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(jobRoot.get("description")),
                                "%" + jobSearchCriteria.getDescription().toLowerCase() + "%"
                        )
                );
            }
        }
        if(Objects.nonNull(jobSearchCriteria.getExperience())){
            if(jobSearchCriteria.getExperience().equalsIgnoreCase("any")){
                predicates.add(
                        criteriaBuilder.like(jobRoot.get("experience"),
                                "%"
                        )
                );
            }else {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(jobRoot.get("experience")),
                                "%" + jobSearchCriteria.getExperience().toLowerCase() + "%"
                        )
                );
            }
        }
        if(Objects.nonNull(jobSearchCriteria.getLocation())){
            if(jobSearchCriteria.getLocation().equalsIgnoreCase("any")){
                predicates.add(
                        criteriaBuilder.like(jobRoot.get("location"),
                                "%"
                        )
                );
            }else {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(jobRoot.get("location")),
                                "%" + jobSearchCriteria.getLocation().toLowerCase() + "%"
                        )
                );
            }
        }
        if(Objects.nonNull(jobSearchCriteria.getType())){
            if(jobSearchCriteria.getType().equalsIgnoreCase("any")){
                predicates.add(
                        criteriaBuilder.like(jobRoot.get("type"),
                                "%"
                        )
                );
            }else {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(jobRoot.get("type")),
                                "%" + jobSearchCriteria.getType().toLowerCase() + "%"
                        )
                );
            }
        }

        if(Objects.nonNull(jobSearchCriteria.getSkills())){
            System.out.println(jobSearchCriteria.getSkills());
            if(jobSearchCriteria.getSkills().contains("any")){
                predicates.add(
                        criteriaBuilder.isNotEmpty(jobRoot.get("skills"))
                );
            }else {

                 for(String skill: jobSearchCriteria.getSkills()){
                    predicates.add(
                            criteriaBuilder.isMember(skill, jobRoot.get("skills"))
                    );
                }
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

