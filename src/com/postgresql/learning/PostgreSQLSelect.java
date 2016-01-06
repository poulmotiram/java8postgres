package com.postgresql.learning;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgreSQLSelect {

   public static void main( String args[] )
     {
       Connection conn = null;
       Statement stmt = null;
       try {
       Class.forName("org.postgresql.Driver");
         conn = DriverManager
                 .getConnection("jdbc:postgresql://" + DATABASE_IP + ":" 
                  + DATABASE_PORT + "/" + DATABASE_NAME, DATABASE_USER,
                  DATABASE_PASSWORD);
                 
         conn.setAutoCommit(false);
         System.out.println("Opened database successfully");
         System.out.println();
         
         stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM \"department\";" );
         while ( rs.next() ) {
            int id = rs.getInt("Department_Id");
            String  name = rs.getString("Department_Name");
            
            System.out.println( "Department_Id = " + id );
            System.out.println( "Department_Name = " + name );

            System.out.println();
         }
         rs.close();
         stmt.close();
         conn.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
       System.out.println("Operation done successfully");
     }

    private static String DATABASE_IP;
    private static final String DOCKER_CONTAINER_NAME = "psql";
    private static final String DATABASE_NAME = "postgres";
    private static final String DATABASE_PORT = "5432";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "postgres";

    static {
      try {
        DATABASE_IP = InetAddress.getByName(DOCKER_CONTAINER_NAME)
          .getHostAddress();
        System.out.println("DATABASE_IP - " + DATABASE_IP);
        } catch (UnknownHostException e) {
          System.out.println("Error Occurred while getting Database IP Address");
      }
    }
}

