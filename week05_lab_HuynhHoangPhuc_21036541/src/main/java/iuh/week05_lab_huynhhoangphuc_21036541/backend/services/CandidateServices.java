package iuh.week05_lab_huynhhoangphuc_21036541.backend.services;

import iuh.week05_lab_huynhhoangphuc_21036541.backend.enums.SkillLevel;
import iuh.week05_lab_huynhhoangphuc_21036541.backend.models.*;
import iuh.week05_lab_huynhhoangphuc_21036541.backend.repositories.CandidateRepository;
import iuh.week05_lab_huynhhoangphuc_21036541.backend.repositories.CompanyRepository;
import iuh.week05_lab_huynhhoangphuc_21036541.backend.repositories.JobRepository;
import iuh.week05_lab_huynhhoangphuc_21036541.backend.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class CandidateServices {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SkillRepository skillRepository;

    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return candidateRepository.findAll(pageable);//findFirst.../findTop...
    }

    public Page<Candidate> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Candidate> list;
        List<Candidate> candidates = candidateRepository.findAll();

        if (candidates.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, candidates.size());
            list = candidates.subList(startItem, toIndex);
        }

        Page<Candidate> candidatePage
                = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), candidates.size());

        return candidatePage;
    }

    public List<Candidate> findCandidatesForJob(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        return job.getJobSkills().stream()
                .flatMap(jobSkill -> candidateRepository.findBySkillLevelAndSkillName(
                        jobSkill.getSkillLevel(), jobSkill.getSkill().getSkillName()).stream())
                .collect(Collectors.toList());
    }

    public List<JobSkill> suggestSkillToLearn(Long candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        return jobRepository.findAll().stream()
                .flatMap(job -> job.getJobSkills().stream())
                .filter(jobSkill -> !candidate.getCandidateSkills().contains(jobSkill))
                .distinct()
                .collect(Collectors.toList());
    }

    public Page<Candidate> searchCandidates(String keyword, int page, int size) {
        SkillLevel skillLevel = null;
        try {
            skillLevel = SkillLevel.valueOf(keyword.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Ignore, keyword is not a valid SkillLevel
        }
        Pageable pageable = PageRequest.of(page, size);
        return candidateRepository.findByKeyword(keyword, skillLevel, pageable);
    }

    public List<Job> suggestJobsForCandidate(Long candidateId) {
        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        return candidate.get()
                .getCandidateSkills().stream()
                .map(
                        candidateSkill ->
                                jobRepository.findJobsBySkills(
                                        candidateSkill.getSkillLevel(), candidateSkill.getSkill().getSkillName()
                                )
                )
                .flatMap(List::stream)
                .toList();
    }

    public List<Map<String, Object>> suggestTopSkills(Long candidateId) {
        List<Object[]> results = jobRepository.findTopSkillsWithJobCount(candidateId, PageRequest.of(0, 10));

        return results.stream().map(result -> {
            Skill skill = (Skill) result[0];
            Long jobCount = (Long) result[1];
            Map<String, Object> skillWithJobCount = new HashMap<>();
            skillWithJobCount.put("skill", skill);
            skillWithJobCount.put("jobCount", jobCount);
            return skillWithJobCount;
        }).collect(Collectors.toList());
    }

}