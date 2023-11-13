package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("주문 테스트")
class OrdersTest {
    private static final String ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    @Test
    @DisplayName("Orders 생성 시 주문 메뉴와 수량이 유효한 범위인지 검사")
    void createOrders() {
        Map<Menu, Integer> orders = new HashMap<>();
        orders.put(Menu.CHOCOLATE_CAKE, 5);
        orders.put(Menu.ZERO_COLA, 10);

        assertThatCode(() -> new Orders(orders)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Orders 생성 시 주문 수량이 최대 범위를 초과하는 경우 예외 처리")
    void createOrdersWithExcessiveQuantity() {
        Map<Menu, Integer> orders = new HashMap<>();
        orders.put(Menu.TAPAS, 21);

        assertThatThrownBy(() -> new Orders(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ORDER_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("Orders 생성 시 모든 주문이 음료인 경우 예외 처리")
    void createOrdersWithAllDrinks() {
        Map<Menu, Integer> orders = new HashMap<>();
        orders.put(Menu.ZERO_COLA, 10);
        orders.put(Menu.RED_WINE, 10);

        assertThatThrownBy(() -> new Orders(orders))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ORDER_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("주문한 메뉴의 총 가격 계산")
    void calculateTotalPrice() {
        Map<Menu, Integer> orders = new HashMap<>();
        orders.put(Menu.ICE_CREAM, 5);
        orders.put(Menu.CHRISTMAS_PASTA, 1);

        Orders validOrders = new Orders(orders);

        int totalPrice = validOrders.totalPrice();

        assertThat(totalPrice).isEqualTo(50_000);
    }

    @Test
    @DisplayName("주문한 디저트의 총 개수 계산")
    void calculateDessertCounts() {
        Map<Menu, Integer> orders = new HashMap<>();
        orders.put(Menu.ICE_CREAM, 5);
        orders.put(Menu.SEAFOOD_PASTA, 10);

        Orders validOrders = new Orders(orders);

        int dessertCounts = validOrders.getDessertCounts();

        assertThat(dessertCounts).isEqualTo(5);
    }

    @Test
    @DisplayName("주문한 메인 요리의 총 개수 계산")
    void calculateMainCounts() {
        Map<Menu, Integer> orders = new HashMap<>();
        orders.put(Menu.SEAFOOD_PASTA, 5);
        orders.put(Menu.WHITE_MUSHROOMS_SOUP, 10);

        Orders validOrders = new Orders(orders);

        int mainCounts = validOrders.getMainCounts();

        assertThat(mainCounts).isEqualTo(5);
    }
}