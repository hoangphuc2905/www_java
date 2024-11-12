package iuh.week04_lab_huynhhoangphuc_21036541.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
public class Skill {
   private final int id;
   private final String skillName;
   private final String description;
   private final String field;
   private Set<SkillCandidate> skillCandidates;
   private Set<JobSkill> jobSkills;

   public Skill(String skillName, String description, String field) {
      this .id = -1;
      this.skillName = skillName;
      this.description = description;
      this.field = field;
   }
}
