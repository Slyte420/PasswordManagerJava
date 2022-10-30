package Forms;

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
    private  Dictionary panels;
    private PasswordManagerModel model;
    private final Dimension size = new Dimension(650,550);
    private final String name ="Create";
    public CreateForm(JPanel parent,Dictionary panels,PasswordManagerModel model){
        this.parent = parent;
        this.panels = panels;
        this.model = model;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) parent.getLayout();
                cl.show(parent,(String) panels.get(0));
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
