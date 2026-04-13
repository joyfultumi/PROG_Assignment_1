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
       /* Scanner input = new Scanner(System.in);
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
        
        input.close();*/
       
       // Task 2
       
        Scanner input = new Scanner(System.in);
        Login loginSystem = new Login();

        boolean running = true;
        boolean userRegistered = false;
        boolean loggedIn = false;
        int totalMessagesSent = 0;

        System.out.println("Welcome to the Registration and Login System");

        while (running) {

            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Open QuickChat");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = input.nextLine();

            switch (choice) {

                // ================= REGISTER =================
                case "1":

                    if (userRegistered) {
                        System.out.println("A user is already registered. Only one user allowed.");
                        break;
                    }

                    System.out.println("\n--- Register User ---");

                    System.out.print("Enter first name: ");
                    String firstName = input.nextLine();

                    System.out.print("Enter last name: ");
                    String lastName = input.nextLine();

                    System.out.print("Enter username: ");
                    String username = input.nextLine();

                    System.out.print("Enter password: ");
                    String password = input.nextLine();

                    System.out.print("Enter SA cell number (+27...): ");
                    String cellPhone = input.nextLine();

                    String registerResult = loginSystem.registerUser(
                            firstName, lastName, username, password, cellPhone
                    );

                    System.out.println(registerResult);

                    if (registerResult.equals("User has been registered successfully.")) {
                        userRegistered = true;
                    }

                    break;

                // ================= LOGIN =================
                case "2":

                    if (!userRegistered) {
                        System.out.println("Please register first.");
                        break;
                    }

                    System.out.println("\n--- Login ---");

                    System.out.print("Enter username: ");
                    String loginUsername = input.nextLine();

                    System.out.print("Enter password: ");
                    String loginPassword = input.nextLine();

                    loggedIn = loginSystem.loginUser(loginUsername, loginPassword);

                    System.out.println(loginSystem.returnLoginStatus(loggedIn));

                    break;

                // ================= QUICKCHAT =================
                case "3":

                    if (!loggedIn) {
                        System.out.println("You must log in first before using QuickChat.");
                        break;
                    }

                    boolean quickChatRunning = true;

                    while (quickChatRunning) {

                        System.out.println("\n=== Welcome to QuickChat ===");
                        System.out.println("1) Send Messages");
                        System.out.println("2) Show recently sent messages");
                        System.out.println("3) Quit");
                        System.out.print("Choose an option: ");

                        String quickChoice = input.nextLine();

                        switch (quickChoice) {

                            case "1":

                                System.out.print("How many messages would you like to send? ");
                                int numMessages = Integer.parseInt(input.nextLine());

                                for (int i = 1; i <= numMessages; i++) {

                                    System.out.println("\n--- Message " + i + " ---");

                                    System.out.print("Enter recipient (+27...): ");
                                    String recipient = input.nextLine();

                                    System.out.print("Enter message: ");
                                    String messageText = input.nextLine();

                                    Message message = new Message(i, recipient, messageText);

                                    // Recipient check
                                    String recipientCheck = message.checkRecipientCell(recipient);
                                    System.out.println(recipientCheck);

                                    if (!recipientCheck.equals("Cell phone number successfully captured.")) {
                                        continue;
                                    }

                                    // Message length check
                                    String lengthCheck = message.checkMessageLength();
                                    System.out.println(lengthCheck);

                                    if (!lengthCheck.equals("Message ready to send.")) {
                                        continue;
                                    }

                                    // Options
                                    System.out.println("\nChoose what to do:");
                                    System.out.println("1. Send Message");
                                    System.out.println("2. Discard Message");
                                    System.out.println("3. Store Message");
                                    System.out.print("Option: ");

                                    int option = Integer.parseInt(input.nextLine());

                                    String result = message.sentMessage(option);
                                    System.out.println(result);

                                    if (option == 1) {
                                        totalMessagesSent++;

                                        System.out.println("\n--- Message Details ---");
                                        System.out.println(message.printMessages());

                                    } else if (option == 2) {
                                        System.out.println("Message discarded.");

                                    } else if (option == 3) {
                                       // MessageStore.storeMessage(message);
                                        System.out.println("Message saved to JSON file.");
                                    }
                                }

                                System.out.println("\nTotal messages sent: " + totalMessagesSent);

                                break;

                            case "2":
                                System.out.println("Coming Soon.");
                                break;

                            case "3":
                                quickChatRunning = false;
                                System.out.println("Exiting QuickChat...");
                                break;

                            default:
                                System.out.println("Invalid option.");
                        }
                    }

                    break;

                case "4":
                    running = false;
                    System.out.println("Goodbye.");
                    break;

                default:
                    System.out.println("Invalid menu option.");
            }
        }

        input.close();
    }
}
