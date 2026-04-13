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

}
