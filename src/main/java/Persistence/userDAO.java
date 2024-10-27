package Persistence;

import business.user;

public interface userDAO {

    user usernames(String u);
    boolean save(user u);
    }

