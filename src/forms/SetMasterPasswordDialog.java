package forms;

import randompasswordgenerator.PassGen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SetMasterPasswordDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField passwordField;
    private JPanel buttonPanel;
    private JLabel errorLabel;

    private char[] password;
    private boolean cancelled;

    public SetMasterPasswordDialog() {
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

    private void onOK() {
        password = passwordField.getText().toCharArray();
        if (PassGen.validPassword(password)) {
            dispose();
        } else {
            errorLabel.setText("Invalid password!");
        }
    }

    private void onCancel() {
        cancelled = true;
        dispose();
    }

    public char[] getPassword() {
        return password;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public static void main(String[] args) {
        SetMasterPasswordDialog dialog = new SetMasterPasswordDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
