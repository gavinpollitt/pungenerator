package uk.gav.pun.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class Pun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String pun;

    //Required for De-serialisation
    public Pun() {

    }

    public Pun(final String pun) {
        this.pun = pun;
    }

    @JsonIgnore
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPun() {
        return pun;
    }

    public void setPun(String pun) {
        this.pun = pun;
    }

}
