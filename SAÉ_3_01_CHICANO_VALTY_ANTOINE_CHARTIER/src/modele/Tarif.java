package modele;

public class Tarif {

	private int id;
	private float durée;
	private float majoration;
	
	public Tarif(int id, float durée, float majoration) {
		super();
		this.id = id;
		this.durée = durée;
		this.majoration = majoration;
	}

	public int getId() {
		return id;
	}

	public float getDurée() {
		return durée;
	}

	public void setDurée(float durée) {
		this.durée = durée;
	}

	public float getMajoration() {
		return majoration;
	}

	public void setMajoration(float majoration) {
		this.majoration = majoration;
	}
	
}
