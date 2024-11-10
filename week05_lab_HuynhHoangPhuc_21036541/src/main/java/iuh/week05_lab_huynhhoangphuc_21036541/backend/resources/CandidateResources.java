package iuh.week05_lab_huynhhoangphuc_21036541.backend.resources;

import iuh.week05_lab_huynhhoangphuc_21036541.backend.services.CandidateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/vi/candidates")
public class CandidateResources {
    @Autowired
    private CandidateServices services;
}