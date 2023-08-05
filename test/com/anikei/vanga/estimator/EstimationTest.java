package com.anikei.vanga.estimator;

import com.anikei.vanga.estimator.Estimation;
import com.anikei.vanga.util.Fraction;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class EstimationTest {

    @Test
    public void testMerge() {
        Estimation est1 = new Estimation("1");
        est1.mergeProbability(new Fraction(2, 1));
        assertTrue(est1.probability.strongEqual(new Fraction(3, 1)));
    }

}