package modele.test;


import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import modele.Type_vehicule;
import modele.Utilisateur;
import modele.Vehicule;

public class testUtilisateur {


    private Utilisateur utilisateur;

    @Before
    public void setUp() {
        utilisateur = new Utilisateur(
                1, 
                "Doe", 
                "John", 
                "0123456789", 
                LocalDate.of(1990, 1, 1), 
                "john.doe@example.com", 
                "password123"
        );
    }

    @Test
    public void testGetters() {
        assertEquals(1, utilisateur.getid());
        assertEquals("Doe", utilisateur.getNom());
        assertEquals("John", utilisateur.getPrenom());
        assertEquals("0123456789", utilisateur.getTel());
        assertEquals("john.doe@example.com", utilisateur.getMail());
        assertEquals(LocalDate.of(1990, 1, 1), utilisateur.getNaissance());
        assertEquals("password123", utilisateur.getPassword());
    }

    @Test
    public void testSetters() {
        utilisateur.setNom("Smith");
        assertEquals("Smith", utilisateur.getNom());

        utilisateur.setPrenom("Jane");
        assertEquals("Jane", utilisateur.getPrenom());

        utilisateur.setTel("0987654321");
        assertEquals("0987654321", utilisateur.getTel());

        utilisateur.setMail("jane.smith@example.com");
        assertEquals("jane.smith@example.com", utilisateur.getMail());

        LocalDate newDate = LocalDate.of(2000, 12, 31);
        utilisateur.setNaissance(newDate);
        assertEquals(newDate, utilisateur.getNaissance());
    }

    @Test
    public void testAddVehicule() {
        Vehicule v1 = new Vehicule("ABC123", 2.5f, utilisateur.getid(), "Toyota", Type_vehicule.THERMIQUE);
        utilisateur.addVehicule(v1);
        Set<Vehicule> vehicules = utilisateur.getVehicules();
        assertTrue(vehicules.contains(v1));
        assertEquals(1, vehicules.size());
    }

    @Test
    public void testToString() {
        assertEquals("John Doe", utilisateur.toString());
    }
}
