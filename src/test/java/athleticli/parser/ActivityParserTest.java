package athleticli.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import athleticli.data.Goal;
import athleticli.data.activity.Activity;
import athleticli.data.activity.ActivityGoal;
import athleticli.data.activity.Run;
import athleticli.data.activity.Swim;
import athleticli.exceptions.AthletiException;

/**
 * Tests the ActivityParser class.
 */
public class ActivityParserTest {
    //@@author  AlWo223

    /**
     * Tests the parsing of the activity index for valid input.
     * @throws AthletiException if the index is invalid.
     */
    @Test
    void parseActivityIndex_validIndex_returnIndex() throws AthletiException {
        int expected = 5;
        int actual = ActivityParser.parseActivityIndex("5");
        assertEquals(expected, actual);
    }

    /**
     * Tests the parsing of the activity index for invalid input. An AthletiException should be thrown.
     */
    @Test
    void parseActivityIndex_invalidIndex_throwAthletiException() {
        assertThrows(AthletiException.class, () -> ActivityParser.parseActivityIndex("abc"));
    }

    /**
     * Tests the parsing of valid edit-activity command, which should not throw an exception.
     */
    @Test
    void parseActivityEdit_validInput_returnActivityEdit() {
        String validInput = "1 Morning Run distance/10000 datetime/2021-09-01 06:00";
        assertDoesNotThrow(() -> ActivityParser.parseActivityEdit(validInput));
    }

    /**
     * Tests the parsing of an invalid edit-activity command, which should throw an AthletiException.
     */
    @Test
    void parseActivityEdit_invalidInput_throwAthletiException() {
        String invalidInput = "1 Morning Run duration/60";
        assertThrows(AthletiException.class, () -> ActivityParser.parseActivityEdit(invalidInput));
    }

    /**
     * Tests the parsing of an invalid edit-run command, which should throw an AthletiException.
     */
    @Test
    void parseRunEdit_invalidInput_throwAthletiException() {
        String invalidInput = "1 Morning Run duration/60";
        assertThrows(AthletiException.class, () -> ActivityParser.parseRunCycleEdit(invalidInput));
    }

    /**
     * Tests the parsing of a valid edit-run command, which should not throw an exception.
     */
    @Test
    void parseRunEdit_validInput_returnRunEdit() {
        String validInput =
                "2 duration/02:00:00 distance/20000 datetime/2021-09-01 18:00 elevation/1000";
        assertDoesNotThrow(() -> ActivityParser.parseRunCycleEdit(validInput));
    }

    /**
     * Tests the parsing of a valid edit-cycle command, which should not throw an exception.
     */
    @Test
    void parseCycleEdit_validInput_returnRunEdit() {
        String validInput =
                "2 Evening Ride datetime/2021-09-01 18:00 elevation/1000";
        assertDoesNotThrow(() -> ActivityParser.parseRunCycleEdit(validInput));
    }

    /**
     * Tests the parsing of an invalid edit-cycle command, which should throw an exception.
     */
    @Test
    void parseCycleEdit_invalidInput_throwAthletiException() {
        String invalidInput = "1 ";
        assertThrows(AthletiException.class, () -> ActivityParser.parseRunCycleEdit(invalidInput));
    }

    /**
     * Tests the parsing of a valid edit-swim command, which should not throw an exception.
     */
    @Test
    void parseSwimEdit_validInput_noExceptionThrown() {
        String validInput =
                "2 Evening Ride duration/02:00:00 distance/20000 datetime/2021-09-01 18:00 style/freestyle";
        assertDoesNotThrow(() -> ActivityParser.parseSwimEdit(validInput));
    }

    /**
     * Tests the parsing of an invalid edit-swim command, which should throw an exception.
     */
    @Test
    void parseSwimEdit_invalidInput_throwAthletiException() {
        String invalidInput = "1 Morning Run duration/60";
        assertThrows(AthletiException.class, () -> ActivityParser.parseRunCycleEdit(invalidInput));
    }

    /**
     * Tests the correct parsing of a valid edit-activity index.
     *
     * @throws AthletiException If the index is invalid.
     */
    @Test
    void parseActivityEditIndex_validInput_returnIndex() throws AthletiException {
        int expected = 5;
        int actual = ActivityParser.parseActivityEditIndex("5");
        assertEquals(expected, actual);
    }

