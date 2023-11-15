package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.domain.event.Event;
import christmas.domain.event.EventBadge;
import christmas.domain.event.EventPlanner;
import christmas.view.InputMapper;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;
import java.util.Optional;

public class ChristmasPromotion {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView(new InputMapper());

    public void run() {
        outputView.printWelcomeMessage();
        VisitDate visitDate = inputView.readVisitDate();
        Orders orders = inputView.readOrders();
        outputView.printEventPreviewMessage(visitDate);

        outputView.printOrderMenuMessage(orders);

        EventPlanner eventPlanner = new EventPlanner(orders, visitDate);
        printEventResult(eventPlanner);
    }

    private void printEventResult(EventPlanner eventPlanner) {
        printTotalPriceBeforeDiscount(eventPlanner);
        printGiftMenu(eventPlanner);
        printDiscountDetail(eventPlanner);
        printTotalDiscountAmount(eventPlanner);
        printEstimatedPaymentAfterDiscount(eventPlanner);
        printEventBadge(eventPlanner);
    }

    private void printTotalPriceBeforeDiscount(EventPlanner eventPlanner) {
        int totalPriceBeforeDiscount = eventPlanner.totalPriceBeforeDiscount();
        outputView.printTotalOrderPrice(totalPriceBeforeDiscount);
    }

    private void printGiftMenu(EventPlanner eventPlanner) {
        Map<Menu, Integer> giftMenu = eventPlanner.giftMenu(eventPlanner.totalPriceBeforeDiscount());
        outputView.printGiftMenu(giftMenu);
    }

    private void printDiscountDetail(EventPlanner eventPlanner) {
        Map<Event, Integer> discountDetail = eventPlanner.totalDiscountDetails(eventPlanner.totalPriceBeforeDiscount());
        outputView.printDiscountDetail(discountDetail);
    }

    private void printTotalDiscountAmount(EventPlanner eventPlanner) {
        int totalDiscountAmount = eventPlanner.totalDiscountAmount(eventPlanner.totalDiscountDetails(eventPlanner.totalPriceBeforeDiscount()));
        outputView.printTotalDiscountPrice(totalDiscountAmount);
    }

    private void printEstimatedPaymentAfterDiscount(EventPlanner eventPlanner) {
        int estimatedPaymentAfterDiscount = eventPlanner.estimatedPaymentAfterDiscount(eventPlanner.totalDiscountDetails(eventPlanner.totalPriceBeforeDiscount()),
                eventPlanner.totalPriceBeforeDiscount());
        outputView.printEstimatedPayment(estimatedPaymentAfterDiscount);
    }

    private void printEventBadge(EventPlanner eventPlanner) {
        Optional<EventBadge> eventBadge = eventPlanner.eventBadge(eventPlanner.totalDiscountAmount(eventPlanner.totalDiscountDetails(eventPlanner.totalPriceBeforeDiscount())));
        outputView.printEventBadge(eventBadge);
    }
}
