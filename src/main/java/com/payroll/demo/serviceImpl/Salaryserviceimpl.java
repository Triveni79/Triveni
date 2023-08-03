package com.payroll.demo.serviceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.payroll.demo.entity.AddEmployee;
import com.payroll.demo.entity.Candidate;
import com.payroll.demo.entity.Salary;
import com.payroll.demo.repository.AddEmployeeRepo;
import com.payroll.demo.repository.CandidateRepo;
import com.payroll.demo.repository.SalaryRepo;
import com.payroll.demo.service.SalaryService;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class Salaryserviceimpl implements SalaryService {

	@Autowired
	private SalaryRepo Erepo;

	@Autowired
	private AddEmployeeRepo Arepo;

	@Autowired
	private CandidateRepo candidateRepo;

	@Autowired
	private SalaryService serviceInter;

	String ss;
	String lastValueCheck() {
		String currentValuee = Erepo.lastValueCheck();
		return ss = currentValuee;
	}

	@Override
	public Salary saveEmpp(@Valid Salary e, String month, int year, int leaves, int loans, double profTax, int absent,
			int apprPoints, int warningPoints, int delayPoints, double allowances, double bonus, double netSalary,
			double tds, Date transactionDate, String sourceBank, String creditedBy) {
		// Tax Calculation
		double Prof_tax = e.getProfTax() / 100 * e.getNetSalary();
		double Tds = e.getAbsent() / 100 * e.getNetSalary();
		double Taxs = Prof_tax + Tds;
		double after_Tax = e.getNetSalary() - Taxs;
		// Deduction of Absents Amounts
		int absents = e.getAbsent();
		double absentsAmountPerDay = e.getNetSalary() / 30;
		double totalAbsentsAmountPerCount = 0;
		for (int i = 1; i <= absents; i++) {
			totalAbsentsAmountPerCount = totalAbsentsAmountPerCount + absentsAmountPerDay;
		}
		double after_Absents = after_Tax - totalAbsentsAmountPerCount;
		// Deduction of Leaves Amounts
		int leaves1 = e.getLeaves();
		double LeavesAmountPerDay = 0.0;
		double perDay = e.getNetSalary() / 30;
		if (leaves1 > 1) {
			for (int i = 1; i <= (leaves1 - 1); i++) {
				LeavesAmountPerDay = (double) LeavesAmountPerDay + perDay;
			}
		}
		double after_leaves = after_Absents - LeavesAmountPerDay;
		// Bonus Addition
		double bonus1 = e.getBonus();
		double after_bouns = after_leaves + ((bonus1 / 100) * (after_leaves));
		// after_loans Deduction
		double after_loans = after_bouns - e.getLeaves();
		// after_appp_amont Addition
		double after_appp_amont = after_loans + e.getApprPoints();
		// after_Waring_amont Addition
		double after_Waring_amont = after_appp_amont - e.getWarningPoints();
		// After All Transactions Over all salary is settled
		e.setFinalSalary(after_Waring_amont);
		if (lastValueCheck() != null) {
			System.out.println("IF part..");
			int jj = Integer.parseInt(ss) + 1;
			final DecimalFormat decimalFormattt = new DecimalFormat("000");
			String ee = decimalFormattt.format(jj);
			String s = "SAL";
			DateFormat df = new SimpleDateFormat("YY"); // Just the year, with 2 digits
			String formattedDate = df.format(Calendar.getInstance().getTime());
			DateFormat dff = new SimpleDateFormat("MM"); // Just the year, with 2 digits
			String formattedDatee = dff.format(Calendar.getInstance().getTime());
			String Concatenated = s + formattedDate + formattedDatee + ee;
			e.setTransactionId(Concatenated);
		} else {
			System.out.println("ELSE part..");
			String s = "SAL";
			DateFormat df = new SimpleDateFormat("YY"); // Just the year, with 2 digits
			String formattedDate = df.format(Calendar.getInstance().getTime());
			DateFormat dff = new SimpleDateFormat("MM"); // Just the year, with 2 digits
			String formattedDatee = dff.format(Calendar.getInstance().getTime());
			final DecimalFormat decimalFormat = new DecimalFormat("000");
			int u = 0;
			int o = ++u;
			String j = decimalFormat.format(o);
			String Concatenated = s + formattedDate + formattedDatee + j;
			e.setTransactionId(Concatenated);
		}
		e.setAddEmployee(e.getAddEmployee());
		return Erepo.save(e);
	}

	@Override
	public List<Salary> getAllSalaryDetails() {
		return Erepo.findAll();
	}

	@Override
	public Salary getEmppThroughTransID(String transaction_Id) {
		return Erepo.findById(transaction_Id).get();
	}

	@Override
	public List<Salary> getEmppThroughEmpID(AddEmployee addEmployee) {
		return Erepo.findByAddEmployee(addEmployee);
	}

	// to write the final salary in the words
	@Override
	public String convertToIndianCurrency(double finalSalary) {
		BigDecimal bd = new BigDecimal(finalSalary);
		long number = bd.longValue();
		long no = bd.longValue();
		int decimal = (int) (bd.remainder(BigDecimal.ONE).doubleValue() * 100);
		int digits_length = String.valueOf(no).length();
		int i = 0;
		ArrayList<String> str = new ArrayList<>();
		HashMap<Integer, String> words = new HashMap<>();
		words.put(0, "");
		words.put(1, "One");
		words.put(2, "Two");
		words.put(3, "Three");
		words.put(4, "Four");
		words.put(5, "Five");
		words.put(6, "Six");
		words.put(7, "Seven");
		words.put(8, "Eight");
		words.put(9, "Nine");
		words.put(10, "Ten");
		words.put(11, "Eleven");
		words.put(12, "Twelve");
		words.put(13, "Thirteen");
		words.put(14, "Fourteen");
		words.put(15, "Fifteen");
		words.put(16, "Sixteen");
		words.put(17, "Seventeen");
		words.put(18, "Eighteen");
		words.put(19, "Nineteen");
		words.put(20, "Twenty");
		words.put(30, "Thirty");
		words.put(40, "Forty");
		words.put(50, "Fifty");
		words.put(60, "Sixty");
		words.put(70, "Seventy");
		words.put(80, "Eighty");
		words.put(90, "Ninety");
		String digits[] = { "", "Hundred", "Thousand", "Lakh", "Crore" };
		while (i < digits_length) {
			int divider = (i == 2) ? 10 : 100;
			number = no % divider;
			no = no / divider;
			i += divider == 10 ? 1 : 2;
			if (number > 0) {
				int counter = str.size();
				String plural = (counter > 0 && number > 9) ? "s" : "";
				String tmp = (number < 21) ? words.get(Integer.valueOf((int) number)) + " " + digits[counter] + plural
						: words.get(Integer.valueOf((int) Math.floor(number / 10) * 10)) + " "
								+ words.get(Integer.valueOf((int) (number % 10))) + " " + digits[counter] + plural;
				str.add(tmp);
			} else {
				str.add("");
			}
		}
		Collections.reverse(str);
		String Rupees = String.join(" ", str).trim();
		return Rupees + " Rupees ";
	}
	
	public boolean checkEmployeeExistence(String empId) {
		Optional<AddEmployee> employee = Arepo.findById(empId);
		return employee.isPresent();
	}

	// Helper method to get the list of months between the from-month and to-month
	// (inclusive)
	private List<String> getMonthsBetween(String fromMonth, String toMonth) {
		List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December");
		String fromMonthLowerCase = fromMonth.toLowerCase();
		String toMonthLowerCase = toMonth.toLowerCase();
		int fromIndex = -1;
		int toIndex = -1;
		for (int i = 0; i < months.size(); i++) {
			String month = months.get(i).toLowerCase();
			if (month.equals(fromMonthLowerCase)) {
				fromIndex = i;
			}
			if (month.equals(toMonthLowerCase)) {
				toIndex = i;
			}
		}
		if (fromIndex == -1 || toIndex == -1 || fromIndex > toIndex) {
			return Collections.emptyList(); // Return an empty list if the input is invalid
		}
		return months.subList(fromIndex, toIndex + 1);
	}

	// generate document of payslip of employee
	@Override
	public void generatePdf(AddEmployee EmpId, int ye, String fmo, String tmo, HttpServletResponse response)
			throws IOException, DocumentException {
//			System.err.println( "IAM LINE 131" + EmpId );
		List<Salary> personList = serviceInter.getEmppThroughEmpID(EmpId);
		Salary firstPerson = personList.get(0);
		// Get the list of months between the from-month and to-month
		List<String> monthsBetween = getMonthsBetween(fmo, tmo);
		System.err.println("300" + monthsBetween);
		List<Salary> filteredList = new ArrayList<>();
		try {
			for (Salary emp : personList) {
				if (emp.getYear() == ye && monthsBetween.contains(emp.getMonth())) {
					filteredList.add(emp);
				}

			}
			if (filteredList.isEmpty()) {
				throw new YearMonthNotFoundException("\"The year or month was not found.\"");
			}
		} catch (YearMonthNotFoundException e) {
			// Print in yellow color
			System.out.println(e.getMessage());
			// Send the error message to the frontend
			response.getWriter().write(e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;

		}
		System.out.println("IAM 324" + filteredList);
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		Salary secondPerson = null;
		for (int i = 0; i < filteredList.size(); i++) {
			secondPerson = filteredList.get(i);
			System.out.println(i + " " + secondPerson);
			document.newPage();
			String OEmpId = "";
			for (Salary person1 : personList) {
				OEmpId = person1.getAddEmployee().getEmpId();
			}
			Candidate candidate_Id = null;
			for (Salary person2 : personList) {
				candidate_Id = person2.getAddEmployee().getCandidateId();
			}
			System.err.println("IAM LINE 174  " + candidate_Id);
			Optional<Candidate> details = candidateRepo.findById(candidate_Id.getCandidateId());
			System.err.println("Iam Line 176 " + candidateRepo.findById(candidate_Id.getCandidateId()));
			System.err.println("Iam Line 177 " + details.get());
			Candidate can = details.get();
			String peru = can.getFirstName() + can.getLastName();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + peru + ".pdf\"");
			BaseColor reddishOrange = new BaseColor(255, 99, 0); // RGB values for reddish orange
			Font font1 = new Font(Font.FontFamily.HELVETICA, 25, Font.BOLD, reddishOrange);
			Paragraph heading1 = new Paragraph("APT  IT  SOLUTIONS", font1);
			heading1.setAlignment(Element.ALIGN_CENTER);
			heading1.setSpacingAfter(3);
			document.add(heading1);
			Font addressFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
			Paragraph addressParagraph = new Paragraph(
					"1-98/7/3/83 Vaibhav Enclave, 2nd Floor, Block-B, Arunodaya Colony, Madhapur, Hyderabad-500081, India.",
					addressFont);
			addressParagraph.setAlignment(Element.ALIGN_CENTER);
			document.add(addressParagraph);
			Font emailFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, BaseColor.BLUE);
			Paragraph emailParagraph = new Paragraph("E-mail: info@aptits.com", emailFont);
			emailParagraph.setAlignment(Element.ALIGN_CENTER);
			document.add(emailParagraph);
			Font font = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
			Paragraph heading = new Paragraph("PAY SLIP" + " " + secondPerson.getYear() + " " + secondPerson.getMonth(),
					font);
			heading.setAlignment(Element.ALIGN_CENTER);
			heading.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD, 40));
			heading.setSpacingAfter(3);
			document.add(heading);
			String account_HolderName = "";
			String account_Number = "";
			String bank_Name = "";
			String ifsc_Code = "";
			String pan_Card = "";
			String blood_Group = "";
			String nominee = "";
			String Nominee_Contact = "";
			String branch = "";
			String Joining_Date = "";
			for (Salary person : personList) {
				account_HolderName = String.valueOf(person.getAddEmployee().getAccountHolderName());
				account_Number = String.valueOf(person.getAddEmployee().getAccountNumber());
				bank_Name = String.valueOf(person.getAddEmployee().getBankName());
				ifsc_Code = String.valueOf(person.getAddEmployee().getIfscCode());
				pan_Card = String.valueOf(person.getAddEmployee().getPanCard());
				blood_Group = String.valueOf(person.getAddEmployee().getBloodGroup());
				nominee = String.valueOf(person.getAddEmployee().getNominee());
				Nominee_Contact = String.valueOf(person.getAddEmployee().getNomineeContact());
				branch = String.valueOf(person.getAddEmployee().getBranch());
				Joining_Date = String.valueOf(person.getAddEmployee().getJoiningDate());
			}
			// AddEmpolyee data in the form of table form database
			PdfPTable tableofAddEmployee = new PdfPTable(2); // 2 columns
			tableofAddEmployee.setWidthPercentage(105);
			tableofAddEmployee.setSpacingBefore(10f);
			tableofAddEmployee.setSpacingAfter(10f);
			// Add table headers
			tableofAddEmployee.addCell(new PdfPCell(new Phrase("Employee Name")));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase((can.getFirstName() + can.getLastName()))));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase("Account Holder Name")));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase(account_HolderName)));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase("Account Number")));
			String maskedAccountNumber = "XXXXXXXXXXXX" + account_Number.substring(account_Number.length() - 4);
			tableofAddEmployee.addCell(new PdfPCell(new Phrase(maskedAccountNumber)));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase("Bank Name")));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase(bank_Name)));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase("IFSC Code")));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase(ifsc_Code)));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase("Pan Card")));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase(pan_Card)));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase("Email")));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase(can.getEmail())));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase("Phone Number")));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase(can.getPhoneNumber())));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase("Address")));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase(can.getAddress() + can.getCity() + can.getPincode())));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase("Branch")));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase(branch)));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase("Joining Date")));
			tableofAddEmployee.addCell(new PdfPCell(new Phrase(Joining_Date)));
			document.add(tableofAddEmployee);
			// Creating table to store data
			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);
			PdfPTable table2 = new PdfPTable(2);
			table2.setWidthPercentage(100);
			table2.setSpacingBefore(10f);
			table2.setSpacingAfter(10f);
			PdfPTable table3 = new PdfPTable(2);
			table3.setWidthPercentage(100);
			table3.setSpacingBefore(10f);
			table3.setSpacingAfter(10f);
			PdfPTable table4 = new PdfPTable(2);
			table4.setWidthPercentage(100);
			table4.setSpacingBefore(10f);
			table4.setSpacingAfter(10f);
			PdfPTable table5 = new PdfPTable(2);
			table5.setWidthPercentage(100);
			table5.setSpacingBefore(10f);
			table5.setSpacingAfter(10f);
			PdfPTable table6 = new PdfPTable(2);
			table6.setWidthPercentage(100);
			table6.setSpacingBefore(10f);
			table6.setSpacingAfter(10f);
			PdfPTable table7 = new PdfPTable(2);
			table7.setWidthPercentage(100);
			table7.setSpacingBefore(10f);
			table7.setSpacingAfter(10f);
			PdfPTable table8 = new PdfPTable(2);
			table8.setWidthPercentage(100);
			table8.setSpacingBefore(10f);
			table8.setSpacingAfter(10f);
			PdfPTable table9 = new PdfPTable(2);
			table9.setWidthPercentage(100);
			table9.setSpacingBefore(10f);
			table9.setSpacingAfter(10f);
			PdfPTable table10 = new PdfPTable(2);
			table10.setWidthPercentage(100);
			table10.setSpacingBefore(10f);
			table10.setSpacingAfter(10f);
			// Adding table headers
			PdfPCell cell1 = new PdfPCell(new Paragraph("Transaction Id"));
			PdfPCell cell2 = new PdfPCell(new Paragraph("Employee Id"));
			PdfPCell cell3 = new PdfPCell(new Paragraph("Month"));
			PdfPCell cell4 = new PdfPCell(new Paragraph("Year"));
			PdfPCell cell5 = new PdfPCell(new Paragraph("Leaves"));
			PdfPCell cell6 = new PdfPCell(new Paragraph("Loans"));
			PdfPCell cell7 = new PdfPCell(new Paragraph("Prof Tax"));
			PdfPCell cell8 = new PdfPCell(new Paragraph("Absent"));
			PdfPCell cell9 = new PdfPCell(new Paragraph("Appraisal  Points"));
			PdfPCell cell10 = new PdfPCell(new Paragraph("Warning Points"));
			PdfPCell cell11 = new PdfPCell(new Paragraph("Delay Points"));
			PdfPCell cell12 = new PdfPCell(new Paragraph("Allowances"));
			PdfPCell cell13 = new PdfPCell(new Paragraph("Bonus"));
			PdfPCell cell14 = new PdfPCell(new Paragraph("Net Salary"));
			PdfPCell cell15 = new PdfPCell(new Paragraph("TDS"));
			PdfPCell cell16 = new PdfPCell(new Paragraph("Transaction Date"));
			PdfPCell cell17 = new PdfPCell(new Paragraph("Source Bank"));
			PdfPCell cell18 = new PdfPCell(new Paragraph("Credited By"));
			PdfPCell cell19 = new PdfPCell(new Paragraph("Final Salary"));
			table.addCell(cell1);
			table.addCell(cell2);
			table2.addCell(cell11);
			table2.addCell(cell12);
			table3.addCell(cell3);
			table3.addCell(cell4);
			table4.addCell(cell5);
			table4.addCell(cell6);
			table5.addCell(cell7);
			table5.addCell(cell8);
			table6.addCell(cell9);
			table6.addCell(cell10);
			table7.addCell(cell13);
			table8.addCell(cell15);
			table8.addCell(cell16);
			table9.addCell(cell17);
			table9.addCell(cell18);
			table10.addCell(cell14);
			table10.addCell(cell19);
			// Adding person data to table
			PdfPCell transaction_Id = new PdfPCell(new Paragraph(secondPerson.getTransactionId()));
			PdfPCell empId = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getAddEmployee().getEmpId())));
			table.addCell(transaction_Id);
			table.addCell(empId);
			PdfPCell delay_Points = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getDelayPoints())));
			PdfPCell allowances = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getAllowances())));
			table2.addCell(delay_Points);
			table2.addCell(allowances);
			PdfPCell month = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getMonth())));
			PdfPCell year = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getYear())));
			table3.addCell(month);
			table3.addCell(year);
			PdfPCell leaves = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getLeaves())));
			PdfPCell loans = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getLoans())));
			table4.addCell(leaves);
			table4.addCell(loans);
			PdfPCell prof_Tax = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getProfTax())));
			PdfPCell absent = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getAbsent())));
			table5.addCell(prof_Tax);
			table5.addCell(absent);
			PdfPCell appr_Points = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getApprPoints())));
			PdfPCell warning_Points = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getWarningPoints())));
			table6.addCell(appr_Points);
			table6.addCell(warning_Points);
			PdfPCell cell = new PdfPCell(new Phrase(String.valueOf(secondPerson.getBonus())));
			table7.addCell(cell);
			table7.addCell("YOU");
			PdfPCell tds = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getTds())));
			PdfPCell transaction_Date = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getTransactionDate())));
			table8.addCell(tds);
			table8.addCell(transaction_Date);
			PdfPCell source_Bank = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getSourceBank())));
			PdfPCell credited_By = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getCreditedBy())));
			table9.addCell(source_Bank);
			table9.addCell(credited_By);
			PdfPCell net_Salary = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getNetSalary())));
			PdfPCell final_Salary = new PdfPCell(new Paragraph(String.valueOf(secondPerson.getFinalSalary())));
			table10.addCell(net_Salary);
			table10.addCell(final_Salary);
			// create parent table with two columns
			PdfPTable parentTable = new PdfPTable(2);
			parentTable.setWidthPercentage(80);
			parentTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			// add left and right tables to the parent table
			PdfPCell leftCell = new PdfPCell(table);
			leftCell.setBorder(Rectangle.NO_BORDER);
			parentTable.addCell(leftCell);
			PdfPCell rightCell = new PdfPCell(table2);
			rightCell.setBorder(Rectangle.NO_BORDER);
			parentTable.addCell(rightCell);
			// add left and right tables to the parent table 2
			PdfPTable parentTable2 = new PdfPTable(2);
			parentTable2.setWidthPercentage(80);
			parentTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			PdfPCell leftCell2 = new PdfPCell(table3);
			leftCell2.setBorder(Rectangle.NO_BORDER);
			parentTable2.addCell(leftCell2);
			PdfPCell rightCell2 = new PdfPCell(table4);
			rightCell2.setBorder(Rectangle.NO_BORDER);
			parentTable2.addCell(rightCell2);
			// add left and right tables to the parent table 3
			PdfPTable parentTable3 = new PdfPTable(2);
			parentTable2.setWidthPercentage(80);
			parentTable2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			PdfPCell leftCell3 = new PdfPCell(table5);
			leftCell3.setBorder(Rectangle.NO_BORDER);
			parentTable3.addCell(leftCell3);
			PdfPCell rightCell3 = new PdfPCell(table6);
			rightCell3.setBorder(Rectangle.NO_BORDER);
			parentTable3.addCell(rightCell3);
			// add left and right tables to the parent table 4
			PdfPTable parentTable4 = new PdfPTable(2);
			parentTable4.setWidthPercentage(80);
			parentTable4.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			PdfPCell leftCell4 = new PdfPCell(table7);
			leftCell4.setBorder(Rectangle.NO_BORDER);
			parentTable4.addCell(leftCell4);
			PdfPCell rightCell4 = new PdfPCell(table8);
			rightCell4.setBorder(Rectangle.NO_BORDER);
			parentTable4.addCell(rightCell4);
			// add left and right tables to the parent table 5
			PdfPTable parentTable5 = new PdfPTable(2);
			parentTable5.setWidthPercentage(80);
			parentTable5.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			// add left and right tables to the parent table 6
			PdfPTable parentTable6 = new PdfPTable(1);
			parentTable6.setWidthPercentage(80);
			parentTable6.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			PdfPCell LeftCell6 = new PdfPCell(table9);
			// LeftCell6.setBorder(Rectangle.NO_BORDER);
			parentTable6.addCell(LeftCell6);
			PdfPCell rightCell6 = new PdfPCell(table10);
			rightCell6.setBorder(Rectangle.NO_BORDER);
			parentTable6.addCell(rightCell6);
			PdfPTable combinedTable = new PdfPTable(1);
			combinedTable.setWidthPercentage(105);
			PdfPCell parentTableobj = new PdfPCell(parentTable);
			parentTableobj.setBorder(Rectangle.NO_BORDER);
			combinedTable.addCell(parentTableobj);
			PdfPCell parentTable2obj = new PdfPCell(parentTable2);
			parentTable2obj.setBorder(Rectangle.NO_BORDER);
			combinedTable.addCell(parentTable2obj);
			PdfPCell parentTable3obj = new PdfPCell(parentTable3);
			parentTable3obj.setBorder(Rectangle.NO_BORDER);
			combinedTable.addCell(parentTable3obj);
			PdfPCell parentTable4obj = new PdfPCell(parentTable4);
			parentTable4obj.setBorder(Rectangle.NO_BORDER);
			combinedTable.addCell(parentTable4obj);
			PdfPCell parentTable5obj = new PdfPCell(parentTable5);
			parentTable5obj.setBorder(Rectangle.NO_BORDER);
			combinedTable.addCell(parentTable5obj);
			PdfPCell parentTable6obj = new PdfPCell(parentTable6);
			parentTable6obj.setBorder(Rectangle.NO_BORDER);
			combinedTable.addCell(parentTable6obj);
			document.add(combinedTable);
			System.err.println("855" + secondPerson.getFinalSalary());
			String inWords = serviceInter.convertToIndianCurrency(secondPerson.getFinalSalary());
			Paragraph paragraph = new Paragraph("Final Salary In Words : " + inWords);
			document.add(paragraph);
		}
		document.close();
	}
}