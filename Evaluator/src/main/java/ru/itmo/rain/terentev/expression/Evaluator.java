package ru.itmo.rain.terentev.expression;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Evaluator {
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        List<BigDecimal> a = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            try {
                a.add(sc.nextBigDecimal());
            } catch (InputMismatchException e) {
                System.err.println("Invalid arguments: Number expected");
                return;
            }
        }
        try {
            System.out.println(evaluate(a));
        } catch (ArithmeticException e) {
            System.err.println("Invalid arguments: / by zero");
        }
    }

    private static BigDecimal evaluate(List<BigDecimal> a) throws ArithmeticException {
        return a.get(0)
                .add(a.get(1))
                .divide(a.get(2)
                                .add(a.get(3)),
                        3,
                        RoundingMode.CEILING);
    }


}
