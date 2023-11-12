package edu.hw2;

import edu.hw2.Task1.Expr.*;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    public void testConstant() {
        Constant number = new Constant(5);

        assertThat(number.evaluate()).isEqualTo(5);
    }

    @Test
    public void testNegate() {
        Negate number = new Negate(new Constant(5));

        assertThat(number.evaluate()).isEqualTo(-5);
    }

    @Test
    public void testExponent() {
        Exponent number = new Exponent(new Constant(5), 2);

        assertThat(number.evaluate()).isEqualTo(25);
    }

    @Test
    public void testAddition() {
        Addition number = new Addition(new Constant(5), new Constant(5));

        assertThat(number.evaluate()).isEqualTo(10);
    }

    @Test
    public void testMultiplication() {
        var number = new Multiplication(new Constant(5), new Constant(5));

        assertThat(number.evaluate()).isEqualTo(25);
    }

    @Test
    public void testAllFunctional() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        assertThat(res.evaluate()).isEqualTo(37);
    }
}
