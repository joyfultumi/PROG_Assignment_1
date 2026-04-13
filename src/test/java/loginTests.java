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
    
    
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
