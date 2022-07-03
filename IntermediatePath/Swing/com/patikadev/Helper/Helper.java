package com.patikadev.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static int screenCenterLocation(int point, Dimension size) {
        switch (point) {
            case 0:
                return (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case 1:
                return (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default:
                return -1;
        }
    }

    public static void setLayout() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if (info.getName().equals("Nimbus")) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static boolean isAllFieldsNotEmpty (String... fields) {
        for (String s : fields) {
            if (s.trim().isEmpty()) return false;
        }

        return true;
    }

    public static boolean confirm() {
        return JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void showMessageBox(String message, String head, String type) {
        JOptionPane.showMessageDialog(null, message, head, type.equals("Warning") ? JOptionPane.WARNING_MESSAGE : type.equals("Information") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.QUESTION_MESSAGE);
    }

    public static void emptyFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText(null);
        }
    }
}
