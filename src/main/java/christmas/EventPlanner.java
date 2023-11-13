package christmas;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class EventPlanner {
    private static final int MINIMUM_DISCOUNT_PRICE = 10000;

    private final Map<Menu, Integer> orders;
    private final VisitDate visitDate;

    public EventPlanner(Map<Menu, Integer> orders, VisitDate visitDate) {
        this.orders = orders;
        this.visitDate = visitDate;
    }

    public int totalPriceBeforeDiscount() {
        return orders.entrySet().stream()
                .mapToInt(map -> map.getValue() * map.getKey().getPrice())
                .sum();
    }

    public Optional<Menu> giftMenu() {
        GiftEvent giftEvent = new GiftEvent(totalPriceBeforeDiscount());
        return giftEvent.provideGift();
    }

    public Map<Event, Integer> totalDiscountDetails() {
        Map<Event, Integer> discountAmount = new HashMap<>();
        if (isEligibleForDiscount()) {
            discountAmount.putAll(calculateDiscount(new ChristmasDdayEvent(visitDate)));
            discountAmount.putAll(calculateDiscount(new WeekdayEvent(visitDate, orders)));
            discountAmount.putAll(calculateDiscount(new WeekendEvent(visitDate, orders)));
            discountAmount.putAll(calculateDiscount(new StarEvent(visitDate)));
            discountAmount.putAll(calculateDiscount(new GiftEvent(totalPriceBeforeDiscount())));

        }
        return discountAmount;
    }

    private boolean isEligibleForDiscount() {
        return totalPriceBeforeDiscount() >= MINIMUM_DISCOUNT_PRICE;
    }

    private Map<Event, Integer> calculateDiscount(Event event) {
        Map<Event, Integer> discounts = new HashMap<>();
        int discountAmount = event.discountAmount();
        discounts.put(event, discountAmount);
        return discounts;
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