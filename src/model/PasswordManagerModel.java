package model;

import encryption.DES;
import encryption.Encryption;
import forms.Form;
import inputoutputhandling.FileHandler;
import user.*;

import java.util.ArrayList;

public class PasswordManagerModel {
    private ArrayList<ArrayList<Entry>> entries;
    private FileHandler fileHandler;
    private Encryption instanceEnc;
    private ArrayList<Form> forms;
    private static PasswordManagerModel instance;

    private PasswordManagerModel() {
        entries = new ArrayList<ArrayList<Entry>>();
        fileHandler = new FileHandler();
        instanceEnc = DES.getInstance();
        for (int i = 0; i < IDs.values().length; ++i) {
            entries.add(new ArrayList<Entry>());
        }
    }

    public static PasswordManagerModel getInstance() {
        if (instance == null) {
            instance = new PasswordManagerModel();
        }
        return instance;
    }

    public ArrayList<ArrayList<Entry>> getEntries() {
        return entries;
    }

    public Encryption getInstanceEnc() {
        return instanceEnc;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public void setEntries(ArrayList<ArrayList<Entry>> entries) {
        this.entries = entries;
    }

    public void setFileHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public void setInstanceEnc(Encryption instanceEnc) {
        this.instanceEnc = instanceEnc;
    }

    public void setForms(ArrayList<Form> forms) {
        this.forms = forms;
    }

    public ArrayList<Form> getForms() {
        return forms;
    }

    public void clearEntries() {
        for (ArrayList EntryGroup : entries) {
            int index;
            while ((index = EntryGroup.size() - 1) >= 0) {
                EntryGroup.remove(index);
            }
        }
    }

    public void addEntry(String username, char[] password) {
        entries.get(IDs.ENTRY.getID()).add(new Entry(username, new String(password)));
    }

    public void addEntry(String username, String password) {
        entries.get(IDs.ENTRY.getID()).add(new Entry(username, password));
    }

    public void addEntry(String username) {
        entries.get(IDs.ENTRY.getID()).add(new Entry(username));
    }

    public void addEntryGeneral(String username, char[] password, String notes) {
        entries.get(IDs.ENTRYGENERAL.getID()).add(new EntryGeneral(username, new String(password), notes));
    }

    public void addEntryGeneral(String username, String password, String notes) {
        entries.get(IDs.ENTRYGENERAL.getID()).add(new EntryGeneral(username, password, notes));
    }

    public void addEntryGeneral(String username, String notes) {
        entries.get(IDs.ENTRYGENERAL.getID()).add(new EntryGeneral(username, notes));
    }

    public void addEntryEmail(String username, char[] password, String email) {
        entries.get(IDs.ENTRYEMAIL.getID()).add(new EntryEmail(username, new String(password), email));
    }

    public void addEntryEmail(String username, String password, String email) {
        entries.get(IDs.ENTRYEMAIL.getID()).add(new EntryEmail(username, password, email));
    }

    public void addEntryEmail(String username, String email) {
        entries.get(IDs.ENTRYEMAIL.getID()).add(new EntryEmail(username, email));
    }

    public void addEntryInternet(String username, char[] password, String URL) {
        entries.get(IDs.ENTRYINTERNET.getID()).add(new EntryInternet(username, new String(password), URL));
    }

    public void addEntryInternet(String username, String password, String URL) {
        entries.get(IDs.ENTRYINTERNET.getID()).add(new EntryInternet(username, password, URL));
    }

    public void addEntryInternet(String username, String URL) {
        entries.get(IDs.ENTRYINTERNET.getID()).add(new EntryInternet(username, URL));
    }

    public boolean deleteEntry(int index, int group) {
        if (entries.size() > group) {
            if (entries.get(group).size() > index) {
                entries.get(group).remove(index);
                return true;
            }
        }
        return false;
    }


    public Entry getEntry(int index, int group) {
        if (entries.size() > group) {
            if (entries.get(group).size() > index) {
                return entries.get(group).get(index);
            }
        }
        return null;
    }

    public int getSize(int group) {
        if (entries.size() > group) {
            return entries.get(group).size();
        }
        return -1;
    }

}
