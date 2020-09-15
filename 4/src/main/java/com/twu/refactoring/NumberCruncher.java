package com.twu.refactoring;

public class NumberCruncher {
    private final int[] numbers;

    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    public int countEven() {
        return getResult(0);
    }

    public int countOdd() {
        return getResult(1);
    }

    private int getResult(int num) {
        int count = 0;
        for (int number : numbers) {
            if (number % 2 == num) count++;
        }
        return count;
    }


    public int countPositive() {
        int count = 0;
        for (int number : numbers) {
            if (number >= 0) count++;
        }
        return count;
    }

    public int countNegative() {
        int count = 0;
        for (int number : numbers) {
            if (number < 0) count++;
        }
        return count;
    }
}
