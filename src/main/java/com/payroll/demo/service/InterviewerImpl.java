package com.payroll.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.demo.entity.Interviewer;
import com.payroll.demo.repository.InterviewerRepo;

@Service
public class InterviewerImpl implements InterviewerServices {

	@Autowired
	InterviewerRepo interviwerRepo;

	@Override
	public List<Interviewer> getAllInterviewerListById(Long id) {
	
		ArrayList<Interviewer> intListbyId = new ArrayList<Interviewer>();
		
		
		
		return null;
	}
	
	


}
