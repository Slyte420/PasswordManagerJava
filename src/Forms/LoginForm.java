package Forms;

import InputOutputHandling.FileHandler;
import InputOutputHandling.FileNameInvalid;
import InputOutputHandling.FilePathIsNullException;
import Launcher.FormsID;
import Model.PasswordManagerModel;
import User.IDs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;

public class LoginForm implements Form {
    private JPanel parent;
    private JPanel mainPanel;

    private JPasswordField pleaseEnterYourMasterPasswordField;
    private JButton loginButton;
    private JLabel errorLabel;
    private JButton loadButton;
    private JButton backButton;
    private JLabel fileNameLabel;
    private Dictionary panels;
    private PasswordManagerModel model;
    private final String name = "LoginForm";
    private final Dimension size = new Dimension(350, 350);

    public LoginForm(JPanel parent, Dictionary panels) {
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
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFile();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginIntoFile();
            }
        });
    }


    private void loadFile() {
        FileChoosingDialog diag = new FileChoosingDialog(model.getFileHandler().getFolderPath(), model.getFileHandler().getFileFormat());
        String filename = diag.getFile();
        if (filename != null) {
            fileNameLabel.setText(filename);
            filename = filename.replace(model.getFileHandler().getFileFormat(), "");
            try {
                model.getFileHandler().setFilePath(filename);
            } catch (FileNameInvalid e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void insertEntriesFromFile() {
        FileHandler handler = model.getFileHandler();
        int lineNumbers = handler.getNumberLines();
        for (int lineindex = 2; lineindex <= lineNumbers; ++lineindex) {
            String line = null;
            try {
                line = handler.read(lineindex);
            } catch (FilePathIsNullException e) {
                throw new RuntimeException(e);
            }
            String[] decrypted = model.getInstanceEnc().decrypt(line).split(" ");
            int IDindex = Integer.parseInt(decrypted[0]);
            IDs ID = IDs.values()[IDindex];
            switch (ID){
                case ENTRY:{
                    model.addEntry(decrypted[1],decrypted[2]);
                    break;
                }
                case ENTRYEMAIL:{
                    model.addEntryEmail(decrypted[1],decrypted[2],decrypted[3]);
                    break;
                }
                case ENTRYINTERNET: {
                    model.addEntryInternet(decrypted[1],decrypted[2],decrypted[3]);
                    break;
                }
                case ENTRYGENERAL:{
                    model.addEntryGeneral(decrypted[1],decrypted[2],decrypted[3]);
                    break;
                }
            }
        }
    }

    private void loginIntoFile() {
        char[] password = pleaseEnterYourMasterPasswordField.getPassword();
        String encryptedPassword;
        try {
            encryptedPassword = model.getFileHandler().read(1);
            if (encryptedPassword == null) {
                errorLabel.setText("Empty file!");
                return;
            }
            model.getInstanceEnc().init(password);
            if (encryptedPassword.equals(String.valueOf(model.getInstanceEnc().encryptedMasterPassword()))) {
                PasswordMenu form = (PasswordMenu) model.getForms().get(FormsID.PASSWORDMENU.getID());
                insertEntriesFromFile();
                form.initTable();
                reset();
                CardLayout cl = (CardLayout) parent.getLayout();
                cl.show(parent, (String) panels.get(FormsID.PASSWORDMENU.getID()));
            } else {
                errorLabel.setText("Wrong password!");
            }
        } catch (FilePathIsNullException e) {
            errorLabel.setText("No file selected!");
        }

    }

    private void reset(){
        errorLabel.setText("");
        pleaseEnterYourMasterPasswordField.setText("");
        fileNameLabel.setText("");
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
