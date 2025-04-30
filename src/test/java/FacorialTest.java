import org.example.FactorialCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FacorialTest {
    @DisplayName("Тестирование вычисления факториала")
    @Test
    void firstTest(){
        Assertions.assertEquals(
                FactorialCalculator.calculateFactorial(5), 120
        );
    }
}