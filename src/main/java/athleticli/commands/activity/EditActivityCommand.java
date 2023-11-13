package athleticli.commands.activity;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.activity.Activity;
import athleticli.data.activity.ActivityChanges;
import athleticli.data.activity.ActivityList;
import athleticli.data.activity.Cycle;
import athleticli.data.activity.Run;
import athleticli.data.activity.Swim;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Executes the edit activity command provided by the user.
 */
public class EditActivityCommand extends Command {
    private static final Logger logger = Logger.getLogger("EditActivityCommand");
    private final int index;
    private final ActivityChanges activityChanges;
    private final Class<?> activityType;

    /**
     * Constructs EditActivityCommand.
     *
     * @param index Index of the activity to be edited.
     * @param activityChanges Updated Activity.
     */
    public EditActivityCommand(int index, ActivityChanges activityChanges, Class<?> activityType) {
        this.index = index;
        assert index > 0 : "Index should be greater than 0";
        this.activityChanges = activityChanges;
        this.activityType = activityType;
    }

    /**
     * Edits the activity at the specified index.
     *
     * @param data Data object containing the current list of activities.
     * @return String array containing the messages to be printed to the user.
     * @throws AthletiException If the index provided is out of bounds.
     */
    @Override
    public String[] execute(Data data) throws AthletiException {
        logger.log(Level.INFO, "Editing activity at index " + index);
        ActivityList activities = data.getActivities();
        try {
            // Adjusting index as user input is 1-based and list is 0-based
            Activity activity = activities.get(index-1);

            if (!activityType.isInstance(activity)) {
                throw new AthletiException(Message.MESSAGE_ACTIVITY_TYPE_MISMATCH);
            }

            applyActivityChanges(activity, activityChanges);

            activities.sort();
            logger.log(java.util.logging.Level.INFO, "Activity at index " + index + "successfully edited");
            return new String[]{
                Message.MESSAGE_ACTIVITY_UPDATED,
                activity.toString(),
                String.format(Message.MESSAGE_ACTIVITY_COUNT, activities.size())
            };
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Activity index out of bounds");
            throw new AthletiException(Message.MESSAGE_ACTIVITY_INDEX_OUT_OF_BOUNDS);
        }
    }

    /**
     * Applies the changes to the activity object.
     *
     * @param activity Activity to be edited.
     * @param activityChanges ActivityChanges object containing the changes to be applied.
     */
    private void applyActivityChanges(Activity activity, ActivityChanges activityChanges) {
        if (activityChanges.getCaption() != null) {
            activity.setCaption(activityChanges.getCaption());
        }
        if (activityChanges.getDistance() != 0) {
            activity.setDistance(activityChanges.getDistance());
        }
        if (activityChanges.getDuration() != null) {
            activity.setMovingTime(activityChanges.getDuration());
        }
        if (activityChanges.getStartDateTime() != null) {
            activity.setStartDateTime(activityChanges.getStartDateTime());
        }
        if (activityChanges.getElevation() != 0) {
            if (activity instanceof Run) {
                ((Run) activity).setElevationGain(activityChanges.getElevation());
            } else if (activity instanceof Cycle) {
                ((Cycle) activity).setElevationGain(activityChanges.getElevation());
            }
        }
        if (activity instanceof Swim && activityChanges.getSwimmingStyle() != null) {
            ((Swim) activity).setStyle(activityChanges.getSwimmingStyle());
        }
    }
}
