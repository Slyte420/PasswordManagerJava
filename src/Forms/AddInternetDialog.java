package Forms;

import RandomPasswordGenerator.PassGen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class AddInternetDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel errorLabel;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JTextField URLTextField;

    private String username;
    private char[] password;
    private String URL;
    public AddInternetDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setMinimumSize(new Dimension(200,250));
        setResizable(false);
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
    public AddInternetDialog(String username, char[] password,String notes) {
        this();
        this.username = username;
        this.password = password;
        this.URL = URL;
        usernameTextField.setText(username);
        passwordTextField.setText(new String(password));
        URLTextField.setText(URL);
    }
    private void onOK() {
        username = usernameTextField.getText();
        password = passwordTextField.getText().toCharArray();
        URL = URLTextField.getText();
        if (PassGen.validPassword(password)) {
            dispose();
        }
        else{
            errorLabel.setText("Invalid password!");
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AddInternetDialog dialog = new AddInternetDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
