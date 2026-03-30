import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import modele.Tarif;

public class TestTarif {

    private Tarif tarif;

    @Before
    public void setUp() {
        tarif = new Tarif(1, 2.5f, 10);
    }

    @Test
    public void testGetters() {
        assertEquals(1, tarif.getId());
        assertEquals(2.5f, tarif.getDuree(), 0.001f);
        assertEquals(10, tarif.getMajoration(), 0.001f);
    }

    @Test
    public void testSetDurée() {
        tarif.setDuree(3.75f);
        assertEquals(3.75f, tarif.getDuree(), 0.001f);
    }

    @Test
    public void testSetMajoration() {
        tarif.setMajoration(20);
        assertEquals(20, tarif.getMajoration(), 0.001f);
    }

    @Test
    public void testToString() {
        // 2.5h = 2H30M
        String expected = "Durée : 2H30M, tarif : 10€";
        assertEquals(expected, tarif.toString());

        tarif.setDuree(1.75f);
        tarif.setMajoration(15);
        expected = "Durée : 1H45M, tarif : 15€";
        assertEquals(expected, tarif.toString());
    }
}
