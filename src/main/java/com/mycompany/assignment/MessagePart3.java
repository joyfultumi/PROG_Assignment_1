/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assignment;

/**
 *
 * @author tumithobejane
 */


import java.util.Random;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class MessagePart3 {

    private String messageID;
    private int messageNumber;
    private String sender;
    private String recipient;
    private String messageText;
    private String flag;

    private static final String FILE_NAME = "stored_messages.json";

    private static MessagePart3[] sentMessagesArray;
    private static MessagePart3[] disregardedMessagesArray;
    private static MessagePart3[] storedMessagesArray;
    private static String[] messageHashesArray;
    private static String[] messageIDsArray;

    // Main constructor used in the app
    public MessagePart3(int messageNumber, String sender, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.sender = sender;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.flag = "";
    }

    // Constructor used for testing / JSON loading
    public MessagePart3(String messageID, int messageNumber, String sender, String recipient, String messageText, String flag) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.sender = sender;
        this.recipient = recipient;
        this.messageText = messageText;
        this.flag = flag;
    }

    // Generate random 10-digit message ID
    private String generateMessageID() {
        Random random = new Random();
        String id = "";

        for (int i = 0; i < 10; i++) {
            id += random.nextInt(10);
        }

        return id;
    }

    // Check message ID length
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Reuse cell phone validation from Login class
    public String checkRecipientCell() {
        Login loginHelper = new Login();

        if (loginHelper.checkCellPhoneNumber(recipient)) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Check message length
    public String checkMessageLength() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int extraCharacters = messageText.length() - 250;
            return "Message exceeds 250 characters by " + extraCharacters + ", please reduce the size.";
        }
    }

    // Create message hash
    public String createMessageHash() {
        String cleanMessage = messageText.replaceAll("[^a-zA-Z0-9 ]", "");
        String[] words = cleanMessage.trim().split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        String hash = messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }

    // Send / Disregard / Store message
    public String sentMessage(int option) {
        if (option == 1) {
            this.flag = "Sent";
            return "Message successfully sent.";
        } else if (option == 2) {
            this.flag = "Disregard";
            return "Press 0 to delete the message.";
        } else if (option == 3) {
            this.flag = "Stored";
            storeMessageToJson();
            return "Message successfully stored.";
        } else {
            return "Invalid option.";
        }
    }

    // Store message in JSON
    public void storeMessageToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<MessagePart3> messages = new ArrayList<>();

        try (FileReader reader = new FileReader(FILE_NAME)) {
            Type listType = new TypeToken<ArrayList<Message>>() {}.getType();
            ArrayList<MessagePart3> existingMessages = gson.fromJson(reader, listType);

            if (existingMessages != null) {
                messages = existingMessages;
            }
        } catch (IOException e) {
            // file may not exist yet
        }

        messages.add(this);

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(messages, writer);
        } catch (IOException e) {
            System.out.println("Error saving message to JSON.");
        }
    }

    // Read stored messages from JSON
    public static ArrayList<MessagePart3> readStoredMessagesFromJson() {
        Gson gson = new Gson();
        ArrayList<MessagePart3> messages = new ArrayList<>();

        try (FileReader reader = new FileReader(FILE_NAME)) {
            Type listType = new TypeToken<ArrayList<MessagePart3>>() {}.getType();
            ArrayList<MessagePart3> existingMessages = gson.fromJson(reader, listType);

            if (existingMessages != null) {
                messages = existingMessages;
            }
        } catch (IOException e) {
            // file may not exist yet
        }

        return messages;
    }

    // Delete stored message by hash
    public static boolean deleteStoredMessageByHash(String hash) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<MessagePart3> messages = readStoredMessagesFromJson();

        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).createMessageHash().equalsIgnoreCase(hash)) {
                messages.remove(i);

                try (FileWriter writer = new FileWriter(FILE_NAME)) {
                    gson.toJson(messages, writer);
                } catch (IOException e) {
                    System.out.println("Error updating JSON file.");
                }

                return true;
            }
        }

        return false;
    }

    // Populate arrays for Part 3
    public static void populateMessageArrays(ArrayList<MessagePart3> allMessages) {
        ArrayList<MessagePart3> sentList = new ArrayList<>();
        ArrayList<MessagePart3> disregardedList = new ArrayList<>();
        ArrayList<MessagePart3> storedList = readStoredMessagesFromJson();
        ArrayList<String> hashList = new ArrayList<>();
        ArrayList<String> idList = new ArrayList<>();

        for (MessagePart3 msg : allMessages) {
            if (msg.getFlag().equalsIgnoreCase("Sent")) {
                sentList.add(msg);
            } else if (msg.getFlag().equalsIgnoreCase("Disregard")) {
                disregardedList.add(msg);
            }

            hashList.add(msg.createMessageHash());
            idList.add(msg.getMessageID());
        }

        for (MessagePart3 msg : storedList) {
            hashList.add(msg.createMessageHash());
            idList.add(msg.getMessageID());
        }

        sentMessagesArray = sentList.toArray(new MessagePart3[0]);
        disregardedMessagesArray = disregardedList.toArray(new MessagePart3[0]);
        storedMessagesArray = storedList.toArray(new MessagePart3[0]);
        messageHashesArray = hashList.toArray(new String[0]);
        messageIDsArray = idList.toArray(new String[0]);
    }

    // Display sender and recipient of all stored messages
    public static String displayStoredSendersAndRecipients() {
        if (storedMessagesArray == null || storedMessagesArray.length == 0) {
            return "No stored messages found.";
        }

        String result = "";

        for (MessagePart3 msg : storedMessagesArray) {
            result += "Sender: " + msg.getSender()
                    + ", Recipient: " + msg.getRecipient() + "\n";
        }

        return result;
    }

    // Display longest stored message
    public static String displayLongestStoredMessage() {
        if (storedMessagesArray == null || storedMessagesArray.length == 0) {
            return "No stored messages found.";
        }

        MessagePart3 longest = storedMessagesArray[0];

        for (MessagePart3 msg : storedMessagesArray) {
            if (msg.getMessageText().length() > longest.getMessageText().length()) {
                longest = msg;
            }
        }

        return longest.getMessageText();
    }

    // Search stored messages by message ID
    public static String searchMessageByID(String messageID) {
        if (storedMessagesArray == null || storedMessagesArray.length == 0) {
            return "No stored messages found.";
        }

        for (MessagePart3 msg : storedMessagesArray) {
            if (msg.getMessageID().equals(messageID)) {
                return "Recipient: " + msg.getRecipient() + "\nMessage: " + msg.getMessageText();
            }
        }

        return "Message ID not found.";
    }

    // Search stored messages by recipient
    public static String searchMessagesByRecipient(String recipient) {
        if (storedMessagesArray == null || storedMessagesArray.length == 0) {
            return "No stored messages found.";
        }

        String result = "";

        for (MessagePart3 msg : storedMessagesArray) {
            if (msg.getRecipient().equals(recipient)) {
                result += msg.getMessageText() + "\n";
            }
        }

        if (result.isEmpty()) {
            return "No messages found for this recipient.";
        }

        return result;
    }

    // Delete message using hash and return message text
    public static String deleteMessageByHash(String hash) {
        ArrayList<MessagePart3> messages = readStoredMessagesFromJson();

        for (MessagePart3 msg : messages) {
            if (msg.createMessageHash().equalsIgnoreCase(hash)) {
                String deletedText = msg.getMessageText();

                boolean deleted = deleteStoredMessageByHash(hash);

                if (deleted) {
                    return "Message: \"" + deletedText + "\" successfully deleted.";
                }
            }
        }

        return "Message hash not found.";
    }

    // Display report of all stored messages
    public static String displayMessageReport() {
        if (storedMessagesArray == null || storedMessagesArray.length == 0) {
            return "No stored messages to display.";
        }

        String result = "STORED MESSAGE REPORT\n\n";

        for (MessagePart3 msg : storedMessagesArray) {
            result += "Message Hash: " + msg.createMessageHash() + "\n";
            result += "Recipient: " + msg.getRecipient() + "\n";
            result += "Message: " + msg.getMessageText() + "\n\n";
        }

        return result;
    }

    // Print single message details
    public String printMessages() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + createMessageHash()
                + "\nSender: " + sender
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText
                + "\nFlag: " + flag;
    }

    // Getters
    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getFlag() {
        return flag;
    }

    // Static getters for arrays
    public static MessagePart3[] getSentMessagesArray() {
        return sentMessagesArray;
    }

    public static MessagePart3[] getDisregardedMessagesArray() {
        return disregardedMessagesArray;
    }

    public static MessagePart3[] getStoredMessagesArray() {
        return storedMessagesArray;
    }

    public static String[] getMessageHashesArray() {
        return messageHashesArray;
    }

    public static String[] getMessageIDsArray() {
        return messageIDsArray;
    }
}
