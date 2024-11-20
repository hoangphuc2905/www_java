package iuh.week05_lab_huynhhoangphuc_21036541.backend.repositories;

import iuh.week05_lab_huynhhoangphuc_21036541.backend.models.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CompanyRepository extends JpaRepository<Company, Long> {
    // findByKeyword
    @Query("""
    SELECT c FROM Company c
    LEFT JOIN c.address a
    WHERE c.compName LIKE %:keyword%
    OR a.city LIKE %:keyword%
    OR c.phone LIKE %:keyword%
    """)
    Page<Company> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}