package schedule.curefit.com.curefitscheduleapp.models;


public class DaySchedule {

    private String type;
    private String title;
    private String subTitle;
    private boolean isCurrentDayFeed;

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public boolean isCurrentDayFeed() {
        return isCurrentDayFeed;
    }

    public DaySchedule(String type, String title, String subTitle, boolean isCurrentDayFeed) {
        this.type = type;
        this.title = title;
        this.subTitle = subTitle;
        this.isCurrentDayFeed = isCurrentDayFeed;
    }
}
