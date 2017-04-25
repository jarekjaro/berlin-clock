package com.ubs.opsit.interviews;

import java.util.stream.Stream;

public class BerlinClock implements TimeConverter {

    @Override
    public String convertTime(String aTime) throws IllegalArgumentException {
        validateInputString(aTime);
        int[] splitTime = Stream.of(aTime.split(":")).mapToInt(Integer::parseInt).toArray();
        return getBerlinTimeRepresentation(splitTime[0], splitTime[1], splitTime[2]);
    }

    private void validateInputString(String aTime) throws IllegalArgumentException {
        if (aTime == null) {
            throw new IllegalArgumentException("Given time should not be null!");
        }
        if (aTime.equals("")) {
            throw new IllegalArgumentException("Given time is empty!");
        }
        String regex = "([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
        if (!aTime.matches(regex)) {
            String regex24 = "(24:00:00)";
            if (aTime.matches(regex24)) {
                return;
            }
            throw new IllegalArgumentException("Given time is in a wrong format. Should look like XX:XX:XX" +
                    " and match 24h clock rules.");
        }
    }

    private String getBerlinTimeRepresentation(Integer hours, Integer minutes, Integer seconds) {
        String lineSeparator = System.lineSeparator();
        return getTopLampRepresentation(seconds) + lineSeparator +
                getTopHoursRepresentation(hours) + lineSeparator +
                getBottomHoursRepresentation(hours) + lineSeparator +
                getTopMinutesRepresentation(minutes) + lineSeparator +
                getBottomMinutesRepresentation(minutes);
    }

    private String getTopHoursRepresentation(Integer hours) {
        return getLampRepresentationForRow(getHowManyOnForLampsOfValueFive(hours), BerlinClockRow.TOP_HOURS);
    }

    private String getBottomHoursRepresentation(Integer hours) {
        Integer hoursModFive = hours % 5;
        return getLampRepresentationForRow(hoursModFive, BerlinClockRow.BOTTOM_HOURS);
    }

    private String getTopMinutesRepresentation(Integer minutes) {
        return getLampRepresentationForRow(getHowManyOnForLampsOfValueFive(minutes), BerlinClockRow.TOP_MINUTES);
    }

    private String getBottomMinutesRepresentation(Integer minutes) {
        Integer minutesModFive = minutes % 5;
        return getLampRepresentationForRow(minutesModFive, BerlinClockRow.BOTTOM_MINUTES);
    }

    private String getTopLampRepresentation(Integer seconds) {
        return getLampRepresentationForRow(seconds % 2, BerlinClockRow.TOP_LAMP);
    }

    private int getHowManyOnForLampsOfValueFive(Integer hours) {
        return (hours - (hours % 5)) / 5;
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
                if (howManyOn == 0) return "Y";
                else return "O";
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
