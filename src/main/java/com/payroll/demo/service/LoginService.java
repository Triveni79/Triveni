package com.payroll.demo.service;

import java.util.List;
import java.util.Optional;

import com.payroll.demo.entity.Login;

public interface LoginService {
    Login login(String officialEmail, String password);

    boolean changePassword(String officialMailPassword, String newPassword, String confirmPassword);

    void registerEmployee(Login employee);

    List<Login> getLogins();

	Optional<Login> getLogin(String empId);
}
