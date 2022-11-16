package forms;

import inputoutputhandling.FileNameInvalidException;
import inputoutputhandling.FilePathIsNullException;
import launcher.FormsID;
import model.PasswordManagerModel;
import randompasswordgenerator.PassGen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Dictionary;

public class CreateForm implements Form {
    private JPanel parent;
    private JPanel mainPanel;
    private JTextField enterTheNameForTextField;
    private JPasswordField pleaseEnterTheMasterPasswordField;
    private JButton createButton;
    private JLabel fileNameLabel;
    private JButton backButton;
    private JLabel errorLabel;
    private Dictionary panels;
    private PasswordManagerModel model;
    private static final String NAME = "Create";

    public CreateForm(JPanel parent, Dictionary panels) {
        this.parent = parent;
        this.panels = panels;
        this.model = PasswordManagerModel.getInstance();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
                CardLayout cl = (CardLayout) parent.getLayout();
                cl.show(parent, (String) panels.get(FormsID.MAINMENU.getID()));
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = enterTheNameForTextField.getText();
                if (!name.isEmpty() && !name.equals("temp")) {
                    char[] password = pleaseEnterTheMasterPasswordField.getPassword();
                    if (!PassGen.validPassword(password)) {
                        errorLabel.setText("Password is invalid!");
                    } else {
                        errorLabel.setText("");
                        try {
                            model.getFileHandler().setFilePath(name);
                            if (model.getFileHandler().doesFileExist()) {
                                errorLabel.setText("File does already exist");
                            } else {
                                model.getInstanceEnc().init(password);
                                model.getFileHandler().write( new String(model.getInstanceEnc().encryptedMasterPassword()));
                                Arrays.fill(password,(char)0);
                                reset();
                                CardLayout cl = (CardLayout) parent.getLayout();
                                cl.show(parent,(String) panels.get(FormsID.PASSWORDMENU.getID()));
                            }
                        } catch (FileNameInvalidException exc) {
                            errorLabel.setText(exc.getMessage());
                        } catch (FilePathIsNullException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    errorLabel.setText("No file name or wrong file name!");
                }
            }
        });
    }

    private void reset(){
        pleaseEnterTheMasterPasswordField.setText("");
        errorLabel.setText("");
        enterTheNameForTextField.setText("");
    }
    @Override
    public JPanel getPanel() {
        return mainPanel;
    }

    @Override
    public String getCardName() {
        return NAME;
    }
}
