package com.example.practice.calculator.domain;

import java.util.Arrays;

public enum EnumArithmeticOperator {
    ADDITION("+") {
        @Override
        public int arithmeticCalculator(int operand1, int operand2) {
            return operand1 + operand2;
        }
    },
    SUBTRACTION("-"){
        @Override
        public int arithmeticCalculator(int operand1, int operand2) {
            return operand1 - operand2;
        }
    },
    MULTIPLICATION("*"){
        @Override
        public int arithmeticCalculator(int operand1, int operand2) {
            return operand1 * operand2;
        }
    },
    DIVISION("/") {
        @Override
        public int arithmeticCalculator(int operand1, int operand2) {
            if(operand2 == 0) {
                throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
            }

            return operand1 / operand2;
        }
    };

    private final String operator;

    EnumArithmeticOperator(String operator) {
        this.operator = operator;
    }
    protected abstract int arithmeticCalculator(final int operand1, final int operand2);

    public static int calculate(int operand1, String operator, int operand2) {
        EnumArithmeticOperator arithmeticOperator = Arrays.stream(values())
                                                    .filter(value -> value.operator.equals(operator))
                                                    .findFirst()
                                                    .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));

        return arithmeticOperator.arithmeticCalculator(operand1, operand2);
    }
}
