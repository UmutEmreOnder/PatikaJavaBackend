package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.sql.*;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String uname;
    private String pass;
    private String type;

    public User(int id, String name, String uname, String pass, String type) {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.pass = pass;
        this.type = type;
    }

    public static ArrayList<User> getList() {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM public.\"userTable\"";

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                userList.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("uname"), rs.getString("pass"), rs.getString("userType")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    public static boolean add(String name, String uname, String pass, String type) {
        if (!isUserNameUnique(uname)) {
            return false;
        }

        String query = "INSERT INTO public.\"userTable\" (name, uname, pass, \"userType\") VALUES(?, ?, ?, ?)";

        int result;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, uname);
            pr.setString(3, pass);
            pr.setString(4, type);

            result = pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result == 1;
    }

    public static boolean delete(int id) {
        String query = "DELETE FROM public.\"userTable\" WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            pr.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean update(int id, String name, String uname, String pass, String type) {
        if (!Helper.isAllFieldsNotEmpty(name, uname, pass, type) || !changesItself(uname, id)) {
            return false;
        }

        String query = "UPDATE public.\"userTable\" SET name = ?, uname = ?, pass = ?, \"userType\" = ? WHERE id = ?";

        int result;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, uname);
            pr.setString(3, pass);
            pr.setString(4, type);
            pr.setInt(5, id);

            result = pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result == 1;
    }

    public static ArrayList<User> searchUserList(String query) {
        ArrayList<User> userList = new ArrayList<>();

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                userList.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("uname"), rs.getString("pass"), rs.getString("userType")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    public static User getFetch(int id) {
        User obj = null;

        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.\"userTable\" WHERE id = " + id);

            if (rs.next()) {
                obj = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }

    public static ArrayList<User> getEducatorList() {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM public.\"userTable\" WHERE \"userType\" = 'educator'";

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userList;
    }

    public static String searchQuery(String name, String uname, String type) {
        String query = "SELECT * FROM public.\"userTable\" WHERE uname ILIKE '%{{uname}}%' AND name ILIKE '%{{name}}%' AND \"userType\" ILIKE '%{{type}}%'";

        query = query.replace("{{uname}}", uname);
        query = query.replace("{{name}}", name);
        query = query.replace("{{type}}", type);

        return query;
    }

    public static boolean isUserNameUnique(String uname) {
        String unameChecker = "SELECT * FROM public.\"userTable\" WHERE uname = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(unameChecker);
            pr.setString(1, uname);
            ResultSet result = pr.executeQuery();

            int rowNum = getRowCount(result);

            return rowNum == 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean changesItself(String uname, int id) {
        String unameChecker = "SELECT * FROM public.\"userTable\" WHERE uname = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(unameChecker);
            pr.setString(1, uname);
            ResultSet result = pr.executeQuery();

            while (result.next()) {
                if (result.getInt(1) != id) {
                    return false;
                }
            }

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getRowCount(ResultSet resultSet) {
        int count = 0;

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ++count;
        }

        return count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
