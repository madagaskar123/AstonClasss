import org.example.TriangleAreaCalculator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TriangleAreaTestNG {
    @Test(description = "Тестирование площади треугольника")
    void area(){
        Assert.assertEquals(
                TriangleAreaCalculator.calculateArea(10.0, 5.0), 25
        );
    }
}
