import Hangman.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    
    @DisplayName("printAllOutputs[x]")
    @Test void printAllOutputs() {
        System.out.println(Output.LOGO);
        System.out.println(Output.START);
        System.out.println(Output.HEAD);
        System.out.println(Output.BODY);
        System.out.println(Output.ARM_1);
        System.out.println(Output.ARM_2);
        System.out.println(Output.LEG_1);
        System.out.println(Output.LEG_2);
        System.out.println(Output.LOSE);
    }
    
    
    @DisplayName("printFromArray[x]")
    @Test void printFromArray() {
        for (int tries = 0; tries < Output.values().length; tries++){
            System.out.println(Hangman.array[tries]);
        }
    }
    
    
    @DisplayName("returnAQuote[x]")
    @Test void returnAQuote(){
        String quote = Hangman.returnRandomQuote();
        assertEquals("To Be, Or Not To Be.", quote);
    }
    
    
    @DisplayName("checkGuessesHashSet[x]")
    @Test void checkGuessesHashSet(){
        int tries = 0;
        HashSet<String> guessesHash = new HashSet<>(Set.of("G".toLowerCase()));
        
        try{
            // increment tries, check if Guess in Set
            tries++;
            String guess = "T".toLowerCase();
            if (guessesHash.contains(guess)) {throw new Exception();}
            // add guess to Set if not in Set
            else {guessesHash.add(guess);}
        } catch (Exception e) {
            // if Guess in Set, let player know of duplicate value
            System.out.println("Already guessed that");
        }
        
        for (String x : guessesHash){
            System.out.println(x);
        }
        System.out.println(guessesHash.size());
    
        assertEquals(1, tries);
    }
    
    
    @DisplayName("checkPlayerGuesses[x]")
    @Test void checkPlayerGuesses(){
        String secretQuote = "To Be, Or Not To Be.";
        String guess = "T".toLowerCase();
        assertTrue(secretQuote.toLowerCase().contains(guess));
    }
    
    
    @DisplayName("returnHiddenQuote[x]")
    @Test void returnHiddenQuote(){
        String secretQuote = "To Be, Or Not To Be.";
        String expect = "__ __, __ ___ __ __.";
        
//        HashSet<String> guessesHash = new HashSet<>();
        HashSet<String> guessesHash = new HashSet<>(Set.of(
                "B".toLowerCase(), "T".toLowerCase(), "N".toLowerCase())
        );
        
        
        String output = "";
        for (int i = 0; i < secretQuote.length(); i++){
            if (guessesHash.contains(
                    String.valueOf(secretQuote.charAt(i)).toLowerCase())){
                output += secretQuote.charAt(i);
            } else if (String.valueOf(secretQuote.charAt(i))
                    .matches("[^\\p{Alpha}]")){
                output += secretQuote.charAt(i);
            } else {
                output += "_";
            }
        
        }
    
        System.out.println(expect);
        
        assertEquals(expect, output);
    }
    
    
    @DisplayName("getPlayerGuess[]")
    @Test void getPlayerGuess(){
        Scanner scan = new Scanner(System.in);
        String guess = "";
        while (true){
            System.out.print("Take A Guess: ");
            try {
                guess = "6".toLowerCase();
                if (guess.matches("[^\\p{Alpha}]")){
                    throw new Exception("Only Letters Allowed...");
                }
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                guess = scan.nextLine();
            }
        }
    }
    
    @DisplayName("getSetCount[]")
    @Test void getSetCount(){
        String secretQuote = "To Be, Or Not To Be.";
        HashSet<String> guesses = new HashSet<>();
        
        for (int i = 0; i < secretQuote.length(); i++){
            if (String.valueOf(secretQuote.charAt(i)).matches("\\p{Alpha}")){
                guesses.add(String.valueOf(secretQuote.charAt(i)).toLowerCase());
            }
        }
        System.out.println(guesses.size());
        assertEquals(6, guesses.size());
        
    }
}