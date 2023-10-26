package athleticli.commands.activity;

import athleticli.data.Data;
import athleticli.data.Goal.Timespan;
import athleticli.data.activity.ActivityGoal;
import athleticli.data.activity.Run;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class SetActivityGoalCommandTest {
    private SetActivityGoalCommand setActivityGoalCommand;
    private Data data;
    private ActivityGoal activityGoal;

    @BeforeEach
    void setUp() {
        data = new Data();

        ActivityGoal.GoalType goalType = ActivityGoal.GoalType.DISTANCE;
        ActivityGoal.Sport sport = ActivityGoal.Sport.RUNNING;
        Timespan period = Timespan.WEEKLY;
        LocalDate date = LocalDate.now();
        activityGoal = new ActivityGoal(period, goalType, sport, 10000);

        setActivityGoalCommand = new SetActivityGoalCommand(activityGoal);

        String caption = "Sunday = Runday";
        int distance = 3000;
        LocalTime duration = LocalTime.of(1, 24);
        Run run = new Run(caption, duration, distance, LocalDateTime.now(), 0);

        AddActivityCommand addActivityCommand = new AddActivityCommand(run);
        addActivityCommand.execute(data);
    }

    @Test
    void execute() {
        String[] actual = setActivityGoalCommand.execute(data);
        String[] expected = {"Alright, I've added this activity goal:", activityGoal.toString()};
    }
}