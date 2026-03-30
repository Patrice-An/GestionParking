import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import modele.Parking;
import modele.Vehicule;
import modele.Type_vehicule;
import modele.Utilisateur;
import modele.Transport;
import modele.Type_Transport;
import modele.Tarif;

public class TestParking {

    private Parking parking;
    private Vehicule vThermique;
    private Vehicule vElectrique;
    private Vehicule vMoto;
    private Transport transport;
    private Utilisateur user;

    @Before
    public void setUp() {
    	user = new Utilisateur(
                1, 
                "Doe", 
                "John", 
                "0123456789", 
                LocalDate.of(1990, 1, 1), 
                "john.doe@example.com", 
                "password123"
        );
        parking = new Parking(1, "123 Rue Exemple", "Parking Central", 2.5f, 10, 2, 3);

        vThermique = new Vehicule("ABC123", 1.5f, user.getid(), "Toyota", Type_vehicule.THERMIQUE);
        vElectrique = new Vehicule("XYZ987", 2.0f, user.getid(), "Tesla", Type_vehicule.ELECTRIQUE);
        vMoto = new Vehicule("MOTO1", 1.8f, user.getid(), "Yamaha", Type_vehicule.MOTO);

        transport = new Transport(1, "Station 1", "Ligne A", Type_Transport.BUS);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, parking.getidParking());
        assertEquals("Parking Central", parking.getNom());
        parking.setNom("Parking Nord");
        assertEquals("Parking Nord", parking.getNom());

        assertEquals("123 Rue Exemple", parking.getAdresse());
        parking.setAdresse("456 Avenue Test");
        assertEquals("456 Avenue Test", parking.getAdresse());

        assertEquals(2.5f, parking.getHauteur(), 0.001f);
        parking.setHauteur(3.0f);
        assertEquals(3.0f, parking.getHauteur(), 0.001f);

        assertEquals(10, parking.getplacesMax());
        parking.setplacesMax(12);
        assertEquals(12, parking.getplacesMax());
    }

    @Test
    public void testAddVehiculeAndGetters() {
        parking.addVehicule(vThermique);
        parking.addVehicule(vElectrique);
        parking.addVehicule(vMoto);

        assertTrue(parking.getVehicules().contains(vThermique));
        assertTrue(parking.getVehicules().contains(vElectrique));
        assertTrue(parking.getVehicules().contains(vMoto));

        assertTrue(parking.getVoituresThermiques().contains(vThermique));
        assertTrue(parking.getVoituresElectriques().contains(vElectrique));
        assertTrue(parking.getMotos().contains(vMoto));
    }

    @Test
    public void testRemoveVehiculeAndVider() {
        parking.addVehicule(vThermique);
        parking.addVehicule(vElectrique);

        parking.removeVehicule(vThermique);
        assertFalse(parking.getVehicules().contains(vThermique));

        try {
            parking.removeVehicule(vThermique);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Impossible de retirer un véhicule absent du parking", e.getMessage());
        }

        parking.vider();
        assertTrue(parking.getVehicules().isEmpty());
    }

    @Test
    public void testEstRelais() {
        assertFalse(parking.estRelais());
        parking.addTransport(transport);
        assertTrue(parking.estRelais());
    }

    @Test
    public void testNbPlaceLibres() {
        parking.addVehicule(vThermique);
        parking.addVehicule(vElectrique);
        parking.addVehicule(vMoto);

        int expected = parking.getplacesMax() - 3;
        assertEquals(expected, parking.nbPlaceLibres());
    }

    @Test
    public void testAddTarif() {
        Tarif t = new Tarif(1, 2.5f, 10);
        parking.addTarif(t);
        assertTrue(parking.getTarifs().contains(t));
    }

    @Test
    public void testToString() {
        String expected = "Parking Central - 123 Rue Exemple";
        assertEquals(expected, parking.toString());
    }

    @Test
    public void testPlaceMaxExceptions() {
        try {
            parking.setplacesMaxMoto(20);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Le nombre de place incorrect"));
        }

        try {
            parking.setplacesMaxElectrique(20);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Le nombre de place incorrect"));
        }
    }
}
