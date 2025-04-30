import org.example.FactorialCalculator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FactorialTestNG {
    @Test(description = "Тестирования программы факториала")
    void factorial(){
        Assert.assertEquals(
                FactorialCalculator.calculateFactorial(5), 120
        );
    }
}
