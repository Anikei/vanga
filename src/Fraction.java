import java.math.BigInteger;
import java.util.Objects;

public class Fraction {
	private BigInteger numeral;
	private BigInteger denominator;

	Fraction(BigInteger numeral, BigInteger denominator) {
		this.numeral = numeral;
		this.denominator = denominator;
	}

	public Fraction(int numeral, int denominator) {
		this.numeral = BigInteger.valueOf(numeral);
		this.denominator = BigInteger.valueOf(denominator);
	}

	void add(Fraction fraction) {
		if (Objects.equals(this.denominator, fraction.denominator)) {
			this.numeral = this.numeral.add(fraction.numeral);
		} else {
			throw new RuntimeException(); //this should never occur during real work
		}
	}

	void multiply(Fraction fraction) {
		if (Objects.equals(this.denominator, fraction.denominator)) {
			this.numeral = this.numeral.multiply(fraction.numeral);
			this.denominator = this.denominator.multiply(fraction.denominator);
		} else {
			throw new RuntimeException(); //this should never occur during real work
		}
	}

	boolean strongEqual(Fraction b) {
		return ((this.numeral.equals(b.numeral) ) &&
		(this.denominator.equals(b.denominator)));
	}

}