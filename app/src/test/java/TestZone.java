import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import modele.Zone;
import modele.Tarif;

public class TestZone {

    private Zone zone;

    @Before
    public void setUp() throws SQLException {
        zone = new Zone(1, "Rouge");
    }

    @After
    public void tearDown() {
        zone = null;
    }

    @Test
    public void testGetters() {
        assertEquals(1, zone.getId());
        assertEquals("Rouge", zone.getCouleur());
    }

    @Test
    public void testAddTarifs() {
        Tarif t2 = new Tarif(2, 20.0f, 5);
        zone.addTarifs(t2);
        List<Tarif> tarifs = zone.getTarifs();
        assertTrue(tarifs.contains(t2));
    }

    @Test
    public void testSetTarifs() {
        List<Tarif> newTarifs = Arrays.asList(new Tarif(3, 30.0f, 5));
        zone.setTarifs(newTarifs);
        assertEquals(newTarifs, zone.getTarifs());
    }

    @Test
    public void testToString() {
        assertEquals("Rouge", zone.toString());
    }
}
