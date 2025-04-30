import org.example.TriangleAreaCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TriangleAreaCalculatorTests {
    @Test
    @DisplayName("Тестирование нахождения площади треугольника")
    void areaTest(){
        Assertions.assertEquals(
                TriangleAreaCalculator.calculateArea(10.0, 5.0), 25.0
        );

    }
}
