package efs.task.unittests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

//class FitCalculatorTest {

    //@Test
   // void shouldReturnTrue_whenDietRecommended() {
        //given
    //    double weight = 89.2;
     //   double height = 1.72;

        //when
     //   boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
      //  assertTrue(recommended);
   // }
//}




class FitCalculatorTest {
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
      double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
     Assertions.assertTrue(recommended);

    @Test
    @DisplayName("isBMICorrect should return true for valid BMI")
    void isBMICorrect_ShouldReturnTrueForValidBMI() {
        // Given
        double weight = 70.0;
        double height = 1.75;

        // When
        boolean result = FitCalculator.isBMICorrect(weight, height);

        // Then
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("isBMICorrect should return false for invalid BMI")
    void isBMICorrect_ShouldReturnFalseForInvalidBMI() {
        // Given
        double weight = 69.5;
        double height = 1.72;

        // When
        boolean result = FitCalculator.isBMICorrect(weight, height);

        // Then
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("isBMICorrect should throw IllegalArgumentException for zero height")
    void isBMICorrect_ShouldThrowIllegalArgumentExceptionForZeroHeight() {
        // Given
        double weight = 80.0;
        double height = 0.0;

        // When/Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            FitCalculator.isBMICorrect(weight, height);
        });
    }

    @ParameterizedTest(name = "isBMICorrect should return true for weight={0} and different heights")
    @ValueSource(doubles = {60.0, 65.0, 70.0})
    void isBMICorrect_ShouldReturnTrueForDifferentHeights(double weight) {
        // Given
        double height = 1.80;

        // When
        boolean result = FitCalculator.isBMICorrect(weight, height);

        // Then
        Assertions.assertTrue(result);
    }

    @ParameterizedTest(name = "isBMICorrect should return false for height={0} and weight={1}")
    @CsvSource({"1.70, 60.0", "1.75, 70.0", "1.80, 80.0"})
    void isBMICorrect_ShouldReturnFalseForDifferentHeightAndWeight(double height, double weight) {
        // When
        boolean result = FitCalculator.isBMICorrect(weight, height);

        // Then
        Assertions.assertFalse(result);
    }

    @ParameterizedTest(name = "isBMICorrect should return false for height={0} and weight={1}")
    @CsvFileSource(resources = "/data.csv")
    void isBMICorrect_ShouldReturnFalseForDifferentHeightAndWeightFromFile(double height, double weight) {
        // When
        boolean result = FitCalculator.isBMICorrect(weight, height);

        // Then
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("findUserWithTheWorstBMI should return user with the worst BMI")
    void findUserWithTheWorstBMI_ShouldReturnUserWithWorstBMI() {
        // Given
        List<User> users = TestConstants.TEST_USERS_LIST;

        // When
        User result = FitCalculator.findUserWithTheWorstBMI(users);

        // Then
        User expectedUser = new User("John", 97.3, 1.79);
        Assertions.assertEquals(expectedUser, result);
    }

    @Test
    @DisplayName("findUserWithTheWorstBMI should return null for empty user list")
    void findUserWithTheWorstBMI_ShouldReturnNullForEmptyUserList() {
        // Given
        List<User> users = new ArrayList<>();

        // When
        User result = FitCalculator.findUserWithTheWorstBMI(users);

        // Then
        Assertions.assertNull(result);
    }

    @Test
    @DisplayName("calculateBMIScore should return correct BMI scores")
    void calculateBMIScore_ShouldReturnCorrectBMIScores() {
        // Given
        List<User> users = TestConstants.TEST_USERS_LIST;

        // When
        double[] result = FitCalculator.calculateBMIScore(users);

        // Then
        double[] expectedScores = TestConstants.TEST_USERS_BMI_SCORE;
        Assertions.assertArrayEquals(expectedScores, result, 0.01);
    }
}
}
class PlannerTest {

    private Planner planner;

    @BeforeEach
    void setUp() {
        planner = new Planner();
    }

    @ParameterizedTest(name = "calculateDailyCaloriesDemand should return correct calorie demand for activity level {0}")
    @EnumSource(ActivityLevel.class)
    void calculateDailyCaloriesDemand_ShouldReturnCorrectCalorieDemand(ActivityLevel activityLevel) {
        // Given
        User user = TestConstants.TEST_USER;

        // When
        double result = planner.calculateDailyCaloriesDemand(user, activityLevel);

        // Then
        double expectedCalories = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(activityLevel);
        Assertions.assertEquals(expectedCalories, result, 0.01);
    }

    @Test
    @DisplayName("calculateDailyIntake should return correct daily nutrient intake")
    void calculateDailyIntake_ShouldReturnCorrectDailyNutrientIntake() {
        // Given
        User user = TestConstants.TEST_USER;

        // When
        NutrientIntake result = planner.calculateDailyIntake(user);

        // Then
        NutrientIntake expectedIntake = TestConstants.TEST_USER_DAILY_INTAKE;
        Assertions.assertEquals(expectedIntake, result);
    }
}
