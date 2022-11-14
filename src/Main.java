
import Forms.AddEntryDialog;
import Forms.FileChoosingDialog;
import InputOutputHandling.FileHandler;
import InputOutputHandling.FilePathIsNullException;
import Launcher.ThreadLauncher;
import User.IDs;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadLauncher());
        thread.start();

    }
}
