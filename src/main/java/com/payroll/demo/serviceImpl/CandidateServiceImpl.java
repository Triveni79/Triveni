package com.payroll.demo.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
//				candidate.setResume(file.getBytes());
//			}
//
//			
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return candidaterepo.save(candidate);
//		
//	}
//	
//	

	public List<Candidate> getAllCandidatesOrderedById() {
	    return candidaterepo.findAllOrderedById();
	}
	
	
	@Override
	public Candidate saveCandidate(Candidate candidate, MultipartFile file) throws IOException {
	    try {
	        String[] names = candidate.getFirstName().split(" ");
	        String surname = names[names.length - 1].toUpperCase();

	        // Retrieve the last CID from the repository
	        List<Candidate> existingCandidates = candidaterepo.findAll();
	        int maxSequenceNumber = 0;

	        if (existingCandidates != null && !existingCandidates.isEmpty()) {
	            for (Candidate existingCandidate : existingCandidates) {
	                String cid = existingCandidate.getCandidateId();
	                if (cid.matches(".*\\d+")) { // Check if CID ends with a number
	                    int sequenceNumber = Integer.parseInt(cid.replaceAll("[^0-9]", ""));
	                    if (sequenceNumber > maxSequenceNumber) {
	                        maxSequenceNumber = sequenceNumber;
	                    }
	                }
	            }
	        }

	        // Increment the sequence number
	        int sequenceNumber = maxSequenceNumber + 1;

	        // Get the first four letters of the user's first name
	        String firstNamePrefix = candidate.getFirstName().substring(0, Math.min(candidate.getFirstName().length(), 4)).toUpperCase();

	        // Format the new CID with the first name prefix and the incremented sequence number
	        String candidateId = String.format("%s%03d", firstNamePrefix, sequenceNumber);

	        candidate.setCandidateId(candidateId);

	        if (!file.isEmpty()) {
	            candidate.setResume(file.getBytes());
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return candidaterepo.save(candidate);
	}

	@Override
    public void editCandidate(Candidate candidate, MultipartFile file) throws IOException {
        if (file != null) {
            // logic to handle file upload
            candidate.setResume(file.getBytes());
        }
        candidaterepo.save(candidate);
    }

	public Candidate loadResumeAsResource(String candidateId) {
		// TODO Auto-generated method stub
		return candidaterepo.getById(candidateId);
	}
	  
	public byte[] getResumeById(String candidateId) {
          Candidate candidate = candidaterepo.findById(candidateId).orElse(null);
          if (candidate == null || candidate.getResume() == null) {
              return new byte[0];
          }
          return candidate.getResume();
      
}


	
}
