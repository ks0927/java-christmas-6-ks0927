package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.VisitDate;
import christmas.domain.event.Event;
import christmas.domain.event.StarEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("특별 할인 테스트")
class StarEventTest {
    @Test
    @DisplayName("달력이 별이 있는날 할인 금액 테스트")
    void discountAmountOnEventDay() {
        VisitDate visitDate = VisitDate.from(3);
        Event starEvent = new StarEvent(visitDate);

        int discountAmount = starEvent.discountAmount();

        assertThat(discountAmount).isEqualTo(1000);
    }

    @Test
    @DisplayName("달력이 별이 없는날 할인 금액 테스트")
    void discountAmountOnNonEventDay() {
        VisitDate visitDate = VisitDate.from(1);
        Event starEvent = new StarEvent(visitDate);

        int discountAmount = starEvent.discountAmount();

        assertThat(discountAmount).isEqualTo(0);
    }
}