package Hangman;

import java.util.Scanner;

class Player {
    private int tries;
    private final Scanner scan;
    
    public Player() {
        this.tries = 0;
        scan = new Scanner(System.in);
    }
    
    //RETURNS PLAYER GUESS
    String provideGuess() {
        String guess;
        while (true){
            try {
                System.out.print("Guess A Letter: ");
                guess = scan.nextLine().toLowerCase();
                if (guess.matches("([^\\p{Alpha}])|(\\p{Alpha}{2,})")){
                    throw new Exception("Only One Letter Allowed...");
                }
                break;
            } catch (Exception e) {
                System.out.printf("""
                    %n%s%n
                    """, e.getMessage());
            }
        }
        return guess;
    }
    
    //RETURNS T/F FOR PLAYER DECISION
    boolean provideReplay() {
        while (true){
            try {
                System.out.print("Would You Like To Play Again [YES|NO]? ");
                String x = scan.nextLine().toLowerCase().strip();
                return switch (x) {
                    case "yes" -> true;
                    case "no" -> false;
                    default -> throw new Exception("Invalid Input");
                    
                };
            } catch (Exception e) {
                System.out.printf("""
                    %n%s%n
                    """, e.getMessage());
            }
        }
    }
    
    //RETURNS # OF TRIES
    int getTries() { return tries; }
    
    //INCREMENTS # OF TRIES
    void setTries(int tries) { this.tries = tries; }
}
