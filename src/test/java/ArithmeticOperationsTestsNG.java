import org.example.ArithmeticOperations;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ArithmeticOperationsTestsNG {
    @Test(description = "Тестирование сложения числе")
    void addNum(){
        Assert.assertEquals(
                ArithmeticOperations.add(10, 3), 13
        );
    }
    @Test(description = "Тестирование вычитания числел")
    void subtractNum(){
        Assert.assertEquals(
                ArithmeticOperations.subtract(10,3), 7
        );
    }
    @Test(description = "Тестирование умножения числел")
    void multiplyNum() {
        Assert.assertEquals(
                ArithmeticOperations.multiply(10, 3), 30
        );
    }
    @Test(description = "Тестирование деления числел")
    void divideNum() {
        Assert.assertEquals(
                ArithmeticOperations.divide(10, 3), 3.3333333333333335
        );
    }
}
