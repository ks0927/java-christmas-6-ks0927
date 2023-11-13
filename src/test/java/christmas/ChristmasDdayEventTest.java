package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("크리스마스 디데이 할인 테스트")
class ChristmasDdayEventTest {
    @DisplayName("크리스마스 디데이 할인은 1000원에서 시작하여 매일 100원씩 증가하고 25일 이후엔 적용되지 않는다.")
    @ParameterizedTest(name = "12월 {0}일 방문 시 할인 금액은 {1}원이어야 한다")
    @CsvSource({
            "1, 1000",
            "5, 1400",
            "25, 3400",
            "26, 0"
    })
    public void discountAmountTest(int dayOfMonth, int expectedDiscount) {
        VisitDate visitDate = VisitDate.from(dayOfMonth);
        ChristmasDdayEvent event = new ChristmasDdayEvent(visitDate);
        assertThat(event.discountAmount()).isEqualTo(expectedDiscount);
    }
}