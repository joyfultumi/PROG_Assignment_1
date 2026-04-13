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
    
    
    public boolean checkPasswordComplexity(String _password){
        
        boolean containsCapitalLetter = false;
        boolean containsNumber = false;
        boolean containsSpecialCharacter = false;
        
        for(int i = 0; i < _password.length(); i++){
          char character = _password.charAt(i);
          
          if(Character.isDigit(character)){
            containsNumber = true;
          }
          else if(Character.isUpperCase(character)){
            containsCapitalLetter = true;
          } else if (!Character.isLetterOrDigit(character)){
              containsSpecialCharacter = true;
          }
          
        }
        
        return containsCapitalLetter && containsNumber && containsSpecialCharacter;
       
    }
    
    public boolean checkCellPhoneNumber(String  cell) {
        
        if(cell == null){
            return false;
        }
        
        String pattern = "^\\+27[0-9]{9}$";
        
        if(cell.matches(pattern)){
            return true;
        } else {
            return false;
        }
        
    }
    
    public String registerUser(String _firstname, String _lastname, String _username, String _password, String _cell){
        
        boolean isUsernameValid = checkUserName(_username);
        boolean isPasswordValid = checkPasswordComplexity(_password);
        boolean isCellValid = checkCellPhoneNumber(_cell);
        
        if(isUsernameValid == false ){
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        if(isPasswordValid == false ){
            return  "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        if(isCellValid == false){
            return  "Cell phone number incorrectly formatted or does not contain international code; please correct the number and try again.";
        }
        
        this.username = _username;
        this.password = _password;
        this.cellnumber = _cell;
        this.firstname = _firstname;
        this.lastname = _lastname;
        
        return "User has been registered successfully.";
    }
    
    public boolean loginUser(String _username, String _password){
        
        if(username.equals(_username) && password.equals(_password)){
            return true;
        } else {
            return false;
        }
          
    }
    
    public String returnLoginStatus(boolean loginSuccess){
        if(loginSuccess == true) {
            return "Welcome "+ firstname + ", "+ lastname + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
           
    }
    
}
