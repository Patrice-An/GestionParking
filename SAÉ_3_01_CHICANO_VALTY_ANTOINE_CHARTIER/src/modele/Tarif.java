package modele;

public class Tarif {

	private float durée;
	private float majoration;
	
	public Tarif(float durée, float majoration) {
		super();
		this.durée = durée;
		this.majoration = majoration;
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
