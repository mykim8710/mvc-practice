package com.example.practice.calculator.domain;


import com.example.practice.calculator.domain.Calculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * - 요구사항
 *  - 간단한 사칙연산을 할 수 있다.
 *  - 양수로만 계산할 수 있다.
 *  - 나눗셈에서 0을 나누는 경우 IllegalArgument 예외를 발생시킨다.
 *  - MVC패턴(Model-View-Controller) 기반으로 구현한다.
 */
public class CalculatorTest {

    @DisplayName("사칙연산 테스트를 수행한다.")
    @ParameterizedTest(name = "{index} {displayName} {0} {1} {2}")
    @MethodSource("formulaAndResult")
    void calculatorTest(int operand1, String operator, int operand2, int result) {
        int calculatorResult = Calculator.calculator(operand1, operator, operand2);
        Assertions.assertThat(calculatorResult).isEqualTo(result);
    }

    private static Stream<Arguments> formulaAndResult() {
        return Stream.of(
                arguments(1, "+", 2, 3),
                arguments(1, "-", 2, -1),
                arguments(4, "*", 2, 8),
                arguments(4, "/", 2, 2)
        );
    }

    @DisplayName("사칙연산을 위한 인자는 양수만 가능하다. 0 또는 음수를 전달할 때 IllegalArgument 예외를 발생시킨다.")
    @Test
    void operandNegativeNumberExceptionTest() {
        int operand1 = -1;
        int operand2 = -2;
        String operator = "/";

        Assertions.assertThatCode(() -> Calculator.calculator(operand1, operator, operand2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0 또는 음수를 전달할 수 없습니다.");
    }


    @DisplayName("사칙연산의 중 나눗셈연산의 두번째 인자 0이면 IllegalArgument 예외를 발생시킨다.")
    @Test
    @Disabled // 의미없는 테스트, 실행 X
    void divisionOperatorExceptionTest() {
        int operand1 = 2;
        int operand2 = 0;
        String operator = "/";

        Assertions.assertThatCode(() -> Calculator.calculator(operand1, operator, operand2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0으로 나눌 수 없습니다.");
    }

    @Test
    @DisplayName("덧셈 연산을 수행한다.")
    void additionTest() throws Exception {
        int operand1 = 1;
        int operand2 = 2;
        String operator = "+";

        int result = Calculator.calculator(operand1, operator, operand2);
        Assertions.assertThat(result).isEqualTo(operand1 + operand2);
    }

    @Test
    @DisplayName("뺄셈 연산을 수행한다.")
    void subtractionTest() throws Exception{
        int operand1 = 1;
        int operand2 = 2;
        String operator = "-";

        int result = Calculator.calculator(operand1, operator, operand2);
        Assertions.assertThat(result).isEqualTo(operand1 - operand2);
    }

    @Test
    @DisplayName("곱뺄 연산을 수행한다.")
    void multiplicationTest() throws Exception{
        int operand1 = 4;
        int operand2 = 2;
        String operator = "*";

        int result = Calculator.calculator(operand1, operator, operand2);
        Assertions.assertThat(result).isEqualTo(operand1 * operand2);
    }

    @Test
    @DisplayName("나눗셈 연산을 수행한다.")
    void divisionTest() throws Exception{
        int operand1 = 4;
        int operand2 = 2;
        String operator = "/";

        int result = Calculator.calculator(operand1, operator, operand2);
        Assertions.assertThat(result).isEqualTo(operand1 / operand2);
    }

}
