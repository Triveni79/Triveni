package com.payroll.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payroll.demo.entity.Candidate;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate,Integer>{

}
