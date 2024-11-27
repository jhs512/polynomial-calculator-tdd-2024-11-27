import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.example.App;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    @DisplayName("덧셈 테스트")
    void addTest() {
        //given
        String expression = """
            1 + 2
            exit
            """;
        ByteArrayInputStream input = new ByteArrayInputStream(expression.getBytes());
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        //when
        App app = new App(scanner);
        app.run();

        //then
        String result = output.toString().trim();
        assertThat(result).contains("calculate = 3");
    }

    @Test
    @DisplayName("뺄셈 테스트")
    void subTest() {
        //given
        String expression = """
            70 - 10
            exit
            """;
        ByteArrayInputStream input = new ByteArrayInputStream(expression.getBytes());
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        //when
        App app = new App(scanner);
        app.run();

        //then
        String result = output.toString().trim();
        assertThat(result).contains("calculate = 60");
    }

    @Test
    @DisplayName("곱셈 테스트")
    void mulTest() {
        //given
        String expression = """
            5 * 11
            exit
            """;
        ByteArrayInputStream input = new ByteArrayInputStream(expression.getBytes());
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        //when
        App app = new App(scanner);
        app.run();

        //then
        String result = output.toString().trim();
        assertThat(result).contains("calculate = 55");
    }

    @Test
    @DisplayName("나눗셈 테스트")
    void divTest() {
        //given
        String expression = """
            55 / 11
            exit
            """;
        ByteArrayInputStream input = new ByteArrayInputStream(expression.getBytes());
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        //when
        App app = new App(scanner);
        app.run();

        //then
        String result = output.toString().trim();
        assertThat(result).contains("calculate = 5");
    }

    @Test
    @DisplayName("다항식 테스트")
    void combinedOperationsTest() {
        //given
        String expression = """
            55 / 11 + 1
            exit
            """;
        ByteArrayInputStream input = new ByteArrayInputStream(expression.getBytes());
        Scanner scanner = new Scanner(input);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        //when
        App app = new App(scanner);
        app.run();

        //then
        String result = output.toString().trim();
        assertThat(result).contains("calculate = 6");
    }

}
