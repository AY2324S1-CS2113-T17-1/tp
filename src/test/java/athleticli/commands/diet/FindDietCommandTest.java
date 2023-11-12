package athleticli.commands.diet;

import athleticli.data.Data;
import athleticli.data.diet.Diet;
import athleticli.exceptions.AthletiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class FindDietCommandTest {
    private FindDietCommand findDietCommand;
    private Data data;

    @BeforeEach
    void setUp() {
        Diet diet1 = new Diet(100, 20, 30, 40, LocalDateTime.of(2020, 10, 10, 10, 10));
        Diet diet2 = new Diet(200, 50, 35, 20, LocalDateTime.of(2023, 1, 10, 10, 11));
        Diet diet3 = new Diet(100, 20, 30, 40, LocalDateTime.of(2023, 1, 10, 10, 11));
        data = new Data();
        data.getDiets().add(diet1);
        data.getDiets().add(diet2);
        data.getDiets().add(diet3);
    }

    @Test
    void execute_findTwoDiets() throws AthletiException {
        findDietCommand = new FindDietCommand(LocalDate.of(2023, 1, 10));
        String[] expected = {"I've found these diets:", data.getDiets().get(1).toString(),
                             data.getDiets().get(2).toString()};
        String[] actual = findDietCommand.execute(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    void execute_findOneDiet() throws AthletiException {
        findDietCommand = new FindDietCommand(LocalDate.of(2020, 10, 10));
        String[] expected = {"I've found these diets:", data.getDiets().get(0).toString()};
        String[] actual = findDietCommand.execute(data);
        assertArrayEquals(expected, actual);
    }

    @Test
    void execute_findNoDiets() throws AthletiException {
        findDietCommand = new FindDietCommand(LocalDate.of(2020, 10, 11));
        String[] expected = {"I've found these diets:"};
        String[] actual = findDietCommand.execute(data);
        assertArrayEquals(expected, actual);
    }
}
