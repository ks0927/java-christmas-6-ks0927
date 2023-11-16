package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Menu;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.domain.event.WeekendEvent;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("주말 할인 테스트")
class WeekendEventTest {
    private static final int DISCOUNT_PER_MAIN = 2023;

    @DisplayName("할인이 적용되는 요일에 대한 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void discount_on_event_day(int dayOfMonth) {
        VisitDate visitDate = VisitDate.from(dayOfMonth);
        Map<Menu, Integer> orders = new HashMap<>();
        orders.put(Menu.T_BONE_STEAK, 2);
        WeekendEvent weekendEvent = new WeekendEvent(visitDate, new Orders(orders));

        int discountAmount = weekendEvent.discountAmount();

        assertThat(discountAmount).isEqualTo(2 * DISCOUNT_PER_MAIN);
    }

    @DisplayName("할인이 적용되지 않는 요일에 대한 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7})
    void no_discount_on_non_event_day(int dayOfMonth) {
        VisitDate visitDate = VisitDate.from(dayOfMonth);
        Map<Menu, Integer> orders = new HashMap<>();
        orders.put(Menu.T_BONE_STEAK, 2);
        WeekendEvent weekendEvent = new WeekendEvent(visitDate, new Orders(orders));

        int discountAmount = weekendEvent.discountAmount();

        assertThat(discountAmount).isEqualTo(0);
    }
}