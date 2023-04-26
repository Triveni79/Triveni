package com.payroll.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.payroll.demo.entity.Candidate;
import com.payroll.demo.repository.CandidateRepo;

import jakarta.servlet.MultipartConfigElement;

@RestController
@RequestMapping("/api/v1/")
public class CandidateController {
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		long maxFileSize = 10 * 1024 * 1024; // 10 MB
		long maxRequestSize = 10 * 1024 * 1024; // 10 MB
		int fileSizeThreshold = 0;
		return new MultipartConfigElement(null, maxFileSize, maxRequestSize, fileSizeThreshold);
	}

	@Autowired
	private CandidateRepo candidaterepo;

	private static int n = 001;

	// get all employees
	@GetMapping("/candidates")
	public List<Candidate> getAllEmployees() {
		return candidaterepo.findAll();
	}


	@PostMapping("/candidates")
	public Candidate save(@Valid @ModelAttribute Candidate candidate, BindingResult result,
			@RequestParam("file") MultipartFile file) throws IOException {

		if (result.hasErrors()) {
			// Handle validation errors and return an error response
		}

		if (!file.isEmpty()) {
			candidate.setFile(file.getBytes());
		}

		String[] names = candidate.getFirstname().split(" ");
		String surname = names[names.length - 1].toUpperCase();

		String candidateId = String.format("%s%03d", surname.substring(0, 4), n);

		candidate.setCandidateid(candidateId);
		n++;
		return candidaterepo.save(candidate);

	}

	// get employee by id rest api
	@GetMapping("/candidates/{id}")
	public ResponseEntity<Candidate> getCandidateById(@PathVariable Integer id) {
		Candidate candidate = candidaterepo.findById(id).orElseThrow();
		return ResponseEntity.ok(candidate);
	}
}
