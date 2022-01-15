package util;

public class Time {
    //static variables are initialed ass soon as the application starts up
    public static float timeStarted = System.nanoTime();

    public static float getTime() {
        //1E-9 is number of seconds in nanotime. multiplying the subtraction of current time
        //with start time by 1E-9  will give you the elapsed time in seconds
        return (float) ((System.nanoTime() - timeStarted) * 1E-9);
    }

}
