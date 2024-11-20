package iuh.week05_lab_huynhhoangphuc_21036541.backend.repositories;

import iuh.week05_lab_huynhhoangphuc_21036541.backend.models.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import iuh.week05_lab_huynhhoangphuc_21036541.backend.enums.SkillLevel;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    Page<Job> findByJobNameContainingOrCompany_CompNameContainingOrJobSkills_Skill_SkillNameContaining(
            String jobName, String companyName, String skillName, Pageable pageable);

    // findJobsBySkills
    @Query(" select j from Job j inner join j.jobSkills jobSkills " +
            "where jobSkills.skillLevel <= ?1 and jobSkills.skill.skillName = ?2")
    List<Job> findJobsBySkills(SkillLevel skillLevel, String skillName);
}