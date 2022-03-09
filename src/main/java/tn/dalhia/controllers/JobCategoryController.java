package tn.dalhia.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.dalhia.entities.JobCategory;
import tn.dalhia.services.IJobCategory;

import java.util.List;

@RestController
@RequestMapping("/JobCategory")
@Slf4j
public class JobCategoryController {
    @Autowired
    IJobCategory jobCategoryService;

    // http://localhost:8089/api/v1/offer/retrieve-all-offers
    @GetMapping("/retrieve-all-JobCategory")
    public List<JobCategory> getJobCategorys() {
        List<JobCategory> listJobCategorys = jobCategoryService.retrieveAllJobCategorys();
        return listJobCategorys;
    }

    // http://localhost:8089/api/v1/offer/retrieve-offer/8
    @GetMapping("/retrieve-JobCategory/{JobCategory-id}")
    public JobCategory retrieveJobCategory(@PathVariable("JobCategory-id") Long id) {
        return jobCategoryService.retrieveJobCategory(id);
    }

    @PostMapping("/add-JobCategory")
    public JobCategory addJobCategory (@RequestBody JobCategory c) {
        return jobCategoryService.addJobCategory(c);
    }

    // http://localhost:8089/api/v1/Offer/remove-Offer/{Offer-id}
    @DeleteMapping("/remove-JobCategory/{JobCategory-id}")
    public void removeJobCategory(@PathVariable("JobCategory-id") Long id) {
        jobCategoryService.deleteJobCategory(id);
    }

    // http://localhost:8089/api/v1/offer/modify-offer
    @PutMapping("/modify-JobCategory")
    public JobCategory modifyJobCategory(@RequestBody JobCategory jobCategory) {
        return jobCategoryService.updateJobCategory(jobCategory);
    }
}
