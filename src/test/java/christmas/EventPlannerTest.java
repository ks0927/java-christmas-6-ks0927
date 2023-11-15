package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("이벤트 플래너 테스트")
class EventPlannerTest {
    private Orders orders;
    private VisitDate visitDate;
    private EventPlanner eventPlanner;

    @BeforeEach
    void setUp() {
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.T_BONE_STEAK, 1);
        order.put(Menu.BARBECUE_RIB, 1);
        order.put(Menu.CHOCOLATE_CAKE, 2);
        order.put(Menu.ZERO_COLA, 1);
        orders = new Orders(order);
        visitDate = VisitDate.from(3);
        eventPlanner = new EventPlanner(orders, visitDate);
    }

    @Test
    @DisplayName("주문 전체 가격 계산 테스트")
    void testTotalPriceBeforeDiscount() {
        int expectedTotalPrice = 142_000;
        assertThat(eventPlanner.totalPriceBeforeDiscount()).isEqualTo(expectedTotalPrice);
    }

    @Test
    @DisplayName("선물 메뉴 제공 테스트")
    void testGiftMenu() {
        Map<Menu, Integer> gifts = eventPlanner.giftMenu(orders.totalPrice());
        assertThat(gifts.containsKey(Menu.CHAMPAGNE)).isTrue();
    }

    @Test
    @DisplayName("전체 할인 내역은 적용되는 이벤트만 Map에 들어간다.")
    void testTotalDiscountDetails() {
        Map<Event, Integer> actualDiscountDetails = eventPlanner.totalDiscountDetails(orders.totalPrice());
        assertThat(actualDiscountDetails.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("할인 총액 계산 테스트")
    void testTotalDiscountAmount() {
        Map<Event, Integer> discountDetails = eventPlanner.totalDiscountDetails(orders.totalPrice());
        int expectedTotalDiscountAmount = 31_246;
        assertThat(eventPlanner.totalDiscountAmount(discountDetails)).isEqualTo(expectedTotalDiscountAmount);
    }

    @Test
    @DisplayName("할인 적용 후 예상 결제액 계산 테스트")
    void testEstimatedPaymentAfterDiscount() {
        Map<Event, Integer> discountDetails = eventPlanner.totalDiscountDetails(orders.totalPrice());
        int expectedEstimatedPayment = 135_754;
        assertThat(eventPlanner.estimatedPaymentAfterDiscount(discountDetails, orders.totalPrice())).isEqualTo(
                expectedEstimatedPayment);
    }

    @Test
    @DisplayName("이벤트 배지 제공 테스트")
    void testEventBadge() {
        Map<Event, Integer> discountDetails = eventPlanner.totalDiscountDetails(orders.totalPrice());
        int totalDiscountAmount = eventPlanner.totalDiscountAmount(discountDetails);
        EventBadge eventBadge = eventPlanner.eventBadge(totalDiscountAmount).get();
        assertThat(eventBadge).isEqualTo(EventBadge.SANTA);
    }
}