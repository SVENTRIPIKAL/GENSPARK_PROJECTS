import java.util.Scanner;

public class Main {
    private static int userInput = -1;
    private static boolean done = false;
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("""
            You are in a land full of dragons. In front of you,
            you see two caves. In one cave, the dragon is friendly
            and will share his treasure with you. In the other, the
            dragon is greedy and hungry and will eat you on sight.
            """);
        
        int input = getUserInput();
        
        switch (input) {
            case 1 -> System.out.println("""
                \nYou approach a cave...
                It is dark and spooky...
                A large dragon jumps out in front of you!
                He opens his jaws and gobbles you down in one bite!  )=
                """);
            
            case 2 -> System.out.println("""
                \nYou approach a mysterious cave...
                It glows with various colors inside...
                A miniature dragon reveals itself to you as you near!
                He grants you entry into the cave and to all his treasures  =D
                """);
        }
    }
    
    private static int getUserInput() {
        String message = "Only 1 and 2 are valid! TRY AGAIN!!!  >_<'";
        while (!done) {
            System.out.print("Which cave will you go into? (1 or 2) ");
            try {
                userInput = Integer.parseInt(scanner.nextLine());
                if (userInput != 1 && userInput != 2) {
                    throw new Exception();
                }
                setDoneTrue();  closeScanner();
            } catch (Exception e) {
                System.out.format("%nCaught Exception: %s%n%n", message);
            }
        }
        return userInput;
    }
    
    private static void setDoneTrue() { done = true; }
    
    private static void closeScanner() { scanner.close(); }
}
