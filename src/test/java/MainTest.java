import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainTest {
    @Test
    @Timeout(22)
    @Disabled
    public void main_method_22Second_test(){
        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4),
                new Horse("Ace of Spades", 2.5),
                new Horse("Zephyr", 2.6),
                new Horse("Blaze", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Cherry", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);

        for (int i = 0; i < 100; i++) {
            hippodrome.move();
            try {
                Main.watch(hippodrome);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String winnerName = hippodrome.getWinner().getName();
        System.out.println(winnerName + " wins!");
    }
}
