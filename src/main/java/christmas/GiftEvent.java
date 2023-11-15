package christmas;

import java.util.HashMap;
import java.util.Map;

public class GiftEvent implements Event {
    private static final Menu GIFT = Menu.CHAMPAGNE;
    private static final int EVENT_CRITERIA = 120_000;
    private static final int GIFT_COUNTS = 1;

    private final String name = "증정 이벤트";
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

    public Map<Menu, Integer> provideGifts() {
        Map<Menu, Integer> gifts = new HashMap<>();
        if (isEligibleForGift()) {
            gifts.put(GIFT, GIFT_COUNTS);
        }
        return gifts;
    }

    private boolean isEligibleForGift() {
        return orderPrice >= EVENT_CRITERIA;
    }

    @Override
    public String getName() {
        return name;
    }
}
