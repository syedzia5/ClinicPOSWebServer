package com.clinic.patient.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.clinic.common.datarec.ClinicConstants;
import com.clinic.common.datarec.UserProfile;
import com.clinic.common.datarec.VTransaction;
import com.clinic.common.db.DBUtils;
import com.clinic.common.db.UserProfileDB;
import com.clinic.common.exception.DBErrorException;
import com.clinic.common.exception.IncompleteDataException;
import com.clinic.patient.datarec.PatientRegData;

public class PatientRegDataDB {

	public PatientRegDataDB() {
		// TODO Auto-generated constructor stub
	}

	
	public static List<PatientRegData> getPatientRegData (PatientRegData iMM) throws NamingException, SQLException, DBErrorException {
	
		//String q = " SELECT p.*,u.*,s FROM PatientRegData ";
		String q = 	" select p.*, u.*, s.*, c.*, r.*, sm.* ";
		q += 		" from PatientRegData p, userprofile u, statelist s, city c, userrole r, sexmaster sm  ";
		q +=		" where p.PUID = u.upid";
		q +=		" u.UPSex = sm.SMID ";
		q += 		" and u.isActive = 1 ";
		q += 		" and u.city = c.cid ";
		q += 		" and u.state = s.stlid ";
		q +=		" and u.roleid = r.urid ";
		q += 		" and u.roleid = 13  ";
	
		String w = "";
		
		int iv;
		String sv;
		//, , , , , 
		/*
		iv = iMM.PID();
		w += (iv < 0)?"":(((w.length() > 0)?" and ":"")+" p.PID = "+Integer.toString(iv));
		iv = iMM.PIsHead();
		w += (iv < 0)?"":(((w.length() > 0)?" and ":"")+" p.PIsHead = "+Integer.toString(iv));
		iv = iMM.PHeadID();
		w += (iv < 0)?"":(((w.length() > 0)?" and ":"")+" p.PHeadID = "+Integer.toString(iv));
		sv = iMM.PAllergy();
		w += (sv == null)?"":(((w.length() > 0)?" and ":"")+" p.PAllergy like \"%"+sv+"%\"; ");
		sv = iMM.PPastDisease();
		w += (sv == null)?"":(((w.length() > 0)?" and ":"")+" p.PPastDisease like \"%"+sv+"%\"; ");
		*/
		iv = iMM.PID();
		q += (iv < 0)?"":(" and p.PID = "+Integer.toString(iv));
		iv = iMM.PIsHead();
		q += (iv < 0)?"":(" and p.PIsHead = "+Integer.toString(iv));
		iv = iMM.PHeadID();
		q += (iv < 0)?"":(" and p.PHeadID = "+Integer.toString(iv));
		sv = iMM.PAllergy();
		q += (sv == null)?"":(" and p.PAllergy like \"%"+sv+"%\"; ");
		sv = iMM.PPastDisease();
		q += (sv == null)?"":(" and p.PPastDisease like \"%"+sv+"%\"; ");
		if (iMM.uid() > 0) {
			q += " and u.upid = "+iMM.uid()+ " ";
		}
		if (iMM.role() == 0) {
			q += " and u.roleid not in ( "+Integer.toString(ClinicConstants.UserRole_admin)+" , "+Integer.toString(ClinicConstants.UserRole_investor)+" , "+Integer.toString(ClinicConstants.UserRole_CIMCAdmin)+" ) "; // This will be the case only in delete user
		}
		else if (iMM.role() > 0) {
			q += " and u.roleid = "+Integer.toString(iMM.role())+ " ";
		}
		if (iMM.approle() > 0) {
			q += " and u.approle = "+Integer.toString(iMM.approle())+ " ";
		}
		if (iMM.UPSex() > 0) {
			q += " and u.UPSex = "+Integer.toString(iMM.UPSex())+ " ";
		}
		if (iMM.rolename() != null) {
			q += " and r.urname like '%"+iMM.rolename()+"%' ";
		}
		if (iMM.userid() != null) {
			q += " and u.user_id like '%"+iMM.userid()+"%' ";
		}
		if (iMM.password() != null) {
			q += " and u.passwd like '%"+iMM.password()+"%' ";
		}
		if (iMM.name() != null) {
			q += " and u.UserName like '%"+iMM.name()+"%' ";
		}
		if (iMM.Address1() != null) {
			q += " and u.address1 like '%"+iMM.Address1()+"%' ";
		}
		if (iMM.Email() != null) {
			q += " and u.email like '%"+iMM.Email()+"%' ";
		}
		if (iMM.Address2() != null) {
			q += " and u.address2 like '%"+iMM.Address2()+"%' ";
		}
		if (iMM.Address3() != null) {
			q += " and u.address3 like '%"+iMM.Address3()+"%' ";
		}
		if (iMM.Zipcode() != null) {
			q += " and u.zipcode like '%"+iMM.Zipcode()+"%' ";
		}
		if (iMM.City() > 0) {
			q += " and u.city = "+Integer.toString(iMM.City())+ " ";
		}
		if (iMM.cityName() != null) {
			q += " and c.DistName like '%"+iMM.cityName()+"%' ";
		}
		if (iMM.State() > 0) {
			q += " and u.state = "+Integer.toString(iMM.State())+ " ";
		}
		if (iMM.stateName() != null) {
			q += " and s.StateName like '%"+iMM.stateName()+"%' ";
		}
		if (iMM.isActive() > 0) {
			q += " and u.isActive like '%"+iMM.isActive()+"%' ";
		}
	
		q += " ; ";
		
		//q += (w.length() > 0)?" where "+w:"";
		
		return getPatientRegDataHelp(q);
	}
	
