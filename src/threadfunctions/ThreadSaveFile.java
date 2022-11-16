package threadfunctions;

import inputoutputhandling.FileHandler;
import inputoutputhandling.FilePathIsNullException;
import model.PasswordManagerModel;
import user.Entry;
import user.IDs;

public class ThreadSaveFile extends Thread {

    private PasswordManagerModel model;
    private boolean isExit;

    public ThreadSaveFile(PasswordManagerModel model,boolean isExit){
        this.model = model;
        this.isExit = isExit;
    }

    @Override
    public void run() {
        saveFileRunnable();
    }

    private void saveFileRunnable() {
        try {
            FileHandler fileHandler = model.getFileHandler();
            FileHandler temp = new FileHandler("temp.db");
            temp.deleteFile();
            temp.write(fileHandler.read(1));
            for (int groupIndex = 0; groupIndex < IDs.values().length; ++groupIndex) {
                for (int entryIndex = 0; entryIndex < model.getSize(groupIndex); ++entryIndex) {
                    Entry entry = model.getEntry(entryIndex, groupIndex);
                    String writeLine = model.getInstanceEnc().encrypt(entry.toString());
                    temp.write(writeLine);
                }
            }
            fileHandler.deleteFile();
            temp.rename(fileHandler.getFilePath());
        } catch (FilePathIsNullException e) {
            throw new RuntimeException(e);
        } finally {
            if (isExit) {
                reset();
            }
        }
    }
    private void reset() {
        model.getInstanceEnc().reset();
        model.clearEntries();
        model.getFileHandler().resetFilePath();
    }
}
