package me.dralle.genius.utilities;

import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static me.dralle.genius.utilities.ExtraUtil.getConfigMessage;

public class TimeUtil {
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public static final DecimalFormat df = new DecimalFormat("0.00");

    // Configuration switch case for time units
    private static String getUnits(String key) {
        return switch (key) {
            case "year" -> getConfigMessage("settings.time.units.year");
            case "years" -> getConfigMessage("settings.time.units.years");
            case "month" -> getConfigMessage("settings.time.units.month");
            case "months" -> getConfigMessage("settings.time.units.months");
            case "week" -> getConfigMessage("settings.time.units.week");
            case "weeks" -> getConfigMessage("settings.time.units.weeks");
            case "day" -> getConfigMessage("settings.time.units.day");
            case "days" -> getConfigMessage("settings.time.units.days");
            case "hour" -> getConfigMessage("settings.time.units.hour");
            case "hours" -> getConfigMessage("settings.time.units.hours");
            case "minute" -> getConfigMessage("settings.time.units.minute");
            case "minutes" -> getConfigMessage("settings.time.units.minutes");
            case "second" -> getConfigMessage("settings.time.units.second");
            case "seconds" -> getConfigMessage("settings.time.units.seconds");
            default -> "";
        };
    }

    public static String updateTimestamp() {
        LocalDateTime currentTime = LocalDateTime.now();
        return dtf.format(currentTime);
    }

    public static String calculateTime(Date firstDate, Date secondDate) {
        long diff = secondDate.getTime() - firstDate.getTime();

        long diffSeconds = (diff / 1000) % 60;
        long diffMinutes = (diff / (1000 * 60)) % 60;
        long diffHours = (diff / (1000 * 60 * 60)) % 24;
        long diffDays = (diff / (1000 * 60 * 60 * 24)) % 7;
        long diffWeeks = (diff / (1000 * 60 * 60 * 24 * 7)) % 4;
        long diffMonths = (diff / (1000L * 60 * 60 * 24 * 30)) % 12;
        long diffYears = (diff / (1000L * 60 * 60 * 24 * 365));

        StringBuilder resultBuilder = new StringBuilder();

        appendTimeUnit(resultBuilder, diffYears, "year", "years");
        appendTimeUnit(resultBuilder, diffMonths, "month", "months");
        appendTimeUnit(resultBuilder, diffWeeks, "week", "weeks");
        appendTimeUnit(resultBuilder, diffDays, "day", "days");
        appendTimeUnit(resultBuilder, diffHours, "hour", "hours");
        appendTimeUnit(resultBuilder, diffMinutes, "minute", "minutes");
        appendTimeUnit(resultBuilder, diffSeconds, "second", "seconds");

        return resultBuilder.toString().trim();
    }

    public static String getTimePlayed(OfflinePlayer targetPlayer) {
        // Get the player's total time played in ticks
        int totalTicks = targetPlayer.getStatistic(Statistic.TOTAL_WORLD_TIME);

        // Convert ticks to milliseconds (1 tick = 50 milliseconds)
        long totalMilliseconds = totalTicks * 50L;

        // Calculate the time from the start date using the total milliseconds
        long diffSeconds = (totalMilliseconds / 1000) % 60;
        long diffMinutes = (totalMilliseconds / (1000 * 60)) % 60;
        long diffHours = (totalMilliseconds / (1000 * 60 * 60)) % 24;
        long diffDays = (totalMilliseconds / (1000 * 60 * 60 * 24)) % 7;
        long diffWeeks = (totalMilliseconds / (1000 * 60 * 60 * 24 * 7)) % 4;
        long diffMonths = (totalMilliseconds / (1000L * 60 * 60 * 24 * 30)) % 12;
        long diffYears = (totalMilliseconds / (1000L * 60 * 60 * 24 * 365));

        // Build the time played string
        StringBuilder resultBuilder = new StringBuilder();

        appendTimeUnit(resultBuilder, diffYears, "year", "years");
        appendTimeUnit(resultBuilder, diffMonths, "month", "months");
        appendTimeUnit(resultBuilder, diffWeeks, "week", "weeks");
        appendTimeUnit(resultBuilder, diffDays, "day", "days");
        appendTimeUnit(resultBuilder, diffHours, "hour", "hours");
        appendTimeUnit(resultBuilder, diffMinutes, "minute", "minutes");
        appendTimeUnit(resultBuilder, diffSeconds, "second", "seconds");

        return resultBuilder.toString().trim();
    }

    // Helper method to append time unit with singular/plural logic
    private static void appendTimeUnit(StringBuilder builder, long diff, String singularKey, String pluralKey) {
        if (diff != 0) {
            String unit = diff == 1 ? getUnits(singularKey) : getUnits(pluralKey);
            builder.append(diff).append(" ").append(unit).append(" ");
        }
    }

}
