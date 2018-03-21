import java.sql.*;
public class JavaSquadJdbc{
   public static void main(String[] args){
      throws SQLException, ClassNotFoundException {
      
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver Loaded");
      
      Connection connection = DriverManager.getConnection
         ("jdbc:mysql://localhost/java_squad", "final", "me");
        System.out.println("Database connected");
        
 
        Statement statement = connection.createStatement();
        
        ResultSet resultSet = statement.executeQuery
        ("select * from Player");
         
        while(resultSet.next())
        System.out.println(resultSet.getString(1) + "\t" +
         resultSet.getString(2) + "\t" + resultSet.getString(3));
         
        connection.close();
      }}