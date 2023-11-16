package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Menu;
import christmas.domain.event.GiftEvent;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("증정 이벤트 테스트")
class GiftEventTest {
    @Test
    @DisplayName("증정 이벤트 조건에 충족할 때, 할인 금액 테스트")
    void discountAmountWhenConditionMet() {
        GiftEvent giftEvent = new GiftEvent(120_000);
        int discountAmount = giftEvent.discountAmount();
        assertThat(discountAmount).isEqualTo(Menu.CHAMPAGNE.getPrice());
    }

    @Test
    @DisplayName("증정 이벤트 조건에 충족하지 않을 때, 할인 금액 테스트")
    void discountAmountWhenConditionNotMet() {
        GiftEvent giftEvent = new GiftEvent(119_999);
        int discountAmount = giftEvent.discountAmount();
        assertThat(discountAmount).isEqualTo(0);
    }

    @Test
    @DisplayName("증정 이벤트 조건에 충족할 때, 증정 메뉴 테스트")
    void giftWhenConditionMet() {
        GiftEvent giftEvent = new GiftEvent(120_000);
        Map<Menu, Integer> gifts = giftEvent.provideGifts();
        assertThat(gifts.containsKey(Menu.CHAMPAGNE)).isTrue();
    }

    @Test
    @DisplayName("증정 이벤트 조건에 충족하지 않을 때, 증정 메뉴 테스트")
    void giftWhenConditionNotMet() {
        GiftEvent giftEvent = new GiftEvent(119_999);
        Map<Menu, Integer> gifts = giftEvent.provideGifts();
        assertThat(gifts.containsKey(Menu.CHAMPAGNE)).isFalse();
    }
}