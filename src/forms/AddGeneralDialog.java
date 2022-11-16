package forms;

import randompasswordgenerator.PassGen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddGeneralDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JTextField notesTextField;
    private JLabel errorLabel;

    private String username;
    private char[] password;
    private String notes;
    private boolean cancelled;
    public AddGeneralDialog() {
        cancelled = false;
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

    public AddGeneralDialog(String username, char[] password,String notes) {
        this();
        this.username = username;
        this.password = password;
        this.notes = notes;
        usernameTextField.setText(username);
        passwordTextField.setText(new String(password));
        notesTextField.setText(notes);
    }
    private void onOK() {
        username = usernameTextField.getText();
        password = passwordTextField.getText().toCharArray();
        notes = notesTextField.getText();
        if (PassGen.validOther(notes)) {
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
            errorLabel.setText("Invalid notes!");
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

    public String getNotes() {
        return notes;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
