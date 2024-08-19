package application;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
   public static void main(String[] args) {
      DB db = new DB();
      Connection connection;
      Statement st = null;
      ResultSet rs = null;

      try {
         connection = db.getConnection();
         st = connection.createStatement();
         rs = st.executeQuery("select * from department");

         while (rs.next()) {
            System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         db.closeResultSet(rs);
         db.closeStatement(st);
         db.closeConnection();
      }

   }
}
