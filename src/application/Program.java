package application;

import db.DB;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Program {
   public static void main(String[] args) {
      DB db = new DB();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      LocalDate date = LocalDate.parse("22/04/1985", formatter);
      Date sqlDate = Date.valueOf(date);
      Connection connection;
      PreparedStatement statement = null;

      try {
         connection = db.getConnection();

         statement = connection.prepareStatement(
                 "INSERT INTO seller "
                 + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                 + "VALUES "
                 + "(?, ?, ?, ?, ?)");
         statement.setString(1, "Carl Purple");
         statement.setString(2, "carl@gmail.com");
         statement.setDate(3, sqlDate);
         statement.setDouble(4, 3000);
         statement.setInt(5, 4);
         int rowsAffected = statement.executeUpdate();

         System.out.println("Done! Rows affected: " + rowsAffected);

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         db.closeStatement(statement);
         db.closeConnection();
         System.out.println("Closed!");
      }

   }
}
