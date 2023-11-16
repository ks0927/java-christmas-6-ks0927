package christmas.domain;

import christmas.exception.PromotionException;
import java.util.HashMap;
import java.util.Map;

public class Orders {
    private static final int MAX_ORDER_QUANTITY = 20;

    private final Map<Menu, Integer> orders;

    public Orders(Map<Menu, Integer> orders) {
        validate(orders);
        this.orders = orders;
    }

    private void validate(Map<Menu, Integer> orders) {
        if (isInvalidRange(orders)) {
            throw new IllegalArgumentException(PromotionException.orderException());
        }
        if (isAllDrinks(orders)) {
            throw new IllegalArgumentException(PromotionException.orderException());
        }
    }

    private boolean isInvalidRange(Map<Menu, Integer> target) {
        int orderQuantity = target.entrySet().stream()
                .mapToInt(Map.Entry::getValue)
                .sum();
        return orderQuantity > MAX_ORDER_QUANTITY;
    }

    private boolean isAllDrinks(Map<Menu, Integer> target) {
        return target.entrySet().stream()
                .allMatch(menu -> menu.getKey().getType() == MenuType.DRINK);
    }

    public int totalPrice() {
        return orders.entrySet().stream()
                .mapToInt(map -> map.getValue() * map.getKey().getPrice())
                .sum();
    }

    public int getDessertCounts() {
        return orders.entrySet().stream()
                .filter(menuIntegerEntry -> menuIntegerEntry.getKey().getType() == MenuType.DESSERT)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public int getMainCounts() {
        return orders.entrySet().stream()
                .filter(menuIntegerEntry -> menuIntegerEntry.getKey().getType() == MenuType.MAIN)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    /**
     * 주문 결과를 Map의 형태로 반환한다.
     * HashMap을 통한 반환은 얕은 복사지만 Menu와 Integer는 불변객체로 충분히 안전하다.
     * 단, 출력 등의 이유를 제외하고는 사용을 권장하지 않는다.
     */
    public Map<Menu, Integer> getOrders() {
        return new HashMap<>(orders);
    }
}
