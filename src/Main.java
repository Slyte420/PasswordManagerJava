import Encryption.DES;
import InputOutputHandling.FileHandler;
import InputOutputHandling.FilePathIsNullException;
import Model.PasswordManagerModel;
import RandomPasswordGenerator.PassGen;
import User.EntryGeneral;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*final String password = "aaaaaaaaaaaaaaaaaaaaaaaa";
        DES instance = DES.getInstance();
        instance.init(password);
        String encrypted = instance.encrypt("Bro");
        System.out.println(encrypted);
        String decrypt = instance.decrypt(encrypted);
        System.out.println(decrypt);
        instance.init(password);
        encrypted = instance.encrypt("Bro");
        System.out.println(encrypted);
        decrypt = instance.decrypt(encrypted);
        System.out.println(decrypt);*/
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
