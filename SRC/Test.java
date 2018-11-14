/**
 *
 * Sirixby Personal Assistant
 *
 * Network Programming Final Project
 *
 * Dylan Goldrick
 * Ben
 * Max
 *
 * Just doing basic inputs rn -DG
 *
 */

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

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
    }
}


