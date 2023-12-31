package christmas.domain.event;

import christmas.domain.Orders;
import christmas.domain.VisitDate;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

public class WeekdayEvent implements Event {
    private static final List<DayOfWeek> EVENT_DAYS = Arrays.asList(DayOfWeek.SUNDAY, DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY);
    private static final int DISCOUNT_PER_DESSERT = 2023;

    private final String name = "평일 할인";
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
        int appetizerCounts = orders.getDessertCounts();
        return appetizerCounts * DISCOUNT_PER_DESSERT;
    }

    private boolean isEventDay() {
        return visitDate.containDayOfWeek(EVENT_DAYS);
    }

    @Override
    public String getName() {
        return name;
    }
}
