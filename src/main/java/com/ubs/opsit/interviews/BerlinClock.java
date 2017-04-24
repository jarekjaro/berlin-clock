package com.ubs.opsit.interviews;

public class BerlinClock implements TimeConverter {
    public static void main(String[] args) {
        TimeConverter berlinClock = new BerlinClock();
        System.out.println(berlinClock.convertTime("12:34:54"));
        System.out.println(berlinClock.convertTime("00:45:01"));


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
        return getTopLampRepresentation(seconds) + "\n" +
                getTopHoursRepresentation(hours) + "\n" +
                getBottomHoursRepresentation(hours) + "\n" +
                getTopMinutesRepresentation(minutes) + "\n" +
                getBottomMinutesRepresentation(minutes);
    }

    private String getTopHoursRepresentation(Integer hours) {
        return getLampRepresentationForRow((hours - (hours % 5)) / 5, BerlinClockRow.TOP_HOURS);
    }

    private String getBottomHoursRepresentation(Integer hours) {
        Integer hoursModFive = hours % 5;
        return getLampRepresentationForRow(hoursModFive, BerlinClockRow.BOTTOM_HOURS);
    }

    private String getTopMinutesRepresentation(Integer minutes) {
        return getLampRepresentationForRow((minutes - (minutes % 5)) / 5, BerlinClockRow.TOP_MINUTES);
    }

    private String getBottomMinutesRepresentation(Integer minutes) {
        Integer minutesModFive = minutes % 5;
        return getLampRepresentationForRow(minutesModFive, BerlinClockRow.BOTTOM_MINUTES);
    }

    private String getTopLampRepresentation(Integer seconds) {
        return getLampRepresentationForRow(seconds%2, BerlinClockRow.TOP_LAMP);
    }

    private String getLampRepresentationForRow(Integer howManyOn, BerlinClockRow whichBerlinClockRow) {
        String out = "";
        switch (whichBerlinClockRow) {
            case BOTTOM_HOURS:
            case TOP_HOURS: {
                for (int i = 0; i < howManyOn; i++) {
                    out += "R";
                }
                for (int i = 0; i < 4 - howManyOn; i++) {
                    out += "O";
                }
                return out;
            }
            case TOP_LAMP: {
                if (howManyOn ==0) return "Y"; else return "O";
            }
            case BOTTOM_MINUTES: {
                for (int i = 0; i < howManyOn; i++) {
                    out += "Y";
                }
                for (int i = 0; i < 4 - howManyOn; i++) {
                    out += "O";
                }
                return out;
            }
            case TOP_MINUTES: {
                for (int i = 0; i < howManyOn; i++) {
                    out += "Y";
                }
                for (int i = 0; i < 11 - howManyOn; i++) {
                    out += "O";
                }
                return out.replaceAll("YYY", "YYR");
            }
        }
        return null;
    }

}
