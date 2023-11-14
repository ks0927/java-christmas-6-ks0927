package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.view.InputMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("입력값 매핑 테스트")
class InputMapperTest {
    private final InputMapper inputMapper = new InputMapper();

    @Test
    @DisplayName("유효한 날짜를 입력받아 VisitDate로 변환")
    public void mapToVisitDate_Valid() {
        String input = "25";

        VisitDate visitDate = inputMapper.mapToVisitDate(input);

        assertThat(visitDate.getDate()).isEqualTo(25);
    }

    @Test
    @DisplayName("유효하지 않은 날짜를 입력받아 예외 발생")
    public void mapToVisitDate_Invalid() {
        String input = "abc";

        assertThatThrownBy(() -> inputMapper.mapToVisitDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PromotionException.dateException());
    }

    @Test
    @DisplayName("유효한 주문을 입력받아 Orders로 변환")
    public void mapToOrders_Valid() {
        String input = "바비큐립-2,시저샐러드-1";

        Orders orders = inputMapper.mapToOrders(input);

        assertThat(orders.getOrders()).containsKeys(Menu.BARBECUE_RIB, Menu.CAESAR_SALAD);
    }

    @Test
    @DisplayName("주문 형식이 유효하지 않을때 예외 발생")
    public void mapToOrders_Format_Invalid() {
        String input = "바비큐립:1,시저샐러드:2";

        assertThatThrownBy(() -> inputMapper.mapToOrders(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PromotionException.orderException());
    }

    @DisplayName("주문 갯수가 유효하지 않을때 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "0"})
    public void mapToOrders_Count_Invalid(String count) {
        String input = "바비큐립-" + count;

        assertThatThrownBy(() -> inputMapper.mapToOrders(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PromotionException.orderException());
    }

    @Test
    @DisplayName("중복 메뉴를 입력했을때 예외 발생")
    public void mapToOrders_Distinct_Invalid() {
        String input = "바비큐립-2,바비큐립-2";

        assertThatThrownBy(() -> inputMapper.mapToOrders(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PromotionException.orderException());
    }

    @Test
    @DisplayName("없는 메뉴를 입력했을때 예외 발생")
    public void mapToOrders_None_Menu_Invalid() {
        String input = "피자-1,바비큐립-2";

        assertThatThrownBy(() -> inputMapper.mapToOrders(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PromotionException.orderException());
    }
}