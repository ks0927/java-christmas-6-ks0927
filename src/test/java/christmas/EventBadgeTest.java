package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.event.EventBadge;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("12월 이벤트 배지 테스트")
class EventBadgeTest {
    @DisplayName("총 혜택금액이 5000원이상일때 별 배지 반환 테스트")
    @Test
    public void whenDiscountAmountIs5000_thenBadgeIsStar() {
        Optional<EventBadge> badge = EventBadge.getBadgeByDiscountAmount(5000);
        assertThat(badge).isPresent();
        assertThat(badge.get()).isEqualTo(EventBadge.STAR);
    }

    @DisplayName("총 혜택금액이 10000원이상일때 트리 배지 반환 테스트")
    @Test
    public void whenDiscountAmountIs10000_thenBadgeIsTree() {
        Optional<EventBadge> badge = EventBadge.getBadgeByDiscountAmount(10000);
        assertThat(badge).isPresent();
        assertThat(badge.get()).isEqualTo(EventBadge.TREE);
    }

    @DisplayName("총 혜택금액이 20000원이상일때 산타 배지 반환 테스트")
    @Test
    public void whenDiscountAmountIs20000_thenBadgeIsSanta() {
        Optional<EventBadge> badge = EventBadge.getBadgeByDiscountAmount(20000);
        assertThat(badge).isPresent();
        assertThat(badge.get()).isEqualTo(EventBadge.SANTA);
    }

    @DisplayName("어떤 배지에도 해당되지 않는 혜택 금액일때 Optional.empty() 반환 테스트")
    @Test
    public void whenDiscountAmountIsBelowMinimum_thenNoBadge() {
        Optional<EventBadge> badge = EventBadge.getBadgeByDiscountAmount(3000);
        assertThat(badge).isNotPresent();
    }
}