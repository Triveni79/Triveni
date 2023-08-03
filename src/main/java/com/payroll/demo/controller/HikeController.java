package com.payroll.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payroll.demo.entity.AddEmployee;
import com.payroll.demo.entity.Hike;
import com.payroll.demo.repository.HikeRepo;
import com.payroll.demo.service.HikeServiceInter;

@RestController
@RequestMapping("/api") 
public class HikeController {

	
	@Autowired
	private HikeServiceInter serviceInter;
	

	@Autowired
	HikeRepo hikeRepo;
	
	@PostMapping("/hikes")
	public Hike saveDetails(@Valid @ModelAttribute("Hike")  Hike h,
			@RequestParam String	department,
			@RequestParam  String	designation,
			@RequestParam Date	dates_Of_Start,
			@RequestParam Date	  dates_Of_End,
			@RequestParam double	  consolidated_sal,
			@RequestParam  String	position,
			@RequestParam String	reporting_manager,
			@RequestParam String	approved_by,
			@RequestParam  double	hike_percentage
		
			
			) {
		
	
				h.setFinal_salary((h.getConsolidated_sal()) + ((h.getHike_percentage() / 100) * (h.getConsolidated_sal())));

					LocalDate date = LocalDate.now();
					int Year =  date.getYear();
	  
					int LastTwo = Year%100;
	   
	    
					AddEmployee EmpId_Which_is_given =  h.getEmpId();
					String String_EmpId_Which_is_given = EmpId_Which_is_given.getEmpId();
	 
	  

					String Hike_Id_With_Serial = "HK" + LastTwo + String_EmpId_Which_is_given  ;
	    
	    
					h.setHikeId(Hike_Id_With_Serial);
		
		
					return serviceInter.saveHike(h,
				 	department,
				  	designation,
				 	dates_Of_Start,
				 	dates_Of_End,
				 	consolidated_sal,
				  	position,
				 	reporting_manager,
				 	approved_by,
				  	hike_percentage
				);
	}
	
	
	@GetMapping("/getallhike")
    public List<Hike>getAllHikes(){
    	 return hikeRepo.findAll();
    }
	
	  @GetMapping("/getOne/{Hike_Id}")
	    public Optional<Hike> getEmployeeDetailsThroughTransID(@PathVariable("Hike_Id") String Hike_Id) {
	     	    	
	    	return	serviceInter.getEmppThroughHikeID(Hike_Id);
	        		    
	    }
	          
}
