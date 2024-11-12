package iuh.week04_lab_huynhhoangphuc_21036541.services;

import iuh.week04_lab_huynhhoangphuc_21036541.models.Job;
import iuh.week04_lab_huynhhoangphuc_21036541.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
   private final JobRepository jobRepository;

   public JobService(JobRepository jobRepository) {
      this.jobRepository = jobRepository;
   }

   public List<Job> findAll() {
      return jobRepository.findAll();
   }

   public Job findById(int id) {
      return jobRepository.findById(id);
   }

   public boolean create(Job job) {
      if (job == null) {
         return false;
      }
      return jobRepository.create(job);
   }

   public boolean update(Job job) {
      if (job == null) {
         return false;
      }
      return jobRepository.update(job);
   }

   public boolean delete(int id) {
      return jobRepository.delete(id);
   }
}
