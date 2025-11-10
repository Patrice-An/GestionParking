package modele;

import static org.junit.Assert.*;

import org.junit.*;

public class testParking {

	private Parking p1 ;
	private Vehicule vt;
	private Vehicule ve;
	private Vehicule moto;
	
	@Before
	public void setup() {
		p1 = new Parking("5 rue du chemin", 100, 10, 10, 3.0f);
		vt = new Vehicule("ab-123-cd", Type_vehicule.VOITURE_THERMIQUE, 2.0f);
		ve = new Vehicule("ef-456-gh", Type_vehicule.VOITURE_ELECTRIQUE, 2.5f);
		moto = new Vehicule("ij-789-kl", Type_vehicule.MOTO, 1.0f);
	}
	
	@After
	public void tearDown() {
		p1.vider();
	}
	
	@Test
	public void testAjoutVehicule() {
		
		p1.addVehicule(vt);
		assertEquals(p1.getVehicules().size(), 1);
		p1.addVehicule(ve);
		assertEquals(p1.getVehicules().size(), 2);
		p1.addVehicule(moto);
		assertEquals(p1.getVehicules().size(), 3);
	}
	
	@Test
	public void testRetirerVehiculeParkingNonVide() {
		p1.addVehicule(vt);
		assertEquals(p1.getVehicules().size(), 1);
		p1.removeVehicule(vt);
		assertEquals(p1.getVehicules().size(), 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRetirerUneVehiculeParkingVide() {
		p1.addVehicule(vt);
		assertEquals(p1.getVehicules().size(), 1);
		p1.removeVehicule(ve);
	}
	
	@Test
	public void testViderParking() {
		p1.addVehicule(ve);
		p1.addVehicule(vt);
		assertEquals(p1.getVehicules().size(), 2);
		p1.vider();
		assertEquals(p1.getVehicules().size(), 0);
	}

}
