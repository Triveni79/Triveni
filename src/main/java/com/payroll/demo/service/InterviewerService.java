package com.payroll.demo.service;

import java.sql.Date;
import java.util.List;

import com.payroll.demo.entity.AddInterviewerName;
import com.payroll.demo.entity.Interviewer;

public interface InterviewerService {
	
	public List<Interviewer> getAllInterviewers();

	public Interviewer createInterviewer(Interviewer interviewer, String roundOneInterviewerid);

	List<Interviewer> findByRoundOneInterviewerid(AddInterviewerName roundOneInterviewerid);

	List<Interviewer> findByRoundTwoInterviewerid(AddInterviewerName roundTwoInterviewerid);

	List<Interviewer> findByRoundThreeInterviewerid(AddInterviewerName roundThreeInterviewerid);

	Interviewer updateRoundTwoInterViewerId(String roundTwoInterviewerid, String candidateId,
			String roundTwoInterviewername, Date roundTwoDate);

	Interviewer updateRoundThreeInterViewerId(String roundThreeInterviewerid, String candidateId,
			String roundThreeInterviewername, Date roundThreeDate);

	Interviewer updateCandidateRoundOne(String candidateId, String roundOneStatus, String roundoneRemarks);

	Interviewer updateRoundTwo(String candidateId, String roundTwoStatus, String roundtwoRemarks);

	Interviewer updateCandidateRoundThree(String candidateId, String roundThreeStatus, String roundthreeRemarks);

}