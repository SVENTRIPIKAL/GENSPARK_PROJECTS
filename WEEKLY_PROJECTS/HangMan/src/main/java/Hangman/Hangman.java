package Hangman;

import java.util.Random;
import java.util.Arrays;
import java.util.HashSet;

public abstract class Hangman {
    private static String secretQuote;
    private static HashSet<String> correctGuesses;
    
    //MAIN GAME METHOD
    public static void play() {
        boolean playing = true;
        
        while (playing){
            Player player = new Player();
            
            secretQuote = returnRandomQuote();
            correctGuesses = new HashSet<>();
            
            int winningLetterCount = returnLetterSetCount();
            HashSet<String> wrongGuesses = new HashSet<>();
            
            System.out.println(array[getTries(player)]);
            
            for (setTries(player); getTries(player) <= array.length-1;){
                System.out.println(array[getTries(player)]);
                
                if (getTries(player) == array.length-1) { break; }
    
                System.out.printf("""
                    \t\t\t\t\t\t\t\tWRONG: %s%n%n
                    QUOTE: %12s%n%n
                    """, Arrays.toString(wrongGuesses.toArray()),
                        returnQuoteHidden());
                
                String guess = getGuess(player);
    
                if (!secretQuote.toLowerCase().contains(guess)){
                    setTries(player);
                    wrongGuesses.add(guess.toUpperCase());
                } else {
                    correctGuesses.add(guess);
                }
                
                if (correctGuesses.size() == winningLetterCount){ break; }
            }
            
            String outcome = getTries(player) <= 7 ? "WIN" : "LOSE";
    
            System.out.printf("""
                %nYOU %s!
                ANSWER: %10s%n%n
                """, outcome, secretQuote);
            
            playing = getReplay(player);    System.out.printf("%n%n");
        }
    
        System.out.println("""
            THANK YOU FOR PLAYING!
            """);
    }
    
    //ARRAY OF HANGMAN CONSOLE OUTPUTS
    private static final Output[] array = {
            Output.LOGO, Output.START, Output.HEAD,
            Output.BODY, Output.ARM_1, Output.ARM_2,
            Output.LEG_1, Output.LEG_2, Output.LOSE
    };
    
    //ARRAY OF QUOTES [MORE CAN BE ADDED AS DESIRED]
    private static final String[] quotes = {
            "To Be, Or Not To Be.",
            "Oh, Happy Days.",
            "I Am Sam...Sam I Am.",
            "Java Is Fun...When You Know What You're Doing!"
    };
    
    //METHOD RETURN RANDOM QUOTE
    private static String returnRandomQuote(){
        Random temp = new Random();
        byte x = (byte) temp.nextInt(0, 101);
        Random random = new Random(x);
        byte quotePick = (byte) random.nextInt(0, quotes.length);
        return quotes[quotePick];
    }
    
    //RETURNS NUM OF LETTERS IN QUOTE AS HASHSET
    private static byte returnLetterSetCount(){
        HashSet<String> lettersSet = new HashSet<>();
        for (int i = 0; i < secretQuote.length(); i++){
            if (String.valueOf(secretQuote.charAt(i))
                    .matches("\\p{Alpha}")){
                lettersSet.add(String.valueOf(secretQuote.charAt(i))
                        .toLowerCase());
            
            }
        }
        return (byte) lettersSet.size();
    }
    
    //RETURNS HIDDEN QUOTE
    private static String returnQuoteHidden(){
        String output = "";
        for (byte i = 0; i < secretQuote.length(); i++){
            if (correctGuesses.contains(
                    String.valueOf(secretQuote.charAt(i)).toLowerCase())){
                output += secretQuote.charAt(i);
            } else if (String.valueOf(secretQuote.charAt(i))
                    .matches("[^\\p{Alpha}]")){
                output += secretQuote.charAt(i);
            } else {
                output += "_";
            }
        }
        return output;
    }
    
    //GET PLAYER TRIES
    private static byte getTries(Player player){
        return (byte) player.getTries();
    }
    
    //INCREASE PLAYER TRIES
    private static void setTries(Player player){
        player.setTries(player.getTries() + 1);
    }
    
    //GET PLAYER GUESS
    private static String getGuess(Player player){
        return player.provideGuess();
    }
    
    //RETURN PLAYER REPLAY REPLY
    private static boolean getReplay(Player player) {
        return player.provideReplay();
    }
}
