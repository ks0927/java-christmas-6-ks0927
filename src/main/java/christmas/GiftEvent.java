package christmas;

import java.util.Optional;

public class GiftEvent implements Event {
    private static final Menu GIFT = Menu.CHAMPAGNE;
    private static final int EVENT_CRITERIA = 120_000;

    private final int orderPrice;

    public GiftEvent(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public int discountAmount() {
        if (isEligibleForGift()) {
            return GIFT.getPrice();
        }
        return 0;
    }

    public Optional<Menu> provideGift() {
        if (isEligibleForGift()) {
            return Optional.of(GIFT);
        }
        return Optional.empty();
    }

    private boolean isEligibleForGift() {
        return orderPrice >= EVENT_CRITERIA;
    }
}
