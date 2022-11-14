package forms;

import java.awt.*;

public class FileChoosingDialog  {
    private FileDialog dialog;

    public FileChoosingDialog(String directory, String fileFormat){
        dialog = new FileDialog((Frame) null,"Choose a file",FileDialog.LOAD);
        dialog.setDirectory(directory);
        dialog.setFile(fileFormat);
        dialog.pack();
        dialog.setVisible(true);
        dialog.dispose();
    }

    public String getFile(){
        return dialog.getFile();
    }
}
