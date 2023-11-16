package christmas.view;

import christmas.domain.Menu;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.domain.event.Event;
import christmas.domain.event.EventBadge;
import java.util.Map;
import java.util.Optional;

public class OutputView {
    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_HEADER = "<주문 메뉴>";
    private static final String MENU_FORMAT = "%s %d개";
    private static final String TOTAL_ORDER_PRICE_HEADER = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_HEADER = "<증정 메뉴>";
    private static final String DISCOUNT_DETAIL_HEADER = "<혜택 내역>";
    private static final String TOTAL_DISCOUNT_PRICE_HEADER = "<총혜택 금액>";
    private static final String ESTIMATED_PAYMENT_HEADER = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_HEADER = "<12월 이벤트 배지>";
    private static final String NONE_MESSAGE = "없음";
    private static final String PRICE_FORMAT = "%,d원";

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printEventPreviewMessage(VisitDate visitDate) {
        System.out.println(String.format(EVENT_PREVIEW_MESSAGE, visitDate.getDayOfMonth()));
        System.out.println();
    }

    public void printOrderMenuMessage(Orders orders) {
        System.out.println(ORDER_MENU_HEADER);
        ordersDetail(orders);
        System.out.println();
    }

    private void ordersDetail(Orders orders) {
        orders.getOrders().entrySet().stream()
                .map(entry -> String.format(MENU_FORMAT, entry.getKey().getName(), entry.getValue()))
                .forEach(System.out::println);
    }

    public void printTotalOrderPrice(int totalOrderPrice) {
        System.out.println(TOTAL_ORDER_PRICE_HEADER);
        System.out.println(String.format(PRICE_FORMAT, totalOrderPrice));
        System.out.println();
    }

    public void printGiftMenu(Map<Menu, Integer> gifts) {
        System.out.println(GIFT_MENU_HEADER);

        if (gifts.isEmpty()) {
            System.out.println(NONE_MESSAGE);
            return;
        }

        gifts.entrySet().stream()
                .map(menu -> String.format(MENU_FORMAT, menu.getKey().getName(), menu.getValue()))
                .forEach(System.out::println);
        System.out.println();
    }

    public void printDiscountDetail(Map<Event, Integer> discountDetail) {
        System.out.println(DISCOUNT_DETAIL_HEADER);

        if (discountDetail.isEmpty()) {
            System.out.println(NONE_MESSAGE);
            return;
        }

        discountDetail.entrySet().stream()
                .map(entry -> entry.getKey().getName() + ": -" + String.format(PRICE_FORMAT, entry.getValue()))
                .forEach(System.out::println);
        System.out.println();
    }

    public void printTotalDiscountPrice(int totalDiscountAmount) {
        System.out.println(TOTAL_DISCOUNT_PRICE_HEADER);
        if (totalDiscountAmount == 0) {
            System.out.println(String.format(PRICE_FORMAT, totalDiscountAmount));
            System.out.println();
            return;
        }
        System.out.println(String.format("-" + PRICE_FORMAT, totalDiscountAmount));
        System.out.println();
    }

    public void printEstimatedPayment(int estimatedPaymentAfterDiscount) {
        System.out.println(ESTIMATED_PAYMENT_HEADER);
        System.out.println(String.format(PRICE_FORMAT, estimatedPaymentAfterDiscount));
        System.out.println();
    }

    public void printEventBadge(Optional<EventBadge> eventBadge) {
        System.out.println(EVENT_BADGE_HEADER);
        if (eventBadge.isEmpty()) {
            System.out.println(NONE_MESSAGE);
            return;
        }
        System.out.println(eventBadge.get().getName());
    }
}
