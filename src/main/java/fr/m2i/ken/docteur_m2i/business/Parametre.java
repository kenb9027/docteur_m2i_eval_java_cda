package fr.m2i.ken.docteur_m2i.business;

public class Parametre {

    private Long id;
    private String nom;

    public Parametre() {
    }

    public Parametre(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Param. : " +
                "id =" + id +
                ", nom ='" + nom + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
