package FirstGame.Rock_Paper_Scissor;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class RPS{

    int numTurn;

    char pChoice, eChoice;
    int pScore, eScore, totalScore;

    Scanner in;

    public RPS(char pChoice, char eChoice, int pScore, int eScore, int numTurn){
         //Player Function
         this.pChoice = pChoice;

         //Enemy Function
         this.eChoice = eChoice;
 
         //Scoring
         this.pScore = pScore;
         this.eScore = eScore;

        //number of turns/ total of meet score
        this.numTurn = numTurn;

    }

    public char playerChoice(){
        in = new Scanner(System.in);

        System.out.print("Choose 1 item \nRock = R \nPaper = P \nScissors = S \n-->: ");
        pChoice = in.next().charAt(0);
        pChoice = Character.toUpperCase(pChoice);
        
        switch(pChoice){
            case 'R':
                System.out.println("You chose Rock! (R)");
            break;
            case 'P':
                System.out.println("You chose paper! (P)");
            break;
            case 'S':
                System.out.println("You chose Scissor! (S)");
            break;
            default:
                System.out.println("Invalid input.\n");
                playerChoice();
            break;
        } 
        return pChoice;
    }

    public char enemyChoice(){
        String word = "RPS";
        Random ran = new Random();
        eChoice = word.charAt(ran.nextInt(word.length()));

        return eChoice;
    }

    public void checkAnswer(char eChoice, char pChoice){
        if ((pChoice == 'R' && eChoice == 'S') || (pChoice == 'S' && eChoice == 'P') || (pChoice == 'P' && eChoice == 'R')){
            pScore += 1;
        }else if (pChoice == eChoice){
            System.out.println("Draw.");
        }else{
            eScore += 1;
        }
    }

    public void printAnswer(char eChoice, char pChoice){
        System.out.println("=========================================================");
        System.out.println("Enemy's Answer: "+ eChoice +"     |     " + "Player's Answer: " + pChoice);
        System.out.println("=========================================================");

        checkAnswer(eChoice, pChoice); // Checking answers
    }

    public void scoreBoard(int eScore, int pScore){
        System.out.println("=========================================================");
        System.out.println("Enemy's Score: "+ eScore +"     |     " + "Player's Score: " + pScore);
        System.out.println("=========================================================");
        
    }

    public void checkWinner(int eScore, int pScore){
        if (pScore > eScore) {
            System.out.printf("Player wins for this round at the score of \"%d\" and enemy score at \"%d\".\n", pScore, eScore);
        }else{
            System.out.printf("Enemy wins for this round at the score of \"%d\" and player score at \"%d\".\n", eScore, pScore);
        }
    }

    public boolean tScore_Limit(int pScore, int eScore, int numTurn){

        if(pScore == numTurn || eScore == numTurn){
            checkWinner(eScore, pScore);
            return false;
        }else{
            return true;
        }

    }

    public int numOfTurn(){
        in = new Scanner(System.in);

            try {
                System.out.println("Rock Paper Scissors");
                System.out.print("How many points to win: ");
                numTurn = in.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Invalid input.\n");
                numOfTurn();
            }

        return numTurn;
    }
}
