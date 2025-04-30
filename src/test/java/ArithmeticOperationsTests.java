import org.example.ArithmeticOperations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArithmeticOperationsTests {
    @Test
    @DisplayName("Тестирование сложения")
    void  addTest(){
        Assertions.assertEquals(
                ArithmeticOperations.add(10, 3), 13
        );

    }
    @Test
    @DisplayName("Тестирования деления")
    void divideTest() {
        Assertions.assertEquals(
                ArithmeticOperations.divide(10, 3), 3.3333333333333335
        );
    }
    @Test
    @DisplayName("Тестирования умножения")
    void multiplyTest(){
        Assertions.assertEquals(
                ArithmeticOperations.multiply(10,3),30
        );
    }
    @Test
    @DisplayName("Тестирования вычитания")
    void subtractTest(){
        Assertions.assertEquals(
                ArithmeticOperations.subtract(10,3),7
        );
    }


}
