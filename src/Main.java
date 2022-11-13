
import Forms.AddEntryDialog;
import Forms.FileChoosingDialog;
import Launcher.ThreadLauncher;
import User.IDs;

public class Main {
    public static void main(String[] args) {

        Thread thread = new Thread(new ThreadLauncher());
        thread.start();

    }
}
