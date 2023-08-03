package com.payroll.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.payroll.demo.entity.Loantransaction;

public interface LoantransactionRepo extends JpaRepository<Loantransaction, Integer> {

}
