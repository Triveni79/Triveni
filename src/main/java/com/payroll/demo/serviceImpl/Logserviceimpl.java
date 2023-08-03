//package com.payroll.demo.serviceImpl;
//
//import java.sql.Date;
//import java.time.Duration;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.payroll.demo.entity.AddEmployee;
//import com.payroll.demo.entity.Logs;
//import com.payroll.demo.exception.EmployeeNotFoundException;
//import com.payroll.demo.repository.AddEmployeeRepo;
//import com.payroll.demo.repository.Logsrepo;
//import com.payroll.demo.service.Logservice;
//
//@Service
//public class Logserviceimpl implements Logservice {
//
//	@Autowired
//	private Logsrepo logrepo;
//
//	@Autowired
//	private AddEmployeeRepo addEmpRepo;
//
//
//	@Override
//	public String processCardTap(String empId) {
//	    // Get the current date
//	    LocalDate currentDate = LocalDate.now();
//
//	    // Find the employee
//	    Optional<AddEmployee> employeeOptional = addEmpRepo.findByEmpId(empId);
//	    if (employeeOptional.isPresent()) {
//	        AddEmployee employee = employeeOptional.get();
//
//	        // Filter the logs for the current date
//	        List<Logs> logsForCurrentDate = employee.getLoginLogoutList()
//	                .stream()
//	                .filter(log -> log.getDate().toLocalDate().equals(currentDate))
//	                .collect(Collectors.toList());
//
//	        if (logsForCurrentDate.size() < 8) {
//	            // Check if there is space for more taps
//
//	            int tapCount = logsForCurrentDate.size();
//
//	            if (tapCount % 2 == 0) {
//	                // It's a login
//	                Logs logs = new Logs();
//	                logs.setLogType("login at " + getCurrentTime());
//	                logs.setDate(getCurrentDate());
//	                employee.getLoginLogoutList().add(logs); // Add the log to the employee's loginLogoutList
//	                addEmpRepo.save(employee); // Save the updated employee
//	                return "Login processed successfully.";
//	            } else {
//	                // It's a logout
//	                Logs logs = new Logs();
//	                logs.setLogType("logout at " + getCurrentTime());
//	                logs.setDate(getCurrentDate());
//
//	                // Find the matching login timestamp
//	                LocalTime loginTime = null;
//	                for (Logs log : logsForCurrentDate) {
//	                    if (log.getLogType().startsWith("login")) {
//	                        loginTime = parseTimestamp(log.getLogType());
//	                        break;
//	                    }
//	                }
//
//	                if (loginTime != null) {
//	                    LocalTime logoutTime = parseTimestamp(logs.getLogType());
//	                    Duration duration = Duration.between(loginTime, logoutTime);
//	                    long hours = duration.toHours();
//	                    long minutes = duration.minusHours(hours).toMinutes();
//
//	                    String workingHours = hours + " hours " + minutes + " minutes";
//	                    logs.setWorkinghours(workingHours);
//
//	                    employee.getLoginLogoutList().add(logs); // Add the log to the employee's loginLogoutList
//	                    addEmpRepo.save(employee); // Save the updated employee
//
//	                    return "Logout processed successfully. Working hours: " + workingHours;
//	                } else {
//	                    return "Matching login not found.";
//	                }
//	            }
//	        } else {
//	            return "Maximum number of taps reached for the employee ID in " + getCurrentDate();
//	        }
//	    } else {
//	        throw new EmployeeNotFoundException("Employee not found with ID: " + empId);
//	    }
//	}
//	
//	@Override
//	public Logs save(Logs logs) {
//		// TODO Auto-generated method stub
//		return logrepo.save(logs);
//	}
//
//
//	private LocalTime parseTimestamp(String logType) {
//		String timestampString = logType.substring(logType.indexOf(" at ") + 4);
//		return LocalTime.parse(timestampString, DateTimeFormatter.ofPattern("hh:mm:ss a", Locale.ENGLISH));
//	}
//
//	private String getCurrentTime() {
//		LocalDateTime now = LocalDateTime.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
//		return now.format(formatter);
//	}
//
//	private Date getCurrentDate() {
//		return new Date(System.currentTimeMillis());
//	}
//
//	@Override
//	public Logs updateLogsByEmployeeId(Long id, Logs updatedLogs) {
//		Logs logs = logrepo.findById(id)
//				.orElseThrow(() -> new EmployeeNotFoundException("Logs not found for employee ID: " + id));
//
//		// Update the necessary fields of the logs object
//		logs.setLogType(updatedLogs.getLogType());
//		logs.setApprovedBy(updatedLogs.getApprovedBy());
//		logs.setWorkinghours(updatedLogs.getWorkinghours());
//		logs.setDate(updatedLogs.getDate());
//
//		// Save the updated logs object
//		logs = logrepo.save(logs);
//
//		return logs;
//	}
//
//	public List<Map<String, Object>> getllusers() {
//		return logrepo.findAllLogsWithEmpId();
//	}
//
//}