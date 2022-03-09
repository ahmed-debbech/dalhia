package tn.dalhia.services;


import tn.dalhia.entities.JobCategory;

import java.util.List;

public interface IJobCategory {

    List<JobCategory> retrieveAllJobCategorys();

    JobCategory addJobCategory(JobCategory c);

    void deleteJobCategory(Long id);

    JobCategory updateJobCategory(JobCategory c);


    JobCategory retrieveJobCategory(Long id);
}
