package com.payroll.demo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.payroll.demo.DTO.InterviewerDto;
import com.payroll.demo.entity.AddInterviewerName;
import com.payroll.demo.entity.Interviewer;
import com.payroll.demo.repository.InterviewerRepo;
import com.payroll.demo.service.AddInterviewerService;
import com.payroll.demo.serviceImpl.InterviewerServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class InterviewerController {

	@Autowired
	InterviewerRepo interviewrepo;
	@Autowired
	private InterviewerRepo interviewerRepo;
	@Autowired
	private AddInterviewerService interviewerIdService;
	@Autowired
	private InterviewerServiceImpl interviewerService;
	
	@GetMapping("/interviewer")
	public List<Interviewer> getAllInterviewers() {
		return interviewerService.getAllInterviewers();
	}

	@PutMapping("/roundone/{candidateId}")
	public ResponseEntity<Interviewer> updateCandidateRoundOne(@PathVariable String candidateId,
			@RequestParam(name = "roundOneStatus") String roundOneStatus,
			@RequestParam(name = "roundoneRemarks") String roundoneRemarks) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(interviewerService.updateCandidateRoundOne(candidateId, roundOneStatus, roundoneRemarks));
	}

	@PutMapping("/save/roundtwo/{candidateId}")
	public ResponseEntity<Interviewer> updateRoundTwo(@PathVariable String candidateId,
			@RequestParam(name = "roundTwoStatus") String roundTwoStatus,
			@RequestParam(name = "roundtwoRemarks") String roundtwoRemarks) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(interviewerService.updateRoundTwo(candidateId, roundTwoStatus, roundtwoRemarks));
	}

	@PutMapping("/save/roundthree/{candidateId}")
	public ResponseEntity<Interviewer> updateRoundThree(@PathVariable String candidateId,
			@RequestParam(name = "roundThreeStatus") String roundThreeStatus,
			@RequestParam(name = "roundthreeRemarks") String roundthreeRemarks) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(interviewerService.updateCandidateRoundThree(candidateId, roundThreeStatus, roundthreeRemarks));
	}

	@GetMapping("/getroundone/{candidateId}")
	public ResponseEntity<Interviewer> getOne(@PathVariable String candidateId, Interviewer intervi) {
		// Logic to fetch candidate round one data
		Optional<Interviewer> interviewer = interviewerService.getOne(candidateId);
		Interviewer inter = interviewer.get();

		Interviewer interview = new Interviewer();
		interview.setRoundOneStatus(inter.getRoundOneStatus());
		interview.setRoundoneRemarks(inter.getRoundoneRemarks());

		if (interview != null) {
			return ResponseEntity.ok(interview);
		} else {
			return ResponseEntity.notFound().build();

		}
	}

	@GetMapping("/getroundoneandtwo/{candidateId}")
	public ResponseEntity<Interviewer> getOneAndTwo(@PathVariable String candidateId, Interviewer intervi) {
		// Logic to fetch candidate round one data
		Optional<Interviewer> interviewer = interviewerService.getOne(candidateId);
		Interviewer inter = interviewer.get();

		Interviewer interview = new Interviewer();
		interview.setRoundOneStatus(inter.getRoundOneStatus());
		interview.setRoundoneRemarks(inter.getRoundoneRemarks());
		interview.setRoundTwoStatus(inter.getRoundTwoStatus());
		interview.setRoundtwoRemarks(inter.getRoundtwoRemarks());

		if (interview != null) {
			return ResponseEntity.ok(interview);
		} else {
			return ResponseEntity.notFound().build();

		}
	}

	@PostMapping("/interviewer")
	public Interviewer createInterview(
	    @RequestParam String candidateId,
	    @RequestParam String lastName,
	    @RequestParam String roundOneInterviewername,
	    @RequestParam Date roundOneDate,
	    @RequestParam String roundOneInterviewerid
	) {
	    Interviewer interviewer = new Interviewer();
	    AddInterviewerName interviewersId = interviewerIdService.getInterviewerId(roundOneInterviewerid);
	    if (interviewersId == null) {
	        return null;
	    }

	    interviewer.setRoundOneInterviewerid(interviewersId);
	    interviewer.setCandidateId(candidateId);
	    interviewer.setLastName(lastName);
	    interviewer.setRoundOneInterviewername(roundOneInterviewername);
	    interviewer.setRoundOneDate(roundOneDate);

	    return interviewerService.createInterviewer(interviewer, roundOneInterviewerid);
	}


	@PutMapping("/roundtwointid/{candidateId}")
	public ResponseEntity<Interviewer> updateRoundTwoInterViewerId(@PathVariable String candidateId,
			@RequestParam("roundTwoInterviewerid") String roundTwoInterviewerid,
			@RequestParam("roundTwoInterviewername") String roundTwoInterviewername,
			@RequestParam("roundTwoDate") Date roundTwoDate) {
		
		AddInterviewerName interviewersId = interviewerIdService.getInterviewerId(roundTwoInterviewerid);
		    if (interviewersId == null) {
		        return null;
		    }
		return ResponseEntity.status(HttpStatus.CREATED).body(interviewerService.updateRoundTwoInterViewerId(roundTwoInterviewerid,
				candidateId, roundTwoInterviewername, roundTwoDate));
	}

	@PutMapping("/roundthreeintid/{candidateId}")
	public ResponseEntity<Interviewer> updateRoundThreeInterViewerId(@PathVariable String candidateId,
			@RequestParam("roundThreeInterviewerid") String roundThreeInterviewerid,
			@RequestParam("roundThreeInterviewername") String roundThreeInterviewername,
			@RequestParam("roundThreeDate") Date roundThreeDate) {
		AddInterviewerName interviewersId = interviewerIdService.getInterviewerId(roundThreeInterviewerid);
		    if (interviewersId == null) {
		        return null;
		    }
		return ResponseEntity.status(HttpStatus.CREATED).body(interviewerService.updateRoundThreeInterViewerId(roundThreeInterviewerid,
				candidateId, roundThreeInterviewername, roundThreeDate));
	}
	
	@GetMapping("/interviewer/{interviewerId}")
	public ResponseEntity<List<Interviewer>> getInterviewerDetails(@PathVariable("interviewerId") AddInterviewerName interviewerId) {
	    List<Interviewer> interviewers = new ArrayList<>();

	    List<Interviewer> roundOneInterviewers = interviewerService.findByRoundOneInterviewerid(interviewerId);
	    List<Interviewer> roundTwoInterviewers = interviewerService.findByRoundTwoInterviewerid(interviewerId);
	    List<Interviewer> roundThreeInterviewers = interviewerService.findByRoundThreeInterviewerid(interviewerId);

	    if (roundOneInterviewers != null) {
	        interviewers.addAll(roundOneInterviewers);
	    }

	    if (roundTwoInterviewers != null) {
	        interviewers.addAll(roundTwoInterviewers);
	    }

	    if (roundThreeInterviewers != null) {
	        interviewers.addAll(roundThreeInterviewers);
	    }

	    if (interviewers.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    List<Interviewer> responseList = new ArrayList<>();
	    for (Interviewer interviewer : interviewers) {
	        if (!isInterviewerComplete(interviewer)) {
	            Interviewer response = new Interviewer();
	            response.setCandidateId(interviewer.getCandidateId());
	            response.setLastName(interviewer.getLastName());

	            if (roundTwoInterviewers.contains(interviewer)) {
	                response.setRoundTwoDate(interviewer.getRoundTwoDate());
	                response.setRoundTwoInterviewername(interviewer.getRoundTwoInterviewername());
	                response.setRoundTwoStatus(interviewer.getRoundTwoStatus());
	                response.setRoundtwoRemarks(interviewer.getRoundtwoRemarks());
	            }

	            if (roundThreeInterviewers.contains(interviewer)) {
	                response.setRoundThreeDate(interviewer.getRoundThreeDate());
	                response.setRoundThreeInterviewername(interviewer.getRoundThreeInterviewername());
	                response.setRoundThreeStatus(interviewer.getRoundThreeStatus());
	                response.setRoundthreeRemarks(interviewer.getRoundthreeRemarks());
	            }

	            responseList.add(response);
	        }
	    }
	    return ResponseEntity.ok(responseList);
	}

	private boolean isInterviewerComplete(Interviewer interviewer) {
	    return interviewer.getRoundOneDate() != null &&
	            interviewer.getRoundOneInterviewername() != null &&
	            interviewer.getRoundOneInterviewerid() != null &&
	            interviewer.getRoundOneStatus() != null &&
	            interviewer.getRoundoneRemarks() != null &&
	            interviewer.getRoundTwoDate() != null &&
	            interviewer.getRoundTwoInterviewername() != null &&
	            interviewer.getRoundTwoInterviewerid() != null &&
	            interviewer.getRoundTwoStatus() != null &&
	            interviewer.getRoundtwoRemarks() != null &&
	            interviewer.getRoundThreeDate() != null &&
	            interviewer.getRoundThreeInterviewername() != null &&
	            interviewer.getRoundThreeInterviewerid() != null &&
	            interviewer.getRoundThreeStatus() != null &&
	            interviewer.getRoundthreeRemarks() != null;
	}

	@GetMapping("/onlyid/{candidateId}")
	public ResponseEntity<InterviewerDto> getCandidate(@PathVariable String candidateId) {
	    InterviewerDto interviewerDto = new InterviewerDto();
	    interviewerDto.setCandidateId(candidateId);
	    return ResponseEntity.ok(interviewerDto);
	}
	
	@GetMapping("/data/check/{candidateId}")
	public ResponseEntity<?> checkDataPresence(@PathVariable String candidateId) {
		try {
			Optional<Interviewer> interviewerOptional = interviewerRepo.findByCandidateId(candidateId);

			if (interviewerOptional.isPresent()) {
				Interviewer interviewer = interviewerOptional.get();
				if (interviewer.getRoundOneStatus() != null && interviewer.getRoundoneRemarks() != null) {
//					return ResponseEntity.ok().body("{\"dataPresent\": true}",interviewer.getRoundOneStatus());
					return ResponseEntity.ok()
							.body("{\"dataPresent\": true, \"status\": \"" + interviewer.getRoundOneStatus() + "\"}");
				} else {
					return ResponseEntity.ok().body("{\"dataPresent\": false}");
				}
			} else {
				return ResponseEntity.ok().body("{\"dataPresent\": false}");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"An error occurred\"}");
		}
	}

	@GetMapping("/data/checkRoundTwo/{candidateId}")
	public ResponseEntity<?> checkRoundTwoDataPresence(@PathVariable String candidateId) {
		try {
			Optional<Interviewer> interviewerOptional = interviewerRepo.findByCandidateId(candidateId);

			if (interviewerOptional.isPresent()) {
				Interviewer interviewer = interviewerOptional.get();
				if (interviewer.getRoundTwoStatus() != null && interviewer.getRoundtwoRemarks() != null) {
//					return ResponseEntity.ok().body("{\"dataPresent\": true}");
					return ResponseEntity.ok()
							.body("{\"dataPresent\": true, \"status\": \"" + interviewer.getRoundTwoStatus() + "\"}");
				} else {
					return ResponseEntity.ok().body("{\"dataPresent\": false}");
				}
			} else {
				return ResponseEntity.ok().body("{\"dataPresent\": false}");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"An error occurred\"}");
		}
	}

	@GetMapping("/data/checkRoundThree/{candidateId}")
	public ResponseEntity<?> checkRoundThreeDataPresence(@PathVariable String candidateId) {
		try {
			Optional<Interviewer> interviewerOptional = interviewerRepo.findByCandidateId(candidateId);

			if (interviewerOptional.isPresent()) {
				Interviewer interviewer = interviewerOptional.get();
				if (interviewer.getRoundThreeStatus() != null && interviewer.getRoundthreeRemarks() != null) {
//					return ResponseEntity.ok().body("{\"dataPresent\": true}");
					return ResponseEntity.ok()
							.body("{\"dataPresent\": true, \"status\": \"" + interviewer.getRoundThreeStatus() + "\"}");
				} else {
					return ResponseEntity.ok().body("{\"dataPresent\": false}");
				}
			} else {
				return ResponseEntity.ok().body("{\"dataPresent\": false}");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"An error occurred\"}");
		}
	}
	
	@GetMapping("/getselectedfornextroundcandidates")
	public List<InterviewerDto> getSelectedCandidate() {
	    List<Interviewer> list = interviewrepo.findAll();
	    List<InterviewerDto> next = new ArrayList<>();

	    boolean isRequirementMet = false; // Flag to check if the requirement is met

	    for (Interviewer interviewer : list) {
	        InterviewerDto interviewerDto = new InterviewerDto(); // Create a new InterviewerDto

	        // Existing code to map Interviewer to InterviewerDto...
	        interviewerDto.setCandidateId(interviewer.getCandidateId());
	        interviewerDto.setLastName(interviewer.getLastName());

	        String roundOneStatus = interviewer.getRoundOneStatus();
	        String roundTwoStatus = interviewer.getRoundTwoStatus();
	        String roundThreeStatus = interviewer.getRoundThreeStatus();

	        if ((!roundOneStatus.equalsIgnoreCase("selected") && !roundOneStatus.equalsIgnoreCase("rejected"))
	                && roundTwoStatus == null && roundThreeStatus == null
	                && roundOneStatus.equalsIgnoreCase("selected for next round")) {
	            // Existing code to set round one details in interviewerDto...
	            interviewerDto.setRoundOneDate(interviewer.getRoundOneDate());
	            interviewerDto.setRoundOneInterviewername(interviewer.getRoundOneInterviewername());
	            interviewerDto.setRoundOneInterviewerid(interviewer.getRoundOneInterviewerid());
	            next.add(interviewerDto); // Add the interviewerDto to the next list
	            isRequirementMet = true; // Requirement is met
	        } else if ((!roundTwoStatus.equalsIgnoreCase("selected") && !roundTwoStatus.equalsIgnoreCase("rejected"))
	                && roundThreeStatus == null && roundOneStatus != null
	                && roundOneStatus.equalsIgnoreCase("selected for next round")) {
	            // Existing code to set round two details in interviewerDto...
	            interviewerDto.setRoundTwoDate(interviewer.getRoundTwoDate());
	            interviewerDto.setRoundTwoInterviewername(interviewer.getRoundTwoInterviewername());
	            interviewerDto.setRoundTwoInterviewerid(interviewer.getRoundTwoInterviewerid());
	            next.add(interviewerDto); // Add the interviewerDto to the next list
	            isRequirementMet = true; // Requirement is met
	        } 
	        }

	    if (!isRequirementMet) {
	        return null; // Return null if the requirement is not met
	    }

	    return next;
	}

	
	@GetMapping("/getselectedcandidates")
	public List<InterviewerDto> getSelectedCandidates() {
	    List<Interviewer> list = interviewrepo.findAll();
	    List<InterviewerDto> aa = new ArrayList<>();

	    for (Interviewer interviewer : list) {
	        InterviewerDto interviewerDto = new InterviewerDto();
	        interviewerDto.setCandidateId(interviewer.getCandidateId());
	        interviewerDto.setLastName(interviewer.getLastName());

	        if (interviewer.getRoundOneStatus() != null && interviewer.getRoundOneStatus().equalsIgnoreCase("selected")) {
	            interviewerDto.setRoundOneDate(interviewer.getRoundOneDate());
	            interviewerDto.setRoundOneInterviewername(interviewer.getRoundOneInterviewername());
	            interviewerDto.setRoundOneInterviewerid(interviewer.getRoundOneInterviewerid());
	            aa.add(interviewerDto);
	        } else if (interviewer.getRoundTwoStatus() != null && interviewer.getRoundTwoStatus().equalsIgnoreCase("selected")) {
	            interviewerDto.setRoundTwoDate(interviewer.getRoundTwoDate());
	            interviewerDto.setRoundTwoInterviewername(interviewer.getRoundTwoInterviewername());
	            interviewerDto.setRoundTwoInterviewerid(interviewer.getRoundTwoInterviewerid());
	            aa.add(interviewerDto);
	        } else if (interviewer.getRoundThreeStatus() != null && interviewer.getRoundThreeStatus().equalsIgnoreCase("selected")) {
	            interviewerDto.setRoundThreeDate(interviewer.getRoundThreeDate());
	            interviewerDto.setRoundThreeInterviewername(interviewer.getRoundThreeInterviewername());
	            interviewerDto.setRoundThreeInterviewerid(interviewer.getRoundThreeInterviewerid());
	            aa.add(interviewerDto);
	        }
	    }
	    return aa;
	}

	@GetMapping("/getrejectedcandidates")
	public List<InterviewerDto> getRejectedCandidates() {
	    List<Interviewer> list = interviewrepo.findAll();
	    List<InterviewerDto> ar = new ArrayList<>();

	    for (Interviewer interviewer : list) {
	        InterviewerDto interviewerDto = new InterviewerDto();
	        interviewerDto.setCandidateId(interviewer.getCandidateId());
	        interviewerDto.setLastName(interviewer.getLastName());

	        if (interviewer.getRoundOneStatus() != null && interviewer.getRoundOneStatus().equalsIgnoreCase("rejected")) {
	            interviewerDto.setRoundOneDate(interviewer.getRoundOneDate());
	            interviewerDto.setRoundOneInterviewername(interviewer.getRoundOneInterviewername());
	            interviewerDto.setRoundOneInterviewerid(interviewer.getRoundOneInterviewerid());
	            ar.add(interviewerDto);
	        } else if (interviewer.getRoundTwoStatus() != null && interviewer.getRoundTwoStatus().equalsIgnoreCase("rejected")) {
	            interviewerDto.setRoundTwoDate(interviewer.getRoundTwoDate());
	            interviewerDto.setRoundTwoInterviewername(interviewer.getRoundTwoInterviewername());
	            interviewerDto.setRoundTwoInterviewerid(interviewer.getRoundTwoInterviewerid());
	            ar.add(interviewerDto);
	        } else if (interviewer.getRoundThreeStatus() != null && interviewer.getRoundThreeStatus().equalsIgnoreCase("rejected")) {
	            interviewerDto.setRoundThreeDate(interviewer.getRoundThreeDate());
	            interviewerDto.setRoundThreeInterviewername(interviewer.getRoundThreeInterviewername());
	            interviewerDto.setRoundThreeInterviewerid(interviewer.getRoundThreeInterviewerid());
	            ar.add(interviewerDto);
	        }
	    }

	    return ar;
	}
	
}