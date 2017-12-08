package com.ione;

import javax.persistence.*;

@Entity
@Table(name = "Cars", schema = "Lab_5_b_Hibernate", catalog = "")
@IdClass(CarsEntityPK.class)
public class CarsEntity {
    private int idCars;
    private String seler;
    private int avtosalonIdAvtosalon;

    @Id
    @Column(name = "idCars", nullable = false)
    public int getIdCars() {
        return idCars;
    }

    public void setIdCars(int idCars) {
        this.idCars = idCars;
    }

    @Basic
    @Column(name = "seler", nullable = true, length = 45)
    public String getSeler() {
        return seler;
    }

    public void setSeler(String seler) {
        this.seler = seler;
    }

    @Id
    @Column(name = "Avtosalon_idAvtosalon", nullable = false)
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

        CarsEntity that = (CarsEntity) o;

        if (idCars != that.idCars) return false;
        if (avtosalonIdAvtosalon != that.avtosalonIdAvtosalon) return false;
        if (seler != null ? !seler.equals(that.seler) : that.seler != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCars;
        result = 31 * result + (seler != null ? seler.hashCode() : 0);
        result = 31 * result + avtosalonIdAvtosalon;
        return result;
    }
}
