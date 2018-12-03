/**
 *
 * Sirixby Personal Assistant
 *
 * Network Programming Final Project
 *
 * Dylan Goldrick
 * Ben Clermont
 * Max Rioux
 *
 * Just doing basic inputs rn -DG
 *
 */

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test {
    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    public static void main(String[] args) throws IOException {

        SirixbyUI sirixbyUI = new SirixbyUI();
        sirixbyUI.setVisible(true);

        Scanner userInput = new Scanner(System.in);
        sirixbyUI.updateTextArea(" " +
                    " __      __       .__                               \n" +
                    "/  \\    /  \\ ____ |  |   ____  ____   _____   ____  \n" +
                    "\\   \\/\\/   // __ \\|  | _/ ___\\/  _ \\ /     \\_/ __ \\ \n" +
                    " \\        /\\  ___/|  |_\\  \\__(  <_> )  Y Y  \\  ___/ \n" +
                    "  \\__/\\  /  \\___  >____/\\___  >____/|__|_|  /\\___  >\n" +
                    "       \\/       \\/          \\/            \\/     \\/\n");

        sirixbyUI.updateTextArea("  ____    _          _          _             \n" +
                    " / ___|  (_)  _ __  (_) __  __ | |__    _   _ \n" +
                    " \\___ \\  | | | '__| | | \\ \\/ / | '_ \\  | | | |\n" +
                    "  ___) | | | | |    | |  >  <  | |_) | | |_| |\n" +
                    " |____/  |_| |_|    |_| /_/\\_\\ |_.__/   \\__, |\n" +
                    "                                        |___/ ");

        //Gathers name from user. Username variable will be used in addressing user in any responses gathered
        sirixbyUI.updateTextArea("What is your name? ");
        String username = userInput.nextLine();

        User user1 = new User(username);
        if(user1.returnUser())
            System.out.println("Welcome back " + username + ", how may I assist you today?");
        else
            System.out.println("Hello " + username + "! I am Sirixby, how may I assist you today?");

        String answer = "no";

        do {


            //Taking search term input from console
            System.out.println("Please enter the search term.");
            String searchTerm = userInput.nextLine();

            String searchURL = GOOGLE_SEARCH_URL + "?q=" + searchTerm + "&num=" + 18;
            //without proper User-Agent, we will get 403 error
            Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();

            String[] webText = new String[11];
            String[] webHref = new String[11];

            //If google search results HTML change the <h3 class="r" to <h3 class="r1"
            //we need to change below accordingly
            Elements results = doc.select("h3.r > a");
            int count = 1;
            String needle = "/url?q=";
            int needleSize = needle.length();

            for (Element result : results) {
                String linkHref = result.attr("href");
                if (linkHref.startsWith(needle) == true) {
                    linkHref = linkHref.startsWith(needle) ? linkHref.substring(needleSize) : linkHref;
                } else {
                    continue;
                }
                String newlinkHref = linkHref.split("&")[0];
                webHref[count] = newlinkHref;
                String linkText = result.text();
                webText[count] = result.text();
                if (webHref[10] != null) {
                    break;
                }
                count++;
            }

            Websites list = new Websites(webHref);
            String[] tops = list.top5Sites();

            for (int i = 1; i <= 5; i++) {
                System.out.println(tops[i]);
            }

            //The User chooses an option and then the website is scraped
            System.out.println("Which option would you like read out: ");
            int choice = userInput.nextInt();
            int local;

            URI choiceURI = null;

            try {
                Desktop desktop = java.awt.Desktop.getDesktop();
                for (int i = 1; i < webHref.length; i++) {
                    if (webHref[i].contains(tops[choice])) {
                        choiceURI = new URI(webHref[i]);
                        local = i;
                    }
                }
                desktop.browse(choiceURI);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.print("How would you rate your experience on " + tops[choice] + " out of 10? ");
            int score = userInput.nextInt();

            list.addScore(score, tops[choice]);

            System.out.println();
            userInput.nextLine();

            System.out.print("Would you like to make another search (yes or no): ");
            answer = userInput.nextLine();
        }
        while(answer.equalsIgnoreCase("yes"));
    }

}
