package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.example.models.order;
public class Main{
    public static final String ORDER_LIST = "ORDER";
    public static void main(String[] args) {
       String url = "jdbc:sqlite:Order.db";

       // Database Creation
       String createQuery = " CREATE TABLE IF NOT EXISTS " + ORDER_LIST + " (order_id integer PRIMARY KEY NOT NULL,"+
                "name text," +
                "size integer,";

        String insertQuery = "INSERT INTO " + ORDER_LIST + " (name, size,) values(?,?)";

        String selectQuery = "SELECT * FROM" + ORDER_LIST;
        try{

            // Database connection
            Connection c = DriverManager.getConnection(url);
            System.out.println("CONNECTED");
            Statement statement = c.createStatement();
            statement.execute(createQuery);
            System.out.println("Code executed successfully");

            // Inserting values
            PreparedStatement preparedStatement = c.prepareStatement(insertQuery);
            order order1 = new order("Ram",30);
            preparedStatement.setString(1, order1.getName());
            preparedStatement.setInt(2,order1.getSize());
            preparedStatement.execute();
            System.out.println("Values inserted");


            ResultSet res = statement.executeQuery(selectQuery);
            List<order> orderList_res= new ArrayList<>();

            while (res.next()){
                String name = res.getString("name");
                int size = res.getInt("size");

                order s = new order(
                        name,
                        size
                );
            }

        }
        catch (Exception e){
            System.out.println("error");
            e.printStackTrace();
        }
    }
}