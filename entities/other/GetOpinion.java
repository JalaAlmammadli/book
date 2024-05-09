package entities.other;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetOpinion {

    public static int[] getAllOpinion(String path){

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            
            String line = br.readLine();
            String reviews[] = line.strip().split(" ", -1);

            int indexes[] = null;

            for(int i = 0; i < reviews.length; i++){

                indexes[i] = Integer.parseInt(reviews[i]);
            }

            return indexes;
        } catch (IOException e) {
            System.out.println("book review file reading failed");
        }
        return null;
    }
}
