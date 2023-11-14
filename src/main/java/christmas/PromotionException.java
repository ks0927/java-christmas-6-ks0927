package christmas;

public enum PromotionException {
    ORDER_EXCEPTION("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    DATE_EXCEPTION("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    private final String message;

    PromotionException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static String orderException() {
        return ERROR_MESSAGE_PREFIX + ORDER_EXCEPTION.getMessage();
    }

    public static String dateException() {
        return ERROR_MESSAGE_PREFIX + DATE_EXCEPTION.getMessage();
    }
}
