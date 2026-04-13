/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import com.mycompany.assignment.MessagePart3;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tumithobejane
 */
public class MessagePart3Tests {

    private ArrayList<MessagePart3> allMessages;

    @BeforeEach
    public void setUp() throws IOException {
        allMessages = new ArrayList<>();

        // Provided test data from screenshots
        MessagePart3 message1 = new MessagePart3("1000000001", 1, "Developer", "+27834557896",
                "Did you get the cake?", "Sent");

        MessagePart3 message2 = new MessagePart3("1000000002", 2, "Developer", "+27838884567",
                "Where are you? You are late! I have asked you to be on time.", "Stored");

        MessagePart3 message3 = new MessagePart3("1000000003", 3, "Developer", "+27834484567",
                "Yohoooo, I am at your gate.", "Disregard");

        MessagePart3 message4 = new MessagePart3("0838884567", 4, "Developer", "+27830000000",
                "It is dinner time !", "Sent");

        MessagePart3 message5 = new MessagePart3("1000000005", 5, "Developer", "+27838884567",
                "Ok, I am leaving without you.", "Stored");

        // Current run messages
        allMessages.add(message1);
        allMessages.add(message2);
        allMessages.add(message3);
        allMessages.add(message4);

        // Stored JSON messages
        ArrayList<MessagePart3> storedMessages = new ArrayList<>();
        storedMessages.add(message2);
        storedMessages.add(message5);

        com.google.gson.Gson gson = new com.google.gson.GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter("stored_messages.json")) {
            gson.toJson(storedMessages, writer);
        }

        MessagePart3.populateMessageArrays(allMessages);
    }

    @Test
    public void testSentMessagesArrayPopulatedCorrectly() {
        MessagePart3[] sentMessages = MessagePart3.getSentMessagesArray();

        assertEquals(2, sentMessages.length);
        assertEquals("Did you get the cake?", sentMessages[0].getMessageText());
        assertEquals("It is dinner time !", sentMessages[1].getMessageText());
    }

    @Test
    public void testDisregardedMessagesArrayPopulatedCorrectly() {
        MessagePart3[] disregardedMessages = MessagePart3.getDisregardedMessagesArray();

        assertEquals(1, disregardedMessages.length);
        assertEquals("Yohoooo, I am at your gate.", disregardedMessages[0].getMessageText());
    }

    @Test
    public void testStoredMessagesArrayPopulatedCorrectly() {
        MessagePart3[] storedMessages = MessagePart3.getStoredMessagesArray();

        assertEquals(2, storedMessages.length);
        assertEquals("Where are you? You are late! I have asked you to be on time.", storedMessages[0].getMessageText());
        assertEquals("Ok, I am leaving without you.", storedMessages[1].getMessageText());
    }

    @Test
    public void testDisplayStoredSendersAndRecipients() {
        String result = MessagePart3.displayStoredSendersAndRecipients();

        assertEquals(
                "Sender: Developer, Recipient: +27838884567\n" +
                "Sender: Developer, Recipient: +27838884567\n",
                result
        );
    }

   
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
