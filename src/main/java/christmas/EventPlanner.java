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

    public Map<Menu, Integer> giftMenu(int totalPriceBeforeDiscount) {
        GiftEvent giftEvent = new GiftEvent(totalPriceBeforeDiscount);
        return giftEvent.provideGifts();
    }

    // 모든 이벤트의 적용 내역을 관리하는 메서드
    public Map<Event, Integer> totalDiscountDetails(int totalPriceBeforeDiscount) {
        Map<Event, Integer> discountAmount = new HashMap<>();
        if (isEligibleForDiscount(totalPriceBeforeDiscount)) {
            addDiscountAmountsForEvents(totalPriceBeforeDiscount, discountAmount);
        }
        return discountAmount;
    }

    private boolean isEligibleForDiscount(int totalPriceBeforeDiscount) {
        return totalPriceBeforeDiscount >= MINIMUM_DISCOUNT_PRICE;
    }

    // 적용할 이벤트를 추가하는 메서드
    private void addDiscountAmountsForEvents(int totalPriceBeforeDiscount, Map<Event, Integer> discountAmount) {
        addDiscountIfNotZero(new ChristmasDdayEvent(visitDate), discountAmount);
        addDiscountIfNotZero(new WeekdayEvent(visitDate, orders), discountAmount);
        addDiscountIfNotZero(new WeekendEvent(visitDate, orders), discountAmount);
        addDiscountIfNotZero(new StarEvent(visitDate), discountAmount);
        addDiscountIfNotZero(new GiftEvent(totalPriceBeforeDiscount), discountAmount);
    }

    private void addDiscountIfNotZero(Event event, Map<Event, Integer> discountAmount) {
        int discount = event.discountAmount();
        if (discount != 0) {
            discountAmount.put(event, discount);
        }
    }

    public int totalDiscountAmount(Map<Event, Integer> totalDiscountDetails) {
        return totalDiscountDetails.entrySet().stream()
                .mapToInt(value -> value.getValue())
                .sum();
    }

    public int estimatedPaymentAfterDiscount(Map<Event, Integer> totalDiscountDetails, int totalPriceBeforeDiscount) {
        int realDiscountAmount = totalDiscountDetails.entrySet().stream()
                .filter(value -> !(value.getKey() instanceof GiftEvent))
                .mapToInt(Entry::getValue)
                .sum();
        return totalPriceBeforeDiscount - realDiscountAmount;
    }

    public Optional<EventBadge> eventBadge(int totalDiscountAmount) {
        return EventBadge.getBadgeByDiscountAmount(totalDiscountAmount);
    }
}