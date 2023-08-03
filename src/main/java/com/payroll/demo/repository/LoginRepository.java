package com.payroll.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.payroll.demo.entity.Login;

public interface LoginRepository extends JpaRepository<Login, String> {
	
  /*  @Query("SELECT m.emp_Id, m.email FROM login_Credentials m WHERE m.id = :id")
    Optional<Object[]> findById(@Param("id") int id);
    */

	 Login findByOfficialEmail(String officialEmail);

	Login findByOfficialMailPassword(String officialMailPassword);

	Optional<Login> findById(String empId);
}
