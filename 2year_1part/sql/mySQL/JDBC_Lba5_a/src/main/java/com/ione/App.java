package com.ione;

import java.sql.*;
import java.util.Scanner;

public class App {
    private static final String url = "jdbc:mysql://localhost:3306/Lab_5?serverTimezone=UTC&useSSL=false";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection connection=null;
    private static Statement statement=null;
    private static ResultSet rs=null;

    public static void main(String args[]){
        try {
//region    0. This will load the MySQL driver, each DB has its own driver //
            Class.forName("com.mysql.cj.jdbc.Driver");
            //endregion


//region    1. Get a connection to database //
            connection = DriverManager.getConnection(url, user, password);
            //endregion

//region  2. Create a statement
            // Statements allow to issue SQL queries to the database
            statement=connection.createStatement();
            //endregion

//            updateDataCity();
//            insertDataCity();
//            DeleteDataCity();
//            CallProcedureForInsertToPersonBook();
            readData();


        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver is not loaded");

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        } finally {
            //close connection ,statement and resultset
            if (rs != null) try { rs.close(); } catch (SQLException e) { } // ignore
            if (statement != null) try { statement.close(); } catch (SQLException e) { }
            if (connection != null) try { connection.close(); } catch (SQLException e) { }
        }
    }

    private static void readData() throws SQLException {
//region    SELECT COUNT(*) FROM Person //
        // 3. executing SELECT query
        rs=statement.executeQuery("SELECT COUNT(*) FROM Cars");

        // 4. Process the result set
        while (rs.next()) {
            int count = rs.getInt(1);
            // Simply Print the results
            System.out.format("\ncount: %d\n", count);
        }
        //endregion

//region    SELECT * FROM Person //
        // 3. executing SELECT query
        rs=statement.executeQuery("SELECT * FROM Cars");

        // 4. Process the result set
        System.out.format("\n--------------------Table Cars --------------------\n");
        System.out.format("%1s %-12s %-12s\n", "Avtosalon_idAvtosalon", "idCars", "seler");
        while (rs.next())
        {
            int id=rs.getInt("Avtosalon_idAvtosalon");
            int idCars = rs.getInt("idCars");
            String seler = rs.getString("seler");
            // Simply Print the results
            System.out.format("%1s %22s %15s\n", id, idCars, seler);
        }
        //endregion
        System.out.format("---------------------------------------------------\n");
//region    SELECT * FROM Book //
        // 3. executing SELECT query
        rs=statement.executeQuery("SELECT * FROM Car_Detail");

        // 4. Process the result set
        System.out.format("\n--------------------Table Car Deatail ---------------------------------------------\n");
        System.out.format("%3s %10s %18s %18s %18s \n", "idCar_Detail", "class", "maxDistanc", "maxSpeed","model");
        while (rs.next())
        {
            int id=rs.getInt("idCar_Detail");
            String clas = rs.getString("class");
            int maxDistance = rs.getInt("maxDistanc");
            int maxSpeed=rs.getInt("maxSpeed");
            String model = rs.getString("model");
            // Simply Print the results
            System.out.format("%3d %18s %18s %18s %18s \n", id, clas, maxDistance, maxSpeed,model);
        }
        //endregion
        System.out.format("---------------------------------------------------------------------------------\n");
//region    SELECT * FROM City //
        // 3. executing SELECT query
        rs=statement.executeQuery("SELECT * FROM Avtosalon");

        // 4. Process the result set
        System.out.format("\n--------------------Table Avtosalon ----------------\n");
        System.out.format("%3s %10s %18s \n", "idAvtosalon","name","ownerName");
        while (rs.next())
        {
            int idAvtosalon =rs.getInt("idAvtosalon");
            String name = rs.getString("name");
            String ownerName = rs.getString("ownerName");
            // Simply Print the results
            System.out.format("%3d %18s %18s \n",idAvtosalon, name,ownerName);
        }
        //endregion
        System.out.format("---------------------------------------------------\n");
//region    SELECT * FROM PersonBook //
        // 3. executing SELECT query
        String query="Select " +
                "(SELECT  seler From Cars WHERE idCars=P.Cars_idCars) AS seler, " +
                "(SELECT model FROM Car_Detail WHERE idCar_Detail=P.Car_Detail_idCar_Detail) AS model "+
                "FROM Car_Detail_has_Cars1 AS P";
        rs=statement.executeQuery(query);

        // 4. Process the result set
        System.out.format("\n---------Joining Table Car AND AVTOSALON --------\n");
        System.out.format("%-15s %s\n", "Seller", "model of car");
        while (rs.next())
        {
            String  seler = rs.getString("seler");
            String  model = rs.getString("model");
            // Simply Print the results
            System.out.format("%-15s %s\n", seler, model);
        }
        //endregion
        System.out.format("-------------------------------------------------\n");

    }

