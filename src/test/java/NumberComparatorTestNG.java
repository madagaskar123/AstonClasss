import org.example.NumberComparator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NumberComparatorTestNG {
    @Test(description = "Тестирование сравнения чисел")
    void compare(){
        Assert.assertEquals(
                NumberComparator.compare(7,12),"7 < 12"
        );
    }
}
