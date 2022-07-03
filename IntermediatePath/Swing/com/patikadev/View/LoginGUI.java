package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Operator;
import com.patikadev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {

    private JPanel wrapper;
    private JPanel wrapperTop;
    private JPanel wrapperBottom;
    private JTextField username_fld;
    private JPasswordField pass_fld;
    private JButton login_btn;

    public LoginGUI() {
        super.add(wrapper);
        super.setSize(400, 400);
        super.setLocation(Helper.screenCenterLocation(0, super.getSize()), Helper.screenCenterLocation(1, super.getSize()));
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setTitle(Config.PROJECT_TITLE);
        super.setResizable(false);
        super.setVisible(true);

        login_btn.addActionListener(actionEvent -> {
            if (Helper.isAllFieldsNotEmpty(pass_fld.getText(), username_fld.getText())) {
                Operator user = Operator.getFetch(username_fld.getText(), pass_fld.getText());

                if (user == null) {
                    Helper.showMessageBox("User is not found", "Error", "Warning");
                }

                else {
                    new OperatorGUI(user);
                    super.dispose();
                }
            }
            else {
                Helper.showMessageBox("Fill all the fields", "Empty Field", "Warning");
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        new LoginGUI();
    }
}
