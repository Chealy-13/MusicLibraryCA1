package Persistence;

import java.sql.Connection;

public abstract class userDAOImpl implements userDAO  {
  private final Connection connection;

  public userDAOImpl(Connection con) {
      this.connection = con;
  }


}
