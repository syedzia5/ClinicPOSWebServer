package com.clinic.utils;

public class VCounter {

	private static int cntr = 1;
	public VCounter() {
		// TODO Auto-generated constructor stub
	}

	public static int cntr() {
		return cntr;
	}
	public static void inccntr() {
		cntr++;
	}
}
