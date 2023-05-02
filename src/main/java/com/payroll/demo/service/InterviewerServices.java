package com.payroll.demo.service;

import java.util.List;

import com.payroll.demo.entity.Interviewer;

public interface InterviewerServices {

	public List<Interviewer> getAllInterviewerListById(Long id);
}
