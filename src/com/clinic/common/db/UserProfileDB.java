package com.clinic.common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.NamingException;

import com.clinic.common.datarec.*;
import com.clinic.common.exception.DBErrorException;
import com.clinic.common.exception.NullParamException;
//import com.clinic.developer.datarec.DeveloperRec;
//import com.clinic.developer.db.DevDocDB;

public class UserProfileDB {

	public UserProfileDB() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<UserProfile> getAllActiveCIMCUserProfiles() throws NullParamException, NamingException, SQLException, DBErrorException {
		String q = "select u.*, s.*, c.*, r.*, sm.* from userprofile u, statelist s, city c, userrole r, sexmaster sm  where u.UPSex = sm.SMID and u.isActive = 1 and u.city = c.cid and u.state = s.stlid and u.roleid = r.urid and u.roleid in (6,7) ; ";
		
		//q += ((role > 0)?" and u.roleid = "+Integer.toString(role)+" ":"");
		
		return getUserProfileHelp(q);
	}
	
	public static List<UserProfile> getActiveUserProfiles(int role) throws NullParamException, NamingException, SQLException, DBErrorException {
		String q = "select u.*, s.*, c.*, r.* from userprofile u, statelist s, city c, userrole r, sexmaster sm   where u.UPSex = sm.SMID and u.isActive = 1 and u.city = c.cid and u.state = s.stlid and u.roleid = r.urid ";
		
		q += ((role > 0)?" and u.roleid = "+Integer.toString(role)+" ":"");
		
		return getUserProfileHelp(q);
	}
	
	public static List<UserProfile> getUserProfile(UserProfile upfilter) throws NullParamException, NamingException, SQLException, DBErrorException{
		
		if(upfilter == null) {
			throw new NullParamException("Null UserProfile passed to UserProfileDB.getUserProfile");
		}
		String remq = "select u.*, s.*, c.*, r.* from userprofile u, statelist s, city c, userrole r where u.city = c.cid and u.state = s.stlid and u.roleid = r.urid ";
		
		if (upfilter.uid() > 0) {
			remq += " and u.upid = "+upfilter.uid()+ " ";
		}
		if (upfilter.role() == 0) {
			remq += " and u.roleid not in ( "+Integer.toString(ClinicConstants.UserRole_admin)+" , "+Integer.toString(ClinicConstants.UserRole_investor)+" , "+Integer.toString(ClinicConstants.UserRole_CIMCAdmin)+" ) "; // This will be the case only in delete user
		}
		else if (upfilter.role() > 0) {
			remq += " and u.roleid = "+Integer.toString(upfilter.role())+ " ";
		}
		if (upfilter.approle() > 0) {
			remq += " and u.approle = "+Integer.toString(upfilter.approle())+ " ";
		}
		if (upfilter.UPSex() > 0) {
			remq += " and u.UPSex = "+Integer.toString(upfilter.UPSex())+ " ";
		}
		if (upfilter.rolename() != null) {
			remq += " and r.urname = '"+upfilter.rolename()+"' ";
		}
		if (upfilter.userid() != null) {
			remq += " and u.user_id = '"+upfilter.userid()+"' ";
		}
		if (upfilter.password() != null) {
			remq += " and u.passwd = '"+upfilter.password()+"' ";
		}
		if (upfilter.name() != null) {
			remq += " and u.UserName = '"+upfilter.name()+"' ";
		}
		if (upfilter.Address1() != null) {
			remq += " and u.address1 = '"+upfilter.Address1()+"' ";
		}
		if (upfilter.Email() != null) {
			remq += " and u.email = '"+upfilter.Email()+"' ";
		}
		if (upfilter.Address2() != null) {
			remq += " and u.address2 = '"+upfilter.Address2()+"' ";
		}
		if (upfilter.Address3() != null) {
			remq += " and u.address3 = '"+upfilter.Address3()+"' ";
		}
		if (upfilter.Zipcode() != null) {
			remq += " and u.zipcode = '"+upfilter.Zipcode()+"' ";
		}
		if (upfilter.City() > 0) {
			remq += " and u.city = "+Integer.toString(upfilter.City())+ " ";
		}
		if (upfilter.cityName() != null) {
			remq += " and c.DistName = '"+upfilter.cityName()+"' ";
		}
		if (upfilter.State() > 0) {
			remq += " and u.state = "+Integer.toString(upfilter.State())+ " ";
		}
		if (upfilter.stateName() != null) {
			remq += " and s.StateName = '"+upfilter.stateName()+"' ";
		}
		if (upfilter.isActive() > 0) {
			remq += " and u.isActive = '"+upfilter.isActive()+"' ";
		}
		/*
		if (upfilter.Country() > 0) {
			remq += " and "+Integer.toString(upfilter.Country())+ " ";
		}
		if (upfilter.countryName() != null) {
			remq += " and "+upfilter.countryName()+" ";
		}
		*/
		remq += ";";
		
		System.out.println(remq);

		return getUserProfileHelp(remq);
	}
	
