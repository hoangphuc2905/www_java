package iuh.week04_lab_huynhhoangphuc_21036541.models;

import lombok.Data;

@Data
public class JobSkill {
   private final Job job;
   private final Skill skill;
   private int level;
}
