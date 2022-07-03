package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Path;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePathGUI extends JFrame {
    private JPanel wrapper;
    private JTextField pathName_fld;
    private JButton update_btn;
    private Path path;

    public UpdatePathGUI(Path path) {
        this.path = path;
        super.add(wrapper);
        super.setSize(300, 135);
        super.setLocation(Helper.screenCenterLocation(0, super.getSize()), Helper.screenCenterLocation(1, super.getSize()));
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setTitle(Config.PROJECT_TITLE);
        super.setVisible(true);

        pathName_fld.setText(path.getName().trim());

        update_btn.addActionListener(actionEvent -> {
            if (Helper.isAllFieldsNotEmpty(pathName_fld.getText())) {
                if(Path.update(path.getId(), pathName_fld.getText())) {
                    Helper.showMessageBox("Path name updated successfully", "Path Updated", "Information");
                }
                else {
                    Helper.showMessageBox("Path name cannot be updated", "Error", "Warning");
                }
            }
            else {
                Helper.showMessageBox("Fill all the fields", "Empty Field", "Warning");
            }

            dispose();
        });
    }
}
