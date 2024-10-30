package Persistence;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 *
 * @author Chris
 *
 */
public class MySQLDao {
    private Properties properties;
    private Connection conn = null;

    /**
     * Constructs a new instance of MySQLDao with the specified database connection.
     * This constructor initializes the data access object by accepting an active database
     * connection. The provided connection will be used for executing SQL queries and
     * managing transactions.
     */
    public MySQLDao(){
        this.conn = conn;
    }

    /**
     * Constructs a new instance of MySQLDao by loading database connection properties
     * from the specified properties file.
     * This constructor initializes the data access object and reads the database configuration
     * settings from the provided properties file. It retrieves the path to the properties file
     * and loads all key-value pairs into a Properties object for later use.
     * @param propertiesFilename the name of the properties file containing database connection
     * settings, such as URL, username, and password.
     * @throws IOException if an error occurs while loading the properties from the file,
     * such as if the file is not found or cannot be read.
     */
    public MySQLDao(String propertiesFilename) {
        properties = new Properties();
        try {
            // Get the path to the specified properties file
            String rootPath = Thread.currentThread().getContextClassLoader().getResource(propertiesFilename).getPath();
            // Load in all key-value pairs from properties file
            properties.load(new FileInputStream(rootPath));
        }catch(IOException e){
            System.out.println("An exception occurred when attempting to load properties from \"" + propertiesFilename + "\": " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a database connection using the properties in the properties file.
     * It checks if an existing database connection is available. If a connection
     * already exists, it returns that connection. If not, it attempts to create a new
     * connection using the database driver, URL, database name, username, and password
     * specified in the properties file. Default values are provided for each property
     * if they are not specified.
     * @return a Connection object representing the database connection.
     * @throws SQLException if an error occurs while establishing the connection to the
     * database, such as incorrect credentials or unreachable database.
     * @throws ClassNotFoundException if the database driver class cannot be found.
     */
    public Connection getConnection() {
        if(conn != null){
            return conn;
        }
        // Retrieve connection information from properties file
        // Where no values exist for a property, a default is used
        String driver = properties.getProperty("driver", "com.mysql.cj.jdbc.Driver");
        String url = properties.getProperty("url", "jdbc:mysql://127.0.0.1:3306/");
        String database = properties.getProperty("database", "musiclibrary");
        String username = properties.getProperty("username", "root");
        String password = properties.getProperty("password", "");

        Connection connection = null;

        try {
            // Load the database driver
            Class.forName(driver);
            // TRY to get a connection to the database
            connection = DriverManager.getConnection(url+database, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException occurred when trying to load driver: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred when attempting to connect to database.");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(2);
        }
        return connection;
    }

    /**
     * Frees the specified database connection by closing it.
     * This method checks if the provided connection is not null. If the connection
     * exists, it attempts to close it, releasing any database resources associated
     * with that connection. If an error occurs while closing the connection,
     * it logs the exception details and terminates the application.
     * @param con the Connection object to be freed; it can be null.
     */

    public void freeConnection(Connection con){
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred when attempting to free connection to database.");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
