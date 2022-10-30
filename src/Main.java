import Encryption.DES;
import InputOutputHandling.FileHandler;
import InputOutputHandling.FilePathIsNullException;
import Model.PasswordManagerModel;
import RandomPasswordGenerator.PassGen;
import User.Entry;
import User.EntryGeneral;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        new Launcher.Launcher();
        /*PassGen a = new PassGen();
        a.generatePassword(15);
        System.out.println(new File("a.in").toString());*/
        //FileHandler fileHandler = new FileHandler();
        //EntryGeneral user = new EntryGeneral();
        //System.out.println(user.toString());
        //PasswordManagerModel model = new PasswordManagerModel();

    }
}
