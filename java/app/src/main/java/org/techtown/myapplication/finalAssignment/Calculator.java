package org.techtown.myapplication.finalAssignment;

public class Calculator implements CalculatorInterface {

    //생성자
    public Calculator() {
    }

    @Override
    public int sum(int first, int second) {
        return first + second;
    }

    @Override
    public int minus(int first, int second) {
        return first - second;
    }

}
