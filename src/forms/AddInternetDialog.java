package forms;

import randompasswordgenerator.PassGen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    private boolean cancelled;

    public AddInternetDialog() {
        cancelled = false;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setMinimumSize(new Dimension(200, 250));
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

    public AddInternetDialog(String username, char[] password, String URL) {
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
        if (PassGen.validOther(URL)) {
            if (PassGen.validUsername(username)) {
                if (PassGen.validPassword(password) || password.length == 0) {
                    dispose();
                } else {
                    errorLabel.setText("Invalid password!");
                }
            } else {
                errorLabel.setText("Invalid username!");
            }
        } else {
            errorLabel.setText("Invalid URL!");
        }
    }

    private void onCancel() {
        cancelled = true;
        dispose();
    }

    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }

    public String getURL() {
        return URL;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
