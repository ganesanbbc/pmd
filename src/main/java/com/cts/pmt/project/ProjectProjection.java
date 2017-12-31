package com.cts.pmt.project;

import com.cts.pmt.project.model.Project;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineData", types = Project.class)
public interface ProjectProjection {
    Long getId();

    String getName();
}