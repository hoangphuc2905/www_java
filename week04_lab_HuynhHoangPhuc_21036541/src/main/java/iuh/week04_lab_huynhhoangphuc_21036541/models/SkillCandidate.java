package iuh.week04_lab_huynhhoangphuc_21036541.models;

import lombok.Data;


@Data
public class SkillCandidate {
   private final Candidate candidate;
   private final Skill skill;
   private int level;
}
