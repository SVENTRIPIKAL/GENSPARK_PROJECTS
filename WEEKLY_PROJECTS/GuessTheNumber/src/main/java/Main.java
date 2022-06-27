import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    private static final BufferedReader buffer = new BufferedReader(
                                        new InputStreamReader(System.in));
    private static final String nameRegex = "(?<name>\\p{Alpha}+)(\\s|\\b)";
    private static final Pattern namePattern = Pattern.compile(nameRegex);
    private static final String guessRegex = "\\d{1,2}";
    private static final Pattern guessPattern = Pattern.compile(guessRegex);
    private static final Random randomizer = new Random();
    private static Matcher matcher;
    private static String playerName;
    private static byte tries, randomNumber;
    private static boolean gameFinish = false, done = false;
    private static void setDoneStatus(boolean status) { done = status; }
    private static void setGameFinishTrue() { gameFinish = true; }
    
    public static void main(String[] args) {
        
        playerName = getNameTitled();
        
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
                    System.out.println("Sorry, only 1 to 2 numbers " +
                            "are allowed!!!  >.<'\n");
                }
                else {
                    if (evaluateGuess()) { break; }
                }
            }
            
            results();
        }
    }
    
    static String getNameTitled() {
        String name = "";   setDoneStatus(false);
        while (!done) {
            System.out.print("Hello! What is your name? ");
            try {
                name = buffer.readLine().strip();
                
                if (name.matches("[^\\p{Alpha}\\s]+")) { throw new Exception(); }
    
                StringBuilder sb = new StringBuilder();
                matcher = namePattern.matcher(name);
                
                while (matcher.find()) {
                    sb.append(matcher.group().substring(0, 1).toUpperCase())
                            .append(matcher.group().substring(1)
                                    .toLowerCase());
                }   name = sb.toString();   setDoneStatus(true);
                
            } catch (Exception e) {
                System.out.format("%nSorry, only letters " +
                        "and spaces are allowed!!!  >.<'%n%n");
            }
        }
        return name;
    }
    
    
    static Matcher getGuess() {
        String guess = "";
        try {
            guess = buffer.readLine().strip();
        } catch (Exception e) {
            System.out.println("Sorry, only 1 to 2 numbers are allowed!!!  >.<'\n");
        }
        return guessPattern.matcher(guess);
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
    
    static void results() {
        String guessText = tries == 1 ? "try" : "tries";
        if (tries <= 6) {
            System.out.printf("""
                %nGood job, %s! You guessed my number in %s %s!  =D%n
                """, playerName, tries, guessText);
        } else {
            System.out.printf("""
                %nANSWER: %s
                Nice try, %s...Maybe next time, yeah? )=%n
                """, randomNumber, playerName);
        }
        
        setDoneStatus(false);
        
        while (!done) {
            String input;
            System.out.print("Would you like to play again? (Y or N) ");
            try {
                input = buffer.readLine().toLowerCase();
                switch (input) {
                    case "y" -> setDoneStatus(true);
                    case "n" -> {
                        System.out.format("""
                        %nThank you for playing! (=%n
                        """);
                        setDoneStatus(true);  setGameFinishTrue();  buffer.close();
                    }
                    default -> throw new Exception();
                }
            } catch (Exception e) {
                System.out.format("""
                    %nOnly 'Y' and 'N' are allowed!!!  >_<'%n
                    """);
            }
        }
    }
}