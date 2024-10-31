package Persistence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class userDAOImplTest {

    private final userDAOImpl userDao = new userDAOImpl();
    @Test
    void testValidateCCInfo() {
        // Valid user input test.
        assertTrue(userDao.validateCCInfo("1234567812345678", "12/25", "123"));

        // Invalid card number(must be 16 digits)
        assertFalse(userDao.validateCCInfo("12345678", "12/25", "123"));

        // Invalid expiry date(must be a valid month from 1-12
        assertFalse(userDao.validateCCInfo("1234567812345678", "13/25", "123"));

        // Invalid CVV(must be 3 or 4 digits,
        assertFalse(userDao.validateCCInfo("1234567812345678", "12/25", "12"));

        // Valid CVV with 4 digits
        assertTrue(userDao.validateCCInfo("1234567812345678", "12/25", "1234"));
    }

    @Test
    void registerU() {
    }

    @Test
    void loginU() {
    }

    @Test
    void deleteByUsername() {
    }
}