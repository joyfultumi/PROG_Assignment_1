/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.assignment.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author tumithobejane
 */
public class loginTests {
    
    public loginTests() {
    }

    
    private Login login;
    
    
    private String firstname;
    private String lastname;
    private String validUsername;
    private String validPassword;
    private String validCell;
    
    private String invalidUsername;
    private String invalidPassword;
    private String invalidCell;
    
    @BeforeEach
    public void setup(){
        login = new Login();
        
        firstname = "John";
        lastname = "Johnny";
        
        validUsername = "kyl_1";
        invalidUsername = "kyle!!!!!!";
        
        validPassword = "Ch&&sec@ke99!";
        invalidPassword = "password";
        
        validCell = "+27838968976";
        invalidCell = "08966553";
    }
    
    
    @Test
    public void testUsernameFormattedCorrectly(){
        
        assertTrue(login.checkUserName(validUsername));
    }
    
    @Test
    public void testUsernameFormattedInCorrectly(){
        
        assertFalse(login.checkUserName(invalidUsername));
    }
    
    @Test
    public void testPasswordComplexityIsCorrect(){
        assertTrue(login.checkPasswordComplexity(validPassword));
    }
    
    @Test
    public void testPasswordIncorrectComplexity(){
        assertFalse(login.checkPasswordComplexity(invalidPassword));
    }
    
    
    @Test
    public void testCellPhoneNumberFormatIsCorrect(){
        
        assertTrue(login.checkCellPhoneNumber(validCell));
        
    }
    
    @Test
    public void testCellPhoneNumberIncorrectFormat(){
        
        assertFalse(login.checkCellPhoneNumber(invalidCell));
        
    }
    
    @Test
    public void testRegisterUserSuccessfully() {
        String result = login.registerUser(firstname, lastname, validUsername, validPassword, validCell);
        assertEquals("User has been registered successfully.", result);
    }

    @Test
    public void testRegisterUserWithBadUsername() {
        String result = login.registerUser(firstname, lastname, invalidUsername, validPassword, validCell);

        assertEquals(
            "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
            result
        );
    }

    @Test
    public void testRegisterUserWithBadPassword() {
        String result = login.registerUser(firstname, lastname, validUsername, invalidPassword, validCell);

        assertEquals(
            "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
            result
        );
    }

    @Test
    public void testRegisterUserWithBadCellNumber() {
        String result = login.registerUser(firstname, lastname, validUsername, validPassword, invalidCell);

        assertEquals(
            "Cell phone number incorrectly formatted or does not contain international code; please correct the number and try again.",
            result
        );
    }

    @Test
    public void testLoginSuccessful() {
        login.registerUser(firstname, lastname, validUsername, validPassword, validCell);
        assertTrue(login.loginUser(validUsername, validPassword));
    }

    @Test
    public void testLoginFailed() {
        login.registerUser(firstname, lastname, validUsername, validPassword, validCell);
        assertFalse(login.loginUser(validUsername, "Wrong@123"));
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        login.registerUser(firstname, lastname, validUsername, validPassword, validCell);
        String result = login.returnLoginStatus(true);

        assertEquals(
            "Welcome " + firstname + ", " + lastname + " it is great to see you again.",
            result
        );
    }

    @Test
    public void testReturnLoginStatusFailure() {
        String result = login.returnLoginStatus(false);
        assertEquals("Username or password incorrect, please try again.", result);
    }
    
    
    
    
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
