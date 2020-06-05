package com.clinic.common.datarec;

public class ClinicConstants {
	
	
	public static final int SessionTimeout = 20*60;
	
	public static final String CIMCHost = "https://144.91.89.90:8443/VRTE-CIMC-PROTO-1/";
	public static final String VRTEHost = "https://144.91.89.90:8443/VRTE-CIMC-PROTO-1/";
	public static final int PrjSts_New_Project = 100;
	public static final int PrjSts_Score_Passed = 200;
	public static final int PrjSts_Market_Research_Done = 300;
	public static final int PrjSts_Scheme_Finalized = 400;
	public static final int PrjSts_Project_Rating_Done = 500;
	public static final int PrjSts_Document_Verification_Done = 600;
	public static final int PrjSts_Trasfered_To_PSF = 601;
	public static final int PrjSts_Scheme_Parameter_Finalized = 700;
	public static final int PrjSts_Marketing_Plan_Received = 800;
	public static final int PrjSts_Scheme_Contract_Signed = 900;
	public static final int PrjSts_Scheme_Rating_Done = 1000;
	public static final int PrjSts_Security_Trusty_Approval_Done = 1100;
	public static final int PrjSts_SEBI_Approval_Done = 1200;
	public static final int PrjSts_Demat_Data_Done = 1300;
	public static final int PrjSts_in_Subscription = 1400;
	public static final int PrjSts_Subscription_Done = 1500;
	public static final int PrjSts_Scheme_Allocation_Done = 1600;
	public static final int PrjSts_Project_Listed = 1700;
	public static final int PrjSts_Project_Closed = 1800;
	public static final int PrjSts_Project_Rejected = 1900;
	public static final int PrjSts_Project_Settled = 2000;
	
	// Bank a/c type
	public static final int BnkAcntType_VRTE_A_c = 1;
	public static final int BnkAcntType_Project_Escrow_A_c = 2;
	public static final int BnkAcntType_Developer = 3;
	public static final int BnkAcntType_Intermediary = 4;
	public static final int BnkAcntType_Investor = 5;
	public static final int BnkAcntType_CIMC_A_c = 6;
	public static final int BnkAcntType_Due_Diligence_Vendor = 7;

	// Developer category
	public static final int DevCat_Premium = 1;
	public static final int DevCat_Gold = 2;
	public static final int DevCat_Standard = 3;
	public static final int DevCat_Average = 4;
	public static final int DevCat_Poor = 5;
	
	//User Roles
	public static final int UserRole_admin = 1;
	public static final int UserRole_staff = 2;
	public static final int UserRole_developer = 3;
	public static final int UserRole_intermediary = 4;
	public static final int UserRole_investor = 5;
	public static final int UserRole_CIMCAdmin = 6;
	public static final int UserRole_CIMCStaff = 7;
	public static final int UserRole_IntmAgnt = 8;
	public static final int UserRole_DDVend = 9;
	
	// User App Role
	public static final int UserAppRole_VRTE = 1;
	public static final int UserAppRole_CIMC = 2;
	
	public static final int PUFV_Verified = 1;
	public static final int PUFV_NotVerified = 0;
	
	public static final int PUFV_Approved = 1;
	public static final int PUFV_NotApproved = 0;
	/*
	0 - NotCompleted
	1 - InVerification
	2 - Completed
	*/
	public static final int PrjStsTbl_NotCompleted = 0;
	public static final int PrjStsTbl_InVerification = 1;
	public static final int PrjStsTbl_Completed = 2;
	
	public static final int TrxnType_NewProjectApplicationFees = 1;
	public static final int TrxnType_NewProjectApplicationFeesRefund = 2;
	public static final int TrxnType_NewSubscription = 3;
	public static final int TrxnType_SubscriptionRefund = 4;
	public static final int TrxnType_IntermediaryCommision = 5;
	public static final int TrxnType_ApartmentSale = 6;
	public static final int TrxnType_ProjectStagePayment = 7;
	public static final int TrxnType_SchemeNotFullySubscribed_Refund = 8;
	public static final int TrxnType_SchemeSettlement_Investor = 9;
	public static final int TrxnType_SchemeSettlement_Developer = 10;
	public static final int TrxnType_CIMCCommission = 11;
	public static final int TrxnType_DeveloperSalesRedemption = 12;

	public static final int PrjPulledNotFromVRTE = 0;
	public static final int PrjPulledFromVRTE = 1;

	public static final int DevPulledNotFromVRTE = 0;
	public static final int DevPulledFromVRTE = 1;
	
	public static final int DocType_Market_Score = 1; //Market score data
	public static final int DocType_Project_Rating = 2; //Project Rating
	public static final int DocType_Scheme_Rating = 3; //Scheme Rating
	public static final int DocType_SEBI_Approval = 4; //SEBI Approval  Report
	public static final int DocType_Security_Trust_Approval = 5; //Security Trust Approval Report
	public static final int DocType_Contract = 6; //The contract with the developer
	public static final int DocType_Developer_Documents = 7; //The contract with the developer
	public static final int DocType_Project_Documents = 8; //The contract with the developer

	public static final int DocStatus_Uploaded = 1; //The document has been just uploaded
	public static final int DocStatus_Accepted = 2; //The document has been accepted
	public static final int DocStatus_Rejected = 3; //The document has been rejected
	public static final int DocStatus_SentBack = 4; //The document has been sent back to the vendor for review

	public static final int DocSubmitType_Pre_Feasibility = 1; //The document has been just uploaded
	public static final int DocSubmitType_Stage_2 = 2; //The document has been accepted
	public static final int DocSubmitType_Financial = 3; //The document has been rejected
	
	public static final String ErrorResp = "<h2>Your action could not be completed due to internal server error. Please contact administrator</h2>";

	//public static final String FuncMissing = "<h2>You are not authorised to use this app.</h2>";

	public static final int DownloadDueDiligenceDocLink_Expiry_Days = 3;
	
	public static final int DeskTop_LeftMenu = 1;
	public static final int Mobile_LeftMenu = 2;
	
	private ClinicConstants() {
		// TODO Auto-generated constructor stub
	}

	public static boolean bothHostLocal() {
		return CIMCHost.contains("localhost") && VRTEHost.contains("localhost");
	}

	public static boolean vrteHostLocal() {
		return VRTEHost.contains("localhost");
	}

	public static boolean cimcHostLocal() {
		return CIMCHost.contains("localhost");
	}
}
