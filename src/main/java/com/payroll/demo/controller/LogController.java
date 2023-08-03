//package com.payroll.demo.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.payroll.demo.entity.Logs;
//import com.payroll.demo.serviceImpl.Logserviceimpl;
//
//@RestController
//@RequestMapping("/api")
//public class LogController {
//
//	@Autowired
//	private Logserviceimpl logservice;
//
//	@PostMapping("/cardtap/{empId}")
//	public ResponseEntity<String> processCardTap(@PathVariable String empId) {
//		String responseMessage = logservice.processCardTap(empId);
//
//		if (responseMessage != null) {
//			return ResponseEntity.ok(responseMessage);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//	@PostMapping("/save")
//	public ResponseEntity<Logs> savelogin(@RequestBody Logs logs){
//		return ResponseEntity.ok(logs);
//		
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<Logs> updateLogsByEmployeeId(@PathVariable Long id, @RequestBody Logs updatedLogs) {
//		Logs logs = logservice.updateLogsByEmployeeId(id, updatedLogs);
//		return ResponseEntity.ok(logs);
//	}
//
//	@GetMapping("/getall")
//	public ResponseEntity<List<Map<String, Object>>> getAllLogs() {
//	    List<Map<String, Object>> logs = logservice.getllusers();
//	    
//	    // Remove underscore symbol from keys
//	    List<Map<String, Object>> modifiedLogs = new ArrayList<>();
//	    for (Map<String, Object> log : logs) {
//	        Map<String, Object> modifiedLog = new HashMap<>();
//	        for (Map.Entry<String, Object> entry : log.entrySet()) {
//	            String key = entry.getKey();
//	            String modifiedKey = key.replace("_", "");
//	            modifiedLog.put(modifiedKey, entry.getValue());
//	        }
//	        modifiedLogs.add(modifiedLog);
//	    }
//	    
//	    return ResponseEntity.ok(modifiedLogs);
//	}
//
//
//}
