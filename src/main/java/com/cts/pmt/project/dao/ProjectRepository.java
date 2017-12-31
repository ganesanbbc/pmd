package com.cts.pmt.project.dao;


import com.cts.pmt.project.ProjectProjection;
import com.cts.pmt.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {


    @Override
    Iterable<Project> findAll();
}
