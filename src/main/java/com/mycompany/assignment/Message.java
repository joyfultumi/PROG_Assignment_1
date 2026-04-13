/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment;
import java.util.Random;

/**
 *
 * @author tumithobejane
 */
public class Message {
    
    private String messageId;
    private String recipient;
    private String messageText;
    private int messageNumber;
    Login login;
    public Message( int messageNumber, String recipient, String messageText ){
        
        this.messageId = createRandomMessageId(10);
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = messageNumber;
        login = new Login();
    }
    
    public Message(String messageID, int messageNumber, String recipient, String messageText) {
        this.messageId = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
    }
    
    
    private String createRandomMessageId(int size) {
        Random random = new Random();
        
        String randomId = "";
        
        for(int i =0; i < size ; i++){
            randomId = randomId + random.nextInt(10);
        }
        
        return randomId;
    }
    
    
    public boolean checkMessageID(){
        
        if(messageId.length() <= 10){
            return true;
        }
        return false;
        
    }
    
    public String checkRecipientCell(String cell){
        
        if(login.checkCellPhoneNumber(cell)) {
            
            return "Cell phone number successfully captured.";
        }
        
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
    
    public String checkMessageLength(){
        
        int extra = messageText.length() - 250;
        if(messageText.length() <=250){
            return "Message ready to send.";
        } else {
             
            return "Message exceeds 250 characters by " + extra + ", please reduce the size.";
        }
    }
    
    
    
    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        String hash = messageId.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    public String sentMessage(int choice) {
        if (choice == 1) {
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            return "Message successfully stored.";
        } else {
            return "Invalid option.";
        }
    }

    public String printMessages() {
        
       
        return "Message ID: " + messageId
                + "\nMessage Hash: " + createMessageHash()
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

    public int returnTotalMessages() {
        return messageNumber;
    }

    public String getMessageID() {
        return messageId;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

}
