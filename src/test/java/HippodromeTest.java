import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void HippodromeConstructor(){
//        Проверить, что при передаче в конструктор null, будет выброшено IllegalArgumentException;
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->{
            new Hippodrome(null);
                });


//        Проверить, что при передаче в конструктор null, выброшенное исключение будет содержать сообщение
//        "Horses cannot be null.";
        assertEquals( "Horses cannot be null.", exception.getMessage());


//        Проверить, что при передаче в конструктор пустого списка, будет выброшено IllegalArgumentException;
        List<Horse> horseList = new ArrayList<>();
        Throwable exception1 = assertThrows(IllegalArgumentException.class,
                ()->{
            new Hippodrome(horseList);
                });



//        Проверить, что при передаче в конструктор пустого списка, выброшенное исключение будет содержать сообщение
//        "Horses cannot be empty.";
        assertEquals("Horses cannot be empty.", exception1.getMessage());


    }

    @Test
    void getHorses() {
    }

    @Test
    void move() {
    }

    @Test
    void getWinner() {
//        Проверить, что метод возвращает лошадь с самым большим значением distance.


        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4, 10),
                new Horse("Ace of Spades", 2.5, 15),
                new Horse("Zephyr", 2.6, 14),
                new Horse("Blaze", 2.7, 2),
                new Horse("Lobster", 2.8, 10),
                new Horse("Pegasus", 2.9, 23),
                new Horse("Cherry", 3, 32)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = horses.get(6);
        assertEquals(winner, hippodrome.getWinner());
    }
}