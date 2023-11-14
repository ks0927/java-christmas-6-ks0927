package christmas.view;

import christmas.Event;
import christmas.EventPlanner;
import christmas.Menu;
import christmas.Orders;
import java.util.Map;

public class OutputView {
    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n";
    private static final String ORDER_MENU_HEADER = "<주문 메뉴>";
    private static final String MENU_FORMAT = "%s %d개";
    private static final String TOTAL_ORDER_PRICE_HEADER = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_HEADER = "<증정 메뉴>";
    private static final String DISCOUNT_DETAIL_HEADER = "<혜택 내역>";
    private static final String TOTAL_DISCOUNT_PRICE_HEADER = "<총혜택 금액>";
    private static final String ESTIMATED_PAYMENT_HEADER = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE_HEADER = "<12월 이벤트 배지>";
    private static final String NONE_MESSAGE = "없음";
    private static final String PRICE_FORMAT = "%,d원\n";

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }
}
