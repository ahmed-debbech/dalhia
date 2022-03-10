package tn.dalhia.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.JobCategory;
import tn.dalhia.entities.Offer;
import tn.dalhia.repositories.JobCategoryRepository;
import tn.dalhia.repositories.OfferRepository;
import tn.dalhia.services.IJobCategory;

import java.util.List;
@Service
public class JobCategoryService implements IJobCategory {

    @Autowired
    private JobCategoryRepository JobCatRepo ;

    public List<JobCategory> retrieveAllJobCategorys() {
        return (List<JobCategory>) JobCatRepo.findAll();

    }

    public JobCategory addJobCategory(JobCategory c)
    {
        return JobCatRepo.save(c);
    }

    public void deleteJobCategory(Long id) {
        JobCatRepo.deleteById(id);

    }

    public JobCategory updateJobCategory(JobCategory c)
    {
        return JobCatRepo.save(c);
    }

    public JobCategory retrieveJobCategory(Long id) {
        return JobCatRepo.findById(id).get();
    }
}
