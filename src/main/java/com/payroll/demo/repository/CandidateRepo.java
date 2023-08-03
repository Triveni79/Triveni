package com.payroll.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payroll.demo.entity.Candidate;
@Repository
public interface CandidateRepo extends JpaRepository<Candidate,String>{
  
	
	@Query("SELECT c FROM Candidate c WHERE c.candidateId LIKE '%__%' AND c.candidateId = (SELECT MAX(c2.candidateId) FROM Candidate c2)")
	Candidate findLastCandidate();
	
	 @Query("SELECT c FROM Candidate c ORDER BY c.id ASC")
	    List<Candidate> findAllOrderedById();

	Candidate findByFirstNameAndLastName(String firstName, String lastName);
}

