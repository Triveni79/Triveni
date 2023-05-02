package com.payroll.demo.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.demo.entity.Candidate;
import com.payroll.demo.repository.CandidateRepo;
import com.payroll.demo.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {
	@Autowired
	private CandidateRepo candidaterepo;

	private static int n = 001;

	@Override
	public List<Candidate> getAllCandidates() {
		return candidaterepo.findAll();
	}

	    @Override
	    public Candidate saveCandidate(Candidate candidate) throws IOException {
	        String[] names = candidate.getFirstName().split(" ");
	        String surname = names[names.length - 1].toUpperCase();

	        String candidateId = String.format("%s%03d", surname.substring(0, 4), n);
	        n++;
	        candidate.setCandidateId(candidateId);


	        return candidaterepo.save(candidate);
	    }

	@Override
	public Candidate getCandidateById(String candidateId) {
		return candidaterepo.findById(candidateId).orElseThrow();
	}

//	@Override
//	public Candidate saveCandidate(Candidate candidate, MultipartFile file) throws IOException {
//		try {
//			String[] names = candidate.getFirstName().split(" ");
//			String surname = names[names.length - 1].toUpperCase();
//
//			String candidateId = String.format("%s%03d", surname.substring(0, 4), n);
//			n++;
//			candidate.setCandidateId(candidateId);
//
//			if (!file.isEmpty()) {
//				candidate.setFile(file.getBytes());
//			}
//
//			
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return candidaterepo.save(candidate);
//		
//	}

}
