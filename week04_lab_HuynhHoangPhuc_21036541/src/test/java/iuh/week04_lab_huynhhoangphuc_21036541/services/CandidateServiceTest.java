package iuh.week04_lab_huynhhoangphuc_21036541.services;

import iuh.week04_lab_huynhhoangphuc_21036541.models.Candidate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CandidateServiceTest {
   @Autowired
   private CandidateService candidateService;

   @Test
   void findAll() {
      List<Candidate> all = candidateService.findAll();
      assertFalse(all.isEmpty());
   }

   @Test
   void findById() {
      Candidate candidate = candidateService.findById(1);
      System.out.println(candidate);
      assertNotNull(candidate);
   }

   @Test
   void create() {
      Candidate candidate = new Candidate(
            "Huynh Hoang Phuc",
            Instant.now(),
            "a@gmail.com",
            "TPHCM",
            "0366666666"
      );
      boolean result = candidateService.create(candidate);
      System.out.println(result);
      assertTrue(result);
   }

   @Test
   void update() {
      Candidate candidate = candidateService.findById(1);
      Candidate newCandidate = new Candidate(
            candidate.getId(),
            "Huynh Hoang Phuc",
            candidate.getDob(),
            candidate.getEmail(),
            candidate.getAddress(),
            candidate.getPhone()
      );
      boolean result = candidateService.update(newCandidate);
      System.out.println(result);
      assertTrue(result);
   }

   @Test
   void delete() {
      boolean result = candidateService.delete(2);
      System.out.println(result);
      assertTrue(result);
   }
}