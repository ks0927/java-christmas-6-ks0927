package christmas;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

public class WeekendEvent implements Event {
    private static final List<DayOfWeek> EVENT_DAYS = Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
    private static final int DISCOUNT_PER_MAIN = 2023;

    private final String name = "주말 할인";
    private final VisitDate visitDate;
    private final Orders orders;

    public WeekendEvent(VisitDate visitDate, Orders orders) {
        this.visitDate = visitDate;
        this.orders = orders;
    }

    @Override
    public int discountAmount() {
        if (!isEventDay()) {
            return 0;
        }

        int mainCounts = orders.getMainCounts();
        return mainCounts * DISCOUNT_PER_MAIN;
    }

    private boolean isEventDay() {
        return visitDate.containDayOfWeek(EVENT_DAYS);
    }

    @Override
    public String getName() {
        return name;
    }
}
