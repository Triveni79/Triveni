package com.payroll.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.demo.entity.Login;
import com.payroll.demo.entity.MasterAdmin;
import com.payroll.demo.repository.MasterAdminRepo;
import com.payroll.demo.service.MasterAdminService;

@Service
public class MasterAdminServiceImpl implements MasterAdminService{
@Autowired
	 private  MasterAdminRepo masterAdminRepo;

    @Override
    public MasterAdmin saveBusiness(MasterAdmin business) {
        generateAndSetEmpId(business);
        return masterAdminRepo.save(business);
    }

    private void generateAndSetEmpId(MasterAdmin business) {
        Login login = business.getLogin();
        if (login == null) {
            login = new Login();
            business.setLogin(login);
        }

        // Extract the year from registrationDate
        int year = business.getRegistrationDate().getYear() + 1900; // Adding 1900 as getYear() returns year - 1900

        // Get the latest empId for the current year from the database (assuming you have a repository to access the database)
        String latestEmpId = masterAdminRepo.findLatestEmpIdByYear("%" + year + "%");

        // Extract the sequential number from the latest empId
        int sequentialNumber = 1;
        if (latestEmpId != null) {
            try {
                sequentialNumber = Integer.parseInt(latestEmpId.substring(4)) + 1;
            } catch (NumberFormatException e) {
                // Handle the exception (if any) when parsing the sequential number
            }
        }

        // Create the new empId in the format "yyMMddXXX"
        String empId = String.format("%02d%02d%03d", year % 100, business.getRegistrationDate().getMonth() + 1, sequentialNumber);

        // Set the new empId and other login details
        login.setEmpId(empId);
        login.setOfficialEmail(business.getBusinessId());
        login.setOfficialMailPassword(business.getPassword());
        login.setMasterAdmin(business);
    }


    @Override
    public MasterAdmin findBusinessById(String businessId) {
        return masterAdminRepo.findById(businessId).orElse(null);
    }

    @Override
    public List<MasterAdmin> findAllBusinesses() {
        return masterAdminRepo.findAll();
    }

    @Override
    public MasterAdmin updateBusiness(MasterAdmin business) {
        return masterAdminRepo.save(business);
    }


	@Override
	public void deleteBusiness(String businessId) {
		masterAdminRepo.deleteById(businessId);
		
	}
}
