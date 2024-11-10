package iuh.week05_lab_huynhhoangphuc_21036541.backend.resources;

import iuh.week05_lab_huynhhoangphuc_21036541.backend.services.CompanyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/vi/companies")
public class CompanyResources {
    @Autowired
    private CompanyServices services;
}
