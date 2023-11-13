package christmas;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public enum EventBadge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String name;
    private final int threshold;

    EventBadge(String name, int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public String getName() {
        return name;
    }

    public int getThreshold() {
        return threshold;
    }

    public static Optional<EventBadge> getBadgeByDiscountAmount(int discountAmount) {
        return Arrays.stream(EventBadge.values())
                .sorted(Comparator.comparing(EventBadge::getThreshold).reversed())
                .filter(badge -> discountAmount >= badge.getThreshold())
                .findFirst();
    }
}
