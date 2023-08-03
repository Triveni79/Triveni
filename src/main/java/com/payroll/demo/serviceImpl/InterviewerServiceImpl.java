package com.payroll.demo.serviceImpl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.payroll.demo.entity.AddInterviewerName;
import com.payroll.demo.entity.Interviewer;
import com.payroll.demo.repository.AddInterviewerRepository;
import com.payroll.demo.repository.InterviewerRepo;
import com.payroll.demo.service.InterviewerService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class InterviewerServiceImpl implements InterviewerService {

	@Autowired
	private InterviewerRepo interviewerRepo;
	@Autowired
	private AddInterviewerRepository interviewerIdRepository;

	@Override
	public List<Interviewer> getAllInterviewers() {
		return interviewerRepo.findAll();
	}

	@Override
	public Interviewer createInterviewer(Interviewer interviewer, String roundOneInterviewerid) {
		AddInterviewerName interviewersId = interviewerIdRepository.findById(roundOneInterviewerid).orElse(null);
		    if (interviewersId == null) {
		        throw new EntityNotFoundException("Login entity not found for roundOneInterviewerid: " + roundOneInterviewerid);
		    }
		    interviewer.setRoundOneInterviewerid(interviewersId);
		return interviewerRepo.save(interviewer);
	}
	

	public Interviewer updateRoundTwoInterViewerId(String roundTwoInterviewerid, String candidateId, String roundTwoInterviewername,
			Date roundTwoDate) {

		Interviewer candidate = interviewerRepo.findByCandidateId(candidateId).get();
		
		AddInterviewerName interviewersId = interviewerIdRepository.findById(roundTwoInterviewerid).orElse(null);
	    if (interviewersId == null) {
	        throw new EntityNotFoundException("Login entity not found for roundTwoInterviewerid: " + roundTwoInterviewerid);
	    }
	    
	    candidate.setRoundTwoInterviewerid(interviewersId);
		candidate.setRoundTwoInterviewername(roundTwoInterviewername);
		candidate.setRoundTwoDate(roundTwoDate);
		return interviewerRepo.save(candidate);
	}

	public Interviewer updateRoundThreeInterViewerId(String roundThreeInterviewerid, String candidateId, String roundThreeInterviewername,
			Date roundThreeDate) {

		Interviewer candidate = interviewerRepo.findByCandidateId(candidateId).get();
		
		AddInterviewerName interviewersId = interviewerIdRepository.findById(roundThreeInterviewerid).orElse(null);
	    if (interviewersId == null) {
	        throw new EntityNotFoundException("Login entity not found for roundThreeInterviewerid: " + roundThreeInterviewerid);
	    }
		candidate.setRoundThreeInterviewerid(interviewersId);
		candidate.setRoundThreeInterviewername(roundThreeInterviewername);
		candidate.setRoundThreeDate(roundThreeDate);
		return interviewerRepo.save(candidate);
	}
	
	
	 @Override
	    public List<Interviewer> findByRoundOneInterviewerid(AddInterviewerName roundOneInterviewerid) {
	        return interviewerRepo.findByRoundOneInterviewerid(roundOneInterviewerid);
	    }

	    @Override
	    public List<Interviewer> findByRoundTwoInterviewerid(AddInterviewerName roundTwoInterviewerid) {
	        return interviewerRepo.findByRoundTwoInterviewerid(roundTwoInterviewerid);
	    }

	    @Override
	    public List<Interviewer> findByRoundThreeInterviewerid(AddInterviewerName roundThreeInterviewerid) {
	        return interviewerRepo.findByRoundThreeInterviewerid(roundThreeInterviewerid);
	    }
	

	// Update round two details based on round one status and remarks
	public Interviewer updateCandidateRoundOne(String candidateId, String roundOneStatus, String roundoneRemarks) {
		Interviewer candidate = interviewerRepo.findByCandidateId(candidateId).get();
		// Here checking round one status and remarks to insert data in Second round if
		// it is null can can't be insert

		candidate.setRoundOneStatus(roundOneStatus);
		candidate.setRoundoneRemarks(roundoneRemarks);

		return interviewerRepo.save(candidate);
	}

	// Update round two details based on round one status and remarks
	public Interviewer updateRoundTwo(String candidateId, String roundTwoStatus, String roundtwoRemarks) {
		Interviewer can = interviewerRepo.findByCandidateId(candidateId).get();
		// Here checking round one status and remarks to insert data in Second round if
		// it is null can can't be insert

		if (can.getRoundOneStatus() != null && can.getRoundoneRemarks() != null) {
			can.setRoundTwoStatus(roundTwoStatus);
			can.setRoundtwoRemarks(roundtwoRemarks);
		}
		return interviewerRepo.save(can);
	}

	// will get Interviewer details based on candidateId
	public Optional<Interviewer> getOne(String candidateId) {
		Optional<Interviewer> inter = interviewerRepo.findByCandidateId(candidateId);

		return inter;
	}

	public Optional<Interviewer> getOneAndTwo(String candidateId) {
		Optional<Interviewer> inter = interviewerRepo.findByCandidateId(candidateId);

		return inter;
	}

	// Update round THREE details based on round two status and remarks
	public Interviewer updateCandidateRoundThree(String candidateId, String roundThreeStatus,
			String roundthreeRemarks) {
		// Update round three details based on round two status and remarks
		Interviewer optionalCandidate = interviewerRepo.findByCandidateId(candidateId).get();
		// Here checking round two status and remarks to insert data in Third round if
		// it is null can can't be insert
		if (optionalCandidate.getRoundTwoStatus() != null && optionalCandidate.getRoundtwoRemarks() != null) {
			optionalCandidate.setRoundThreeStatus(roundThreeStatus);
			optionalCandidate.setRoundthreeRemarks(roundthreeRemarks);

		}
		return interviewerRepo.save(optionalCandidate);

	}
}