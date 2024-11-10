package iuh.week05_lab_huynhhoangphuc_21036541.backend.resources;

import iuh.week05_lab_huynhhoangphuc_21036541.backend.services.JobServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/vi/jobs")
public class JobResources {
    @Autowired
    private JobServices services;
}