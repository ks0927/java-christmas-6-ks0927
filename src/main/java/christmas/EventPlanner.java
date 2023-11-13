package christmas;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class EventPlanner {
    private static final int MINIMUM_DISCOUNT_PRICE = 10000;

    private final Orders orders;
    private final VisitDate visitDate;

    public EventPlanner(Orders orders, VisitDate visitDate) {
        this.orders = orders;
        this.visitDate = visitDate;
    }

    public int totalPriceBeforeDiscount() {
        return orders.totalPrice();
    }

    public Optional<Menu> giftMenu() {
        GiftEvent giftEvent = new GiftEvent(totalPriceBeforeDiscount());
        return giftEvent.provideGift();
    }

    public Map<Event, Integer> totalDiscountDetails() {
        Map<Event, Integer> discountAmount = new HashMap<>();
        if (isEligibleForDiscount()) {
            addDiscountIfNotZero(new ChristmasDdayEvent(visitDate), discountAmount);
            addDiscountIfNotZero(new WeekdayEvent(visitDate, orders), discountAmount);
            addDiscountIfNotZero(new WeekendEvent(visitDate, orders), discountAmount);
            addDiscountIfNotZero(new StarEvent(visitDate), discountAmount);
            addDiscountIfNotZero(new GiftEvent(totalPriceBeforeDiscount()), discountAmount);
        }
        return discountAmount;
    }

    private boolean isEligibleForDiscount() {
        return totalPriceBeforeDiscount() >= MINIMUM_DISCOUNT_PRICE;
    }

    private void addDiscountIfNotZero(Event event, Map<Event, Integer> discountAmount) {
        int discount = event.discountAmount();
        if (discount != 0) {
            discountAmount.put(event, discount);
        }
    }

    public int totalDiscountAmount() {
        return totalDiscountDetails().entrySet().stream()
                .mapToInt(value -> value.getValue())
                .sum();
    }

    public int estimatedPaymentAfterDiscount() {
        int realDiscountAmount = totalDiscountDetails().entrySet().stream()
                .filter(value -> !(value.getKey() instanceof GiftEvent))
                .mapToInt(Entry::getValue)
                .sum();
        return totalPriceBeforeDiscount() - realDiscountAmount;
    }

    public Optional<EventBadge> eventBadge() {
        return EventBadge.getBadgeByDiscountAmount(totalDiscountAmount());
    }
}