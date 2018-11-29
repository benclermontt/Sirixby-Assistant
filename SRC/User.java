/**
   Sirixby
   @author Ben Clermont
 * @author Max Rioux
 *
 */

import java.io.*;
import java.util.Scanner;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public boolean returnUser() throws IOException {
        Scanner file = new Scanner(new File("Users.txt"));

        while(file.hasNextLine()) {
            if(file.nextLine().equalsIgnoreCase(name)) {
                return true;
            }
        }

        Writer write;
        write = new BufferedWriter(new FileWriter("Users.txt", true));

        write.append("\n" + name);
        write.close();
        return false;
    }
}
