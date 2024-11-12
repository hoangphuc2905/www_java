package iuh.week04_lab_huynhhoangphuc_21036541.services;


import iuh.week04_lab_huynhhoangphuc_21036541.models.Candidate;
import iuh.week04_lab_huynhhoangphuc_21036541.repositories.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {
   private final CandidateRepository candidateRepository;

   public CandidateService(CandidateRepository candidateRepository) {
      this.candidateRepository = candidateRepository;
   }

   public List<Candidate> findAll() {
      return candidateRepository.findAll();
   }

   public Candidate findById(int id) {
      return candidateRepository.findById(id);
   }

   public boolean create(Candidate candidate) {
      if (candidate == null) {
         return false;
      }
      return candidateRepository.create(candidate);
   }

   public boolean update(Candidate candidate) {
      if (candidate == null) {
         return false;
      }
      return candidateRepository.update(candidate);
   }

   public boolean delete(int id) {
      return candidateRepository.delete(id);
   }

}
