package Launcher;

import Forms.CreateForm;
import Forms.Form;
import Forms.LoginForm;
import Forms.MainMenuForm;
import Model.PasswordManagerModel;

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
        initform();
        initDictCardPanel();
        mainFrame.setTitle("Password Manager");
        mainFrame.setMinimumSize(new Dimension(800,600));
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.setContentPane(parentPanel);
        mainFrame.setVisible(true);
    }

    private void initDictCardPanel(){
        for(int i = 0; i < forms.size(); ++i){
            Form form = forms.get(i);
            String name = form.getCardName();
            parentPanel.add(form.getPanel(),name);
            dictForm.put(i,name);
        }
    }
    private void initform(){
        forms.add(new MainMenuForm(parentPanel,dictForm));
        forms.add(new CreateForm(parentPanel,dictForm));
        forms.add(new LoginForm(parentPanel,dictForm));
    }
}
