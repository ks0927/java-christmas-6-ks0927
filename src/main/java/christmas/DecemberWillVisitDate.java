package christmas;

import java.util.List;

public class DecemberWillVisitDate implements WillVisitDate {
    private static final int START_DAY = 1;
    private static final int LAST_DAY = 31;
    private static final String INVALID_RANGE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private final int decemberWillVisitDate;

    public DecemberWillVisitDate(int decemberWillVisitDate) {
        validate(decemberWillVisitDate);
        this.decemberWillVisitDate = decemberWillVisitDate;
    }

    private void validate(int decemberWillVisitDate) {
        if (isInvalidRange(decemberWillVisitDate)) {
            throw new IllegalArgumentException(INVALID_RANGE_ERROR_MESSAGE);
        }
    }

    private boolean isInvalidRange(int decemberWillVisitDate) {
        if (START_DAY <= decemberWillVisitDate && decemberWillVisitDate <= LAST_DAY) {
            return false;
        }
        return true;
    }

    @Override
    public int getDate() {
        return decemberWillVisitDate;
    }

    @Override
    public boolean containDate(List<Integer> target) {
        return target.stream()
                .anyMatch(date -> date == decemberWillVisitDate);
    }
}
