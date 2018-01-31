/**
 * 
 */
package com.tcm.college.utill;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class PasswordEncoder
{

    public static void main(String[] args)
    {
    	passwordGenerator();
               
        
    }

    public static void passwordGenerator()
    {
        String password = "12345678";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("Encode password : " + passwordEncoder.encode(password));
    }

}
