package christmas.domain;

import christmas.exception.PromotionException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class VisitDate {
    private static final int DEFAULT_YEAR = 2023;
    private static final int DEFAULT_MONTH = 12;
    private static final int START_OF_MONTH = 1;

    private final LocalDate date;

    private VisitDate(int year, int month, int dayOfMonth) {
        LocalDate date = LocalDate.of(year, month, dayOfMonth);
        this.date = date;
    }

    public static VisitDate from(int dayOfMonth) {
        validate(dayOfMonth);
        return new VisitDate(DEFAULT_YEAR, DEFAULT_MONTH, dayOfMonth);
    }

    private static void validate(int dayOfMonth) {
        if (isInvalidRange(dayOfMonth)) {
            throw new IllegalArgumentException(PromotionException.dateException());
        }
    }

    private static boolean isInvalidRange(int dayOfMonth) {
        YearMonth yearMonth = YearMonth.of(DEFAULT_YEAR, DEFAULT_MONTH);
        int endOfMonth = yearMonth.lengthOfMonth();
        return dayOfMonth < START_OF_MONTH || dayOfMonth > endOfMonth;
    }

    public int getDayOfMonth() {
        return date.getDayOfMonth();
    }

    public boolean containDayOfMonth(List<Integer> target) {
        return target.stream()
                .anyMatch(dayOfMonth -> dayOfMonth == date.getDayOfMonth());
    }

    public boolean containDayOfWeek(List<DayOfWeek> target) {
        return target.stream()
                .anyMatch(dayOfWeek -> dayOfWeek == date.getDayOfWeek());
    }

    public int calculateDaysDifference(int dayOfMonth) {
        return date.getDayOfMonth() - dayOfMonth;
    }

    public DayOfWeek getDayOfWeek() {
        return date.getDayOfWeek();
    }
}
