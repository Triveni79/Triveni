package com.payroll.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.payroll.demo.entity.Loanmaster;

public interface LoanmasterRepo extends JpaRepository<Loanmaster, String> {
	
	@Query("SELECT l FROM Loanmaster l WHERE l.loan_Santioned_id = (SELECT MAX(l2.loan_Santioned_id) FROM Loanmaster l2)")
	Loanmaster findLastLoanmaster();
	
	

}
