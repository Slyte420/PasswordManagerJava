package inputoutputhandling;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FileHandler {

    private String folderPath = "Data/";
    private String fileFormat = ".db";
    private Path filePath;


    public FileHandler(String filePath, String folderPath) {
        this.folderPath = folderPath;
        this.filePath = new File(folderPath + filePath + fileFormat).toPath();
        File file = new File(folderPath);
        if (!file.exists()) {
            file.mkdir();
        }

    }

    public FileHandler(String filePath) {
        this.filePath = new File(folderPath + filePath + fileFormat).toPath();
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

    public void setFilePath(String filePath) throws FileNameInvalidException {
        File file = new File(folderPath + filePath + fileFormat);
        try {
            this.filePath = file.toPath();
        } catch (InvalidPathException e) {
            throw new FileNameInvalidException("File name invalid");
        }
    }

    public boolean doesFileExist() throws FilePathIsNullException {
        if (filePath == null) {
            throw new FilePathIsNullException("File path is null");
        } else {
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

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public void write(String line) throws FilePathIsNullException {
        if (filePath == null) {
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

    public boolean containsLine(String line) {
        int numberLines = getNumberLines();
        if (numberLines != -1) {
            for (int lineIndex = 1; lineIndex <= numberLines; ++lineIndex) {
                String tempLine = null;
                try {
                    tempLine = read(lineIndex);

                } catch (FilePathIsNullException e) {
                    throw new RuntimeException(e);
                }
                if (tempLine.equals(line)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String read(int lineNumber) throws FilePathIsNullException {
        if (filePath == null) {
            throw new FilePathIsNullException("No file selected");
        }
        try (BufferedReader in = new BufferedReader(new InputStreamReader(Files.newInputStream(filePath)))) {
            String line = null;
            for (int i = 1; i <= lineNumber && (line = in.readLine()) != null; ++i) {

            }
            return line;
        } catch (java.io.IOException e) {
            throw new RuntimeException("Can't open the file");
        }
    }

    public boolean rename(String newName) throws FilePathIsNullException {
        if (filePath != null) {
            File currentFile = filePath.toFile();
            File renameFile = new File(folderPath + newName + fileFormat);
            return currentFile.renameTo(renameFile);
        } else {
            throw new FilePathIsNullException("No file selected");
        }
    }

    public boolean rename(Path dest) throws FilePathIsNullException {
        if (filePath != null) {
            File currentFile = filePath.toFile();
            File renameFile = dest.toFile();
            return currentFile.renameTo(renameFile);
        } else {
            throw new FilePathIsNullException("No file selected");
        }
    }

    public int getNumberLines() {
        if (filePath == null) {
            return -1;
        }
        try (BufferedReader in = new BufferedReader(new InputStreamReader(Files.newInputStream(filePath)))) {
            int line = 0;
            while (in.readLine() != null) {
                line++;
            }
            return line;
        } catch (java.io.IOException e) {
            throw new RuntimeException("Can't open the file");
        }
    }

    public boolean deleteFile() throws FilePathIsNullException {
        if (filePath == null) {
            throw new FilePathIsNullException("File doesn't exist");
        } else {
            File file = filePath.toFile();
            return file.delete();
        }
    }

    public void resetFilePath(){
        filePath = null;
    }

    @Override
    public String toString() {
        return filePath.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FileHandler) {
            return filePath.equals(((FileHandler) obj).filePath);
        }
        return false;
    }
}
