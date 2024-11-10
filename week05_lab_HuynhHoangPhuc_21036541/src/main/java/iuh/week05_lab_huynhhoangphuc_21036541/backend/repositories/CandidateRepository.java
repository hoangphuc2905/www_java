package iuh.week05_lab_huynhhoangphuc_21036541.backend.repositories;

import iuh.week05_lab_huynhhoangphuc_21036541.backend.enums.SkillLevel;
import iuh.week05_lab_huynhhoangphuc_21036541.backend.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    @Query("""
            select c from Candidate c inner join c.candidateSkills candidateSkills
            where candidateSkills.skillLevel = ?1 and candidateSkills.skill.skillName = ?2
            """)
    List<Candidate> findBySkillLevelAndSkillName(SkillLevel skillLevel, String skillName);
}