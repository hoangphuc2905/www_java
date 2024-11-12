package iuh.week04_lab_huynhhoangphuc_21036541.services;


import iuh.week04_lab_huynhhoangphuc_21036541.models.Skill;
import iuh.week04_lab_huynhhoangphuc_21036541.repositories.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SkillService {
   private final SkillRepository skillRepository;

   public SkillService(SkillRepository skillRepository) {
      this.skillRepository = skillRepository;
   }

   public List<Skill> findAll() {
      return skillRepository.findAll();
   }

   public Skill findById(int id) {
      return skillRepository.findById(id);
   }

   public boolean create(Skill skill) {
      if (skill == null) {
         return false;
      }
      return skillRepository.create(skill);
   }

   public boolean update(Skill skill) {
      if (skill == null) {
         return false;
      }
      return skillRepository.update(skill);
   }

   public boolean delete(int id) {
      return skillRepository.delete(id);
   }
}
