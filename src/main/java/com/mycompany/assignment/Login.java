/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment;

/**
 *
 * @author tumithobejane
 */
public class Login {
    
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String cellnumber;
    
    public boolean checkUserName(String _username){ 
        boolean isUsernameValid;
        
       if(_username !=null && _username.length()<=5 && _username.contains("_")){
           isUsernameValid = true;
        } else {
            isUsernameValid = false;
        }
    
       return isUsernameValid;
    }
    
    

    
}
