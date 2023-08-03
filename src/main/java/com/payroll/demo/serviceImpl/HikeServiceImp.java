package com.payroll.demo.serviceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.demo.entity.AddEmployee;
import com.payroll.demo.entity.Hike;
import com.payroll.demo.repository.HikeRepo;
import com.payroll.demo.service.HikeServiceInter;

@Service
public class HikeServiceImp implements HikeServiceInter {

	

	@Autowired
	private HikeRepo Hrepo;

	
	
	

	@Override
	public Hike saveHike(Hike h,
			 String	department,
			 String	designation,
			 Date	dates_Of_Start,
			 Date	  dates_Of_End,
			 double	  consolidated_sal,
			 String	position,
			 String	reporting_manager,
			 String	approved_by,
			 double	hike_percentage
			
			
			) {

		h.setFinal_salary((h.getConsolidated_sal()) + ((h.getHike_percentage() / 100) * (h.getConsolidated_sal())));

		  
	      LocalDate date = LocalDate.now();
	    int Year =  date.getYear();
	  
		   int LastTwo = Year%100;
	   
	    
	  AddEmployee EmpId_Which_is_given =  h.getEmpId();
	String String_EmpId_Which_is_given = EmpId_Which_is_given.getEmpId();
	 
	  

	    String Hike_Id_With_Serial = "HK" + LastTwo + String_EmpId_Which_is_given  ;
	    
	    
	h.setHikeId(Hike_Id_With_Serial);
		
		
		
		
		return Hrepo.save(h);

	}


	
	@Override
	public Optional<Hike> getEmppThroughHikeID(String hikeId) {
		
		return  Hrepo.findById(hikeId);
	}
}
