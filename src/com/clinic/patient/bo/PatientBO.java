package com.clinic.patient.bo;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.clinic.common.exception.DBErrorException;
import com.clinic.patient.datarec.PatientRegData;
import com.clinic.patient.db.PatientRegDataDB;
import com.clinic.common.bo.RequestParams;
import com.clinic.common.datarec.VTransaction;

public class PatientBO {

	public PatientBO() {
		// TODO Auto-generated constructor stub
	}

	public static List<PatientRegData> getPatientRegData (PatientRegData iMM) throws NamingException, SQLException, DBErrorException {
		
		return PatientRegDataDB.getPatientRegData (iMM);
	}
	
	public static void createNewPatient(RequestParams rp) {
		
		
		VTransaction trx = null;
		
		try {
			trx = new VTransaction();
			trx.start();
			
			PatientRegData pd =  new PatientRegData();
			
			pd.uid(System.nanoTime());
			
			trx.commit();
		} catch (SQLException | NamingException e) {
			
			try {
				trx.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
