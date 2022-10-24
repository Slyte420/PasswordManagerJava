package Forms;

import javax.swing.*;
import java.awt.*;

public class Launcher {
    private final JFrame mainFrame;

    public Launcher(){
        mainFrame = new JFrame();
        mainFrame.setTitle("Password Manager");
        mainFrame.setMinimumSize(new Dimension(800,600));
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        new CreateForm(mainFrame);
        mainFrame.setVisible(true);
    }
}
