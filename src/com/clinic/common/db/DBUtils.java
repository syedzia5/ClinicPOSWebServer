/**
 * 
 */
package com.clinic.common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.clinic.common.datarec.VConnection;

/**
 * @author admin
 *
 */
public class DBUtils {

	/*
	private static VConnection vconn = null;
	private static boolean trxon = false;
	*/
	/**
	 * 
	 */
	public DBUtils() {
		// TODO Auto-generated constructor stub
	}

	public static Connection DBConnection() throws NamingException, SQLException {
		DataSource pool;  // Database connection pool
	    Connection conn = null;
	    // Create a JNDI Initial context to be able to lookup the DataSource
        InitialContext initCTX = new InitialContext();
        // Lookup the DataSource, which will be backed by a pool
        //   that the application server provides.
        pool = (DataSource)initCTX.lookup("java:/CLINICDB");
        if (pool != null)
        {
    		conn = new VConnection(pool.getConnection(), false);
    		
    		/*
        	if(trxon) {
        		
        		if((vconn == null)||(vconn.isClosed())) {
        			vconn = new VConnection(pool.getConnection(), true);
        			vconn.setAutoCommit(false);
        		}
        		
        		conn = new VConnection(pool.getConnection(), true);
       		}
        	else {
        		conn = new VConnection(pool.getConnection(), false);
        	}
        	*/
        }
        
	    return conn;
	}
	
	/*
	public static void startTrx() {
		trxon = true;
	}
	public void Commit() throws SQLException {
		vconn.commit();
	}
	
	public void Rollback() throws SQLException {
		vconn.rollback();
	}
	public static void endTrx() throws SQLException {
		trxon = false;
		vconn.close();
	}
	*/
}
