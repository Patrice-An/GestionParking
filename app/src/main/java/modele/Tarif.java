package modele;

public class Tarif {

	private final int id;
	private float duree;
	private int majoration;
	
	public Tarif(int id, float duree, int majoration) {
		super();
		this.id = id;
		this.duree = duree;
		this.majoration = majoration;
	}

	public int getId() {
		return id;
	}

	public float getDuree() {
		return duree;
	}

	public void setDuree(float duree) {
		this.duree = duree;
	}

	public float getMajoration() {
		return majoration;
	}

	public void setMajoration(int majoration) {
		this.majoration = majoration;
	}

	@Override
	public String toString() {
		String dureeAffichage = String.valueOf((int)Math.floor(duree)) + "H" + String.valueOf((int)((duree - Math.floor(duree))*60f)) + "M";
		return "Duree : " + dureeAffichage + ", tarif : " + majoration + "€";
	}
	
}
