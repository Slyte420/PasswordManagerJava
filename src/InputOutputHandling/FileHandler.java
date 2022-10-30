package InputOutputHandling;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FileHandler {

    private String folderPath = "Data/";
    private Path filePath;

    public FileHandler(String filePath, String folderPath) {
        this.folderPath = folderPath;
        this.filePath = new File(folderPath + filePath).toPath();
        File file = new File(folderPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public FileHandler(String filePath) {
        this.filePath = new File(folderPath + filePath).toPath();
        File file = new File(folderPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public FileHandler() {
        File file = new File(folderPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public void setFilePath(String filePath) throws FileNameInvalid {
        File file = new File(folderPath + filePath);
            try{
            this.filePath = file.toPath();
            }
            catch (InvalidPathException e){
                throw new FileNameInvalid("File name invalid");
            }
    }

    public boolean doesFileExist()throws FilePathIsNullException{
        if(filePath == null){
            throw new FilePathIsNullException("File path is null");
        }
        else{
            File file = filePath.toFile();
            return file.exists();
        }
    }

    public Path getFilePath() {
        return filePath;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public void write(String line) throws FilePathIsNullException {
        if(filePath == null){
            throw new FilePathIsNullException("No file selected");
        }
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(filePath, CREATE, APPEND));
        ) {
            if (line.charAt(line.length() - 1) != '\n') {
                line = line + "\n";
            }
            out.write(line.getBytes(StandardCharsets.UTF_8));
        } catch (java.io.IOException e) {
            throw new RuntimeException("Can't open the file");
        }
    }

    public String read(int linenumber) throws FilePathIsNullException {
        if(filePath == null){
            throw new FilePathIsNullException("No file selected");
        }
        try (BufferedReader in = new BufferedReader(new InputStreamReader(Files.newInputStream(filePath)))) {
            String line = null;
            for (int i = 1; i <= linenumber && (line = in.readLine()) != null; ++i) {

            }
            return line;
        } catch (java.io.IOException e) {
            throw new RuntimeException("Can't open the file");
        }
    }

    @Override
    public String toString() {
        return filePath.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FileHandler){
            return filePath.equals(((FileHandler) obj).filePath);
        }
        return false;
    }
}
