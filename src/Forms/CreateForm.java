package Forms;

import InputOutputHandling.FileNameInvalid;
import InputOutputHandling.FilePathIsNullException;
import Launcher.FormsID;
import Model.PasswordManagerModel;
import RandomPasswordGenerator.PassGen;

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
    private JLabel ErrorLabel;
    private Dictionary panels;
    private PasswordManagerModel model;
    private final Dimension size = new Dimension(650, 550);
    private final String name = "Create";

    public CreateForm(JPanel parent, Dictionary panels) {
        this.parent = parent;
        this.panels = panels;
        this.model = PasswordManagerModel.getInstance();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) parent.getLayout();
                cl.show(parent, (String) panels.get(FormsID.MAINMENU.getID()));
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = enterTheNameForTextField.getText();
                if (!name.isEmpty()) {
                    char[] password = pleaseEnterTheMasterPasswordField.getPassword();
                    if (PassGen.validPassword(password)) {
                        ErrorLabel.setText("Password is invalid!");
                    } else {
                        ErrorLabel.setText("");
                        try {
                            model.getFileHandler().setFilePath(name);
                            if (model.getFileHandler().doesFileExist()) {
                                ErrorLabel.setText("File does already exist");
                            } else {
                                model.getInstanceEnc().init(password);
                                model.getFileHandler().write( new String(model.getInstanceEnc().encryptedMasterPassword()));
                                Arrays.fill(password,(char)0);
                                //TODO switch to password menu
                                CardLayout cl = (CardLayout) parent.getLayout();
                                cl.show(parent,(String) panels.get(FormsID.PASSWORDMENU.getID()));
                            }
                        } catch (FileNameInvalid exc) {
                            ErrorLabel.setText(exc.getMessage());
                        } catch (FilePathIsNullException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    ErrorLabel.setText("No file name!");
                }
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return mainPanel;
    }

    @Override
    public String getCardName() {
        return name;
    }
}