    /**
     * Tests the parsing of the list-activity flag with the detail flag present.
     *
     * @throws AthletiException If the input is invalid.
     */
    @Test
    void parseActivityListDetail_flagPresent_returnTrue() throws AthletiException {
        String input = "list-activity -d";
        assertTrue(ActivityParser.parseActivityListDetail(input));
    }

    /**
     * Tests the parsing of the list-activity flag with the detail flag absent.
     *
     * @throws AthletiException If the input is invalid.
     */
    @Test
    void parseActivityListDetail_flagAbsent_returnFalse() throws AthletiException {
        String input = "list-activity";
        assertFalse(ActivityParser.parseActivityListDetail(input));
    }

    /**
     * Tests the parsing of the valid add-activity arguments.
     *
     * @throws AthletiException If the input is invalid.
     */
    @Test
    void parseActivity_validInput_activityParsed() throws AthletiException {
        String validInput = "Morning Run duration/01:00:00 distance/10000 datetime/2021-09-01 06:00";
        Activity actual = ActivityParser.parseActivity(validInput);
        LocalTime duration = LocalTime.parse("01:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime time =
                LocalDateTime.parse("2021-09-01 06:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Activity expected = new Activity("Morning Run", duration, 10000, time);
        assertEquals(actual.getCaption(), expected.getCaption());
        assertEquals(actual.getMovingTime(), expected.getMovingTime());
        assertEquals(actual.getDistance(), expected.getDistance());
        assertEquals(actual.getStartDateTime(), expected.getStartDateTime());
    }

    /**
     * Tests the parsing of the valid set-activity-goal arguments.
     *
     * @throws AthletiException If the input is invalid.
     */
    @Test
    void parseActivityGoal_validInput_activityGoalParsed() throws AthletiException {
        String validInput = "sport/running type/distance period/weekly target/10000";
        ActivityGoal actual = ActivityParser.parseActivityGoal(validInput);
        ActivityGoal expected = new ActivityGoal(Goal.TimeSpan.WEEKLY, ActivityGoal.GoalType.DISTANCE,
                ActivityGoal.Sport.RUNNING, 10000);
        assertEquals(actual.getTimeSpan(), expected.getTimeSpan());
        assertEquals(actual.getGoalType(), expected.getGoalType());
        assertEquals(actual.getSport(), expected.getSport());
        assertEquals(actual.getTargetValue(), expected.getTargetValue());
    }

    /**
     * Tests the parsing of valid sport arguments.
     *
     * @throws AthletiException If the input is invalid.
     */
    @Test
    void parseSport_validInput_sportParsed() throws AthletiException {
        String validInput = "running";
        ActivityGoal.Sport actual = ActivityParser.parseSport(validInput);
        ActivityGoal.Sport expected = ActivityGoal.Sport.RUNNING;
        assertEquals(actual, expected);
    }

    /**
     * Tests the parsing of aan invalid sport arguments. An AthletiException should be thrown.
     */
    @Test
    void parseSport_invalidInput_throwAthletiException() {
        String invalidInput = "abc";
        assertThrows(AthletiException.class, () -> ActivityParser.parseSport(invalidInput));
    }

    /**
     * Tests the parsing of a valid goal type argument.
     *
     * @throws AthletiException If the input is invalid.
     */
    @Test
    void parseGoalType_validInput_goalTypeParsed() throws AthletiException {
        String validInput = "distance";
        ActivityGoal.GoalType actual = ActivityParser.parseGoalType(validInput);
        ActivityGoal.GoalType expected = ActivityGoal.GoalType.DISTANCE;
        assertEquals(actual, expected);
    }

    /**
     * Tests the parsing of a valid period argument.
     *
     * @throws AthletiException If the input is invalid.
     */
    @Test
    void parsePeriod_validInput_periodParsed() throws AthletiException {
        String validInput = "weekly";
        Goal.TimeSpan actual = ActivityParser.parsePeriod(validInput);
        Goal.TimeSpan expected = Goal.TimeSpan.WEEKLY;
        assertEquals(actual, expected);
    }

    /**
     * Tests the parsing of an invalid period argument. An AthletiException should be thrown.
     */
    @Test
    void parsePeriod_invalidInput_throwAthletiException() {
        String invalidInput = "abc";
        assertThrows(AthletiException.class, () -> ActivityParser.parsePeriod(invalidInput));
    }

    /**
     * Tests the parsing of a valid target argument.
     *
     * @throws AthletiException If the input is invalid.
     */
    @Test
    void parseTarget_validInput_targetParsed() throws AthletiException {
        String validInput = "10000";
        int actual = ActivityParser.parseTarget(validInput);
        int expected = 10000;
        assertEquals(actual, expected);
    }

    /**
     * Tests the parsing of an invalid target argument. An AthletiException should be thrown.
     */
    @Test
    void parseTarget_invalidInput_throwAthletiException() {
        String invalidInput = "abc";
        assertThrows(AthletiException.class, () -> ActivityParser.parseTarget(invalidInput));
    }

    @Test
    void parseTarget_bigIntegerInput_throwAthletiException() {
        String bigIntegerInput1 = "10000000000000000000000";
        assertThrows(AthletiException.class, () -> ActivityParser.parseTarget(bigIntegerInput1));
    }

    /**
     * Tests the missing activity goal arguments check for the absence of the sport argument.
     */
    @Test
    void checkMissingActivityGoalArguments_missingSport_throwAthletiException() {
        assertThrows(AthletiException.class, () -> ActivityParser.checkMissingActivityGoalArguments(-1, 1, 1, 1));
    }

    /**
     * Tests the missing activity goal arguments check for no missing arguments.
     */
    @Test
    void checkMissingActivityGoalArguments_noMissingArguments_noExceptionThrown() {
        assertDoesNotThrow(() -> ActivityParser.checkMissingActivityGoalArguments(1, 1, 1, 1));
    }

    /**
     * Tests the parsing of a valid duration argument.
     *
     * @throws AthletiException If the input is invalid.
     */
    @Test
    void parseDuration_validInput_durationParsed() throws AthletiException {
        String validInput = "01:00:00";
        LocalTime actual = ActivityParser.parseDuration(validInput);
        LocalTime expected = LocalTime.parse("01:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        assertEquals(actual, expected);
    }

    /**
     * Tests the parsing of an invalid duration argument. An AthletiException should be thrown.
     */
    @Test
    void parseDuration_invalidInput_throwAthletiException() {
        String invalidInput = "abc";
        assertThrows(AthletiException.class, () -> ActivityParser.parseDuration(invalidInput));
    }

    /**
     * Tests the parsing of a valid distance argument.
     *
     * @throws AthletiException If the input is invalid.
     */
    @Test
    void parseDistance_validInput_distanceParsed() throws AthletiException {
        String validInput = "10000";
        int actual = ActivityParser.parseDistance(validInput);
        int expected = 10000;
        assertEquals(actual, expected);
    }

    /**
     * Tests the parsing of an invalid distance argument. An AthletiException should be thrown.
     */
    @Test
    void parseDistance_invalidInput_throwAthletiException() {
        String invalidInput = "abc";
        assertThrows(AthletiException.class, () -> ActivityParser.parseDistance(invalidInput));
    }

    /**
     * Tests the parsing of valid add-run or add-cycle arguments.
     * @throws AthletiException If the input is invalid.
     */
    @Test
    void parseRunCycle_validInput_activityParsed() throws AthletiException {
        String validInput =
                "Morning Run duration/01:00:00 distance/10000 datetime/2021-09-01 06:00 elevation/60";
        Run actual = (Run) ActivityParser.parseRunCycle(validInput, true);
        LocalTime movingTime = LocalTime.parse("01:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime time =
                LocalDateTime.parse("2021-09-01 06:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Run expected = new Run("Morning Run", movingTime, 10000, time, 60);
        assertEquals(actual.getCaption(), expected.getCaption());
        assertEquals(actual.getMovingTime(), expected.getMovingTime());
        assertEquals(actual.getDistance(), expected.getDistance());
        assertEquals(actual.getStartDateTime(), expected.getStartDateTime());
        assertEquals(actual.getElevationGain(), expected.getElevationGain());
    }

    /**
     * Tests the parsing of a valid elevation argument.
     *
     * @throws AthletiException If the input is invalid.
     */
    @Test
    void parseElevation_validInput_elevationParsed() throws AthletiException {
        String validInput = "60";
        int actual = ActivityParser.parseElevation(validInput);
        int expected = 60;
        assertEquals(actual, expected);
    }

    /**
     * Tests the parsing of an invalid elevation argument. An AthletiException should be thrown.
     */
    @Test
    void parseElevation_invalidInput_throwAthletiException() {
        String invalidInput = "abc";
        assertThrows(AthletiException.class, () -> ActivityParser.parseElevation(invalidInput));
    }

    /**
     * Tests the parsing of valid add-swim arguments.
     *
     * @throws AthletiException If the input is invalid.
     */
    @Test
    void parseSwim_validInput_swimParsed() throws AthletiException {
        String validInput =
                "Evening Swim duration/02:00:00 distance/20000 datetime/2021-09-01 18:00 style/freestyle";
        Swim actual = (Swim) ActivityParser.parseSwim(validInput);
        LocalTime movingTime = LocalTime.parse("02:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime time =
                LocalDateTime.parse("2021-09-01 18:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Swim expected = new Swim("Evening Swim", movingTime, 20000, time, Swim.SwimmingStyle.FREESTYLE);
        assertEquals(actual.getCaption(), expected.getCaption());
        assertEquals(actual.getMovingTime(), expected.getMovingTime());
        assertEquals(actual.getDistance(), expected.getDistance());
        assertEquals(actual.getStartDateTime(), expected.getStartDateTime());
        assertEquals(actual.getStyle(), expected.getStyle());
    }

    /**
     * Tests the parsing of a valid swimming style argument.
     *
     * @throws AthletiException If the input is invalid.
     */
    @Test
    void parseSwimmingStyle_validInput_styleParsed() throws AthletiException {
        String validInput = "freestyle";
        Swim.SwimmingStyle actual = ActivityParser.parseSwimmingStyle(validInput);
        Swim.SwimmingStyle expected = Swim.SwimmingStyle.FREESTYLE;
        assertEquals(actual, expected);
    }

    @Test
    void checkMissingActivityArgument_missingDistance_messageDistanceMissing() {
        String expected = "Please specify the activity distance using \"distance/\"!";
        try {
            ActivityParser.checkMissingActivityArgument(-1, "distance/");
        } catch (AthletiException e) {
            assertEquals(expected, e.getMessage());
        }
    }

    //@@author  nihalzp
    @Test
    void parseDeleteActivityGoal_validInput_activityGoalParsed() throws AthletiException {
        String validInput = "sport/running type/distance period/weekly";
        ActivityGoal actual = ActivityParser.parseDeleteActivityGoal(validInput);
        ActivityGoal expected = new ActivityGoal(Goal.TimeSpan.WEEKLY, ActivityGoal.GoalType.DISTANCE,
                ActivityGoal.Sport.RUNNING, 0);
        assertEquals(actual.getTimeSpan(), expected.getTimeSpan());
        assertEquals(actual.getGoalType(), expected.getGoalType());
        assertEquals(actual.getSport(), expected.getSport());
        assertEquals(actual.getTargetValue(), expected.getTargetValue());
    }

    @Test
    void parseDeleteActivityGoal_invalidInput_throwAthletiException() {
        String invalidInput = "abc";
        assertThrows(AthletiException.class, () -> ActivityParser.parseDeleteActivityGoal(invalidInput));
    }

    @Test
    void parseDeleteActivityGoal_missingSport_throwAthletiException() {
        String invalidInput = "type/distance period/weekly";
        assertThrows(AthletiException.class, () -> ActivityParser.parseDeleteActivityGoal(invalidInput));
    }

    @Test
    void parseDeleteActivityGoal_missingType_throwAthletiException() {
        String invalidInput = "sport/running period/weekly";
        assertThrows(AthletiException.class, () -> ActivityParser.parseDeleteActivityGoal(invalidInput));
    }

    @Test
    void parseDeleteActivityGoal_missingPeriod_throwAthletiException() {
        String invalidInput = "sport/running type/distance";
        assertThrows(AthletiException.class, () -> ActivityParser.parseDeleteActivityGoal(invalidInput));
    }

    @Test
    void parseDeleteActivityGoal_missingSportAndType_throwAthletiException() {
        String invalidInput = "period/weekly";
        assertThrows(AthletiException.class, () -> ActivityParser.parseDeleteActivityGoal(invalidInput));
    }

    @Test
    void parseDeleteActivityGoal_missingSportAndPeriod_throwAthletiException() {
        String invalidInput = "type/distance";
        assertThrows(AthletiException.class, () -> ActivityParser.parseDeleteActivityGoal(invalidInput));
    }

    @Test
    void parseDeleteActivityGoal_missingTypeAndPeriod_throwAthletiException() {
        String invalidInput = "sport/running";
        assertThrows(AthletiException.class, () -> ActivityParser.parseDeleteActivityGoal(invalidInput));
    }
}
