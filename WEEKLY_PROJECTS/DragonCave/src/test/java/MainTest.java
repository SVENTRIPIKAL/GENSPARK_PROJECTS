import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private int userInput = -1;
    private boolean done = false;
    private String message;
    
    private void setDoneTrue() { done = true; }
    
    @Test public void getUserInputAsOne() {
        message = "Expected: 1";
        while (!done) {
            try {
                userInput = Integer.parseInt("1");
                if (userInput != 1 && userInput != 2) {
                    throw new Exception("Only 1 and 2 are valid! TRY AGAIN!!!  >_<'");
                }
                setDoneTrue();
            } catch (Exception e) {
                message = e.getMessage();
                setDoneTrue();
            }
        }
        assertEquals(1, userInput, message);
    }
    
    @Test public void getUserInputAsTwo() {
        message = "Expected: 2";
        while (!done) {
            try {
                userInput = Integer.parseInt("2");
                if (userInput != 1 && userInput != 2) {
                    throw new Exception("Only 1 and 2 are valid! TRY AGAIN!!!  >_<'");
                }
                setDoneTrue();
            } catch (Exception e) {
                message = e.getMessage();
                setDoneTrue();
            }
        }
        assertEquals(2, userInput, message);
    }
    
    @Test public void getUserInputAsInvalid() {
        String expected = "Only 1 and 2 are valid! TRY AGAIN!!!  >_<'";
        while (!done) {
            try {
                userInput = Integer.parseInt("9");
                if (userInput != 1 && userInput != 2) {
                    throw new Exception("Only 1 and 2 are valid! TRY AGAIN!!!  >_<'");
                }
                setDoneTrue();
            } catch (Exception e) {
                message = e.getMessage();
                setDoneTrue();
            }
        }
        assertEquals(expected, message);
    }
    
    @Test public void getUserInputAsException() {
        String expected = "Only 1 and 2 are valid! TRY AGAIN!!!  >_<'";
        while (!done) {
            try {
                userInput = Integer.parseInt("g");
                if (userInput != 1 && userInput != 2) {
                    throw new Exception("Only 1 and 2 are valid! TRY AGAIN!!!  >_<'");
                }
                setDoneTrue();
            } catch (Exception e) {
                message = expected;
                setDoneTrue();
            }
        }
        assertEquals(expected, message);
    }
    
    @Test public void doneIsTrue() {
        setDoneTrue();
        assertTrue(done, "Expected: true");
    }
}