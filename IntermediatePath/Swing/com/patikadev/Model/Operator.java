package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Operator extends User {
    public Operator(int id, String name, String uname, String pass, String type) {
        super(id, name, uname, pass, type);
    }

    public static Operator getFetch(String uname, String password) {
        Operator obj = null;

        try {
            Statement statement = DBConnector.getInstance().createStatement();
            String query = "SELECT * FROM public.\"userTable\" WHERE uname = '" + uname + "' AND pass = '" + password + "' AND \"userType\" = 'operator'";
            ResultSet rs = statement.executeQuery(query);
            System.out.println(query);

            if (rs.next()) {
                obj = new Operator(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }
}
