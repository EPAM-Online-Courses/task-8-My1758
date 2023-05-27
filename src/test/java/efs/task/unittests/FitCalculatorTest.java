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
import static org.junit.jupiter.api.Assertions.*;

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
     assertTrue(recommended);

     @Test
    void shouldReturnFalse_whenDietNotRecommended() {
        // Given
        double weight = 69.5;
        double height = 1.72;

        // When
        boolean result = FitCalculator.isBMICorrect(weight, height);

        // Then
        assertFalse(result);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenHeightIsZero() {
        // Given
        double weight = 70.0;
        double height = 0.0;

        // When/Then
        assertThrows(IllegalArgumentException.class, () -> FitCalculator.isBMICorrect(weight, height));
    }

    @ParameterizedTest(name = "Weight={0}")
    @ValueSource(doubles = {50.0, 60.0, 70.0})
    void shouldReturnTrue_forDifferentWeights(double weight) {
        // Given
        double height = 1.75;

        // When
        boolean result = FitCalculator.isBMICorrect(weight, height);

        // Then
        assertTrue(result);
    }

    @ParameterizedTest(name = "Height={0}, Weight={1}")
    @CsvSource({"1.70, 60.0", "1.80, 65.0", "1.90, 70.0"})
    void shouldReturnFalse_forDifferentHeightAndWeight(double height, double weight) {
        // When
        boolean result = FitCalculator.isBMICorrect(weight, height);

        // Then
        assertFalse(result);
    }

    @ParameterizedTest(name = "Height={0}, Weight={1}")
    @CsvFileSource(resources = "/data.csv")
    void shouldReturnFalse_forHeightAndWeightFromFile(double height, double weight) {
        // When
        boolean result = FitCalculator.isBMICorrect(weight, height);

        // Then
        assertFalse(result);
    }

    @Test
    void shouldReturnUserWithWorstBMI() {
        // Given
        List<User> users = TestConstants.TEST_USERS_LIST;

        // When
        User result = FitCalculator.findUserWithTheWorstBMI(users);

        // Then
        assertNotNull(result);
        assertEquals(97.3, result.getWeight());
        assertEquals(1.79, result.getHeight());
    }

    @Test
    void shouldReturnNull_whenUserListIsEmpty() {
        // Given
        List<User> users = List.of();

        // When
        User result = FitCalculator.findUserWithTheWorstBMI(users);

        // Then
        assertNull(result);
    }

    @Test
    void shouldCalculateBMIScore() {
        // Given
        List<User> users = TestConstants.TEST_USERS_LIST;
        double[] expectedScores = TestConstants.TEST_USERS_BMI_SCORE;

        // When
        double[] result = FitCalculator.calculateBMIScore(users);

        // Then
        assertArrayEquals(expectedScores, result);
    }
}
