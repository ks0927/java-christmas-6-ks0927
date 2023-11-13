package christmas;

import java.util.Map;

public class Orders {
    private static final String ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final int MAX_ORDER_QUANTITY = 20;

    private final Map<Menu, Integer> orders;

    public Orders(Map<Menu, Integer> orders) {
        validate(orders);
        this.orders = orders;
    }

    private void validate(Map<Menu, Integer> orders) {
        if (isInvalidRange(orders)) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
        if (isAllDrinks(orders)) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
    }

    private boolean isInvalidRange(Map<Menu, Integer> target) {
        int orderQuantity = target.entrySet().stream()
                .mapToInt(Map.Entry::getValue)
                .sum();
        if (orderQuantity > MAX_ORDER_QUANTITY) {
            return true;
        }
        return false;
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

    public int getAppetizerCounts() {
        return orders.entrySet().stream()
                .filter(menuIntegerEntry -> menuIntegerEntry.getKey().getType() == MenuType.APPETIZER)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public int getMainCounts() {
        return orders.entrySet().stream()
                .filter(menuIntegerEntry -> menuIntegerEntry.getKey().getType() == MenuType.MAIN)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
