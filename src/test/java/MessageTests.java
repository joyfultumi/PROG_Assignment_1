/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.assignment.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tumithobejane
 */
public class MessageTests {
    
    public MessageTests() {
    }
 
    @Test
    public void testMessageLengthSuccess() {
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", message.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String longMessage = "This is a very long message that is only here to make sure that the total number of characters goes over the allowed limit of two hundred and fifty characters for the assignment. It should fail because it is too long and the system must return the correct error message for the user.";

        Message message = new Message(1, "+27718693002", longMessage);

        assertTrue(message.checkMessageLength().contains("Message exceeds 250 characters by"));
    }
    
    @Test
    public void testMessageLengthFailurE() {
        String longMessage = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        Message message = new Message("0000000000", 0, "+27718693002", longMessage);

        assertEquals("Message exceeds 250 characters by 1, please reduce the size.", message.checkMessageLength());
    }

    @Test
    public void testRecipientNumberCorrectlyFormatted() {
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.", message.checkRecipientCell("+27718693002"));
    }

    @Test
    public void testRecipientNumberIncorrectlyFormatted() {
        Message message = new Message(2, "0857975889", "Hi Keegan, did you receive the payment?");
        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                message.checkRecipientCell("0857975889")
        );
    }

    @Test
    public void testMessageHashNotNull() {
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertNotNull(message.createMessageHash());
    }

    @Test
    public void testMessageHashFormat() {
        Message message = new Message(0, "+27718693002", "Hi Mike");
        String hash = message.createMessageHash();

        assertTrue(hash.contains(":"));
        assertEquals(hash, hash.toUpperCase());
    }

    @Test
    public void testMessageIdCreated() {
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(message.checkMessageID());
    }

    @Test
    public void testSendMessageOptionSend() {
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully sent.", message.sentMessage(1));
    }

    @Test
    public void testSendMessageOptionDiscard() {
        Message message = new Message(2, "0857975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Press 0 to delete the message.", message.sentMessage(2));
    }

    @Test
    public void testSendMessageOptionStore() {
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully stored.", message.sentMessage(3));
    }

    @Test
    public void testReturnTotalMessages() {
        Message message = new Message(2, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals(2, message.returnTotalMessages());
    }

    @Test
    public void testPrintMessagesContainsDetails() {
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String output = message.printMessages();

        assertTrue(output.contains("Message ID:"));
        assertTrue(output.contains("Message Hash:"));
        assertTrue(output.contains("Recipient: +27718693002"));
        assertTrue(output.contains("Message: Hi Mike, can you join us for dinner tonight?"));
    }
    
    
    @Test
    public void testMessageHashIsCorrect() {
        Message message = new Message("0000000000", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals("00:0:HITONIGHT?", message.createMessageHash());
    }

    @Test
    public void testMessageIDCreated() {
        Message message = new Message("1234567890", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertTrue(message.checkMessageID());
        assertEquals("1234567890", message.getMessageID());
    }

    @Test
    public void testSentMessageOptionSend() {
        Message message = new Message("1234567890", 0, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals("Message successfully sent.", message.sentMessage(1));
    }

    @Test
    public void testSentMessageOptionDiscard() {
        Message message = new Message("1234567890", 1, "0857975889",
                "Hi Keegan, did you receive the payment?");

        assertEquals("Press 0 to delete the message.", message.sentMessage(2));
    }

    @Test
    public void testSentMessageOptionStore() {
        Message message = new Message("1234567890", 1, "+27718693002",
                "Hi Mike, can you join us for dinner tonight?");

        assertEquals("Message successfully stored.", message.sentMessage(3));
    }
}
