package com.postgresql.learning;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PostgreSQLInsert {

   public static void main(String args[]) {
      Connection conn = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         conn = DriverManager
            .getConnection("jdbc:postgresql://172.17.0.2:5432/postgres",
            "postgres", "postgres");
         conn.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = conn.createStatement();
         String sql = "INSERT INTO Department (Department_Id, Department_Name) "
               + "VALUES (111, 'Information Tech' );";
         stmt.executeUpdate(sql);

         sql = "INSERT INTO Department (Department_Id, Department_Name) "
               + "VALUES (112, 'Computer Science' );";
         stmt.executeUpdate(sql);

         sql = "INSERT INTO Department (Department_Id, Department_Name) "
               + "VALUES (113, 'Electronic');";
         stmt.executeUpdate(sql);

         stmt.close();
         conn.commit();
         conn.close();
      } catch (Exception e) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");
   }
}