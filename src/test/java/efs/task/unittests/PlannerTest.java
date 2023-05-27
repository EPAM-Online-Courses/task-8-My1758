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
