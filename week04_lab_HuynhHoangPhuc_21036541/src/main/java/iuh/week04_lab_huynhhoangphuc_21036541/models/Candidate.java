package iuh.week04_lab_huynhhoangphuc_21036541.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.Set;


@Data
@RequiredArgsConstructor
public class Candidate {
   private final int id;
   private final String fullName;
   private final Instant dob;
   private final String email;
   private final String address;
   private final String phone;
   private Set<SkillCandidate> skillCandidates;

   public Candidate(String fullName, Instant dob, String email, String address, String phone) {
      this.id = -1;
      this.fullName = fullName;
      this.dob = dob;
      this.email = email;
      this.address = address;
      this.phone = phone;
   }
}
