package christmas;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Mapper {
    private static final String VISIT_DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public static VisitDate mapToVisitDate(String input) {
        int dayOfMonth;
        try {
            dayOfMonth = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(VISIT_DATE_ERROR_MESSAGE);
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
                            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
                        }));

        return new Orders(orderMap);
    }

    private static String[] validateOrderFormat(String target) {
        String[] details = target.split("-");
        if (details.length != 2) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
        return details;
    }

    private static Menu validateMenu(String target) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(target))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ORDER_ERROR_MESSAGE));
    }

    private static int validateQuantity(String target) {
        int quantity;
        try {
            quantity = Integer.parseInt(target);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }

        if (quantity < 1) {
            throw new IllegalArgumentException(ORDER_ERROR_MESSAGE);
        }
        return quantity;
    }
}
