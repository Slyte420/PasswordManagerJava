package Forms;

import InputOutputHandling.FileNameInvalid;
import InputOutputHandling.FilePathIsNullException;
import Model.PasswordManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public CreateForm(JPanel parent, Dictionary panels, PasswordManagerModel model) {
        this.parent = parent;
        this.panels = panels;
        this.model = model;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) parent.getLayout();
                cl.show(parent, (String) panels.get(0));
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = enterTheNameForTextField.getText();
                if (!name.isEmpty()) {
                    char[] password = pleaseEnterTheMasterPasswordField.getPassword();
                    if (password.length == 0 || password.length > 24) {
                        ErrorLabel.setText("Password is too short or too long!");
                    } else {
                        ErrorLabel.setText("");
                        try {
                            model.getFileHandler().setFilePath(name + ".db");
                            if (model.getFileHandler().doesFileExist()) {
                                ErrorLabel.setText("File does already exist");
                            } else {
                                model.getInstanceEnc().init(password);
                                model.getFileHandler().write( new String(model.getInstanceEnc().encrypt(password)));
                                //TODO switch to password menu
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
