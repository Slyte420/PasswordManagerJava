package forms;

import inputoutputhandling.FileHandler;
import inputoutputhandling.FileNameInvalidException;
import inputoutputhandling.FilePathIsNullException;
import launcher.FormsID;
import model.PasswordManagerModel;
import user.IDs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;

public class LoginForm implements Form {
    private JPanel parent;
    private JPanel mainPanel;

    private JPasswordField pleaseEnterTheMasterPasswordField;
    private JButton loginButton;
    private JLabel errorLabel;
    private JButton loadButton;
    private JButton backButton;
    private JLabel fileNameLabel;
    private Dictionary panels;
    private PasswordManagerModel model;
    private static final String NAME = "LoginForm";


    public LoginForm(JPanel parent, Dictionary panels) {
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
        if (filename != null && !filename.equals(model.getFileHandler().getFileFormat())) {
            fileNameLabel.setText(filename);
            filename = filename.replace(model.getFileHandler().getFileFormat(), "");
            try {
                model.getFileHandler().setFilePath(filename);
            } catch (FileNameInvalidException e) {
                errorLabel.setText(e.toString());
                throw new RuntimeException(e);
            }
        }
        else{
            errorLabel.setText("No file selected!");
        }
    }


    private void insertEntriesFromFile() {
        FileHandler handler = model.getFileHandler();
        int lineNumbers = handler.getNumberLines();
        int columnmaxcount = -1;
        for (IDs value : IDs.values()) {
            if (columnmaxcount < value.getColumns().length) {
                columnmaxcount = value.getColumns().length;
            }
        }
        columnmaxcount++;
        for (int lineindex = 2; lineindex <= lineNumbers; ++lineindex) {
            String line = null;
            try {
                line = handler.read(lineindex);
            } catch (FilePathIsNullException e) {
                throw new RuntimeException(e);
            }
            String[] decrypted = new String[columnmaxcount];
            String[] temp = model.getInstanceEnc().decrypt(line).split(" ");
            for (int i = 0; i < columnmaxcount; ++i) {
                if (i < temp.length) {
                    decrypted[i] = temp[i];
                } else {
                    decrypted[i] = "";
                }
            }
            int IDindex = Integer.parseInt(decrypted[0]);
            IDs ID = IDs.values()[IDindex];
            switch (ID) {
                case ENTRY: {
                    model.addEntry(decrypted[1], decrypted[2]);
                    break;
                }
                case ENTRYEMAIL: {
                    model.addEntryEmail(decrypted[1], decrypted[2], decrypted[3]);
                    break;
                }
                case ENTRYINTERNET: {
                    model.addEntryInternet(decrypted[1], decrypted[2], decrypted[3]);
                    break;
                }
                case ENTRYGENERAL: {
                    model.addEntryGeneral(decrypted[1], decrypted[2], decrypted[3]);
                    break;
                }
            }
        }
    }

    private void loginIntoFile() {
        char[] password = pleaseEnterTheMasterPasswordField.getPassword();
        String encryptedPassword;
        try {
            encryptedPassword = model.getFileHandler().read(1);
            if (encryptedPassword == null) {
                errorLabel.setText("Empty file!");
                return;
            }
            model.getInstanceEnc().init(password);
            if (encryptedPassword.equals(String.valueOf(model.getInstanceEnc().encryptedMasterPassword()))) {
                PasswordMenuForm form = (PasswordMenuForm) model.getForms().get(FormsID.PASSWORDMENU.getID());
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

    private void reset() {
        errorLabel.setText("");
        pleaseEnterTheMasterPasswordField.setText("");
        fileNameLabel.setText("");
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
