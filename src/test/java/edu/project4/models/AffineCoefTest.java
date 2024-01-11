package edu.project4.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AffineCoefTest {

    @Test
    @DisplayName("create affine coef")
    public void create() {
        AffineCoef affineCoef = AffineCoef.create();
        Assertions.assertTrue(
            affineCoef.a() * affineCoef.a() + affineCoef.d() * affineCoef.d() < 1
        );
        Assertions.assertTrue(
            affineCoef.b() * affineCoef.b() + affineCoef.e() * affineCoef.e() < 1
        );
        Assertions.assertTrue(
            affineCoef.a() * affineCoef.a()
                + affineCoef.b() * affineCoef.b()
                + affineCoef.d() * affineCoef.d()
                + affineCoef.e() * affineCoef.e()
                < 1 + (affineCoef.a() * affineCoef.e() - affineCoef.b() * affineCoef.d())
                * (affineCoef.a() * affineCoef.e() - affineCoef.b() * affineCoef.d())
        );
    }
}
