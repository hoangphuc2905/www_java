package iuh.week05_lab_huynhhoangphuc_21036541.backend.services;

import iuh.week05_lab_huynhhoangphuc_21036541.backend.models.Job;
import iuh.week05_lab_huynhhoangphuc_21036541.backend.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

@Service
public class JobServices {
    @Autowired
    private JobRepository jobRepository;

    public Page<Job> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return jobRepository.findAll(pageable);//findFirst.../findTop...
    }

    public Page<Job> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Job> list;
        List<Job> jobs = jobRepository.findAll();

        if (jobs.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, jobs.size());
            list = jobs.subList(startItem, toIndex);
        }

        Page<Job> jobPage
                = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), jobs.size());

        return jobPage;
    }

    public Page<Job> searchJobs(int pageNo, int pageSize, String searchTerm) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id").ascending());
        return jobRepository.findByJobNameContainingOrCompany_CompNameContainingOrJobSkills_Skill_SkillNameContaining(
                searchTerm, searchTerm, searchTerm, pageable);
    }


}