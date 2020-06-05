package com.clinic.utils;

/*
JSPWiki - a JSP-based WikiWiki clone.

Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.    
*/

import java.security.SecureRandom;
import java.util.Random;

public class StringUtils
{

	private static final Random RANDOM = new SecureRandom();
	
	/** Length of password. @see #generateRandomPassword() */
	public static final int PASSWORD_LENGTH = 11;
	public static final int HASH_STRINIG_LENGTH = 30;
	
	public StringUtils() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	* Generate a random String suitable for use as a temporary password.
	*
	* @return String suitable for use as a temporary password
	* @since 2.4
	*/
	public static String generateRandomPassword()
	{
		return generateRandomString(PASSWORD_LENGTH);
	}
	
	/**
	* Generate a random String suitable for use fro one document download.
	*
	* @return String suitable for use as a temporary password
	* @since 2.4
	*/
	public static String generateRandomLinkString()
	{
		return generateRandomString(HASH_STRINIG_LENGTH);
	}

	private static String generateRandomString(int len) {
		
		// Pick from some letters that won't be easily mistaken for each
		// other. So, for example, omit o O and 0, 1 l and L.
		
		String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789^@";
		
		String pw = "";
		for (int i=0; i<len; i++)
		{
			int index = (int)(RANDOM.nextDouble()*letters.length());
			pw += letters.substring(index, index+1);
		}
		return pw;
	}

	public static String generateRandomSubFileName() {
		
		// Pick from some letters that won't be easily mistaken for each
		// other. So, for example, omit o O and 0, 1 l and L.
		
		String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789_-";
		
		String pw = "";
		for (int i=0; i<12; i++)
		{
			int index = (int)(RANDOM.nextDouble()*letters.length());
			pw += letters.substring(index, index+1);
		}
		return pw;
	}
}
