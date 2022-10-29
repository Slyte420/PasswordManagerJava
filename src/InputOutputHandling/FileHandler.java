package InputOutputHandling;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FileHandler {

    private final String folderPath = "Data/";
    private Path filePath;

    public FileHandler(String filePath) {
        this.filePath = new File(folderPath+filePath).toPath();
        File file = new File(folderPath);
        if (!file.exists()) {
            file.mkdir();
        }

    }

    public void write(String line) {
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(filePath, CREATE, APPEND));
        ) {
            if(line.charAt(line.length()-1) != '\n'){
                line = line + "\n";
            }
            out.write(line.getBytes(StandardCharsets.UTF_8));
        } catch (java.io.IOException e) {
            throw new RuntimeException("Can't open the file");
        }
    }

    public String read(int linenumber){
        try(BufferedReader in = new BufferedReader(new InputStreamReader(Files.newInputStream(filePath)))){
            String line = null;
            for(int i = 1; i <= linenumber && (line = in.readLine()) != null; ++i){

            }
            return line;
        }
        catch (java.io.IOException e){
            throw new RuntimeException("Can't open the file");
        }
    }


}
