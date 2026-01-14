package modele.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.Type_vehicule;
import modele.Vehicule;

public class TestVehicule {

    private Vehicule vehicule;

    @Before
    public void setUp() {
        vehicule = new Vehicule(
                "AB-123-CD",
                3.20f,
                1,
                "Alsace",
                Type_vehicule.THERMIQUE
        );
    }

    @Test
    public void testGetImmatriculation() {
        assertEquals("AB-123-CD", vehicule.getImmatriculation());
    }

    @Test
    public void testGetHauteur() {
        assertEquals(3.20f, vehicule.getHauteur(),0.002);
    }

    @Test
    public void testGetIdUtilisateur() {
        assertEquals(1, vehicule.getId_utilisateur());
    }

    @Test
    public void testGetDesignation() {
        assertEquals("Alsace", vehicule.getDésignation());
    }

    @Test
    public void testGetType() {
        assertEquals(Type_vehicule.THERMIQUE, vehicule.getType());
    }


    @Test
    public void testSetHauteur() {
        vehicule.setHauteur(2.10f);
        assertEquals(2.10f, vehicule.getHauteur(), 0.001);
    }

    @Test
    public void testSetDesignation() {
        vehicule.setDésignation("Audi");
        assertEquals("Audi", vehicule.getDésignation());
    }

    @Test
    public void testSetType() {
        vehicule.setType(Type_vehicule.MOTO);
        assertEquals(Type_vehicule.MOTO, vehicule.getType());
    }

   

    @Test
    public void testToString() {
        String attendu = "Alsace - AB-123-CD";
        assertEquals(attendu, vehicule.toString());
    }
}
