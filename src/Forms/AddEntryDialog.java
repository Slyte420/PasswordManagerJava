package Forms;

import javax.swing.*;
import java.awt.event.*;

public class AddEntryDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField usernameTextField;
    private JTextField passwordTextField;

    private String username;
    private char[] password;
    public AddEntryDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        username = usernameTextField.getText();
        password = passwordTextField.getText().toCharArray();
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public String getUsername(){
        return username;
    }

    public char[] getPassword() {
        return password;
    }

    public static void main(String[] args) {
        AddEntryDialog dialog = new AddEntryDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
