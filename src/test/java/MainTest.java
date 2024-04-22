import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Disabled
    @Test
    @Timeout(value = 22)
//    Проверить, что метод выполняется не дольше 22 секунд. Для этого воспользуйся аннотацией Timeout.
//    После написания этого теста, отключи его (воспользуйся аннотацией Disabled).
//    Так он не будет занимать время при запуске всех тестов, а при необходимости его можно будет запустить вручную.
    public void timeout() throws Exception{
        Main.main(null);
    }

}