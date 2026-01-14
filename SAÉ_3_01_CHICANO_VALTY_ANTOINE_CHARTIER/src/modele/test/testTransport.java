package modele.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.Transport;
import modele.Type_Transport; // Assurez-vous que cet enum existe

public class testTransport {

    private Transport transport;

    @Before
    public void setUp() {
        transport = new Transport(1, "123 Rue Exemple", "Ligne A", Type_Transport.BUS);
    }

    @Test
    public void testGetters() {
        assertEquals(1, transport.getId());
        assertEquals("123 Rue Exemple", transport.getAdresse());
        assertEquals("Ligne A", transport.getLigne());
        assertEquals(Type_Transport.BUS, transport.getType());
    }

    @Test
    public void testSetAdresse() {
        transport.setAdresse("456 Avenue Test");
        assertEquals("456 Avenue Test", transport.getAdresse());
    }

    @Test
    public void testSetLigne() {
        transport.setLigne("Ligne B");
        assertEquals("Ligne B", transport.getLigne());
    }

    @Test
    public void testSetType() {
        transport.setType(Type_Transport.METRO);
        assertEquals(Type_Transport.METRO, transport.getType());
    }
}
