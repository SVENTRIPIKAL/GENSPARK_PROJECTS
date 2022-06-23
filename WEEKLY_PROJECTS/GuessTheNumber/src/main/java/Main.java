import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static String nameRegex = "\\p{Alpha}+", guessRegex = "\\d{1,2}", playerName;
    static Pattern namePattern = Pattern.compile(nameRegex),
                    guessPattern = Pattern.compile(guessRegex);
    static Matcher matcher;
    static Random randomizer = new Random();
    static byte tries, randomNumber;
    static boolean gameFinish = false;
    
    public static void main(String[] args) throws Exception {
        matcher = getName();
    
        playerName = matcher.group();   toTitleCase();
        
        while (!gameFinish) {
            randomNumber = (byte) randomizer.nextInt(1,21);
            
            System.out.printf("""
            %nWell, %s, I am thinking of a number between 1 and 20.
            Try to guess the number within 6 tries.%n
            """, playerName);
            
            for (tries = 1; tries < 7; tries++) {
                System.out.println("Take a guess...");
                matcher = getGuess();
                if (!matcher.find()) {
                    System.out.println("Sorry, only 1-2 numbers are allowed!!!  >.<'\n");
                }
                else {
                    if (evaluateGuess()) { break; }
                }
            }
            
            results();
        }
    }
    
    static Matcher getName() throws Exception {
        System.out.print("Hello! What is your name? ");
        matcher = namePattern.matcher(buffer.readLine().strip());
        while (!matcher.find()){
            System.out.println("""
                Sorry, only letters of the alphabet are allowed!!!  >.<'
                """);
            System.out.print("Hello! What is your name? ");
            matcher = namePattern.matcher(buffer.readLine().strip());
        }
        return matcher;
    }
    
    static Matcher getGuess() throws Exception {
       return guessPattern.matcher(buffer.readLine().strip());
    }
    
    static boolean evaluateGuess() {
        byte guess = (byte) Integer.parseInt(matcher.group());
        if (guess < randomNumber) {
            System.out.println("""
                Your guess is too Low!!!  o_O'
                """);   return false;
        }
        else if (guess > randomNumber) {
            System.out.println("""
                Your guess is too High!!!  O_o'
                """);   return false;
        }
        else return true;
    }
    
    static void results() throws Exception {
        if (tries <= 6) {
            System.out.printf("""
                Good job, %s! You guessed my number in %s guesses!  =D%n
                """, playerName, tries);
        }
        else {
            System.out.printf("""
                ANSWER: %s
                Nice try, %s...Maybe next time, yeah? )=%n
                """, randomNumber, playerName);
        }
        
        boolean thisCheck = false;
        
        while (!thisCheck) {
            System.out.print("Would you like to play again? (Y or N) ");
            String input = buffer.readLine().toLowerCase();  System.out.println();
            switch (input) {
                case "y" -> thisCheck = true;
                case "n" -> {
                    System.out.println("""
                        Thank you for playing! (=
                        """);   thisCheck = true;  gameFinish = true;
                }
                default -> System.out.println("""
                                Only 'Y' and 'N' are allowed!!!  >_<'
                                """);
            }
        }
    }
    
    static void toTitleCase() {
        playerName = playerName.substring(0, 1).toUpperCase() +
                        playerName.substring(1).toLowerCase();
    }
}
