package Forms;

import Model.PasswordManagerModel;
import User.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;

public class PasswordMenu implements Form {
    private JPanel parent;
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JButton Save;
    private JButton setMasterPasswordButton;
    private JButton backButton;

    private ArrayList<JTable> tables;
    private Dictionary panels;
    private PasswordManagerModel model;
    private final String name = "PasswordMenu";


    public PasswordMenu(JPanel parent, Dictionary panels) {
        this.parent = parent;
        this.panels = panels;
        model = PasswordManagerModel.getInstance();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDs selected = IDs.values()[tabbedPane.getSelectedIndex()];
                switch (selected) {
                    case ENTRY: {
                        addEntry();
                        break;
                    }
                    case ENTRYINTERNET: {
                        addEntryInternet();
                        break;
                    }
                    case ENTRYEMAIL: {
                        addEntryEmail();
                        break;
                    }
                    case ENTRYGENERAL: {
                        addEntryGeneral();
                        break;
                    }
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDs selected = IDs.values()[tabbedPane.getSelectedIndex()];
                switch (selected) {
                    case ENTRY: {
                        editEntry();
                        break;
                    }
                    case ENTRYINTERNET: {
                        editEntryInternet();
                        break;
                    }
                    case ENTRYEMAIL: {
                        editEntryEmail();
                        break;
                    }
                    case ENTRYGENERAL: {
                        editEntryGeneral();
                        break;
                    }
                }

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEntry();
            }
        });
    }


    @Override
    public String getCardName() {
        return name;
    }

    @Override
    public JPanel getPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        PasswordMenu menu = new PasswordMenu(null, null);
        frame.setContentPane(menu.getPanel());
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        tables = new ArrayList<JTable>();
        tabbedPane = new JTabbedPane(SwingConstants.TOP);
        for (IDs ID_value : IDs.values()) {
            DefaultTableModel model = new DefaultTableModel(ID_value.getColumns(), 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            JTable table = new JTable(model);
            table.setFillsViewportHeight(true);
            table.getTableHeader().setResizingAllowed(false);
            table.setRowSelectionAllowed(true);
            tables.add(ID_value.getID(), table);
            tabbedPane.addTab(ID_value.getName(), new JScrollPane(table));

        }
    }

    private void addEntry() {
        int ID = Entry.getID();
        AddEntryDialog diag = new AddEntryDialog();
        diag.pack();
        diag.setVisible(true);
        if (!diag.isCancelled()) {
            String username = diag.getUsername();
            char[] password = diag.getPassword();
            if (password.length == 0) {
                model.addEntry(username);
            } else {
                model.addEntry(username, password);
            }
            Entry entry = model.getEntry(model.getSize(ID) - 1, Entry.getID());
            DefaultTableModel table = (DefaultTableModel) tables.get(ID).getModel();
            table.addRow(new String[]{entry.getUsername(), entry.getPassword()});
        }
    }

    private void editEntry() {
        int ID = Entry.getID();
        JTable tableofPane = tables.get(ID);
        int indexTab = tableofPane.getSelectedRow();
        if (indexTab != -1) {
            Entry entry = model.getEntry(indexTab, ID);
            AddEntryDialog diag = new AddEntryDialog(entry.getUsername(), entry.getPassword().toCharArray());
            diag.pack();
            diag.setVisible(true);
            if (!diag.isCancelled()) {
                entry.setUsername(diag.getUsername());
                if (diag.getPassword().length == 0) {
                    entry.generatePassword();
                } else {
                    entry.setPassword(String.valueOf(diag.getPassword()));
                }
                DefaultTableModel table = (DefaultTableModel) tables.get(ID).getModel();
                table.setValueAt(entry.getUsername(), indexTab, 0);
                table.setValueAt(entry.getPassword(), indexTab, 1);
            }
        }
    }

    private void addEntryInternet() {
        AddInternetDialog diag = new AddInternetDialog();
        diag.pack();
        diag.setVisible(true);
        if (!diag.isCancelled()) {
            String username = diag.getUsername();
            char[] password = diag.getPassword();
            String URL = diag.getURL();
            if (password.length == 0) {
                model.addEntryInternet(username, URL);
            } else {
                model.addEntryInternet(username, password, URL);
            }
            int ID = EntryInternet.getID();
            EntryInternet entry = (EntryInternet) model.getEntry(model.getSize(ID) - 1, ID);
            DefaultTableModel table = (DefaultTableModel) tables.get(ID).getModel();
            table.addRow(new String[]{entry.getUsername(), entry.getPassword(), entry.getURL()});
        }
    }

    private void editEntryInternet() {
        int ID = EntryInternet.getID();
        JTable tableofPane = tables.get(ID);
        int indexTab = tableofPane.getSelectedRow();
        if (indexTab != -1) {
            EntryInternet entry = (EntryInternet) model.getEntry(indexTab, ID);
            AddInternetDialog diag = new AddInternetDialog(entry.getUsername(), entry.getPassword().toCharArray(), entry.getURL());
            diag.pack();
            diag.setVisible(true);
            if (!diag.isCancelled()) {
                entry.setUsername(diag.getUsername());
                if (diag.getPassword().length == 0) {
                    entry.generatePassword();
                } else {
                    entry.setPassword(String.valueOf(diag.getPassword()));
                }
                entry.setURL(diag.getURL());
                DefaultTableModel table = (DefaultTableModel) tables.get(ID).getModel();
                table.setValueAt(entry.getUsername(), indexTab, 0);
                table.setValueAt(entry.getPassword(), indexTab, 1);
                table.setValueAt(entry.getURL(), indexTab, 2);
            }
        }
    }

    private void addEntryGeneral() {
        AddGeneralDialog diag = new AddGeneralDialog();
        diag.pack();
        diag.setVisible(true);
        if (!diag.isCancelled()) {
            String username = diag.getUsername();
            char[] password = diag.getPassword();
            String notes = diag.getNotes();
            if (password.length == 0) {
                model.addEntryGeneral(username, notes);
            } else {
                model.addEntryGeneral(username, password, notes);
            }
            int ID = EntryGeneral.getID();
            EntryGeneral entry = (EntryGeneral) model.getEntry(model.getSize(ID) - 1, ID);
            DefaultTableModel table = (DefaultTableModel) tables.get(ID).getModel();
            table.addRow(new String[]{entry.getUsername(), entry.getPassword(), entry.getNotes()});
        }
    }

    private void editEntryGeneral() {
        int ID = EntryGeneral.getID();
        JTable tableofPane = tables.get(ID);
        int indexTab = tableofPane.getSelectedRow();
        if (indexTab != -1) {
            EntryGeneral entry = (EntryGeneral) model.getEntry(indexTab, ID);
            AddGeneralDialog diag = new AddGeneralDialog(entry.getUsername(), entry.getPassword().toCharArray(), entry.getNotes());
            diag.pack();
            diag.setVisible(true);
            if (!diag.isCancelled()) {
                entry.setUsername(diag.getUsername());
                if (diag.getPassword().length == 0) {
                    entry.generatePassword();
                } else {
                    entry.setPassword(String.valueOf(diag.getPassword()));
                }
                entry.setNotes(diag.getNotes());
                DefaultTableModel table = (DefaultTableModel) tables.get(ID).getModel();
                table.setValueAt(entry.getUsername(), indexTab, 0);
                table.setValueAt(entry.getPassword(), indexTab, 1);
                table.setValueAt(entry.getNotes(), indexTab, 2);
            }
        }
    }

    private void addEntryEmail() {
        AddEmailDialog diag = new AddEmailDialog();
        diag.pack();
        diag.setVisible(true);
        if (!diag.isCancelled()) {
            String username = diag.getUsername();
            char[] password = diag.getPassword();
            String email = diag.getEmail();
            if (password.length == 0) {
                model.addEntryEmail(username, email);
            } else {
                model.addEntryEmail(username, password, email);
            }
            int ID = EntryEmail.getID();
            EntryEmail entry = (EntryEmail) model.getEntry(model.getSize(ID) - 1, ID);
            DefaultTableModel table = (DefaultTableModel) tables.get(ID).getModel();
            table.addRow(new String[]{entry.getUsername(), entry.getPassword(), entry.getEmail()});
        }
    }

    private void editEntryEmail() {
        int ID = EntryEmail.getID();
        JTable tableofPane = tables.get(ID);
        int indexTab = tableofPane.getSelectedRow();
        if (indexTab != -1) {
            EntryEmail entry = (EntryEmail) model.getEntry(indexTab, ID);
            AddEmailDialog diag = new AddEmailDialog(entry.getUsername(), entry.getPassword().toCharArray(), entry.getEmail());
            diag.pack();
            diag.setVisible(true);
            if (!diag.isCancelled()) {
                entry.setUsername(diag.getUsername());
                if (diag.getPassword().length == 0) {
                    entry.generatePassword();
                } else {
                    entry.setPassword(String.valueOf(diag.getPassword()));
                }
                entry.setEmail(diag.getEmail());
                DefaultTableModel table = (DefaultTableModel) tables.get(ID).getModel();
                table.setValueAt(entry.getUsername(), indexTab, 0);
                table.setValueAt(entry.getPassword(), indexTab, 1);
                table.setValueAt(entry.getEmail(), indexTab, 2);
            }
        }
    }
    private void deleteEntry(){

    }
}
