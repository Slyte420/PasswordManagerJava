package Forms;

import javax.swing.*;

public class CreateForm {
    private JPanel panel1;
    private JTextField enterTheNameForTextField;
    private JPasswordField pleaseEnterTheMasterPasswordField;
    private JButton createButton;
    private JLabel fileNameLabel;
    private JTabbedPane tabbedPane1;

    public CreateForm(JFrame parent){
        panel1.setVisible(true);
        parent.setContentPane(panel1);
    }
}
