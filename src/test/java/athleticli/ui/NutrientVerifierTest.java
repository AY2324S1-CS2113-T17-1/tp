package athleticli.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import athleticli.parser.NutrientVerifier;

class NutrientVerifierTest {

    @Test
    void verify_inputApprovedNutrients_expectTrue() {
        assertTrue(NutrientVerifier.verify("fat"));
    }
    @Test
    void verify_inputUnapprovedNutrients_expectFalse() {
        assertFalse(NutrientVerifier.verify("Vitamin A"));
    }
}
