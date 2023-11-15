package christmas;

public class ChristmasDdayEvent implements Event {
    private static final int EVENT_START_DAY = 1;
    private static final int CHRISTMAS = 25;
    private static final int INITIAL_DISCOUNT = 1000;
    private static final int INCREASE_DISCOUNT_AMOUNT = 100;

    private final String name = "크리스마스 디데이 할인";
    private final VisitDate visitDate;

    public ChristmasDdayEvent(VisitDate visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public int discountAmount() {
        if (visitDate.calculateDaysDifference(CHRISTMAS) > 0) {
            return 0;
        }
        return INITIAL_DISCOUNT + (visitDate.calculateDaysDifference(EVENT_START_DAY)) * INCREASE_DISCOUNT_AMOUNT;
    }

    @Override
    public String getName() {
        return name;
    }
}
