import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private static final String nameRegex = "(?<name>\\p{Alpha}+)(\\s|\\b)";
    private static final Pattern namePattern = Pattern.compile(nameRegex);
    private static final String guessRegex = "\\d{1,2}";
    private static final Pattern guessPattern = Pattern.compile(guessRegex);
    private static final Random randomizer = new Random();
    private static final String expected = "john jacob";
    private static final Matcher matcher = namePattern.matcher(expected);
    private static String message, playerName;
    private static byte randomNumber;
    private static boolean gameFinish = false, done = false;
    
    private static void setDoneTrue() { done = true; }
    private static void setDoneFalse() { done = false; }
    
    private static void setGameFinishTrue() { gameFinish = true; }
    
    
    @DisplayName("getName -> Exception")
    @Test void getNameException() {
        
        String expected = "Sorry, only letters of the alphabet are allowed!!!  >.<'";
    
        String name = "123";
        if (name.matches("[^\\p{Alpha}\\s]+")) {
            message = expected;
        }
        
        assertEquals(expected, message);
    }
    
    
    @DisplayName("getNameTitled -> Pass")
    @Test void getNamePass() {
        
        StringBuilder sb = new StringBuilder();
        
        while (matcher.find()) {
            sb.append(matcher.group().substring(0, 1).toUpperCase())
                    .append(matcher.group().substring(1)
                            .toLowerCase());
        }
    
        playerName = sb.toString();
        
        assertEquals("John Jacob", playerName);
    }
    
    
    
    @Test void getGuess() {
    
    }
    
    @Test void evaluateGuess() {
    
    }
    
    @DisplayName("Results -> 1 try")
    @Test void resultsInOneTry() {
        byte tries = 1;     playerName = "John Jacob";
    
        String guessText = tries == 1 ? "try" : "tries";
        if (tries <= 6) {
            System.out.printf("""
                    Good job, %s! You guessed my number in %s %s!  =D%n
                    """, playerName, tries, guessText);
        } else {
            System.out.printf("""
                    ANSWER: %s
                    Nice try, %s...Maybe next time, yeah? )=%n
                    """, randomNumber, playerName);
        }
        assertEquals("try", guessText);
    }
    
    @DisplayName("Results -> <= 6 tries")
    @Test void resultsUpToSix() {
        byte tries = 6;     playerName = "John Jacob";
        
        String guessText = tries == 1 ? "try" : "tries";
        if (tries <= 6) {
            System.out.printf("""
                    Good job, %s! You guessed my number in %s %s!  =D%n
                    """, playerName, tries, guessText);
        } else {
            System.out.printf("""
                    ANSWER: %s
                    Nice try, %s...Maybe next time, yeah? )=%n
                    """, randomNumber, playerName);
        }
        assertEquals("tries", guessText);
    }
    
    @DisplayName("Results -> >6 tries")
    @Test void resultsMoreThanSix() {
        byte tries = 7;     playerName = "John Jacob";
        String expected = String.format("""
                    ANSWER: %s
                    Nice try, %s...Maybe next time, yeah? )=%n
                    """, randomNumber, playerName);
        
        String guessText = tries == 1 ? "try" : "tries";
        if (tries <= 6) {
            System.out.printf("""
                    Good job, %s! You guessed my number in %s %s!  =D%n
                    """, playerName, tries, guessText);
        } else {
            message = expected;
        }
        assertEquals(expected, message);
    }
    
    @DisplayName("Results -> Replay")
    @Test void resultsReplay() {
        setDoneFalse();
        String input = "";
        while (!done) {
            System.out.print("Would you like to play again? (Y or N) ");
            try {
                input = "Y".toLowerCase();
                switch (input) {
                    case "y" -> setDoneTrue();
                    case "n" -> {
                        System.out.format("""
                        %nThank you for playing! (=
                        """);   setDoneTrue();  setGameFinishTrue();
                    }
                    default -> throw new Exception();
                }
            } catch (Exception e) {
                System.out.format("""
                    %nOnly 'Y' and 'N' are allowed!!!  >_<'
                    """);
            }
        }
        assertEquals("y", input);
    }
    
    @DisplayName("Results -> Quit")
    @Test void resultsQuit() {
        setDoneFalse();
        String input = "";
        while (!done) {
            System.out.print("Would you like to play again? (Y or N) ");
            try {
                input = "N".toLowerCase();
                switch (input) {
                    case "y" -> setDoneTrue();
                    case "n" -> {
                        System.out.format("""
                        %n%nThank you for playing! (=
                        """);   setDoneTrue();  setGameFinishTrue();
                    }
                    default -> throw new Exception();
                }
            } catch (Exception e) {
                System.out.format("""
                    %nOnly 'Y' and 'N' are allowed!!!  >_<'
                    """);
            }
        }
        assertEquals("n", input);
    }
    
    @DisplayName("Results -> Exceptions")
    @Test void resultsExceptions() {
        setDoneFalse();
        String input = "";
        String expected = String.format("%n%nOnly 'Y' and 'N' are allowed!!!  >_<'");
        while (!done) {
            System.out.print("Would you like to play again? (Y or N) ");
            try {
                input = "*".toLowerCase();
                switch (input) {
                    case "y" -> setDoneTrue();
                    case "n" -> {
                        System.out.format("""
                        %n%nThank you for playing! (=
                        """);   setDoneTrue();  setGameFinishTrue();
                    }
                    default -> throw new Exception(expected);
                }
            } catch (Exception e) {
                message = expected;
                System.out.println(message);
                setDoneTrue();
            }
        }
        assertEquals(expected, message);
    }
}