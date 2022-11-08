package Forms;

import RandomPasswordGenerator.PassGen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddEntryDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JLabel errorLabel;
    private JPanel buttomPanel;
    private JPanel textPanel;

    private String username;
    private char[] password;

    public AddEntryDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setMinimumSize(new Dimension(200,200));
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

    public AddEntryDialog(String username, char[] password) {
        this();
        this.username = username;
        this.password = password;
        usernameTextField.setText(username);
        passwordTextField.setText(new String(password));
    }

    private void onOK() {
        username = usernameTextField.getText();
        password = passwordTextField.getText().toCharArray();
        if (PassGen.validPassword(password)) {
            dispose();
        }
        else{
            errorLabel.setText("Invalid password!");
        }
    }

    private void onCancel() {
        dispose();
    }

    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }

    public static void main(String[] args) {
        AddEntryDialog dialog = new AddEntryDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.out.println(dialog.getPassword());
    }
}
