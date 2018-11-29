import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Websites {
    public String[] url;
    public Websites(String[] url) {
        this.url = url;
    }

    public String[][] top5Sites() throws IOException {
        Scanner file = new Scanner(new File("Websites.txt"));

        ArrayList<String[]> fileUrls = new ArrayList<>();
        String[][] fileScores = new String[10][3];

        while(file.hasNextLine()) {
            fileUrls.add((file.nextLine().split(" ")));
        }

        for(int i = 0; i<url.length; i++) {
            int local = fileUrls.indexOf(url[i]);

            if(local == -1) {
                fileScores[i][0] = url[i];
                fileScores[i][1] = "";
                fileScores[i][2] = "5";
                continue;
            }


            String[] current = fileUrls.get(local);
            fileScores[i] = current;
        }

        return sortScores(fileScores);
    }

    private String[][] sortScores(String[][] fileScores) {
        ArrayList<String[]> newFileScores = new ArrayList<>();
        for(int i = 0; i < fileScores.length; i++)  {
            if(i == 0) {
                newFileScores.add(fileScores[i]);
                continue;
            }

            boolean found = false;

            for(int z = 0; z < newFileScores.size(); z++) {
                String[] currentScore = newFileScores.get(z);
                if(Integer.parseInt(fileScores[i][2]) > Integer.parseInt(currentScore[2])) {
                    newFileScores.add(z, fileScores[i]);
                    found = true;
                    break;
                }
            }

            if(!found) {
                newFileScores.add(fileScores[i]);
            }
        }

        String[][] output = new String[10][3];
        for(int i = 0; i < 10; i++) {
            output[i] = newFileScores.get(i);
        }

        return output;
    }
}
