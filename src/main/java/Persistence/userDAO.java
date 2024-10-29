package Persistence;

import business.user;

public interface userDAO {

    user usernames(String u);
    boolean save(user u);
    boolean deleteByUsername(String username);
    }
