/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.util;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author ISSAH OJIVO
 */
public class GenerateSHA56 {
    
    protected static SecureRandom random = new SecureRandom();
    private static final String DATE_FORMATTER = "yyy-MM-dd HH:mm:ss";
    
    public String generateSha256(String text) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash); // Java 8 feature
        
        return encoded;
    }
    
   
        
        public synchronized String generateToken( ) {
                long longToken = Math.abs( random.nextLong() );
                String random = Long.toString( longToken, 16 );
                return ( random );
        }
        
        public String formatDate(LocalDateTime localDateTime){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
            return localDateTime.format(formatter);
            
        }
        
        
        public LocalDateTime convertStringToLocalDate(String localDate){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
            return LocalDateTime.parse(localDate, formatter);
        }

  
    
}
