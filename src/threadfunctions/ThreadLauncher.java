package threadfunctions;

import launcher.Launcher;

public class ThreadLauncher extends Thread{
    @Override
    public void run() {
        new Launcher();
    }
}
