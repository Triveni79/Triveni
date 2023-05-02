package com.payroll.demo.service;

import java.io.IOException;
import java.util.List;


import com.payroll.demo.entity.Candidate;


public interface CandidateService {
	
	public List<Candidate> getAllCandidates();

	public Candidate saveCandidate(Candidate candidate) throws IOException;

	public Candidate getCandidateById(String candidateId);

}
