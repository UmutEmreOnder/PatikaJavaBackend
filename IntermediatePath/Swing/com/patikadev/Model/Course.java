package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int id;
    private int educator_id;
    private int path_id;
    private String name;
    private String lang;
    private Path path;
    private User educator;

    public Course(int id, int educator_id, int path_id, String name, String lang) {
        this.id = id;
        this.educator_id = educator_id;
        this.path_id = path_id;
        this.name = name;
        this.lang = lang;
        this.path = Path.getFetch(this.path_id);
        this.educator = User.getFetch(this.educator_id);
    }

    public static ArrayList<Course> getList() {
        ArrayList<Course> courseList = new ArrayList<>();
        String query = "SELECT * FROM public.\"lessons\"";

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                courseList.add(new Course(rs.getInt(1), rs.getInt(3), rs.getInt(4), rs.getString(2), rs.getString(5)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return courseList;
    }

    public static Course getFetch(int id) {
        Course obj = null;

        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.\"lessons\" WHERE id = " + id);

            if (rs.next()) {
                obj = new Course(rs.getInt(1), rs.getInt(3), rs.getInt(4), rs.getString(2), rs.getString(5));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }

    public static ArrayList<Course> getList(int user_id) {
        ArrayList<Course> courseList = new ArrayList<>();
        String query = "SELECT * FROM public.\"lessons\" WHERE educator_id = " + user_id;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                courseList.add(new Course(rs.getInt(1), rs.getInt(3), rs.getInt(4), rs.getString(2), rs.getString(5)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return courseList;
    }

    public static boolean deleteLessonsByEducator(int educator_id) {
        String query = "DELETE FROM public.\"lessons\" WHERE educator_id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, educator_id);
            pr.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteLessonsByPath(int path_id) {
        String query = "DELETE FROM public.\"lessons\" WHERE path_id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, path_id);
            pr.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean add(String name, String lang, Item educator, Item path) {
        String query = "INSERT INTO public.\"lessons\" (name, educator_id, path_id, lang) VALUES(?, ?, ?, ?)";

        int result;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setInt(2, educator.getKey());
            pr.setInt(3, path.getKey());
            pr.setString(4, lang);

            result = pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result == 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEducator_id() {
        return educator_id;
    }

    public void setEducator_id(int educator_id) {
        this.educator_id = educator_id;
    }

    public int getPath_id() {
        return path_id;
    }

    public void setPath_id(int path_id) {
        this.path_id = path_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public User getEducator() {
        return educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }
}
