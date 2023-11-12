package christmas;

public class ChristmasDdayEvent implements Event {
    private static final int START_DAY = 1;
    private static final int CHRISTMAS = 25;
    private static final int INITIAL_DISCOUNT = 1000;
    private static final int INCREASE_DISCOUNT_AMOUNT = 100;

    private final DecemberWillVisitDate decemberWillVisitDate;

    public ChristmasDdayEvent(DecemberWillVisitDate decemberWillVisitDate) {
        this.decemberWillVisitDate = decemberWillVisitDate;
    }

    @Override
    public int discountAmount() {
        if (decemberWillVisitDate.getDate() > CHRISTMAS) {
            return 0;
        }
        return INITIAL_DISCOUNT + (decemberWillVisitDate.getDate() - START_DAY) * INCREASE_DISCOUNT_AMOUNT;
    }
}
