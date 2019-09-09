package com.progress;

public class Minimun {
	
	public static void main(String arg[]) {
		String ca = "AAGAGAZZZS";
		StringBuilder v = new StringBuilder();
		
		for (int x = 0; x < ca.length();x++) {
			if (ca.charAt(x) == 'Z') {
				v.append((char) (ca.charAt(x)-23));
			}else if(ca.charAt(x) == '#')
				v.append(" ");
			else
				v.append((char) (ca.charAt(x) + 3));
		}
		
		System.out.println(v);
	}
}
