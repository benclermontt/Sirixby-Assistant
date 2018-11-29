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

import java.util.Scanner;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {
    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    public static void main(String[] args) throws IOException {

            //Fancy looking welcome message

        Scanner userInput = new Scanner(System.in);
        System.out.print(" " +
                    " __      __       .__                               \n" +
                    "/  \\    /  \\ ____ |  |   ____  ____   _____   ____  \n" +
                    "\\   \\/\\/   // __ \\|  | _/ ___\\/  _ \\ /     \\_/ __ \\ \n" +
                    " \\        /\\  ___/|  |_\\  \\__(  <_> )  Y Y  \\  ___/ \n" +
                    "  \\__/\\  /  \\___  >____/\\___  >____/|__|_|  /\\___  >\n" +
                    "       \\/       \\/          \\/            \\/     \\/");
        System.out.println("  ____    _          _          _             \n" +
                    " / ___|  (_)  _ __  (_) __  __ | |__    _   _ \n" +
                    " \\___ \\  | | | '__| | | \\ \\/ / | '_ \\  | | | |\n" +
                    "  ___) | | | | |    | |  >  <  | |_) | | |_| |\n" +
                    " |____/  |_| |_|    |_| /_/\\_\\ |_.__/   \\__, |\n" +
                    "                                        |___/ ");

        //Gathers name from user. Username variable will be used in addressing user in any responses gathered
        System.out.print("What is your name? ");
        String username = userInput.nextLine();

        User user1 = new User(username);
        if(user1.returnUser())
            System.out.println("Welcome back " + username + ", how may I assist you today?");
        else
            System.out.println("Hello " + username + "! I am Sirixby, how may I assist you today?");


                 
        //Taking search term input from console
        System.out.println("Please enter the search term.");
        String searchTerm = userInput.nextLine();

        String searchURL = GOOGLE_SEARCH_URL + "?q="+searchTerm+"&num="+10;
        //without proper User-Agent, we will get 403 error
        Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();

        String[] webText = new String[10];
        String[] webHref = new String[10];

        //If google search results HTML change the <h3 class="r" to <h3 class="r1"
        //we need to change below accordingly
        Elements results = doc.select("h3.r > a");
        int count = 0;
        for (Element result : results) {
            String linkHref = result.attr("href");
            webHref[count] = result.attr("href");
            String linkText = result.text();
            webText[count] = result.text();
            count++;
        }
        for(int i = 0 ; i<5; i++){
            System.out.println(webText[i] + " " + webHref[i]);
        }


        //We should try and have the user pick an option and then we should try and scrape the text off
        //of their choice and print it out for them
        System.out.println("Which option would you like read out: ");
        int choice = userInput.nextInt();

        String html = Jsoup.connect(webHref[choice]).get().html();
        System.out.println(html);

    }
}
