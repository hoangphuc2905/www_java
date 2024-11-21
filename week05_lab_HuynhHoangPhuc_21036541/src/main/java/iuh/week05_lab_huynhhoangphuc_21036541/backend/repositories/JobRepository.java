package iuh.week05_lab_huynhhoangphuc_21036541.backend.repositories;

import iuh.week05_lab_huynhhoangphuc_21036541.backend.models.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import iuh.week05_lab_huynhhoangphuc_21036541.backend.enums.SkillLevel;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    Page<Job> findByJobNameContainingOrCompany_CompNameContainingOrJobSkills_Skill_SkillNameContaining(
            String jobName, String companyName, String skillName, Pageable pageable);

    @Query(" select j from Job j inner join j.jobSkills jobSkills " +
            "where jobSkills.skillLevel <= ?1 and jobSkills.skill.skillName = ?2")
    List<Job> findJobsBySkills(SkillLevel skillLevel, String skillName);

    @Query("SELECT j FROM Job j JOIN j.jobSkills js JOIN js.skill s WHERE s.id = :id")
    List<Job> findJobsBySkillName(@Param("id") long id);

    @Query("SELECT j.skill, COUNT (s) AS jobCount " +
            "FROM Job s " +
            "JOIN s.jobSkills j " +
            "GROUP BY j.skill.id " +
            "ORDER BY jobCount DESC")
    List<Object[]> findTopSkillsWithJobCount(@Param("candidateId") Long candidateId, Pageable pageable);

}