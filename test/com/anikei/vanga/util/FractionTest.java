package com.anikei.vanga.util;

import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class FractionTest {

	@Test
	public void equalsTrueTest() {
		Fraction a = new Fraction(0, 1);
		Fraction b = new Fraction(0, 1);
		assertTrue(a.strongEqual(b));
	}

	@Test
	public void equalsFalseTest1() {
		Fraction a = new Fraction(0, 1);
		Fraction b = new Fraction(1, 1);
		assertFalse(a.strongEqual(b));
	}

	@Test
	public void equalsFalseTest2() {
		Fraction a = new Fraction(0, 1);
		Fraction b = new Fraction(0, 2);
		assertFalse(a.strongEqual(b));
	}

	@Test
	public void equalsFalseTest3() {
		Fraction a = new Fraction(1, 3);
		Fraction b = new Fraction(2, 6);
		assertFalse(a.strongEqual(b));
	}

	@Test
	public void addTest1() {
		Fraction a = new Fraction(0, 1);
		Fraction b = new Fraction(0, 1);
		Fraction c = new Fraction(0, 1);
		assertTrue(c.strongEqual(a.add(b)));
	}

	@Test
	public void addTest2() {
		Fraction a = new Fraction(1, 6);
		Fraction b = new Fraction(4, 6);
		Fraction c = new Fraction(5, 6);
		assertTrue(c.strongEqual(a.add(b)));
	}

	@Test
	public void addTest3() {
		Fraction a = new Fraction(1, 362797056);
		Fraction b = new Fraction(60466176, 362797056);
		Fraction c = new Fraction(60466177, 362797056);
        assertTrue(c.strongEqual(a.add(b)));
	}

	@Test(expected = RuntimeException.class)
	public void addInequalException() {
		Fraction a = new Fraction(1, 1);
		Fraction b = new Fraction(2, 2);
		a.add(b);
	}

	@Test
	public void multiplyTest1() {
		Fraction a = new Fraction(0, 1);
		Fraction b = new Fraction(0, 1);
		Fraction c = new Fraction(0, 1);
		assertTrue(c.strongEqual(a.multiply(b)));
	}

	@Test
	public void multiplyTest2() {
		Fraction a = new Fraction(1, 6);
		Fraction b = new Fraction(4, 6);
		Fraction c = new Fraction(4, 36);
        assertTrue(c.strongEqual(a.multiply(b)));
	}

	@Test
	public void multiplyTest3() {
		Fraction a = new Fraction(1, 362797056);
		Fraction b = new Fraction(60466176, 362797056);
		Fraction c = new Fraction(new BigInteger("60466176"), new BigInteger("131621703842267136"));
        assertTrue(c.strongEqual(a.multiply(b)));
	}

    @Test
    public void testToString() {
        Fraction a = new Fraction(1, 362797056);
        assertTrue("1/362797056".equals(a.toString()));
    }

    @Ignore
    @Test
    public void testToDecimal() {
        Fraction a = new Fraction(1, 4);
		BigDecimal value = new BigDecimal(Float.toString(0.25F));
		//FIXME:
		//		assertTrue(value.equals(a.toDecimal()));
    }

}