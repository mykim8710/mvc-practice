package com.example.calculator.domain;

import com.example.calculator.operator.*;

import java.util.List;

public class Calculator {
    // interface를 이용한 리팩토링
    private static final List<NewArithmeticOperator> newArithmeticOperators = List.of(
        new AdditionOperator(),
        new SubtractionOperator(),
        new MultiplicationOperator(),
        new DivisionOperator());

    public static int calculator(int operand1, String operator, int operand2) {
        if(operand1 <= 0 || operand2 <= 0) {
            throw new IllegalArgumentException("0 또는 음수를 전달할 수 없습니다.");
        }

        // 1차 구현
//        if(operator.equals("+")) {
//            return operand1 + operand2;
//        } else if(operator.equals("-")) {
//            return operand1 - operand2;
//        } else if(operator.equals("*")) {
//            return operand1 * operand2;
//        } else if(operator.equals("/")) {
//                if(operand2 == 0) {
//                    throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
//                }
//            return operand1 / operand2;
//        }
//
//        return 0;

        // enum을 통한 리팩토링
//        return ArithmeticOperatorEnum.calculate(operand1, operator, operand2);

        // interface를 이용한 리팩토링
        return newArithmeticOperators.stream()
                .filter(newArithmeticOperator -> newArithmeticOperator.supports(operator))
                .map(newArithmeticOperator -> newArithmeticOperator.calculate(operand1,operand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));
    }
}
