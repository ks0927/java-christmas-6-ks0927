package christmas;

import java.util.Arrays;
import java.util.List;

public class StarEvent implements Event {
    private static final List<Integer> EVENT_DAYS = Arrays.asList(3, 10, 17, 24, 25, 31);
    private static final int SPECIAL_DISCOUNT = 1000;

    private final String name = "특별 할인";
    private final VisitDate visitDate;

    public StarEvent(VisitDate visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public int discountAmount() {
        if (EVENT_DAYS.contains(visitDate.getDate())) {
            return SPECIAL_DISCOUNT;
        }
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }
}
