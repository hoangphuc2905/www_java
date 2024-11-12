package iuh.week04_lab_huynhhoangphuc_21036541.models.mapper;


import iuh.week04_lab_huynhhoangphuc_21036541.models.Candidate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CandidateMapper implements RowMapper<Candidate> {
   @Override
   public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Candidate(
              rs.getInt("id"),
              rs.getString("first_name") + " " + rs.getString("middle_name") + " " + rs.getString("last_name"),
              rs.getTimestamp("dob").toInstant(),
              rs.getString("email"),
              rs.getString("address"),
              rs.getString("phone")
      );
   }
}
