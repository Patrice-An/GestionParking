package modele;

import java.util.HashSet;
import java.util.Set;

public class Zone {

    private int id;
    private String couleur;
    private Set<Tarif> tarifs;

    public Zone(int id, String couleur) {
        this.id = id;
        this.couleur = couleur;
        this.tarifs = new HashSet<Tarif>();
    }

    public int getId() {
        return id;
    }

    public String getCouleur() {
        return couleur;
    }

    public void addTarifs(Tarif t) {
        this.tarifs.add(t);
    }

    public Set<Tarif> getTarifs() {
        return tarifs;
    }

}
