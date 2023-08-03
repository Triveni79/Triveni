package com.payroll.demo.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payroll.demo.service.UserService;
import com.payroll.demo.util.DBUtil;

@Service
public class UserServiceimpl implements UserService{
	@Autowired
	DBUtil util;
	
	Connection connection;
	int flag = 0;
	public UserServiceimpl() throws SQLException {
		connection = DBUtil.getConnection();
	}
	
	
	@Override
	public int loginValidation(String official_Email, String official_Mail_Password) {
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT official_Email,official_Mail_Password FROM login_Credentials where official_Email='"+official_Email+"'");
			ResultSet rs = statement.executeQuery();
			
			
			while(rs.next()) {
				if(rs.getString(1).equals(official_Email) && rs.getString(2).equals(official_Mail_Password)) {
					flag = 1;
				}
				else {
					System.out.println("Invalid UserName/Password");
					flag = 0;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
