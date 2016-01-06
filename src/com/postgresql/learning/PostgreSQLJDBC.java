package com.postgresql.learning;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PostgreSQLJDBC {

	public static void main( String args[] )
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager
					.getConnection("jdbc:postgresql://172.17.0.2:5432/postgres",
							"postgres", "postgres");
			System.out.println("Opened database successfully");

			stmt = conn.createStatement();
			String sql = "CREATE TABLE Department " +
					"(Department_Id    INT PRIMARY KEY NOT NULL," +
					" Department_Name  CHAR(20))";
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}
}