import org.example.NumberComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NumberComparatorTests {
    @Test
    @DisplayName("Тестирование сравнения чисел")
    void compare(){
        Assertions.assertEquals(
                NumberComparator.compare(7,12),"7 < 12"
        );
    }
}
