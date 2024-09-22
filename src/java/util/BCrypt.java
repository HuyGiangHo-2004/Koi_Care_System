/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Khanh
 */
public class BCrypt {
    
    private static final int WORK_FACTOR = 12;
    
    public static boolean checkpw(String hash, String password) throws IllegalArgumentException{
        if(hash == null || password == null){
            throw new IllegalArgumentException("Both hash and password must be provided");
        }
        return BCrypt.checkpw(password, hash);
    }
    


    public static String hashpw(String password) throws IllegalArgumentException{
        if(password == null){
            throw new IllegalArgumentException("Password cannot be null");
        }
        return BCrypt.hashpw(password, BCrypt.gensalt(WORK_FACTOR));
    }
}
