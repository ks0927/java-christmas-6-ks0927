package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("12월 방문 날짜 테스트")
class DecemberWillVisitDateTest {
    @DisplayName("12월의 날짜는 1이상 31이하의 숫자일떄 통과한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 21, 31})
    public void 날짜_생성_성공_테스트(int date) {
        assertThatCode(() -> new DecemberWillVisitDate(date)).doesNotThrowAnyException();
    }

    @DisplayName("12월의 날짜는 1이상 31이하의 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -11, 32})
    public void 날짜_생성_실패_테스트(int date) {
        assertThatThrownBy(() -> new DecemberWillVisitDate(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("방문 날짜와 날짜 리스트를 비교하여 포함되면 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 31})
    public void containDate_true_테스트(int date) {
        List<Integer> dates = Arrays.asList(1, 15, 31);
        assertThat(new DecemberWillVisitDate(date).containDate(dates)).isTrue();
    }

    @DisplayName("방문 날짜와 날짜 리스트를 비교하여 포함되지 않으면 false를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {2, 16, 30})
    public void containDate_false_테스트(int date) {
        List<Integer> dates = Arrays.asList(1, 15, 31);
        assertThat(new DecemberWillVisitDate(date).containDate(dates)).isFalse();
    }
}