package iuh.week04_lab_huynhhoangphuc_21036541.models.mapper;

import iuh.week04_lab_huynhhoangphuc_21036541.models.Skill;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillMapper implements RowMapper<Skill> {

   @Override
   public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Skill(
               rs.getString("skill_name"),
               rs.getString("description"),
               rs.getString("field")
      );
   }
}
