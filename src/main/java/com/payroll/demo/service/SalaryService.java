package com.payroll.demo.service;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.payroll.demo.entity.AddEmployee;
import com.payroll.demo.entity.Salary;

import jakarta.servlet.http.HttpServletResponse;




@Service
public interface SalaryService {

	public Salary saveEmpp( @Valid Salary e,
		 String month,
       int year,
              int leaves,
             int loans,
             double profTax,
          int absent,
        int apprPoints,
            int warningPoints,
            int delayPoints,
             double allowances,
        double bonus,
             double netSalary,
        double tds,
            Date transactionDate,
            String sourceBank,
          String creditedBy

			);

	public Salary getEmppThroughTransID( String empDummy);
	public List<Salary> getEmppThroughEmpID(AddEmployee empDummy);
	  public  String convertToIndianCurrency(double  finalSalary);
	  
	  public void generatePdf(AddEmployee  EmpId,int ye,String fmo, String tmo,HttpServletResponse response) throws IOException, DocumentException;
	 
	  public List<Salary> getAllSalaryDetails();
}
