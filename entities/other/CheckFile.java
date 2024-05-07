package entities.other;

import java.io.File;

import program_settings.Parametres;

public class CheckFile {
    
    public static boolean check(String path, String name){

        File folder = new File(path);

        for(File file : folder.listFiles()){
            
            if(file.getName().equals(name + Parametres.FILE_FORMAT)) return true;

            continue;
        }

        return false;
    }
}
