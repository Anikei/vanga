import org.junit.Test;

import static org.junit.Assert.*;

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
		a.add(b);
		assertTrue(c.strongEqual(a));
	}

	@Test
	public void addTest2() {
		Fraction a = new Fraction(1, 6);
		Fraction b = new Fraction(4, 6);
		Fraction c = new Fraction(5, 6);
		a.add(b);
		assertTrue(c.strongEqual(a));
	}

	@Test
	public void addTest3() {
		Fraction a = new Fraction(1, 362797056);
		Fraction b = new Fraction(60466176, 362797056);
		Fraction c = new Fraction(60466177, 362797056);
		a.add(b);
		assertTrue(c.strongEqual(a));
	}

}