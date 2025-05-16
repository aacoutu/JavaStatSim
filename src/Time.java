public class Time {
    private int hours;
    private int minutes;
    private int seconds;
    private int totSeconds;

    // initialize the Time class with hours, minutes, and seconds
    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.totSeconds = hours * 3600 + minutes * 60 + seconds;
    }

    // initialize Time class with total time in seconds
    public Time(int totSeconds) {
        this.hours = totSeconds / 3600;
        this.minutes = totSeconds % 3600 / 60;
        this.seconds = totSeconds % 60;
        this.totSeconds = totSeconds;
    }

    // get the number of hours rounded down
    public int getHours() {
        return hours;
    }

    // get the minutes as a remainder after hours
    public int getMinutes() {
        return minutes;
    }

    // get the number of seconds as a remainder after hours and minutes
    public int getSeconds() {
        return seconds;
    }

    // get the total time in seconds
    public int getTotSeconds() {
        return totSeconds;
    }
}
