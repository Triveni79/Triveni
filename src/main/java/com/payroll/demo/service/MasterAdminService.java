package com.payroll.demo.service;

import java.util.List;

import com.payroll.demo.entity.MasterAdmin;

public interface MasterAdminService {


	  MasterAdmin saveBusiness(MasterAdmin business);
	    MasterAdmin findBusinessById(String businessId);
	    List<MasterAdmin> findAllBusinesses();

	    MasterAdmin updateBusiness(MasterAdmin business);

		void deleteBusiness(String businessId);
	
}
