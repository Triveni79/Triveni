package com.payroll.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.demo.entity.Login;
import com.payroll.demo.repository.LoginRepository;
import com.payroll.demo.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Login login(String officialEmail, String password) {
        Login employee = loginRepository.findByOfficialEmail(officialEmail);
        if (employee != null && employee.getOfficialMailPassword().equals(password)) {
            return employee;
        }
        else {
        	return null;
        }
    }

    @Override
    public boolean changePassword(String officialMailPassword, String newPassword, String confirmPassword) {
        Login employee = loginRepository.findByOfficialMailPassword(officialMailPassword);
        if (employee != null && employee.getOfficialMailPassword().equals(officialMailPassword)) {
            // Check if newPassword and confirmPassword are the same
            if (newPassword.equals(confirmPassword)) {
                employee.setOfficialMailPassword(confirmPassword);
                loginRepository.save(employee);
                return true;
            } else {
                // Passwords don't match
                return false;
            }
        }
        return false;
    }     
    
    @Override
    public void registerEmployee(Login employee) {
    	loginRepository.save(employee);
    }

    @Override
    public List<Login> getLogins() {
        return loginRepository.findAll();
    }

	@Override
	public Optional<Login> getLogin(String empId) {
		// TODO Auto-generated method stub
		return loginRepository.findById(empId);
	}
}
