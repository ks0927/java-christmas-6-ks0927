package christmas;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Mapper {
    public static VisitDate mapToVisitDate(String input) {
        int dayOfMonth;
        try {
            dayOfMonth = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PromotionException.dateException());
        }
        return VisitDate.from(dayOfMonth);
    }

    public static Orders mapToOrders(String input) {
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

    private static String[] validateOrderFormat(String target) {
        String[] details = target.split("-");
        if (details.length != 2) {
            throw new IllegalArgumentException(PromotionException.orderException());
        }
        return details;
    }

    private static Menu validateMenu(String target) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(target))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(PromotionException.orderException()));
    }

    private static int validateQuantity(String target) {
        int quantity;
        try {
            quantity = Integer.parseInt(target);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(PromotionException.orderException());
        }

        if (quantity < 1) {
            throw new IllegalArgumentException(PromotionException.orderException());
        }
        return quantity;
    }
}