	public static List<UserProfile> getUserProfileHelp(String q) throws NullParamException, NamingException, SQLException, DBErrorException{
		
		PreparedStatement  stmt = null;
	    ResultSet rset = null;
	    Connection conn = null;
	    List<UserProfile> upl = new ArrayList<UserProfile>();
	    
       	try {

    	    conn = DBUtils.DBConnection();
			
			stmt = conn.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);  
	    	
	        rset = stmt.executeQuery();
	        
			while(rset.next()) {
				UserProfile up = getUserProfileFromRset(rset);
				upl.add(up);
				int len = upl.size();
				if(len%1000 == 0) {
					System.out.println("Num of users retrieved: "+Integer.toString(len)); 
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBErrorException("UserProfileDB.getUserProfile: Error closing connection: "+e.getMessage());
		}
        finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBErrorException("UserProfileDB.getUserProfile: Error closing connection: "+e.getMessage());
			}
        }
       
        //conn.close();
       	System.out.println("Return with "+Integer.toString(upl.size())+" user profile records");
       	
        return upl;
	}
	
	public static UserProfile getUserProfileFromRset(ResultSet rset) throws SQLException {
		
		UserProfile up = new UserProfile();

		up.uid(rset.getLong("upid"));
		up.role(rset.getInt("roleid"));
		up.approle(rset.getInt("approle"));
		up.rolename(rset.getString("urname"));
		up.userid(rset.getString("user_id"));
		up.password(rset.getString("passwd"));
		up.name(rset.getString("UserName"));
		up.Email(rset.getString("email"));
		up.Address1(rset.getString("address1"));
		up.Address2(rset.getString("address2"));
		up.Address3(rset.getString("address3"));
		up.Zipcode(rset.getString("zipcode"));
		up.City(rset.getInt("city"));
		up.cityName(rset.getString("DistName"));
		up.State(rset.getInt("stlid"));
		up.stateName(rset.getString("StateName"));
		up.contactNumber(rset.getString("ContactNum"));
		up.reg_num(rset.getString("reg_num"));
		up.pan_num(rset.getString("pan_num"));
		up.proficonfile(rset.getString("proficonfile"));
		up.isActive(rset.getInt("isActive"));
		
		up.UPSex(rset.getInt("UPSex"));
		up.UPSexName(rset.getString("UPSexName"));
		up.UPDoB(rset.getString("UPDoB"));
		
		//up.Country(rset.getInt(""));
		//up.countryName(rset.getString(""));
		
		return up;
	}
	public static void insertUserProfile(UserProfile up) throws SQLException, NamingException, DBErrorException {

		insertUserProfile(up, null);
	}
	
	public static void insertUserProfile(UserProfile mm, VTransaction trx) throws SQLException, NamingException, DBErrorException {

		PreparedStatement  stmt = null;
	    Connection conn = null;

	    String fl = "";
	    String vl = "";
	    String sv = "";
	    
	    // , , , , , , , , , , approle, , , country, , pan_num, reg_num, , isActive, PrjHead, , , UPRegID
	    
	    fl += " upid, ";
	    vl += " "+mm.uid()+" , ";
	    fl += " UserName, ";
	    vl += " "+mm.name()+" , ";
	    sv = mm.userid();
	    if (sv!=null) {fl += " user_id, "; vl += " \""+sv+"\" ,  ";}
	    sv = mm.password();
	    if (sv!=null) {fl += " passwd, "; vl += " \""+sv+"\" ,  ";}
	    fl += " address1, ";
	    vl += " \""+mm.Address1()+"\" , ";
	    sv = mm.Email();
	    if (sv!=null) {fl += " email, "; vl += " \""+sv+"\" ,  ";}
	    sv = mm.Address2();
	    if (sv!=null) {fl += " address2, "; vl += " \""+sv+"\" ,  ";}
	    sv = mm.Address3();
	    if (sv!=null) {fl += " address3, "; vl += " \""+sv+"\" ,  ";}
	    fl += " zipcode, ";
	    vl += " \""+mm.Zipcode()+"\" , ";
	    fl += " roleid, ";
	    vl += " "+Integer.toString(mm.role())+" , ";
	    fl += " city, ";
	    vl += " "+Integer.toString(mm.City())+" , ";
	    fl += " state, ";
	    vl += " "+Integer.toString(mm.State())+" , ";
	    fl += " ContactNum, ";
	    vl += " \""+mm.contactNumber()+"\" , ";
	    sv = mm.proficonfile();
	    if (sv!=null) {fl += " proficonfile, "; vl += " \""+sv+"\" ,  ";}
	    fl += " UPSex, ";
	    vl += " "+Integer.toString(mm.UPSex())+" , ";
	    fl += " UPDoB ";
	    vl += " \""+mm.UPDoB()+"\"  ";
	    	    
	    String q = "  INSERT INTO PolicyConsidVal ( "+fl+" ) VALUES ( "+vl+" ); ";
		
		System.out.println("Insert user profile query: "+q);

		System.out.println(q);

		try {
			
		    conn = (trx == null)?DBUtils.DBConnection():trx.conn();
		
			stmt = conn.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);  
	    	
	        stmt.executeUpdate();
		}
        finally {
        	try {
				//conn.close();
    		    if(trx == null) {conn.close();};
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBErrorException("UserProfileDB.insertUserProfileHelp(): Error closing connection: "+e.getMessage());
			}
        }
	}
	
	
	public static void UpdateUserPassword(UserProfile up, VTransaction trx) throws DBErrorException {

	    Connection conn = null;
	    PreparedStatement  stmt = null;

		try {
		    
		    //conn = DBUtils.DBConnection();
		    conn = (trx == null)?DBUtils.DBConnection():trx.conn();
			if (conn == null){
				throw new DBErrorException("UserProfileDB.UpdateUserPassword(): Error getting connection");
			}
			
			String st = null;
			String q = " update userprofile  set passwd = \""+up.password()+"\" where user_id = \""+up.userid()+"\"";;

			System.out.println("update user password query: "+q);
	
			System.out.println(q);
			
			stmt = conn.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);  
	    	
	        stmt.executeUpdate();
	        
	        
		} catch (SQLException|NamingException e) {
			// TODO Auto-generated catch block
			throw new DBErrorException("UserProfileDB.UpdateUserPassword(): "+e.getMessage());
		}
        finally {
        	try {
				//conn.close();

			    if(trx == null) {conn.close();};
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBErrorException("UserProfileDB.UpdateUserPassword(): Error closing connection: "+e.getMessage());
			}
        }
	}
	
	public static void Update(UserProfile up, VTransaction trx) throws DBErrorException {
		
		String [] sl = null;
		Update(up, sl, trx);
	}
	
	public static void Update(UserProfile up, String[] uidlst, VTransaction trx) throws DBErrorException {

		String uids;
		//System.out.println("uidlst: "+uidlst.toString());
		
		uids =  (uidlst == null)?"":String.join(",", uidlst);
		
		System.out.println("uids: "+uids);
		
	    Connection conn = null;
	    PreparedStatement  stmt = null;

		try {

			String st = null;
			String q = " update userprofile set ";;

			String sv = "";
			String ts = up.Address1(); sv += ((ts == null)?"":((sv.isEmpty())?"":" , ")+" address1 = \""+ts+"\" ");
			ts = up.name(); sv += ((ts == null)?"":((sv.isEmpty())?"":" , ")+" UserName = \""+ts+"\" ");
			ts = up.Email(); sv += ((ts == null)?"":((sv.isEmpty())?"":" , ")+" email = \""+ts+"\" ");
			ts = up.Email(); sv += ((ts == null)?"":((sv.isEmpty())?"":" , ")+" user_id = \""+ts+"\" ");
			ts = up.Address2(); sv += ((ts == null)?"":((sv.isEmpty())?"":" , ")+" address2 = \""+ts+"\" ");
			ts = up.Address3(); sv += ((ts == null)?"":((sv.isEmpty())?"":" , ")+" address3 = \""+ts+"\" ");
			ts = up.Zipcode(); sv += ((ts == null)?"":((sv.isEmpty())?"":" , ")+" zipcode = \""+ts+"\" ");
			ts = up.contactNumber(); sv += ((ts == null)?"":((sv.isEmpty())?"":" , ")+" ContactNum = \""+ts+"\" ");
			ts = up.proficonfile(); sv += ((ts == null)?"":((sv.isEmpty())?"":" , ")+" proficonfile = \""+ts+"\" ");
			int iv = up.City(); sv += ((iv < 0)?"":((sv.isEmpty())?"":" , ")+" city = "+Integer.toString(iv)+" ");
			iv = up.State(); sv += ((iv < 0)?"":((sv.isEmpty())?"":" , ")+" state = "+Integer.toString(iv)+" ");
			iv = up.isActive(); sv += ((iv < 0)?"":((sv.isEmpty())?"":" , ")+" isActive = "+Integer.toString(iv)+" ");
			iv = up.UPSex(); sv += ((iv < 0)?"":((sv.isEmpty())?"":" , ")+" UPSex = "+Integer.toString(iv)+" ");
			ts = up.UPDoB(); sv += ((ts == null)?"":((sv.isEmpty())?"":" , ")+" UPDoB = \""+ts+"\" ");

			if (sv.isEmpty()) {
				return;
			}
			
		    //conn = DBUtils.DBConnection();
		    conn = (trx == null)?DBUtils.DBConnection():trx.conn();
			if (conn == null){
				throw new DBErrorException("UserProfileDB.UpdateUserPassword(): Error getting connection");
			}
			
			q += sv+" where upid "+((uids.isEmpty())?" = "+up.uid():" in ( "+uids+" ) ");
			
			System.out.println("update user profile query: "+q);
	
			System.out.println(q);
			
			stmt = conn.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);  
	    	
	        stmt.executeUpdate();
		} catch (SQLException|NamingException e) {
			// TODO Auto-generated catch block
			throw new DBErrorException("UserProfileDB.UpdateUserPassword(): "+e.getMessage());
		}
        finally {
        	try {
				//conn.close();

			    if(trx == null) {conn.close();};
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBErrorException("UserProfileDB.UpdateUserPassword(): Error closing connection: "+e.getMessage());
			}
        }
	}

	public static void delete(UserProfile nup, VTransaction trx) throws DBErrorException {
		// TODO Auto-generated method stub
		
		
		PreparedStatement  stmt = null;
	    Connection conn = null;
        try {
      
		    conn = (trx == null)?DBUtils.DBConnection():trx.conn();
			
			if (conn == null){
				throw new DBErrorException("Error getting connection");
			}
		
			String q = " delete from userprofile WHERE upid = \""+nup.uid()+"\" ; ";
						
			System.out.println("UserProfileDB delete Query: "+q);
			
			stmt = conn.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    	
	        stmt.executeUpdate();
		} 
        catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw new DBErrorException("Database Error: "+e1.getMessage());
		}
        catch (NamingException e3) {
			// TODO Auto-generated catch block
			throw new DBErrorException("Database Error: "+e3.getMessage());
		}
        finally {
        	try {
				//conn.close();

			    if(trx == null) {conn.close();};
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBErrorException("Error closing connection: "+e.getMessage());
			}
        }		
	}
	
}
