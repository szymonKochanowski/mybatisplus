package com.springbootmybatis.service.impl;

import com.springbootmybatis.service.Stars;

public class Draw implements Stars {

    private static final String STAR = "*";
    private static final String DASH = "-";
    private static final String NEW_LINE = "\n";


    @Override
    public String drawSquare(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                stringBuilder.append(STAR);
            }
            stringBuilder.append(NEW_LINE);
        }
        return stringBuilder.toString();
    }

    @Override
    public String drawRectangle(int n, int m) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int z = 0; z < n; z++) {
                stringBuilder.append(STAR);
            }
            stringBuilder.append(NEW_LINE);
        }
        return stringBuilder.toString();
    }

    @Override
    public String drawIsoscelesTriangle(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        int amountStarsInRow = 1;
        int amountDashInRow = n - 1;
        for (int i = 0; i < n; i++) {
            for (int z = 0; z < amountDashInRow; z++) {
                stringBuilder.append(DASH);
            }
            for (int s = 0; s < amountStarsInRow; s++) {
                stringBuilder.append(STAR);
            }
            amountDashInRow -= 1;
            amountStarsInRow += 2;
            stringBuilder.append(NEW_LINE);
        }
        return stringBuilder.toString();
    }

    @Override
    public String drawDiamond(int n) {
        isOddNumber(n);
        int numberOfDownRows = n / 2;
        String upPartOfDiamond = drawIsoscelesTriangle(Integer.parseInt(String.valueOf(n / 2 + 1)));
        StringBuilder downPartOfDiamnond = new StringBuilder(upPartOfDiamond);
        int amountStarsInRow = n - 2;
        int amountDashInRow = 1;
        for (int i = 0; i < numberOfDownRows; i++) {
            for (int z = 0; z < amountDashInRow; z++) {
                downPartOfDiamnond.append(DASH);
            }
            for (int s = 0; s < amountStarsInRow; s++) {
                downPartOfDiamnond.append(STAR);
            }
            amountDashInRow += 1;
            amountStarsInRow -= 2;
            downPartOfDiamnond.append(NEW_LINE);
        }
        return downPartOfDiamnond.toString();
    }

    private boolean isOddNumber(int n) {
        if (n % 2 != 0) {
            return true;
        } else {
            throw new IllegalArgumentException("This is not odd number! Please try again with any odd number.");
        }
    }


    @Override
    public String drawRectangleTriangle(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= n; i++) {
            for (int a = 0; a < i; a++) {
                stringBuilder.append(STAR);
            }
            stringBuilder.append(NEW_LINE);
        }
        return stringBuilder.toString();
    }

}
