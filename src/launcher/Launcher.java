package launcher;

import forms.*;
import model.PasswordManagerModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Launcher {
    private final JFrame mainFrame;
    private Dictionary dictForm;
    private ArrayList<Form> forms;
    private JPanel parentPanel;
    public Launcher(){
        mainFrame = new JFrame();
        dictForm = new Hashtable();
        forms = new ArrayList<Form>();
        parentPanel = new JPanel(new CardLayout());
        initForm();
        initDictCardPanel();
        PasswordManagerModel.getInstance().setForms(forms);
        mainFrame.setTitle("Password Manager");
        mainFrame.setMinimumSize(new Dimension(800,600));
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.setContentPane(parentPanel);
        mainFrame.setVisible(true);

    }

    private void initDictCardPanel(){
        for(FormsID IDValue : FormsID.values()){
            int ID = IDValue.getID();
            Form form = forms.get(ID);
            String name = form.getCardName();
            parentPanel.add(form.getPanel(),name);
            dictForm.put(ID,name);
        }
    }
    private void initForm(){
        forms.add(FormsID.MAINMENU.getID(),new MainMenuForm(parentPanel,dictForm));
        forms.add(FormsID.CREATE.getID(),new CreateForm(parentPanel,dictForm));
        forms.add(FormsID.LOGIN.getID(),new LoginForm(parentPanel,dictForm));
        forms.add(FormsID.PASSWORDMENU.getID(),new PasswordMenuForm(parentPanel,dictForm));
    }
}
