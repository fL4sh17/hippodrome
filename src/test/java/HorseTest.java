import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t", "\r", "\f"})
    void HorseConstructor(String argument){

        // Проверить, что при передаче в конструктор первым параметром null,
        // будет выброшено IllegalArgumentException.
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
            new Horse(null, 10, 2);
                });


//        Проверить, что при передаче в конструктор первым параметром null,
//        выброшенное исключение будет содержать сообщение "Name cannot be null.".
//        Для этого нужно получить сообщение из перехваченного исключения
//        и воспользоваться методом assertEquals;
        assertEquals("Name cannot be null.", exception.getMessage());


//        Проверить, что при передаче в конструктор первым параметром пустой строки или строки содержащей
//        только пробельные символы (пробел, табуляция и т.д.), будет выброшено
//        IllegalArgumentException. Чтобы выполнить проверку с разными вариантами пробельных символов,
//        нужно сделать тест параметризованным;
        Throwable exception1 = assertThrows(IllegalArgumentException.class,
                () -> {
            new Horse(argument, 10, 2);
                });


//        Проверить, что при передаче в конструктор первым параметром пустой строки или строки содержащей
//        только пробельные символы (пробел, табуляция и т.д.),
//        выброшенное исключение будет содержать сообщение "Name cannot be blank.";
        assertEquals("Name cannot be blank.", exception1.getMessage());



//        Проверить, что при передаче в конструктор вторым параметром отрицательного числа,
//        будет выброшено IllegalArgumentException;
        Throwable exception3 = assertThrows(IllegalArgumentException.class,
                () -> {
            new Horse("Timur", -3, 3);
                });



//        Проверить, что при передаче в конструктор вторым параметром отрицательного числа,
//        выброшенное исключение будет содержать сообщение "Speed cannot be negative.";
        assertEquals("Speed cannot be negative.", exception3.getMessage());



//        Проверить, что при передаче в конструктор третьим параметром отрицательного числа,
//        будет выброшено IllegalArgumentException;
        Throwable exception4 = assertThrows(IllegalArgumentException.class,
                ()->{
            new Horse("Timur", 5, -2);
                });


//        Проверить, что при передаче в конструктор третьим параметром отрицательного числа,
//        выброшенное исключение будет содержать сообщение "Distance cannot be negative.";
        assertEquals("Distance cannot be negative.", exception4.getMessage());

    }


    @Test
    void getName() {
//        Проверить, что метод возвращает строку, которая была передана первым параметром в конструктор;

        Horse horse = new Horse("Bucephalus", 2.4);
        String result = horse.getName();
        Assertions.assertEquals("Bucephalus", result);
    }

    @Test
    void getSpeed() {
//        Проверить, что метод возвращает число, которое было передано вторым параметром в конструктор;

        Horse horse = new Horse("Bucephalus", 2.4);
        Double result = horse.getSpeed();
        Assertions.assertEquals(2.4, result);
    }

    @Test
    void getDistance() {
//        Проверить, что метод возвращает число, которое было передано третьим параметром в конструктор;
//        Проверить, что метод возвращает ноль, если объект был создан с помощью конструктора с двумя параметрами;

        Horse horse1 = new Horse("Bucephalus", 2.4, 15);
        Horse horse2 = new Horse("Bucephalus", 2.4);
        Double result1 = horse1.getDistance();
        Double result2 = horse2.getDistance();
        Assertions.assertEquals(15, result1);
        Assertions.assertEquals(0, result2);
    }


    @Test
//    Проверить, что метод вызывает внутри метод getRandomDouble с параметрами 0.2 и 0.9.
//    Для этого нужно использовать MockedStatic и его метод verify;
    void move() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            new Horse("Timur", 13, 25).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }

    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.3, 123.456, 12.28})
//    Проверить, что метод присваивает дистанции значение высчитанное по формуле:
//    distance + speed * getRandomDouble(0.2, 0.9). Для этого нужно замокать getRandomDouble,
//    чтобы он возвращал определенные значения, которые нужно задать параметризовав тест.
    void moveCheckDistance(double value){
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            Horse horse = new Horse("Bucephalus", 7, 7);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);
            horse.move();
            assertEquals(7+7*value, horse.getDistance());
        }
    }

}