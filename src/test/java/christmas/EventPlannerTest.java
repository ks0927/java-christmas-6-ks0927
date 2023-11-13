package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("이벤트 플래너 테스트")
class EventPlannerTest {
    private static Orders orders;
    private static VisitDate visitDate;

    @BeforeAll
    static void setUp() {
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.T_BONE_STEAK, 1);
        order.put(Menu.BARBECUE_RIB, 1);
        order.put(Menu.CHOCOLATE_CAKE, 2);
        order.put(Menu.ZERO_COLA, 1);
        orders = new Orders(order);
        visitDate = VisitDate.from(3);
    }

    @Test
    @DisplayName("주문 전체 가격 계산 테스트")
    void testTotalPriceBeforeDiscount() {
        EventPlanner eventPlanner = new EventPlanner(orders, visitDate);

        int expectedTotalPrice = 142_000;
        assertThat(eventPlanner.totalPriceBeforeDiscount()).isEqualTo(expectedTotalPrice);
    }

    @Test
    @DisplayName("선물 메뉴 제공 테스트")
    void testGiftMenu() {
        EventPlanner eventPlanner = new EventPlanner(orders, visitDate);

        Menu giftMenu = eventPlanner.giftMenu().get();
        assertThat(giftMenu).isEqualTo(Menu.CHAMPAGNE);
    }

    @Test
    @DisplayName("전체 할인 내역은 적용되는 이벤트만 Map에 들어간다.")
    void testTotalDiscountDetails() {
        EventPlanner eventPlanner = new EventPlanner(orders, visitDate);

        Map<Event, Integer> actualDiscountDetails = eventPlanner.totalDiscountDetails();
        assertThat(actualDiscountDetails.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("할인 총액 계산 테스트")
    void testTotalDiscountAmount() {
        EventPlanner eventPlanner = new EventPlanner(orders, visitDate);

        int expectedTotalDiscountAmount = 31_246;
        assertThat(eventPlanner.totalDiscountAmount()).isEqualTo(expectedTotalDiscountAmount);
    }

    @Test
    @DisplayName("할인 적용 후 예상 결제액 계산 테스트")
    void testEstimatedPaymentAfterDiscount() {
        EventPlanner eventPlanner = new EventPlanner(orders, visitDate);

        int expectedEstimatedPayment = 135_754;
        assertThat(eventPlanner.estimatedPaymentAfterDiscount()).isEqualTo(expectedEstimatedPayment);
    }

    @Test
    @DisplayName("이벤트 배지 제공 테스트")
    void testEventBadge() {
        EventPlanner eventPlanner = new EventPlanner(orders, visitDate);

        EventBadge eventBadge = eventPlanner.eventBadge().get();
        assertThat(eventBadge).isEqualTo(EventBadge.SANTA);
    }
}