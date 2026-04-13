/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.assignment;

import java.util.Scanner;
import java.lang.Character;

/**
 *
 * @author tumithobejane
 */
public class Assignment {
   
   

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        
        boolean isAppRunning = true;
        boolean isUserRegistered = false;
        
        
        while(isAppRunning){
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            
            String option = input.nextLine();
            
            switch(option){
                
                case "1":
                    if(isUserRegistered){
                      System.out.println("Register a user");
                      break;
                    }
                    System.out.println("\n----- REGISTER ------");
                    System.out.print(" First Name: ");
                    String firstname = input.nextLine();
                    
                    System.out.print(" Last Name: ");
                    String lastname = input.nextLine();
                    
                    System.out.print(" UserName: ");
                    String username = input.nextLine();
                    
                    System.out.print(" Password: ");
                    String password = input.nextLine();
                    
                    System.out.print(" Cell (+27xxxxxxxxx): ");
                    String cell = input.nextLine();
                    
                    String registered = login.registerUser(firstname, lastname, username, password, cell);
                    System.out.println(registered);
                    
                    if(registered.equals("User has been registered successfully")){
                        isUserRegistered = true;
                    }
                    
                    break;
                    
                    
                case "2":
                    
                    if(!isUserRegistered){
                      System.out.println("Register a user");
                      break;
                    }
                    System.out.println("\n----- LOGIN ------");
                    System.out.print(" Username: ");
                    String loginUsername = input.nextLine();
                    
                    System.out.print(" Password: ");
                    String loginPassword = input.nextLine();
                    
                    boolean status = login.loginUser(loginUsername, loginPassword);
                    System.out.println(login.returnLoginStatus(status));
                    
                    break;
                    
                case "3":
                    isAppRunning = false;
                    System.out.println("Thank you!!");
                    break;
                default:
                    System.out.println("Invalid option");
                    
          
            }
        }
        
        input.close();
    }
}
