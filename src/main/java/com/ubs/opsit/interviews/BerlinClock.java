package com.ubs.opsit.interviews;

public class BerlinClock implements TimeConverter {
    public static void main(String[] args) {
        TimeConverter berlinClock = new BerlinClock();
        System.out.println(berlinClock.convertTime("12:34:54"));
        System.out.println(berlinClock.convertTime("00:47:00"));

    }

    @Override
    public String convertTime(String aTime) {

        String[] splitTime = aTime.split(":");
        Integer hours = Integer.parseInt(splitTime[0]);
        Integer minutes = Integer.parseInt(splitTime[1]);
        Integer seconds = Integer.parseInt(splitTime[2]);

        return getBerlinTimeRepresentation(hours, minutes, seconds);
    }

    private String getBerlinTimeRepresentation(Integer hours, Integer minutes, Integer seconds) {
        return getTopLampStateBasedOnSeconds(seconds) + "\n" +
                getTopHoursRepresentation(hours) + "\n" +
                getBottomHoursRepresentation(hours) + "\n" +
                getTopMinutesRepresentation(minutes) + "\n" +
                getBottomMinutesRepresentation(minutes);
    }

    private String getTopHoursRepresentation(Integer hours) {
        Double hoursDivByFive = hours / 5.0d;
        if (hoursDivByFive >= 4) {
            return "RRRR";
        } else if (hoursDivByFive >= 3) {
            return "RRRO";
        } else if (hoursDivByFive >= 2) {
            return "RROO";
        } else if (hoursDivByFive >= 1) {
            return "ROOO";
        } else return "OOOO";
    }

    private String getBottomHoursRepresentation(Integer hours) {
        Integer hoursModFive = hours % 5;
        if (hoursModFive > 3) {
            return "RRRR";
        } else if (hoursModFive > 2) {
            return "RRRO";
        } else if (hoursModFive > 1) {
            return "RROO";
        } else if (hoursModFive > 0) {
            return "ROOO";
        } else return "OOOO";
    }

    private String getTopMinutesRepresentation(Integer minutes) {
        Double minutesDivByFive = minutes / 5.0d;
        if (minutesDivByFive >= 11) {
            return "YYRYYRYYRYY";
        } else if (minutesDivByFive >=10) {
            return "YYRYYRYYRYO";
        } else if (minutesDivByFive >= 9) {
            return "YYRYYRYYROO";
        } else if (minutesDivByFive >= 8) {
            return "YYRYYRYYOOO";
        } else if (minutesDivByFive >= 7) {
            return "YYRYYRYOOOO";
        } else if (minutesDivByFive >= 6) {
            return "YYRYYROOOOO";
        } else if (minutesDivByFive >= 5) {
            return "YYRYYOOOOOO";
        } else if (minutesDivByFive >= 4) {
            return "YYRYOOOOOOO";
        } else if (minutesDivByFive >= 3) {
            return "YYROOOOOOOO";
        } else if (minutesDivByFive >= 2) {
            return "YYOOOOOOOOO";
        } else if (minutesDivByFive >= 1) {
            return "YOOOOOOOOOO";
        } else return "OOOOOOOOOOO";
    }

    private String getBottomMinutesRepresentation(Integer minutes) {
        Integer hoursModFive = minutes % 5;
        if (hoursModFive > 3) {
            return "YYYY";
        } else if (hoursModFive > 2) {
            return "YYYO";
        } else if (hoursModFive > 1) {
            return "YYOO";
        } else if (hoursModFive > 0) {
            return "YOOO";
        } else return "OOOO";
    }

    private String getTopLampStateBasedOnSeconds(Integer seconds) {
        if (seconds % 2 > 0) {
            return "O";
        } else return "Y";
    }
}
