package christmas;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WeekdayEvent implements Event {
    private static final List<DayOfWeek> EVENT_DAYS = Arrays.asList(DayOfWeek.SUNDAY, DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY);
    private static final int DISCOUNT_PER_APPETIZER = 2023;

    private final VisitDate visitDate;
    private final Orders orders;

    public WeekdayEvent(VisitDate visitDate, Orders orders) {
        this.visitDate = visitDate;
        this.orders = orders;
    }

    @Override
    public int discountAmount() {
        if (!isEventDay()) {
            return 0;
        }
        int appetizerCounts = orders.getAppetizerCounts();
        return appetizerCounts * DISCOUNT_PER_APPETIZER;
    }


    private boolean isEventDay() {
        DayOfWeek dayOfWeek = visitDate.getDayOfWeek();
        return EVENT_DAYS.contains(dayOfWeek);
    }
}
