package com.clinic.common.datarec;

public class UserProfile {

	long uid;
	
	private int role;
	private String rolename;
	
	private String userid;
	private String password;
	private String name;
	private String email;
	private String address1;
	private String address2;
	private String address3;
	private String zipcode;
	
	private int UPSex;
	private String UPSexName;
	
	private int city;
	private String cityName;
	
	private String UPDoB;
	private String UPRegID;
	
	private int state;
	private String stateName;
	
	private int country;
	private String countryName;
	
	private String contactNumber;
	
	private String pan_num;
	
	private String reg_num;
	
	private int approle;
	
	private String proficonfile;
	
	private int isActive;
	
	public UserProfile() {
		// TODO Auto-generated constructor stubuid
		uid = -1;
		country = 1;
		countryName = "India";
		role	=	-1	;
		UPSex	=	-1	;
		rolename	=	null	;
		userid	=	null	;
		name	=	null	;
		email	=	null	;
		address1	=	null	;
		address2	=	null	;
		address3	=	null	;
		zipcode	=	null	;
		city	=	-1	;
		cityName	=	null	;
		UPRegID	=	null	;
		UPDoB	=	null	;
		UPSexName	=	null	;
		state	=	-1	;
		stateName	=	null	;
		pan_num = null;
		approle = -1;
		proficonfile = null;
		isActive =  -1;
	}

	public void role (int irole) {
		role = irole;
	}
	public int role() {
		return role;
	}

	public void UPSex (int iUPSex) {
		UPSex = iUPSex;
	}
	public int UPSex() {
		return UPSex;
	}

	public void approle (int iapprole) {
		approle = iapprole;
	}
	public int approle() {
		return approle;
	}
	
	public void userid(String iuserid) {
		userid = iuserid;
	}
	public String userid() {
		return userid;
	}
	
	public void name (String iname) {
		name = iname;
	}
	public String name() {
		return name;
	}

	public String Email(){
		return email;
	}

	public void Email(String email){
		this.email=email;
	}

	public String Address1(){
		return address1;
	}

	public void Address1(String address1){
		this.address1=address1;
	}

	public String Address2(){
		return address2;
	}

	public void Address2(String address2){
		this.address2=address2;
	}

	public String Address3(){
		return address3;
	}

	public void Address3(String address3){
		this.address3=address3;
	}

	public String Zipcode(){
		return zipcode;
	}

	public void Zipcode(String zipcode){
		this.zipcode=zipcode;
	}


	public int City(){
		return city;
	}

	public void City(int city){
		this.city=city;
	}

	public int State(){
		return state;
	}

	public void State(int state){
		this.state=state;
	}

	public int Country(){
		return country;
	}

	public void Country(int country){
		this.country=country;
	}
	
	public String rolename() {
		return rolename;
	}
	public void rolename(String irolename) {
		rolename = irolename;
	}
	
	public String cityName() {
		return cityName;
	}
	public void cityName(String icityName) {
		cityName = icityName;
	}
	
	public String UPRegID() {
		return UPRegID;
	}
	public void UPRegID(String iUPRegID) {
		UPRegID = iUPRegID;
	}
	
	public String UPDoB() {
		return UPDoB;
	}
	public void UPDoB(String iUPDoB) {
		UPDoB = iUPDoB;
	}
	
	public String UPSexName() {
		return UPSexName;
	}
	public void UPSexName(String iUPSexName) {
		UPSexName = iUPSexName;
	}
	
	public String stateName() {
		return stateName;
	}
	public void stateName(String istateName) {
		stateName = istateName;
	}
	
	public String countryName() {
		return countryName;
	}
	public void countryName(String icountryName) {
		countryName = icountryName;
	}
	
	public String password() {
		return password;
	}
	public void password(String ipassword) {
		password = ipassword;
	}
	
	public long uid() {
		return uid;
	}
	public void uid(long iuid) {
		uid = iuid;
	}
	
	public String contactNumber() {
		return contactNumber;
	}
	public void contactNumber(String icontactNumber) {
		contactNumber = icontactNumber;
	}
	
	public String pan_num() {
		return pan_num;
	}
	public void pan_num(String ipan_num) {
		pan_num = ipan_num;
	}
	
	public String reg_num() {
		return reg_num;
	}
	public void reg_num(String ireg_num) {
		reg_num = ireg_num;
	}
	
	public String proficonfile() {
		return proficonfile;
	}
	public void proficonfile(String iproficonfile) {
		proficonfile = iproficonfile;
	}

	public int isActive(){
		return isActive;
	}

	public void isActive(int isActive){
		this.isActive=isActive;
	}
}
