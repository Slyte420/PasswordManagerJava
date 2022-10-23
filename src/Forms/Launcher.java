package Forms;

import javax.swing.*;

public class Launcher {
    private final JFrame mainFrame;

    public Launcher(){
        mainFrame = new JFrame();
        new LoginForm(mainFrame);
    }
}
