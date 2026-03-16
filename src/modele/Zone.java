package modele;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.dao.DaoTarif;

public class Zone {

    private int id;
    private String couleur;
    private List<Tarif> tarifs;

    public Zone(int id, String couleur) {
        this.id = id;
        this.couleur = couleur;
        try {
            this.tarifs = new DaoTarif().findByZone(id);
        } catch (SQLException _) {
            this.tarifs = new ArrayList<>();
        }
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

    public List<Tarif> getTarifs() {
        return tarifs;
    }

    public void setTarifs(List<Tarif> tarifs) {
        this.tarifs = tarifs;
    }

    @Override
    public String toString() {
        return couleur;
    }

}
