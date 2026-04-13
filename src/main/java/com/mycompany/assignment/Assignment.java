/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.assignment;

import java.util.Scanner;
import java.lang.Character;
import java.util.ArrayList;

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
       
       /* Scanner input = new Scanner(System.in);
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

        input.close();*/
       
       
       //Part 3
       
       Scanner input = new Scanner(System.in);
        Login loginSystem = new Login();

        boolean running = true;
        boolean userRegistered = false;
        boolean loggedIn = false;
        int totalMessagesSent = 0;

        ArrayList<MessagePart3> allMessages = new ArrayList<>();
        String currentSender = "";

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
                        currentSender = username;
                    }

                    break;

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
                        System.out.println("3) Stored Messages");
                        System.out.println("4) Quit");
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

                                    MessagePart3 message = new MessagePart3(i, currentSender, recipient, messageText);

                                    String recipientCheck = message.checkRecipientCell();
                                    System.out.println(recipientCheck);

                                    if (!recipientCheck.equals("Cell phone number successfully captured.")) {
                                        continue;
                                    }

                                    String lengthCheck = message.checkMessageLength();
                                    System.out.println(lengthCheck);

                                    if (!lengthCheck.equals("Message ready to send.")) {
                                        continue;
                                    }

                                    System.out.println("\nChoose what to do:");
                                    System.out.println("1. Send Message");
                                    System.out.println("2. Disregard Message");
                                    System.out.println("3. Store Message");
                                    System.out.print("Option: ");

                                    int option = Integer.parseInt(input.nextLine());

                                    String result = message.sentMessage(option);
                                    System.out.println(result);

                                    allMessages.add(message);

                                    if (option == 1) {
                                        totalMessagesSent++;
                                        System.out.println("\n--- Message Details ---");
                                        System.out.println(message.printMessages());
                                    } else if (option == 2) {
                                        System.out.println("Message discarded.");
                                    }
                                }

                                System.out.println("\nTotal messages sent: " + totalMessagesSent);
                                break;

                            case "2":

                                System.out.println("Coming Soon.");
                                break;

                            case "3":

                                MessagePart3.populateMessageArrays(allMessages);

                                boolean storedMenuRunning = true;

                                while (storedMenuRunning) {

                                    System.out.println("\n=== STORED MESSAGES MENU ===");
                                    System.out.println("1. Display sender and recipient of all stored messages");
                                    System.out.println("2. Display the longest stored message");
                                    System.out.println("3. Search for a message ID");
                                    System.out.println("4. Search for messages by recipient");
                                    System.out.println("5. Delete a message using the message hash");
                                    System.out.println("6. Display full report");
                                    System.out.println("7. Back");
                                    System.out.print("Choose an option: ");

                                    String storedChoice = input.nextLine();

                                    switch (storedChoice) {

                                        case "1":
                                            System.out.println(MessagePart3.displayStoredSendersAndRecipients());
                                            break;

                                        case "2":
                                            System.out.println(MessagePart3.displayLongestStoredMessage());
                                            break;

                                        case "3":
                                            System.out.print("Enter message ID: ");
                                            String messageID = input.nextLine();
                                            System.out.println(MessagePart3.searchMessageByID(messageID));
                                            break;

                                        case "4":
                                            System.out.print("Enter recipient number: ");
                                            String recipientSearch = input.nextLine();
                                            System.out.println(MessagePart3.searchMessagesByRecipient(recipientSearch));
                                            break;

                                        case "5":
                                            System.out.print("Enter message hash: ");
                                            String hash = input.nextLine();
                                            System.out.println(MessagePart3.deleteMessageByHash(hash));

                                            MessagePart3.populateMessageArrays(allMessages);
                                            break;

                                        case "6":
                                            System.out.println(MessagePart3.displayMessageReport());
                                            break;

                                        case "7":
                                            storedMenuRunning = false;
                                            break;

                                        default:
                                            System.out.println("Invalid option.");
                                    }
                                }

                                break;

                            case "4":

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
