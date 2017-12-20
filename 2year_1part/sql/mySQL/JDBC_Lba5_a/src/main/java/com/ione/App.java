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
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement=connection.createStatement();
            updateDataAvtosalon();
            insertDataAvtosalon();
            DeleteDataAvtosalon();
            CallProcedureForInsertToCar_Detail();
            readData();
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver is not loaded");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { } // ignore
            if (statement != null) try { statement.close(); } catch (SQLException e) { }
            if (connection != null) try { connection.close(); } catch (SQLException e) { }
        }
    }

    private static void readData() throws SQLException {
        rs=statement.executeQuery("SELECT COUNT(*) FROM Cars");
        while (rs.next()) {
            int count = rs.getInt(1);
            // Simply Print the results
            System.out.format("\ncount: %d\n", count);
        }
        rs=statement.executeQuery("SELECT * FROM Cars");

        System.out.format("\n--------------------Table Cars --------------------\n");
        System.out.format("%1s %-12s %-12s\n", "Avtosalon_idAvtosalon", "idCars", "seler");
        while (rs.next())
        {
            int id=rs.getInt("Avtosalon_idAvtosalon");
            int idCars = rs.getInt("idCars");
            String seler = rs.getString("seler");
            System.out.format("%1s %22s %15s\n", id, idCars, seler);
        }
        System.out.format("---------------------------------------------------\n");
        rs=statement.executeQuery("SELECT * FROM Car_Detail");
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
        System.out.format("---------------------------------------------------------------------------------\n");
        rs=statement.executeQuery("SELECT * FROM Avtosalon");
        System.out.format("\n--------------------Table Avtosalon ----------------\n");
        System.out.format("%3s %10s %18s \n", "idAvtosalon","name","ownerName");
        while (rs.next())
        {
            int idAvtosalon =rs.getInt("idAvtosalon");
            String name = rs.getString("name");
            String ownerName = rs.getString("ownerName");
            System.out.format("%3d %18s %18s \n",idAvtosalon, name,ownerName);
        }
        System.out.format("---------------------------------------------------\n");
        String query="Select " +
                "(SELECT  seler From Cars WHERE idCars=P.Cars_idCars) AS seler, " +
                "(SELECT model FROM Car_Detail WHERE idCar_Detail=P.Car_Detail_idCar_Detail) AS model "+
                "FROM Car_Detail_has_Cars1 AS P";
        rs=statement.executeQuery(query);
        System.out.format("\n---------Joining Table Car AND AVTOSALON --------\n");
        System.out.format("%-15s %s\n", "Seller", "model of car");
        while (rs.next())
        {
            String  seler = rs.getString("seler");
            String  model = rs.getString("model");
            System.out.format("%-15s %s\n", seler, model);
        }
        System.out.format("-------------------------------------------------\n");
    }

    private static void updateDataAvtosalon() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input name of Avtosalon what you want to update: ");
        String name = input.next();
        System.out.println("Input new name Avtosalon for : "+ name);
        String AvtosalonNew = input.next();
        statement.execute("UPDATE Avtosalon SET name='"+AvtosalonNew+"' WHERE name='"+name+"';");
        int n=statement.executeUpdate("UPDATE Avtosalon SET name='"+AvtosalonNew+"' WHERE name='"+name+"';");
        System.out.println("Count rows that updated: "+n);
    }

    private static void insertDataAvtosalon() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a new name of Autosalon: ");
        String name = input.next();
        System.out.println("Input a new name of owner: ");
        String ownerName = input.next();
        PreparedStatement preparedStatement;
        preparedStatement=connection.prepareStatement("INSERT Avtosalon VALUES (?,?,?)");
        preparedStatement.setString(1, null);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, ownerName);
        int n=preparedStatement.executeUpdate();
        System.out.println("Count rows that inserted: "+n);
    }

    private static void DeleteDataAvtosalon() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a name Autosalon for delete: ");
        String name = input.next();
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

    private static void CallProcedureForInsertToCar_Detail() throws SQLException {
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
            System.out.format("\nResult: "+msg);
        }
    }

}