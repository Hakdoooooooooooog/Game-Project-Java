package FirstGame.Rock_Paper_Scissor;



public class MainWindow extends RPS{
    

    public MainWindow(char pChoice, char eChoice, int pScore, int eScore, int numTurn){
        super(pChoice, eChoice, pScore, eScore, numTurn);  
    }

    public void game(){
           
        while(tScore_Limit(pScore, eScore, numTurn)){
            try {
                playerChoice();  // Choosing player's choice
                enemyChoice(); // Choosing enemy's choice in random 

                Thread.sleep(1000); // 1 second interval
            } catch (InterruptedException ie) {
                System.err.println(ie);
            } 
            System.out.println("Enemy choose: " + enemyChoice());  
            printAnswer(eChoice, pChoice);  // Print enemy and player's answer 
            scoreBoard(eScore, pScore); // Print enemy and player's score
        }
    }
}