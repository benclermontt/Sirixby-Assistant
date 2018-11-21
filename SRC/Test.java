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
            System.out.print(" __  __              ___       ___                          ______                                       \n" +
                    "/\\ \\/\\ \\            /\\_ \\     /\\_ \\                        /\\__  _\\                                      \n" +
                    "\\ \\ \\_\\ \\      __   \\//\\ \\    \\//\\ \\      ___              \\/_/\\ \\/                  __       ___ ___    \n" +
                    " \\ \\  _  \\   /'__`\\   \\ \\ \\     \\ \\ \\    / __`\\               \\ \\ \\                /'__`\\   /' __` __`\\  \n" +
                    "  \\ \\ \\ \\ \\ /\\  __/    \\_\\ \\_    \\_\\ \\_ /\\ \\L\\ \\               \\_\\ \\__            /\\ \\L\\.\\_ /\\ \\/\\ \\/\\ \\ \n" +
                    "   \\ \\_\\ \\_\\\\ \\____\\   /\\____\\   /\\____\\\\ \\____/               /\\_____\\           \\ \\__/.\\_\\\\ \\_\\ \\_\\ \\_\\\n" +
                    "    \\/_/\\/_/ \\/____/   \\/____/   \\/____/ \\/___/                \\/_____/            \\/__/\\/_/ \\/_/\\/_/\\/_/\n" +
                    "                                                                                                         \n" +
                    "                                                                                                         ");
            System.out.println("  ____    _          _          _             \n" +
                    " / ___|  (_)  _ __  (_) __  __ | |__    _   _ \n" +
                    " \\___ \\  | | | '__| | | \\ \\/ / | '_ \\  | | | |\n" +
                    "  ___) | | | | |    | |  >  <  | |_) | | |_| |\n" +
                    " |____/  |_| |_|    |_| /_/\\_\\ |_.__/   \\__, |\n" +
                    "                                        |___/ ");

            //Gathers name from user. Username variable will be used in addressing user in any responses gathered
            System.out.print("What is your name? ");
            String username = userInput.nextLine();

            System.out.println("Welcome " + username + ". I am Sirixby. How may I help you? ");
                 
            //Taking search term input from console
        System.out.println("Please enter the search term.");
        String searchTerm = userInput.nextLine();
        System.out.println("Please enter the number of results. Example: 5 10 20");
        int num = userInput.nextInt();

        String searchURL = GOOGLE_SEARCH_URL + "?q="+searchTerm+"&num="+num;
        //without proper User-Agent, we will get 403 error
        Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();

        //below will print HTML data, save it to a file and open in browser to compare
        System.out.println(doc.html());

        //If google search results HTML change the <h3 class="r" to <h3 class="r1"
        //we need to change below accordingly
        Elements results = doc.select("h3.r > a");

        for (Element result : results) {
            String linkHref = result.attr("href");
            String linkText = result.text();
            System.out.println("Text::" + linkText + ", URL::" + linkHref.substring(6, linkHref.indexOf("&")));
        }

        System.out.println("Which option looks the best to you: ");
        int choice = userInput.nextInt();

    }
}




