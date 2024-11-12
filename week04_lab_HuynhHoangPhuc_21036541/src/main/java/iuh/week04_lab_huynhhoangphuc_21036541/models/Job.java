package iuh.week04_lab_huynhhoangphuc_21036541.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
public class Job {
   private final int id;
   private final String description;
   private Set<JobSkill> jobSkills;

   public Job(String description) {
      this.id = -1;
      this.description = description;
   }
}
