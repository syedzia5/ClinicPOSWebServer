package com.clinic.patient.datarec;

import com.clinic.common.datarec.UserProfile;

public final class PatientRegData extends UserProfile{

	int PID; 
	int PUID;
	int PIsHead; 
	int PHeadID; 
	String PAllergy; 
	String PPastDisease;
	//UserProfile PUserProfile;
	
	public PatientRegData() {
		// TODO Auto-generated constructor stub
		PID = -1;  
		PUID = -1;
		PIsHead = -1; 
		PHeadID = -1; 
		PAllergy = null; 
		PPastDisease = null;
		//PUserProfile = null;
	}

	public void PID (int iPID) {
		PID = iPID;
	}
	public int PID() {
		return PID;
	}


	public void PUID (int iPUID) {
		PUID = iPUID;
	}
	public int PUID() {
		return PUID;
	}

	public void PIsHead (int iPIsHead) {
		PIsHead = iPIsHead;
	}
	public int PIsHead() {
		return PIsHead;
	}

	public void PHeadID (int iPHeadID) {
		PHeadID = iPHeadID;
	}
	public int PHeadID() {
		return PHeadID;
	}

	public void PAllergy (String iPAllergy) {
		PAllergy = iPAllergy;
	}
	public String PAllergy() {
		return PAllergy;
	}

	public void PPastDisease (String iPPastDisease) {
		PPastDisease = iPPastDisease;
	}
	public String PPastDisease() {
		return PPastDisease;
	}

	/*
	public void PUserProfile (UserProfile iPUserProfile) {
		PUserProfile = iPUserProfile;
	}
	public UserProfile PUserProfile() {
		return PUserProfile;
	}
	*/
}
