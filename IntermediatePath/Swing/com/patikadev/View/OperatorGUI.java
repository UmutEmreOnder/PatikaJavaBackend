package com.patikadev.View;

import com.patikadev.Helper.*;
import com.patikadev.Model.Course;
import com.patikadev.Model.Operator;
import com.patikadev.Model.Path;
import com.patikadev.Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class OperatorGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane paths_pnl;
    private JLabel welcome_lbl;
    private JPanel top_pnl;
    private JButton exit_btn;
    private JPanel userList_pnl;
    private JScrollPane userList_scroll;
    private JTable userList_tbl;
    private JPanel form_pnl;
    private JTextField namesurname_fld;
    private JTextField uname_field;
    private JPasswordField pass_fld;
    private JComboBox usertype_cmb;
    private JButton adduser_btn;
    private JPanel add_pnl;
    private JPanel remove_pnl;
    private JTextField deleteId_fld;
    private JButton delete_btn;
    private JLabel deleteId_lbl;
    private JTextField searchname_fld;
    private JTextField searchuname_fld;
    private JComboBox searchtype_cmb;
    private JButton search_btn;
    private JScrollPane path_scrll;
    private JTable path_tbl;
    private JPanel pathAdd_pnl;
    private JTextField pathName_fld;
    private JButton addPath_btn;
    private JPanel lessons_pnl;
    private JTable lessons_tbl;
    private JPanel lessonsAdd_pnl;
    private JTextField lessonName_fld;
    private JTextField lessonLang_fld;
    private JComboBox lessonsPath_cmb;
    private JComboBox lessonsEducator_cmb;
    private JButton lessonsAdd_btn;
    private DefaultTableModel userList_mdl;
    private Object[] userlist_row;
    private DefaultTableModel pathList_mdl;
    private Object[] pathList_row;
    private final Operator operator;
    private JPopupMenu pathMenu;
    private DefaultTableModel lessonsList_mdl;
    private Object[] lessonsList_row;

    public OperatorGUI(Operator operator) {
        this.operator = operator;

        super.add(this.wrapper);
        super.setSize(1500, 1000);

        super.setLocation(Helper.screenCenterLocation(0, super.getSize()), Helper.screenCenterLocation(1, super.getSize()));
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setTitle(Config.PROJECT_TITLE);
        super.setVisible(true);

        welcome_lbl.setText(welcome_lbl.getText() + " " + this.operator.getName());


        Object[] userList_col = {"ID", "Name Surname", "Nickname", "Password", "User Type"};
        userlist_row = new Object[userList_col.length];

        userList_mdl = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        userList_mdl = new DefaultTableModel();
        userList_mdl.setColumnIdentifiers(userList_col);
        updateUserTable();
        userList_tbl.setModel(userList_mdl);
        userList_tbl.getTableHeader().setReorderingAllowed(false);
        userList_tbl.getColumnModel().getColumn(0).setMaxWidth(70);

        Object[] lessonsList_col = {"ID", "Name", "Educator ID", "Path ID", "Language"};
        lessonsList_row = new Object[lessonsList_col.length];

        lessonsList_mdl = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        lessonsList_mdl = new DefaultTableModel();
        lessonsList_mdl.setColumnIdentifiers(lessonsList_col);
        lessons_tbl.setModel(lessonsList_mdl);
        lessons_tbl.getColumnModel().getColumn(0).setMaxWidth(70);
        lessons_tbl.getTableHeader().setReorderingAllowed(false);
        updateLessonsTable();

        updateComboBoxes();

        userList_tbl.getSelectionModel().addListSelectionListener(listSelectionEvent -> {
            try {
                int id = (int) userList_tbl.getValueAt(userList_tbl.getSelectedRow(), 0);
                deleteId_fld.setText(String.valueOf(id));
            } catch (Exception ignored) {}
        });


        pathMenu = new JPopupMenu();
        JMenuItem updateMenu = new JMenuItem("Update");
        JMenuItem deleteMenu = new JMenuItem("Delete");
        pathMenu.add(updateMenu);
        pathMenu.add(deleteMenu);

        pathList_mdl = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) return false;

                return super.isCellEditable(row, column);
            }
        };

        Object[] pathList_col = {"ID", "Path Name"};
        pathList_mdl.setColumnIdentifiers(pathList_col);
        pathList_row = new Object[pathList_col.length];
        path_tbl.setModel(pathList_mdl);
        path_tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPopupMenuFor(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                showPopupMenuFor(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopupMenuFor(e);
            }

            private void showPopupMenuFor(MouseEvent e) {
                if (pathMenu.isPopupTrigger(e)) {
                    Point point = e.getPoint();
                    int row = path_tbl.rowAtPoint(point);

                    if (!path_tbl.isRowSelected(row)) {
                        path_tbl.setRowSelectionInterval(row, row);
                    }

                    pathMenu.show(path_tbl, e.getX(), e.getY());
                }
            }
        });

        updateMenu.addActionListener(actionEvent -> {
            int select_id = Integer.parseInt(path_tbl.getValueAt(path_tbl.getSelectedRow(), 0).toString());
            UpdatePathGUI updatePathGUI = new UpdatePathGUI(Path.getFetch(select_id));
            updatePathGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    updatePathTable();
                    updateComboBoxes();
                }
            });
        });

        deleteMenu.addActionListener(actionEvent -> {
            int select_id = Integer.parseInt(path_tbl.getValueAt(path_tbl.getSelectedRow(), 0).toString());
            if (Helper.confirm()) {
                if(Path.remove(select_id)) {
                    Helper.showMessageBox("Path removed successfully", "Path Removed", "Information");
                    deleteLessonsByPath(select_id);
                    updateComboBoxes();
                    updateUserTable();
                } else {
                    Helper.showMessageBox("Path cannot be removed", "Error", "Warning");
                    updateUserTable();
                }
                updatePathTable();
            }
        });

        updatePathTable();
        path_tbl.getTableHeader().setReorderingAllowed(false);
        path_tbl.getColumnModel().getColumn(0).setMaxWidth(70);
     //   path_tbl.setComponentPopupMenu(pathMenu);


        userList_tbl.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int id = Integer.parseInt(userList_tbl.getValueAt(userList_tbl.getSelectedRow(), 0).toString());
                String name = userList_tbl.getValueAt(userList_tbl.getSelectedRow(), 1).toString();
                String uname = userList_tbl.getValueAt(userList_tbl.getSelectedRow(), 2).toString();
                String pass = userList_tbl.getValueAt(userList_tbl.getSelectedRow(), 3).toString();
                String type = userList_tbl.getValueAt(userList_tbl.getSelectedRow(), 4).toString();

                if(User.update(id, name, uname, pass, type)) {
                    Helper.showMessageBox("User updated successfully", "User Updated", "Information");
                    updateComboBoxes();
                    updateUserTable();
                } else {
                    Helper.showMessageBox("User cannot be updated", "Error", "Warning");
                    updateUserTable();
                }
            }

        });



        adduser_btn.addActionListener(actionEvent -> {

            if (!Helper.isAllFieldsNotEmpty(namesurname_fld.getText(), uname_field.getText(), pass_fld.getText())) {
                Helper.showMessageBox("Fill all the fields", "Empty Field", "Warning");
            } else {
                if (User.add(namesurname_fld.getText(), uname_field.getText(), pass_fld.getText(), (String) usertype_cmb.getSelectedItem())) {
                    Helper.showMessageBox("User added successfully", "User Added", "Information");
                    updateComboBoxes();
                } else {
                    Helper.showMessageBox("User cannot be added", "Error", "Warning");
                }

                updateUserTable();

                Helper.emptyFields(namesurname_fld, uname_field, pass_fld);
            }
        });

        delete_btn.addActionListener(actionEvent -> {
            if (!Helper.isAllFieldsNotEmpty(deleteId_fld.getText())) {
                Helper.showMessageBox("Fill all the fields", "Empty Field", "Warning");

            } else {
                int id = Integer.parseInt(deleteId_fld.getText());
                if (Helper.confirm()) {
                    if (User.delete(id)) {
                        Helper.showMessageBox("User deleted successfully", "User deleted", "Information");
                        deleteLessonsByEducator(id);
                        updateComboBoxes();
                    } else {
                        Helper.showMessageBox("User cannot be deleted", "Error", "Warning");
                    }
                }
                updateUserTable();
                Helper.emptyFields(deleteId_fld);
            }
        });


        search_btn.addActionListener(actionEvent -> {
            String name = searchname_fld.getText();
            String uname = searchuname_fld.getText();
            String type = searchtype_cmb.getSelectedItem().toString();

            ArrayList<User> searchedUser = User.searchUserList(User.searchQuery(name, uname, type));
            updateTable(searchedUser);
            Helper.emptyFields(searchname_fld, searchuname_fld);
            searchtype_cmb.setSelectedIndex(0);
        });

        exit_btn.addActionListener(actionEvent -> dispose());
        addPath_btn.addActionListener(actionEvent -> {
            if (Helper.isAllFieldsNotEmpty(pathName_fld.getText())) {
                if (Path.add(pathName_fld.getText())) {
                    Helper.showMessageBox("Path added successfully", "Path added", "Information");
                    updateComboBoxes();
                }
                else {
                    Helper.showMessageBox("Path cannot be added", "Error", "Warning");
                }
            }
            else {
                Helper.showMessageBox("Fill all the fields", "Empty Field", "Warning");
            }

            pathName_fld.setText(null);
            updatePathTable();
        });

        lessonsAdd_btn.addActionListener(actionEvent -> {
            if (Helper.isAllFieldsNotEmpty(lessonName_fld.getText(), lessonLang_fld.getText())) {
                Item pathItem = (Item) lessonsPath_cmb.getSelectedItem();
                Item userItem = (Item) lessonsEducator_cmb.getSelectedItem();
                if (Course.add(lessonName_fld.getText(), lessonLang_fld.getText(), userItem, pathItem)) {
                    Helper.showMessageBox("Course added successfully", "Course added", "Information");
                }
                else {
                    Helper.showMessageBox("Course cannot be added", "Error", "Warning");
                }
            }
            else {
                Helper.showMessageBox("Fill all the fields", "Empty Field", "Warning");
            }

            updateLessonsTable();
            lessonName_fld.setText(null);
            lessonLang_fld.setText(null);
        });
    }

    private void updateLessonsTable() {
        DefaultTableModel clear = (DefaultTableModel) lessons_tbl.getModel();
        clear.setRowCount(0);

        for (Course obj : Course.getList()) {
            lessonsList_row[0] = obj.getId();
            lessonsList_row[1] = obj.getName();
            lessonsList_row[2] = obj.getEducator_id();
            lessonsList_row[3] = obj.getPath_id();
            lessonsList_row[4] = obj.getLang();

            lessonsList_mdl.addRow(lessonsList_row);
        }
    }

    private void updatePathTable() {
        DefaultTableModel clear = (DefaultTableModel) path_tbl.getModel();
        clear.setRowCount(0);

        for (Path obj : Path.getList()) {
            pathList_row[0] = obj.getId();
            pathList_row[1] = obj.getName();

            pathList_mdl.addRow(pathList_row);
        }
    }

    public void deleteLessonsByEducator(int id) {
        Course.deleteLessonsByEducator(id);
        updateLessonsTable();
    }

    public void deleteLessonsByPath(int id) {
        Course.deleteLessonsByPath(id);
        updateLessonsTable();
    }

    public void updateUserTable() {
        DefaultTableModel clear = (DefaultTableModel) userList_tbl.getModel();
        clear.setRowCount(0);

        for (User obj : User.getList()) {
            userlist_row[0] = obj.getId();
            userlist_row[1] = obj.getName();
            userlist_row[2] = obj.getUname();
            userlist_row[3] = obj.getPass();
            userlist_row[4] = obj.getType();

            userList_mdl.addRow(userlist_row);
        }

    }

    public void updateComboBoxes() {
        lessonsEducator_cmb.removeAllItems();
        lessonsPath_cmb.removeAllItems();

        for (Path path : Path.getList()) {
            Item item = new Item(path.getId(), path.getName());
            lessonsPath_cmb.addItem(item);
        }

        for (User user : User.getEducatorList()) {
            Item item = new Item(user.getId(), user.getName());
            lessonsEducator_cmb.addItem(item);
        }
    }

    public void updateTable(ArrayList<User> searchedUser) {
        DefaultTableModel clear = (DefaultTableModel) userList_tbl.getModel();
        clear.setRowCount(0);

        for (User obj : searchedUser) {
            userlist_row[0] = obj.getId();
            userlist_row[1] = obj.getName();
            userlist_row[2] = obj.getUname();
            userlist_row[3] = obj.getPass();
            userlist_row[4] = obj.getType();

            userList_mdl.addRow(userlist_row);
        }

    }
}
