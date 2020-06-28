package com.clinic.common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.NamingException;

import com.clinic.common.datarec.*;
import com.clinic.common.exception.DBErrorException;

public class UserRoleDB {

	private static List<UserRole> urList = null;
	public UserRoleDB() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<UserRole> getUserRoleList()  throws NamingException, SQLException, DBErrorException {
		if (urList == null) {
			urList = new ArrayList<UserRole>();
			PreparedStatement  stmt = null;
		    ResultSet rset = null;
		    Connection conn = DBUtils.DBConnection();
			
			if (conn == null){
				throw new SQLException("Error getting connection");
			}
	
			try {
				String q = "SELECT * FROM `vrte-proto`.userrole ; ";
		        stmt = conn.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);  
		    	
		        rset = stmt.executeQuery();
		        
		        while(rset.next()) {
		        	UserRole cr = new UserRole();
		        	cr.UID(rset.getInt("urid"));
		        	cr.URName(rset.getString("urname"));
		        	cr.URDesc(rset.getString("urdesc"));
		        	urList.add(cr);
		        }
			}
	        finally {
	        	try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new DBErrorException("UserRoleDB.getUserRoleList(): Error closing connection: "+e.getMessage());
				}
	        }
	        //conn.close();
		}
		
		return urList;
	}
}
