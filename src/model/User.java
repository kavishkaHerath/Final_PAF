package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hosptal", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertUser(String Pid,String Fname, String Lname, String Age, String Gender, String email, String Phone)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into user(`Pid`,`Fname`,`Lname`,`Age`,`Gender`,`email`,`Phone`)"+ " values (?, ?,?, ?, ?, ?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, Pid);
	 preparedStmt.setString(2, Fname);
	 preparedStmt.setString(3, Lname);
	 preparedStmt.setString(4, Age);
	 preparedStmt.setString(5, Gender);
	 preparedStmt.setString(6, email); 
	 preparedStmt.setInt(7, Integer.parseInt(Phone));
	 
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readItems()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>User ID</th><th>Frist Name</th><th>Last Name</th><th>Age</th><th>Gender</th><th>email</th><th>Phone</th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from user";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
			 
	String Pid = rs.getString("Pid");
	String Fname = rs.getString("Fname"); 
	String Lname = rs.getString("Lname");
	String Age = rs.getString("Age");
	String Gender = rs.getString("Gender");
	String email = rs.getString("email");
	String Phone = Integer.toString(rs.getInt("Phone"));
	 // Add into the html table
	 output += "<tr><td>" + Pid + "</td>";
	 output += "<td>" + Fname + "</td>";
	 output += "<td>" + Lname + "</td>";
	 output += "<td>" + Age + "</td>";
	 output += "<td>" + Gender + "</td>";
	 output += "<td>" + email + "</td>";
	 output += "<td>" + Phone + "</td>";
	 // buttons
	 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"items.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
	 + "<input name=\"Pid\" type=\"hidden\" value=\"" + Pid
	 + "\">" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	public String updateUser(String Pid,String Fname, String Lname,String Age, String Gender, String email, String Phone)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	
	 String query = "UPDATE user SET Fname=?,Lname=?,Age=?,Gender=?,email=?,Phone=?WHERE Pid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	
	 preparedStmt.setString(1, Fname);
	 preparedStmt.setString(2, Lname);
	 preparedStmt.setString(3, Age);
	 preparedStmt.setString(4, Gender);
	 preparedStmt.setString(5, email); 
	 preparedStmt.setString(6, Phone);
	 preparedStmt.setString(7, Pid);
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deleteUser(String Pid)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from user where Pid=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, Pid);
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

}
