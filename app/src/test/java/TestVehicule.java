import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import modele.Type_vehicule;
import modele.Utilisateur;
import modele.Vehicule;

public class TestVehicule {
    private Vehicule vThermique;
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

        vThermique = new Vehicule("ABC123", 1.5f, user.getid(), "Toyota", Type_vehicule.THERMIQUE);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("ABC123", vThermique.getImmatriculation());
        assertEquals("Toyota", vThermique.getDesignation());
        assertEquals(Type_vehicule.THERMIQUE, vThermique.getType());
        assertEquals(1, vThermique.getidUtilisateur());
        vThermique.setDesignation("Totoyota");
        vThermique.setHauteur(2.0f);
        vThermique.setType(Type_vehicule.ELECTRIQUE);
        assertEquals("Totoyota", vThermique.getDesignation());
        assertEquals(Type_vehicule.ELECTRIQUE, vThermique.getType());
        
    }
}
