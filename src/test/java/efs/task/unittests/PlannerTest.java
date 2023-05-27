import efs.task.unittests.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import efs.task.unittests.Planner;

import java.util.List;
import efs.task.unittests.ActivityLevel;
import efs.task.unittests.User;

import static org.junit.jupiter.api.Assertions.*;
class PlannerTest {

    private Planner planner;

    @BeforeEach
    void setUp() {
        planner = new Planner();
    }

    @ParameterizedTest(name = "ActivityLevel={0}")
    @CsvSource({"LOW_ACTIVITY", "MEDIUM_ACTIVITY", "HIGH_ACTIVITY"})
    void shouldCalculateDailyCaloriesDemand(ActivityLevel level) {
        // Given
        User user = TestConstants.TEST_USER;
        int expectedCalories = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(level);

        // When
        int result = planner.calculateDailyCaloriesDemand(user, level);

        // Then
        assertEquals(expectedCalories, result);
    }

    @Test
    void shouldCalculateDailyIntake() {
        // Given
        User user = TestConstants.TEST_USER;
        DailyIntake expectedIntake = TestConstants.TEST_USER_DAILY_INTAKE;

        // When
        DailyIntake result = planner.calculateDailyIntake(user);

        // Then
        assertEquals(expectedIntake, result);
    }
}
