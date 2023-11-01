import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.times;


public class HippodromeTest {

    @Nested
    public class ConstructorTest{


        @Test
        public void constructor_param_is_null_and_message(){
            String expected = "Horses cannot be null.";
            Throwable throwable = Assertions.assertThrows(IllegalArgumentException.class,()->new Hippodrome(null));
            Assertions.assertEquals(expected,throwable.getMessage());
        }
        @Test
        public void constructor_param_isEmptyList_and_message(){
            String expected = "Horses cannot be empty.";
            Throwable throwable = Assertions.assertThrows(IllegalArgumentException.class,()->new Hippodrome(new ArrayList<>()));
            Assertions.assertEquals(expected,throwable.getMessage());
        }
    }
    @Nested
    public class MethodTest{
        @Spy
        Hippodrome hippodrome;
        @Test
        public void getHorses_Test(){
            boolean isEqualsList = true;
             List<Horse> horses = new ArrayList<>();
             for(int i = 0;i<30;i++){
                 horses.add(new Horse(i+" horse",i));
             }
             Hippodrome hippodrome = new Hippodrome(horses);
            for(int i = 0;i<horses.size();i++){
                if(!horses.get(i).equals(hippodrome.getHorses().get(i))){
                    isEqualsList = false;
                }
            }
            Assertions.assertTrue(isEqualsList);
        }

        @Test
        public void move_test() {
            List<Horse> horses = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                horses.add(Mockito.mock(Horse.class));
            }
            hippodrome = new Hippodrome(horses);
            hippodrome.move();
            for (int i = 0; i < hippodrome.getHorses().size(); i++) {
                Mockito.verify(hippodrome.getHorses().get(i), times(1)).move();
            }
        }
        @Test
        public void getWinner_test(){
               List<Horse> horses = new ArrayList<>();
               for(int i = 0;i<3;i++){
                   horses.add(new Horse(i+"horse",i,i));
               }
               Hippodrome hippodrome1 = new Hippodrome(horses);
               Horse expectedWinner = hippodrome1.getHorses().get(2);
               Assertions.assertEquals(expectedWinner,hippodrome1.getWinner());
               horses.add(0,new Horse("Slevin",1.1,15));
               expectedWinner = hippodrome1.getHorses().get(0);
               Assertions.assertEquals(expectedWinner,hippodrome1.getWinner());
        }
    }
}
