import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


public class HorseTest {
    @Nested
    public class ConstructorTest{
        @Test
        public void constructor_firstParam_is_null_and_exception_message_test(){
            String expected = "Name cannot be null.";
            Throwable throwable = Assertions.assertThrows(IllegalArgumentException.class,()->new Horse(null,1.0));
            Assertions.assertEquals(expected, throwable.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {" "," ","  ","  ","     "})
        public void constructor_firstParam_is_spaceOrTab_and_exceprion_message_test(String param){
            String expected = "Name cannot be blank.";
            Throwable throwable =  Assertions.assertThrows(IllegalArgumentException.class,()->new Horse(param,1.0));
            Assertions.assertEquals(expected,throwable.getMessage());
        }

        @Test
        public void constructor_secondParam_is_NegativeNumber_and_exception_message_test(){
            String expected = "Speed cannot be negative.";
            Throwable throwable = Assertions.assertThrows(IllegalArgumentException.class,()->new Horse("Slevin",-1));
            Assertions.assertEquals(expected,throwable.getMessage());
        }

        @Test
        public void constructor_thirdParam_is_NegativeNumber_and_exception_message_test(){
            String expected = "Distance cannot be negative.";
            //IOexe
            Throwable throwable = Assertions.assertThrows(IllegalArgumentException.class,()->new Horse("Slevin",1,-1));
            Assertions.assertEquals(expected,throwable.getMessage());
        }

    }

    @Nested
    public class MethodTest{
        Horse horse = new Horse("Slevin",1.1,4.4);
        @Test
        public void getName_test(){
            String expected = "Slevin";
            Assertions.assertEquals(expected,horse.getName());
        }
        @Test
        public void getSpeed_test(){
            String expected = "1.1";
            String actual = String.valueOf(horse.getSpeed());
            Assertions.assertEquals(expected,actual);
        }
        @Test
        public void getDistance_test(){
            String expected = "4.4";
            String actual = String.valueOf(horse.getDistance());
            Assertions.assertEquals(expected,actual);
            Horse horse2 = new Horse("Pegas",1.1);
            String expected2 = "0.0";
            String actual2 = String.valueOf(horse2.getDistance());
            Assertions.assertEquals(expected2,actual2);
        }
            @ParameterizedTest
            @ValueSource(doubles = {4.4,4.2,3.6,6.0})
            public void move_test(double arg){
                try(MockedStatic<Horse> mocked = Mockito.mockStatic(Horse.class);){
                 mocked.when(()->Horse.getRandomDouble(0.2, 0.9)).thenReturn(arg);
                 Horse horse1 = new Horse("Pony",2.2);
                 Double expectedDistance = horse1.getDistance()+ horse1.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
                 horse1.move();
                 Assertions.assertEquals(expectedDistance,horse1.getDistance());
                }catch (Exception e){}
            }
    }
}
