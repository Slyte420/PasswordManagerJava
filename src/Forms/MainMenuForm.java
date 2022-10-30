package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;

public class MainMenuForm implements Form {
    private JPanel parent;
    private JPanel mainPanel;
    private JButton createButton;
    private JButton loginButton;

    private Dictionary panels;

    private final String name = "MainMenu";
    public MainMenuForm(JPanel parent,Dictionary panels) {
    this.parent = parent;
    this.panels = panels;
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               CardLayout cl =(CardLayout) (parent.getLayout());
               cl.show(parent,(String) panels.get(1));
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl =(CardLayout) (parent.getLayout());
                cl.show(parent,(String) panels.get(2));
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
