import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Websites {
    public String[] url;
    public Websites(String[] url) {

        for(int i =0; i < url.length; i++) {
            String[] newString = url[i].split("/");
            this.url[i] = newString[0];
        }
    }

    public String[][] top5Sites() throws IOException {
        Scanner file = new Scanner(new File("Websites.txt"));

        ArrayList<String[]> fileUrls = new ArrayList<>();
        String[][] fileScores = new String[10][3];

        while(file.hasNextLine()) {
            fileUrls.add((file.nextLine().split(" ")));
        }

        for(int i = 0; i<url.length; i++) {
            int local = -1;
            for(int z = 0; z<fileUrls.size(); z++) {
                String current = fileUrls.get(z)[0];
                if(current.equals(url)) local = z;
            }

            if(local == -1) {
                fileScores[i][0] = url[i];
                fileScores[i][1] = "5";
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

    public boolean addScore(int score, String url) throws IOException{
        String[] newUrl = url.split("/");
        int index = -1;

        Scanner file = new Scanner(new File("Websites.txt"));
        ArrayList<String[]> fileUrls = new ArrayList<>();

        while(file.hasNextLine()) {
            fileUrls.add((file.nextLine().split(" ")));
        }

        for(int i = 0; i< fileUrls.size(); i++) {
            if(url.equals(fileUrls.get(i)[0])) {
                index = i;
                break;
            }
        }

        if(index != -1) {
            String[] current = fileUrls.get(index);
            current[1] = current[1] + "," + Integer.toString(score);
            current[2] = Integer.toString(calcAvg(current[1]));
        }

        return true;
    }

    private int calcAvg(String vals) {
        String[] nums = vals.split(",");
        int sum = 0;

        for(int i = 0; i < nums.length; i++) {
            sum += Integer.parseInt(nums[i]);
        }

        return sum/nums.length;
    }
}
