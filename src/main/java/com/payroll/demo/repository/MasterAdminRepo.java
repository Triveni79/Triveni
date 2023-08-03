package com.payroll.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.payroll.demo.entity.MasterAdmin;

@Repository
public interface MasterAdminRepo extends JpaRepository<MasterAdmin, String> {
	@Query("SELECT MAX(l.empId) FROM Login l WHERE l.empId LIKE CONCAT('%', :year, '%')")
	String findLatestEmpIdByYear(@Param("year") String year);
}
