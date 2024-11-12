package iuh.week04_lab_huynhhoangphuc_21036541.models.mapper;

import iuh.week04_lab_huynhhoangphuc_21036541.models.Job;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JobMapper implements RowMapper<Job> {
   @Override
   public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Job(
              rs.getInt("id"),
              rs.getString("description")
      );
   }
}
