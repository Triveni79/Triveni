package com.payroll.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.payroll.demo.entity.AddInterviewerName;
import com.payroll.demo.entity.Candidate;
import com.payroll.demo.entity.Interviewer;

public interface InterviewerRepo extends JpaRepository<Interviewer, Long>{

	//Optional<Interviewer> findBy(String candidateId);
	
	@Query("SELECT i FROM Interviewer i WHERE i.candidateId = :candidateId")
	Optional<Interviewer> findByCandidateId(@Param("candidateId") String candidateId);

	List<Interviewer>findAll();

	Candidate getCandidateById(String candidateId);

	List<Interviewer> findByRoundOneInterviewerid(AddInterviewerName roundOneInterviewerid);

	List<Interviewer> findByRoundTwoInterviewerid(AddInterviewerName roundTwoInterviewerid);

	List<Interviewer> findByRoundThreeInterviewerid(AddInterviewerName roundThreeInterviewerid);
}
