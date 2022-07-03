package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Path {
    private int id;
    private String name;

    public Path(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ArrayList<Path> getList() {
        ArrayList<Path> pathArrayList = new ArrayList<>();
        Path obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM public.\"paths\"");

            while (resultSet.next()) {
                obj = new Path(resultSet.getInt(1), resultSet.getString(2));
                pathArrayList.add(obj);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pathArrayList;
    }

    public static boolean add(String name) {
        String query = "INSERT INTO public.\"paths\" (name) VALUES(?)";

        int result;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            result = pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result == 1;
    }

    public static boolean update(int id, String name) {
        String query = "UPDATE public.\"paths\" SET name = ? WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setInt(2, id);

            return pr.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Path getFetch(int id) {
        Path obj = null;

        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.\"paths\" WHERE id = " + id);

            if (rs.next()) {
                obj = new Path(rs.getInt(1), rs.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }

    public static boolean remove(int id) {
        try {
            Statement statement = DBConnector.getInstance().createStatement();
            int rs = statement.executeUpdate("DELETE FROM public.\"paths\" WHERE id = " + id);
            return rs == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
}
