package com.payroll.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payroll.demo.entity.Interviewer;

@Repository
public interface InterviewerRepo extends JpaRepository<Interviewer, Long>{

}
