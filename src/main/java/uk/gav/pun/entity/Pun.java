package uk.gav.pun.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String pun;

    //Required for De-serialisation
    public Pun() {

    }

    public Pun(final String pun) {
        this.pun = pun;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPun() {
        return pun;
    }

    public void setPun(String pun) {
        this.pun = pun;
    }

}
