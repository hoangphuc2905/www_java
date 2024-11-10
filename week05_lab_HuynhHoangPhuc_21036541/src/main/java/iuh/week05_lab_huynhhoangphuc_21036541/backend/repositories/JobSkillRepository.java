package iuh.week05_lab_huynhhoangphuc_21036541.backend.repositories;

import iuh.week05_lab_huynhhoangphuc_21036541.backend.models.JobSkill;
import iuh.week05_lab_huynhhoangphuc_21036541.backend.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobSkillRepository extends JpaRepository<JobSkill, Skill> {
}
