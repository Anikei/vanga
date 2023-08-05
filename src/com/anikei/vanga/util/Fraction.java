package com.anikei.vanga.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;

public class Fraction {
    private final BigInteger numeral;
    private final BigInteger denominator;

    Fraction(BigInteger numeral, BigInteger denominator) {
        this.numeral = numeral;
        this.denominator = denominator;
    }

    public Fraction(int numeral, int denominator) {
        this.numeral = BigInteger.valueOf(numeral);
        this.denominator = BigInteger.valueOf(denominator);
    }

    public Fraction add(Fraction fraction) {
        if (Objects.equals(this.denominator, fraction.denominator)) {
            BigInteger n = this.numeral.add(fraction.numeral);
            return new Fraction(n, this.denominator);
        } else {
            throw new RuntimeException(); //this should never occur during real work
        }
    }

    public Fraction multiply(Fraction fraction) {
        BigInteger n = this.numeral.multiply(fraction.numeral);
        BigInteger d = this.denominator.multiply(fraction.denominator);
        return new Fraction(n, d);
    }

    public boolean strongEqual(Fraction b) {
        return ((this.numeral.equals(b.numeral)) &&
                (this.denominator.equals(b.denominator)));
    }

    @Override
    public String toString() {
        return "" + numeral + "/" + denominator + "";
    }

    public BigDecimal toDecimal() {
        BigDecimal n = new BigDecimal(numeral);
        BigDecimal d = new BigDecimal(denominator);
        return n.divide(d, 50, RoundingMode.HALF_UP);
    }

}