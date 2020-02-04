package ca.uwo.dataAccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ca.uwo.model.Item;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This class is responsible for the data persistence, i.e., database connection, updates, etc.
 */
public class DataManager {
	private static DataManager instance = null;
	
	private Connection conn;

	/**
	 * connect to sqlite database, prints the error message if failed.
	 */
	public void connect() {
        conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:warehouse.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	/**
	 * create a new database.
	 */
	public void createNewDatabase() {
		
		if (conn != null) {
			DatabaseMetaData meta;
			try {
				meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
	            System.out.println("A new database has been created.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }   
    }
	
	/**
	 * create a table in the database.
	 */
	public void createNewTable() {
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS buyers (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    name text NOT NULL,\n"
                + "    password varchar NOT NULL,\n"
                + "    pin integer\n"
                + ");";
        
        try {
                Statement stmt = conn.createStatement();
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	/**
	 * retrieves a buyer from the database using a combination of name and password or a pin.
	 * @param userName the user name of the buyer
	 * @param password the passsword of the buyer
	 * @return ID of the user;
	 */
	public int getBuyerByPassword(String userName, string password) {
		int buyerID = null;
		String sql = "SELECT id, name, FROM buyers WHERE name = ? AND password = ?";
        
        try (PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
        	pstmt.setString(1, userName);
        	pstmt.setString(2, password);
        	ResultSet rs    = pstmt.executeQuery();
            rs.next();
            buyerID = rs.getInt("id");             				
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return buyerID;
	}		
	
	
	/**
	 * insert a user into the database.
	 * 
	 */
	public void insertUser(int id, string name, string password, int pin = 0) {
		String sql = "INSERT INTO buyers(id,username,password, pin) VALUES(?,?,?,?)";
		 
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, name);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setInt(4, pin);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}

	/**
	 * there should be only one instance of UserManager class.
	 * @return the instance of UserManager.
	 */
	public static UserManager getInstance() {
		if (instance == null)
			instance = new DataManager();
		
		return instance;
	}
	
	private void populateUsers() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("buyer_file")));
			String line;
			while ((line = br.readLine()) != null) {
				String[] lineTokens = line.split("\t");
				insertUser(lineTokens[0],lineTokens[1], lineTokens[2]);
			}
			br.close();
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			ioe.printStackTrace();
		}	
	}
	
	/**
	 * there should be only one instance of DataManager class.
	 * @return the instance of DataManager.
	 */
	public static BuyerManager getInstance() {
		if (instance == null)
			instance = new BuyerManager();
		
		return instance;
	}
	
	/**
	 * constructor for DataManager class.
	 */
	private BuyerManager() {
		connect();
		createNewDatabase();
		createNewTable();
		populateUsers();
	}

}
