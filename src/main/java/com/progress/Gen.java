package com.progress;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Gen {
		
	public static void main(String[] args) {
		
		System.out.println(new BCryptPasswordEncoder().encode("456"));		
	}	
}
