package com.clinic.common.datarec;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.clinic.common.db.DBUtils;

public class VTransaction {

	private VConnection conn;
	public VTransaction() throws NamingException, SQLException {
		// TODO Auto-generated constructor stub
		conn = (VConnection) DBUtils.DBConnection();
		//conn.trConn(true);
	}

	public VTransaction(VConnection iconn) {
		// TODO Auto-generated constructor stub
		conn = iconn;
	}

	public void start() throws SQLException {
		conn.trConn(true);
		conn.setAutoCommit(false);
	}
	
	public void commit() throws SQLException {
		conn.commit();
		conn.close();
	}

	public void rollback() throws SQLException {
		conn.rollback();
		conn.close();
	}
	
	public Connection conn() {
		return conn;
	}

	public void end() {
		// TODO Auto-generated method stub
		
	}
}
