package com.payroll.demo.service;

import java.util.List;

import com.payroll.demo.entity.Loanmaster;
import com.payroll.demo.entity.Loantransaction;

public interface LoanService {

	Loanmaster saveDetails(Loanmaster master);

	Loantransaction save(Loantransaction loan);

	List<Loanmaster> getAllLoanmaster();

	Loanmaster updateLoanmaster(String loan_Santionedid, Loanmaster updatedLoanMaster);

	void deleteLoanmaster(String loan_Santionedid);

	List<Loantransaction> getAllLoantransaction();
	
	 
    double getLoantransactionBalanceAmountById(int id);
    
    int getLoantransactionTenureRemainingById(int id);

 


    
}
