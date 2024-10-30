package Persistence;

import business.user;

public interface userDAO {

    user LoginU(String username);

    boolean RegisterU(String username, String password, String email);

    boolean deleteByUsername(String username);

    boolean validateCCInfo(String cardNumber, String expiryDate, String cvv);
}
