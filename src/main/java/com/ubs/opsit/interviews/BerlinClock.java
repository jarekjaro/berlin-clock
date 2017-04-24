package com.ubs.opsit.interviews;

public class BerlinClock implements TimeConverter {
    @Override
    public String convertTime(String aTime) {

        String[] splitTime = aTime.split(":");
        Integer hours = Integer.parseInt(splitTime[0]);
        Integer minutes = Integer.parseInt(splitTime[1]);
        Integer seconds = Integer.parseInt(splitTime[2]);

        String berlinClockTimeRepresentation;

        return hours.toString() +" "+ minutes.toString() +" "+ seconds.toString();
    }

    private String getTopHoursRepresentation(Integer hours) {

        return null;
    }

    private String getBottomHoursRepresentation(Integer hours) {

        return null;
    }

    private String getTopMinutesRepresentation(Integer minutes) {

        return null;
    }

    private String getBottomMinutesRepresentation(Integer minutes) {

        return null;
    }

    private String getTopLampStateBasedOnSeconds(Integer seconds) {

        return null;
    }

    public static void main(String[] args) {
        TimeConverter berlinClock = new BerlinClock();
        System.out.println(berlinClock.convertTime("12:34:54"));

    }
}
