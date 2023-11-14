package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.Orders;
import christmas.VisitDate;
import java.util.function.Supplier;

public class InputView {
    private static final String READ_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String READ_ORDERS_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private final InputMapper inputMapper;

    public InputView(InputMapper inputMapper) {
        this.inputMapper = inputMapper;
    }

    public VisitDate readVisitDate() {
        System.out.println(READ_VISIT_DATE_MESSAGE);
        return getInputUntilValid(()->inputMapper.mapToVisitDate(Console.readLine()));
    }

    public Orders readOrders() {
        System.out.println(READ_ORDERS_MESSAGE);
        return getInputUntilValid(()->inputMapper.mapToOrders(Console.readLine()));
    }

    private <T> T getInputUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
