package FirstGame;

import java.io.IOException;
import java.util.Scanner;

import FirstGame.MonsterQuest.MainWindow1;
import FirstGame.Rock_Paper_Scissor.MainWindow;

/**
 * @author Darenz Hicap
 * @version 1.0
 * @since 2020-05-01
 */

public class WelcomeScreen {

    static MainWindow mw1;
    static MainWindow1 mw2;

    static Scanner c1;

    public static void main(String[] args) throws InterruptedException, IOException {
        boolean running = true;
        int choice;

        c1 = new Scanner(System.in);
        new ProcessBuilder("powershell", "Clear-Host").inheritIO().start().waitFor();
        
        while (running) {
            System.out.println("==========================================================");
            System.out.print("Select a game \nRPS = 1 \nRPG = 2 \nExit = 3 \n--> ");
            choice = c1.nextInt();

            switch (choice) {
                case 1:
                    mw1 = new MainWindow(' ', ' ', 0, 0, 0);
                    mw1.numOfTurn(); // Set limit of Score
                    mw1.game();

                    break;
                case 2:
                    mw2 = new MainWindow1(0, 0, 0, 0, 0, 0, 0, 0, 0.1, "", "");
                    mw2.startGame();
                    break;

                case 3:
                    running = false;
                    break;

                default:
                    System.out.println("Error pls enter a valid option.");
            }
        }
    }
}