	public static List<PatientRegData> getPatientRegDataHelp (String q) throws NamingException, SQLException, DBErrorException {
		
		List<PatientRegData> mml = new ArrayList<PatientRegData>();
		int iv;
		String sv;
	
		PreparedStatement  stmt = null;
	    ResultSet rset = null;
	    Connection conn = DBUtils.DBConnection();
		
		if (conn == null){
			throw new DBErrorException("PatientRegDataDB.getPatientRegDataHelp():Error getting connection");
		}
		
		try {
			
			stmt = conn.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        rset = stmt.executeQuery();
	        
	        while(rset.next()) {
	        	
	        	PatientRegData mm =  getPatientRegDataFromRow(rset);
	        	if (mm == null) {continue;}
	
	        	mml.add(mm);
	        }
		}
	    finally {
	    	try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBErrorException("PatientRegDataDB.getPatientRegDataHelp(): Error closing connection: "+e.getMessage());
			}
	    }
	
		return mml;
	}
	
	public static PatientRegData getPatientRegDataFromRow (ResultSet rset) throws SQLException {
		
		int iv;
		int pid = rset.getInt("PID"); 
		if (pid == 0) {return null;}
		
		long uid = rset.getInt("upid");
		if (uid == 0) {return null;}
		
		PatientRegData mm = new PatientRegData();
		mm =  (PatientRegData) UserProfileDB.getUserProfileFromRset(rset);
		
		mm.PID(pid);
		iv = rset.getInt("PIsHead");
		mm.PIsHead((iv==0)?-1:iv);
		iv = rset.getInt("PHeadID");
		mm.PHeadID((iv==0)?-1:iv);
		mm.PAllergy(rset.getString("PAllergy"));
		mm.PPastDisease(rset.getString("PPastDisease"));
		
		return mm;
	}
	
	public static void insert(PatientRegData mm, VTransaction trx) throws DBErrorException {
		
		PreparedStatement  stmt = null;
	    Connection conn = null;
	    
	    String fl = "";
	    String vl = "";
	    String sv = "";
	    int iv;
	    // , , , , ,    
	    
	    iv =  mm.PIsHead(); if (iv > 0) {fl += " PIsHead, "; vl += " "+Integer.toString(iv)+" , ";}
	    iv =  mm.PHeadID(); if (iv > 0) {fl += " PHeadID, "; vl += " "+Integer.toString(iv)+" , ";}
	    sv = mm.PAllergy();
	    if (sv!=null) {fl += " PAllergy, "; vl += " \""+sv+"\" ,  ";}
	    sv = mm.PPastDisease();
	    if (sv!=null) {fl += " PPastDisease, "; vl += " \""+sv+"\" ,  ";}
	    fl += " PUID ";
	    vl += " "+Integer.toString(mm.PUID())+"  ";
	    	    
	    String q = "  INSERT INTO PatientRegData ( "+fl+" ) VALUES ( "+vl+" ); ";
	    
	    try {
			
			UserProfileDB.insertUserProfile(mm, trx);
	
		    //conn = DBUtils.DBConnection();
		    //conn = DBUtils.DBConnection();
		    conn = (trx == null)?DBUtils.DBConnection():trx.conn();
	
			if (conn == null){
				throw new DBErrorException("MMProject.insert: Error getting connection");
			}
			
			System.out.println("MMProjectDB.insert Query: "+q);
			
			stmt = conn.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
	        stmt.executeUpdate();	
		} 
	    catch (SQLException | NamingException e1) {
			// TODO Auto-generated catch block
			throw new DBErrorException("MMProject.insert: DBDatabase Error: "+e1.getMessage());
		}
	    finally {
	    	try {
				//conn.close();
				if(trx == null) {conn.close();};
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBErrorException("DevDocDB.getCityList(): Error closing connection: "+e.getMessage());
			}
	    }
	}
	
	public static void update(PatientRegData mm, VTransaction trx) throws DBErrorException {
		
		PreparedStatement  stmt = null;
	    Connection conn = null;
	    
	    int iv;
	    String sv;
	    
	    String stv = "";
	    //, , , , , , , , 
	    iv = mm.PIsHead();
	    stv += (iv < 0)?"":(((stv.length() > 0)?" , ":"")+" PIsHead = "+Integer.toString(iv)+" ");
	    iv = mm.PIsHead();
	    stv += (iv < 0)?"":(((stv.length() > 0)?" , ":"")+" PIsHead = "+Integer.toString(iv)+" ");
	    sv = mm.PAllergy();
	    stv += (sv == null)?"":(((stv.length() > 0)?" , ":"")+" PAllergy = \""+sv+"\" ");
	    sv = mm.PPastDisease();
	    stv += (sv == null)?"":(((stv.length() > 0)?" , ":"")+" PPastDisease = \""+sv+"\" ");
	    
	    if (stv.length() == 0)  return;
	    
	    String q = " UPDATE  PatientRegData SET "+stv+" WHERE PID = "+Integer.toString(mm.PID()) ;
	    
	
	    try {
	
			UserProfileDB.Update(mm, trx);

		    //conn = DBUtils.DBConnection();
		    conn = (trx == null)?DBUtils.DBConnection():trx.conn();
	
			if (conn == null){
				throw new DBErrorException("PatientRegData.update: Error getting connection");
			}
			
			System.out.println("PatientRegDataDB.update Query: "+q);
			
			stmt = conn.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
	        stmt.executeUpdate();	        
		} 
	    catch (SQLException | NamingException e1) {
			// TODO Auto-generated catch block
			throw new DBErrorException("PatientRegData.update: DBDatabase Error: "+e1.getMessage());
		}
	    finally {
	    	try {
				//conn.close();
	
			    if(trx == null) {conn.close();};
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBErrorException("PatientRegData.update: Error closing connection: "+e.getMessage());
			}
	    }
	}
	
	public static void delete(PatientRegData mm, VTransaction trx) throws DBErrorException  {
		
		PreparedStatement  stmt = null;
	    Connection conn = null;
	    
	    String q = " DELETE FROM  PatientRegData WHERE PID = "+Integer.toString(mm.PID()) ;
	    
	
	    try {
	
		    //conn = DBUtils.DBConnection();
		    conn = (trx == null)?DBUtils.DBConnection():trx.conn();
	
			if (conn == null){
				throw new DBErrorException("PatientRegData.delete: Error getting connection");
			}
			
			System.out.println("PatientRegDataDB.delete Query: "+q);
			
			stmt = conn.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
	        stmt.executeUpdate();	
	        
			List<PatientRegData> mml = getPatientRegData(mm);
			
			UserProfile up = mml.get(0);
			
			UserProfileDB.delete(up, trx);	        
		} 
	    catch (SQLException | NamingException e1) {
			// TODO Auto-generated catch block
			throw new DBErrorException("PatientRegData.delete: DBDatabase Error: "+e1.getMessage());
		}
	    finally {
	    	try {
				//conn.close();
	
			    if(trx == null) {conn.close();};
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBErrorException("PatientRegData.delete: Error closing connection: "+e.getMessage());
			}
	    }
	}
}