    private static void updateDataCity() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input name of Avtosalon what you want to update: ");
        String name = input.next();
        System.out.println("Input new name Avtosalon for : "+ name);
        String AvtosalonNew = input.next();

        // 3. executing SELECT query
// 1
        statement.execute("UPDATE Avtosalon SET name='"+AvtosalonNew+"' WHERE name='"+name+"';");

// 2  Returns count of updated rows
        int n=statement.executeUpdate("UPDATE Avtosalon SET name='"+AvtosalonNew+"' WHERE name='"+name+"';");
        System.out.println("Count rows that updated: "+n);

// 3  PreparedStatements can use variables and are more efficient
//        PreparedStatement preparedStatement;
//        preparedStatement=connection.prepareStatement("UPDATE Avtosalon SET name=? WHERE name=?;");
//        preparedStatement.setString(1, citynew);
//        preparedStatement.setString(2, city);
//        int k=preparedStatement.executeUpdate();
//        System.out.println("Count rows that updated: "+k);

    }

    private static void insertDataCity() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a new name of Autosalon: ");
        String name = input.next();
        System.out.println("Input a new name of owner: ");
        String ownerName = input.next();

        // 3. executing SELECT query
        //   PreparedStatements can use variables and are more efficient
        PreparedStatement preparedStatement;
        preparedStatement=connection.prepareStatement("INSERT Avtosalon VALUES (?,?,?)");
        preparedStatement.setString(1, null);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, ownerName);
        int n=preparedStatement.executeUpdate();
        System.out.println("Count rows that inserted: "+n);

    }

    private static void DeleteDataCity() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a name Autosalon for delete: ");
        String name = input.next();

        // 3. executing SELECT query
        //   PreparedStatements can use variables and are more efficient
        PreparedStatement preparedStatement;
        PreparedStatement fornkey;
        fornkey = connection.prepareStatement("SET FOREIGN_KEY_CHECKS=0;");
        fornkey.executeUpdate();
        preparedStatement=connection.prepareStatement(" DELETE FROM Avtosalon WHERE name = ? ");
        preparedStatement.setString(1, name);
        int n=preparedStatement.executeUpdate();
        fornkey = connection.prepareStatement("SET FOREIGN_KEY_CHECKS=1;");
        fornkey.executeUpdate();
        System.out.println("Count rows that deleted: "+n);
    }

    private static void CallProcedureForInsertToPersonBook() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\nInput Name of Seller: ");
        String name = input.next();
        System.out.println("Input model of car: ");
        String model = input.next();

        CallableStatement callableStatement;
        callableStatement= connection.prepareCall("{call InsertCar_Detail_has_Cars1(?, ?)}");
        callableStatement.setString("nameIn",name);
        callableStatement.setString("modelIN",model);
        ResultSet rs = callableStatement.executeQuery();

        while (rs.next())
        {
            String msg = rs.getString(1);
            // Simply Print the results
            System.out.format("\nResult: "+msg);
        }
    }

}