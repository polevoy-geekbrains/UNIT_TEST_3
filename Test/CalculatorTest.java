
import jdk.jfr.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private Calculator calculator;
    @BeforeEach
    void init(){
        calculator = new Calculator();
    }
    @Test
    void evaluatesExpression() {
        Calculator calculator = new Calculator();
        assertThat(calculator.calculation(2, 6, '+')).isEqualTo(8);
    }

    @Test
    void subtractionExpression() {
        Calculator calculator = new Calculator();
        assertThat(Calculator.calculation(2, 2, '-')).isEqualTo(0);
    }

    @Test
    void multiplicationExpression() {
        Calculator calculator = new Calculator();
        assertThat(Calculator.calculation(2, 7, '*')).isEqualTo(14);
    }

    @Test
    void divisionExpression() {
        Calculator calculator = new Calculator();
        assertThat(Calculator.calculation(100, 50, '/')).isEqualTo(2);
    }

    @Test
    void expectedIllegalStateExpression() {
        Calculator calculator = new Calculator();
        assertThatThrownBy(() -> Calculator.calculation(8, 4, '_'))
                .isInstanceOf(IllegalStateException.class);
    }

    // <-------JUnit-Jupiter-Params
    @ParameterizedTest
    @ValueSource(chars = {'&', '#', '='})
    void expectedIllegalStateExpressionToo(char i) {
        // Arrange
        Calculator calculator = new Calculator();
        char o = i;

        // Act
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            calculator.calculation(8, 4, o);
        });

        // Assert
        String expectedMessage = "Unexpected value operator: " + o;
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }

    @Test
    void getOperandCompletesCorrectlyWithNumbers() {
        String testedValue = "9"; // Значение для тестов
        ByteArrayInputStream in = new ByteArrayInputStream(testedValue.getBytes());
        InputStream inputStream = System.in;  // Сохраняем ссылку на ввод с клавиатуры
        System.setIn(in); // Подменяем ввод

        Calculator.getOperand(); // Вызываем метод

        System.out.println(testedValue); // Для наглядности вывода
        System.setIn(inputStream); // Подменяем обратно
    }

    @Test
    void getOperandCompletesCorrectlyWithNotNumbers() {
        String testedValue = "K";
        ByteArrayInputStream in = new ByteArrayInputStream(testedValue.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream inputStream = System.in;
        System.setIn(in);
        System.setOut(new PrintStream(out));
        assertThatThrownBy(() -> Calculator.getOperand())
                .isInstanceOf(IllegalStateException.class)
                .describedAs("Input error");
        System.setIn(inputStream);
        System.setOut(null);
    }

    @Test
    void computeAreaCircle() {
        Calculator calculator = new Calculator();
        assertEquals(314.1592653589793, calculator.computeAreaCircle(10), "Should return right circle area");
    }

    //HW3.1L: Попробуйте реализовать в калькуляторе с помощью методологии TDD (с описанием шагов) функцию расчета длины окружности
    // P=2πR (Для окружности с радиусом 10 длина окружности = 62.83185307179586)
    @Test
    void computeLengthCircle() {}


    // ДЗ №1. Разбить большой метод под номером 3 и проверить покрытие(должно быть 100%)
    @ParameterizedTest
    @CsvSource({
            "0.9, 1, 10",
            "100, 100, 0",
            "99, 100, 1",
            "500, 1000, 50",
            "10, 1000, 99",
            "0, 100,100"
    })
    void positiveTestsCalculatingDiscount(double expected, double inputA, int inputB){
        assertEquals(expected, calculator.calculatingDiscount(inputA,inputB));
    }
    @Test
    void negativeTestBigDiscount(){
        assertThatThrownBy(() ->
                calculator.calculatingDiscount(2000.0, 101))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("Скидка должна быть в диапазоне от 0 до 100%"); // процент скидки больше 100%
    }
    @Test
    void negativeTestLessDiscount(){
        Exception exception = assertThrows(ArithmeticException.class, () ->
                calculator.calculatingDiscount(2000.0, -1));

        String expectedMessage = "Скидка должна быть в диапазоне от 0 до 100%";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void negativeTestLessPurchaseAmount(){
        assertThatThrownBy(() ->
                calculator.calculatingDiscount(-0.01, 10))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("Сумма покупки не может быть отрицательной"); // процент скидки больше 100%
    }
}