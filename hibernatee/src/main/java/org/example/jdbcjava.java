package org.example;

import java.sql.*;

public class jdbcjava {

    public static void main(String[] args) {

        String query = "Select ename from emp where sal>2000";
        String url = "jdbc:mysql://localhost:3306/scott";
        String username = "root";
        String password = "Mohit";

        try {
            // Register MySQL Driver (Optional in newer JDBC versions, but good for debugging)
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()) {
                System.out.println(rs.getString(1));
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}