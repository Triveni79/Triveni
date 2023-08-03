package com.payroll.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.demo.entity.Loanmaster;
import com.payroll.demo.entity.Loantransaction;
import com.payroll.demo.repository.LoanmasterRepo;
import com.payroll.demo.repository.LoantransactionRepo;
import com.payroll.demo.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanmasterRepo masterepo;

	@Autowired
	private LoantransactionRepo transactionrepos;

	@Override
	public Loanmaster saveDetails(Loanmaster master) {
		return masterepo.save(master);
	}

	@Override
	public Loantransaction save(Loantransaction loan) {
		return transactionrepos.save(loan);
	}

	@Override
	public List<Loanmaster> getAllLoanmaster() {
		// TODO Auto-generated method stub
		return masterepo.findAll();
	}

	@Override
	public Loanmaster updateLoanmaster(String loan_Santionedid, Loanmaster updatedLoanMaster) {
		Optional<Loanmaster> loanMasterOptional = masterepo.findById(loan_Santionedid);

		if (loanMasterOptional.isPresent()) {
			Loanmaster existingLoanMaster = loanMasterOptional.get();

			// Perform the necessary updates to the existingLoanMaster object using
			// updatedLoanMaster
			existingLoanMaster.setTenure(updatedLoanMaster.getTenure());
			existingLoanMaster.setLoan_SantionedAmount(updatedLoanMaster.getLoan_SantionedAmount());
			existingLoanMaster.setEMI(updatedLoanMaster.getEMI());
			existingLoanMaster.setLoan_Transaction_id(updatedLoanMaster.getLoan_Transaction_id());
			existingLoanMaster.setLoan_Source_Bank(updatedLoanMaster.getLoan_Source_Bank());
			existingLoanMaster.setLoan_Credited_By(updatedLoanMaster.getLoan_Credited_By());
			existingLoanMaster.setApproved_date(updatedLoanMaster.getApproved_date());

			return masterepo.save(existingLoanMaster);
		}

		return null;
	}

	@Override
	public void deleteLoanmaster(String loan_Santionedid) {
		masterepo.deleteById(loan_Santionedid);
	}

	@Override
	public List<Loantransaction> getAllLoantransaction() {
		return transactionrepos.findAll();
	}

	@Override
	public double getLoantransactionBalanceAmountById(int id) {
		Optional<Loantransaction> transactionOptional = transactionrepos.findById(id);

		if (transactionOptional.isPresent()) {
			Loantransaction loanTransaction = transactionOptional.get();
			return loanTransaction.getBalanceAmount();
		}

		return 0.0; // Return default value if loan transaction not found
	}

	@Override
	public int getLoantransactionTenureRemainingById(int id) {
		Optional<Loantransaction> transactionOptional = transactionrepos.findById(id);

		if (transactionOptional.isPresent()) {
			Loantransaction loanTransaction = transactionOptional.get();
			return loanTransaction.getTenureremaining();
		}

		return 0; // Return default value if loan transaction not found
	}

	

}

