package christmas;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WeekendEvent implements Event {
    private static final List<DayOfWeek> EVENT_DAYS = Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
    private static final int DISCOUNT_PER_MAIN = 2023;

    private final VisitDate visitDate;
    private final Map<Menu, Integer> orders;

    public WeekendEvent(VisitDate visitDate, Map<Menu, Integer> orders) {
        this.visitDate = visitDate;
        this.orders = orders;
    }

    @Override
    public int discountAmount() {
        if (!isEventDay()) {
            return 0;
        }

        int mainCounts = getMainCounts();
        return mainCounts * DISCOUNT_PER_MAIN;
    }

    private int getMainCounts() {
        int mainCounts = orders.entrySet().stream()
                .filter(menuIntegerEntry -> menuIntegerEntry.getKey().getType() == MenuType.MAIN)
                .mapToInt(Map.Entry::getValue)
                .sum();
        return mainCounts;
    }

    private boolean isEventDay() {
        DayOfWeek dayOfWeek = visitDate.getDayOfWeek();
        return EVENT_DAYS.contains(dayOfWeek);
    }
}
