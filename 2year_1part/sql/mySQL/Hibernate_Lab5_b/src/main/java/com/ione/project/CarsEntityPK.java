package com.ione.project;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CarsEntityPK implements Serializable {
    private int idCars;
    private int avtosalonIdAvtosalon;

    @Column(name = "idCars", nullable = false)
    @Id
    public int getIdCars() {
        return idCars;
    }

    public void setIdCars(int idCars) {
        this.idCars = idCars;
    }

    @Column(name = "Avtosalon_idAvtosalon", nullable = false)
    @Id
    public int getAvtosalonIdAvtosalon() {
        return avtosalonIdAvtosalon;
    }

    public void setAvtosalonIdAvtosalon(int avtosalonIdAvtosalon) {
        this.avtosalonIdAvtosalon = avtosalonIdAvtosalon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarsEntityPK that = (CarsEntityPK) o;

        if (idCars != that.idCars) return false;
        if (avtosalonIdAvtosalon != that.avtosalonIdAvtosalon) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCars;
        result = 31 * result + avtosalonIdAvtosalon;
        return result;
    }
}
