import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
            You are in a land full of dragons. In front of you,
            you see two caves. In one cave, the dragon is friendly
            and will share his treasure with you. The other dragon
            is greedy and hungry and will eat you on sight.
            """);
        while (!done) {
            System.out.print("Which cave will you go into? (1 or 2) ");
            String input = scanner.nextLine();  System.out.println();
            switch (input) {
                case "1" -> {
                    System.out.println("""
                        You approach a cave...
                        It is dark and spooky...
                        A large dragon jumps out in front of you!
                        He opens his jaws and gobbles you down in one bite!  )=
                        """);   done = true;
                }
                case "2" -> {
                    System.out.println("""
                        You approach a mysterious cave...
                        It glows with various colors inside...
                        A miniature dragon reveals itself to you as you near!
                        He grants you entry into the cave and to all his treasures  =D
                        """);   done = true;
                }
                default -> System.out.println("""
                        Only 1 and 2 are valid! TRY AGAIN!!!  >_<'
                        """);
            }
        }
    }
}
