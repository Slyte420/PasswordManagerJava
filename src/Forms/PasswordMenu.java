package Forms;

import Model.PasswordManagerModel;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;

public class PasswordMenu implements Form{
    private JPanel parent;
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton insertButton;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JButton button1;
    private JButton setMasterPasswordButton;
    private JTable table1;

    private Dictionary panels;
    private PasswordManagerModel model;
    private final String name = "PasswordMenu";


    public PasswordMenu(JPanel parent, Dictionary panels){
        this.parent = parent;
        this.panels = panels;
        model = PasswordManagerModel.getInstance();
    }
    @Override
    public String getCardName() {
        return name;
    }

    @Override
    public JPanel getPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here Tabbed pane
    }
}
