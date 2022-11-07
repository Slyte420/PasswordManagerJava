package Forms;

import Model.PasswordManagerModel;
import User.IDs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                JDialog add = new AddEntryDialog();
                add.pack();
                add.setVisible(true);
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

            tabbedPane.addTab(ID_value.getName(), new JScrollPane(table));

        }
    }
}
