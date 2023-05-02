package com.payroll.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.payroll.demo.entity.Candidate;
import com.payroll.demo.service.CandidateService;
import com.payroll.demo.serviceImpl.CandidateServiceImpl;

@RestController
@RequestMapping("/api")
public class CandidateController {
	 @Autowired
	    private CandidateServiceImpl candidateService;

	    // get all candidates
	    @GetMapping("/candidates")
	    public List<Candidate> getAllCandidates() {
	        return candidateService.getAllCandidates();
	    }

	    // create candidate rest api
	    @PostMapping("/candidates")
	    public Candidate saveCandidate(@RequestBody Candidate candidate) throws IOException {
//	        if (result.hasErrors()) {
//	            // Handle validation errors and return an error response
//	        }

	        return candidateService.saveCandidate(candidate);
	    }

	    // get candidate by id rest api
	    @GetMapping("/candidates/{candidateId}")
	    public ResponseEntity<Candidate> getCandidateById(@PathVariable String candidateId) {
	        Candidate candidate = candidateService.getCandidateById(candidateId);
	        return ResponseEntity.ok(candidate);
	    }
}
