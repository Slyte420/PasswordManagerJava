package Launcher;

public class ThreadLauncher implements Runnable {
    @Override
    public void run() {
        new Launcher();
    }
}
