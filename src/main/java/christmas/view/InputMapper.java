package christmas.view;

import christmas.domain.Menu;
import christmas.domain.Orders;
import christmas.exception.PromotionException;
import christmas.domain.VisitDate;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class InputMapper {
    public VisitDate mapToVisitDate(String input) {
        int dayOfMonth;
        try {
            dayOfMonth = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PromotionException.dateException());
        }
        return VisitDate.from(dayOfMonth);
    }

    public Orders mapToOrders(String input) {
        String[] orders = input.split(",", -1);

        Map<Menu, Integer> orderMap = Arrays.stream(orders)
                .map(order -> validateOrderFormat(order))
                .collect(Collectors.toMap(
                        details -> validateMenu(details[0]),
                        details -> validateQuantity(details[1]),
                        (existing, replacement) -> {
                            throw new IllegalArgumentException(PromotionException.orderException());
                        }));

        return new Orders(orderMap);
    }

    private String[] validateOrderFormat(String input) {
        String[] details = input.split("-");
        if (details.length != 2) {
            throw new IllegalArgumentException(PromotionException.orderException());
        }
        return details;
    }

    private Menu validateMenu(String input) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(PromotionException.orderException()));
    }

    private int validateQuantity(String input) {
        int quantity;
        try {
            quantity = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PromotionException.orderException());
        }

        if (quantity < 1) {
            throw new IllegalArgumentException(PromotionException.orderException());
        }
        return quantity;
    }
}
