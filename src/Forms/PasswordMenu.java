package Forms;

import javax.swing.*;
import java.awt.*;

public class PasswordMenu implements Form{
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton insertButton;

    private final String name = "PasswordMenu";
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        PasswordMenu menu = new PasswordMenu();
        frame.setContentPane(menu.getPanel());
        frame.setMinimumSize(new Dimension(800,600));
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public String getCardName() {
        return name;
    }

    @Override
    public JPanel getPanel() {
        return panel1;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
