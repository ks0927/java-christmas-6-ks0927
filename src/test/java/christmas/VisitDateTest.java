package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("방문 날짜 테스트")
class VisitDateTest {
    @DisplayName("날짜 생성 성공 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 21, 31})
    public void create_visitDate_success(int dayOfMonth) {
        assertThatCode(() -> VisitDate.from(dayOfMonth)).doesNotThrowAnyException();
    }

    @DisplayName("날짜 생성 실패 테스트")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 32})
    public void create_visitDate_fail(int dayOfMonth) {
        assertThatThrownBy(() -> VisitDate.from(dayOfMonth))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("날짜 리스트에 방문 날짜가 포함되면 true 반환 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 31})
    public void containDate_true(int dayOfMonth) {
        List<Integer> dates = Arrays.asList(1, 15, 31);
        VisitDate visitDate = VisitDate.from(dayOfMonth);
        assertThat(visitDate.containDayOfMonth(dates)).isTrue();
    }

    @DisplayName("날짜 리스트에 방문 날짜가 포함되지 않으면 false 반환 테스트")
    @ParameterizedTest
    @ValueSource(ints = {2, 16, 30})
    public void containDate_false(int dayOfMonth) {
        List<Integer> dates = Arrays.asList(1, 15, 31);
        VisitDate visitDate = VisitDate.from(dayOfMonth);
        assertThat(visitDate.containDayOfMonth(dates)).isFalse();
    }
}