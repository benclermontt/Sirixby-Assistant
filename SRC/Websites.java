/**
 * Websites.java
 *
 * This class does the sorting and machine learning aspects of the project
 *
 * @author Ben Clermont
 * @date 2/12/2018
 */


import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Websites {
    public String[] url = new String[10];
    ArrayList<String[]> fileUrls = new ArrayList<>();

    public Websites(String[] url) throws IOException {

        // Checks if null + splits string at / to remove search specific parts of link
        for (int i = 1, z = 0; i < url.length; i++) {
            try {
                if(url[i] == null) {}
                else {
                    String[] newString = url[i].split("/");
                    this.url[z] = newString[2];
                    z++;
                }
            }
            catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        // Adds everything from the file to arrayList
        Scanner file = new Scanner(new File("Websites.txt"));
        while (file.hasNextLine()) {
            fileUrls.add((file.nextLine().split(" ")));
        }
    }


    /**
     * Returns the top 5 highest rated websites from websites.txt
     * Uses my questionable-at-best sorting algorithm sortScores
     * @return String[]
     * @throws IOException
     */
    public String[]top5Sites() throws IOException {
        String[][] fileScores = new String[url.length][3];


        for(int i = 0; i<url.length; i++) {
            int local = -1;
            for(int z = 0; z<fileUrls.size(); z++) {
                String current = fileUrls.get(z)[0];
                if(current.equals(url[i])) local = z;
            }

            if(local == -1) {
                fileScores[i][0] = url[i];
                fileScores[i][1] = "5";
                fileScores[i][2] = "5";
                addToFile(fileScores[i]);
            }
            else {
                String[] current = fileUrls.get(local);
                fileScores[i] = current;
            }
        }

        ArrayList<String[]> sortedFileScores = sortScores(fileScores);
        String[] output = new String[sortedFileScores.size()];

        for(int i = 0; i < output.length; i++) {
            output[i] = sortedFileScores.get(i)[0];
        }

        return output;

    }


    /**
     * My questionable-at-best sorting algorithm
     * @param fileScores
     * @return String[][]
     */
    private ArrayList<String[]> sortScores(String[][] fileScores) {
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

        return newFileScores;
    }

    /**
     * Updates a score in the database
     * @param int score
     * @param url
     * @return
     * @throws IOException
     */
    public void addScore(int score, String url) throws IOException{
        String[] newUrl = url.split("/");
        int index = -1;

        updateUrls();

        for(int i = 0; i< fileUrls.size(); i++) {
            if(url.equals(fileUrls.get(i)[0])) {
                index = i;
                break;
            }
        }

        String[] current = fileUrls.get(index);

        if(index != -1) {
            current[1] = current[1] + "," + Integer.toString(score);
            current[2] = Integer.toString(calcAvg(current[1]));
        }

        addToFile(current, index);
    }

    /**
     * calculates the avergae score for a given website in the database
     * @param vals
     * @return
     */
    private int calcAvg(String vals) {
        String[] nums = vals.split(",");
        int sum = 0;

        for(int i = 0; i < nums.length; i++) {
            sum += Integer.parseInt(nums[i]);
        }

        return sum/nums.length;
    }

    /**
     * Adds a URL to the database by updating everything in the database
     * (inefficient af but idk how else to do it)
     * @param current
     * @param index
     * @throws IOException
     */
    private void addToFile(String[] current, int index) throws IOException {
        Writer write;
        write = new BufferedWriter(new FileWriter("Websites.txt", true));

        updateUrls();

        clearTextFile();

        fileUrls.set(index, current);

        for(int i = 1; i < fileUrls.size(); i++) {
            if(i == 1) {
                write.write(fileUrls.get(i)[0] + " ");
                write.append(fileUrls.get(i)[1] + " ");
                write.append(fileUrls.get(i)[2] + "\n");
            }
            else {
                write.append(fileUrls.get(i)[0] + " ");
                write.append(fileUrls.get(i)[1] + " ");
                write.append(fileUrls.get(i)[2] + "\n");
            }
        }
        write.close();
    }

    private void addToFile(String[] current) throws IOException {
        Writer write;
        write = new BufferedWriter(new FileWriter("Websites.txt", true));

        updateUrls();

        clearTextFile();

        fileUrls.add(current);

        for(int i = 1; i < fileUrls.size(); i++) {
            if(i == 1) {
                write.write(fileUrls.get(i)[0] + " ");
                write.append(fileUrls.get(i)[1] + " ");
                write.append(fileUrls.get(i)[2] + "\n");
            }
            else {
                write.append(fileUrls.get(i)[0] + " ");
                write.append(fileUrls.get(i)[1] + " ");
                write.append(fileUrls.get(i)[2] + "\n");
            }
        }
        write.close();
    }

    public String toString() {
        String toString = "";
        for(String s : url) {
            toString = toString + s + "\n";
        }

        return toString;
    }

    private void clearTextFile() throws IOException {
        PrintWriter writer = new PrintWriter("Websites.txt");
        writer.print("");
        writer.close();
    }

    private void updateUrls() throws IOException{
        Scanner file = new Scanner(new File("Websites.txt"));
        while (file.hasNextLine()) {
            String[] current = file.nextLine().split(" ");
            boolean found = false;
            for(String[] s : fileUrls) {
                if(current[0].equals(s[0])) found = true;
            }

            if(!found) {
                fileUrls.add(current);
            }
        }
    }
}
