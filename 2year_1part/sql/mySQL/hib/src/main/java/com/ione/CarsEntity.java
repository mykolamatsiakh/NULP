package com.ione;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Cars", schema = "Lab_5_b_Hibernate", catalog = "")
public class CarsEntity {
    private int idCars;
    private String seler;
    private AvtosalonEntity avtosalon;
    private Set<CarDetailEntity> details;

    @Id
    @Column(name = "idCars", nullable = false)
    public int getIdCars() {
        return idCars;
    }

    public CarsEntity(){}

    public CarsEntity(String a){
        seler =a;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarsEntity that = (CarsEntity) o;

        if (idCars != that.idCars) return false;
        if (seler != null ? !seler.equals(that.seler) : that.seler != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCars;
        result = 31 * result + (seler != null ? seler.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Avtosalon_idAvtosalon", referencedColumnName = "idAvtosalon", nullable = false)
    public AvtosalonEntity getAvtosalon() {
        return avtosalon;
    }

    public void setAvtosalon(AvtosalonEntity avtosalon) {
        this.avtosalon = avtosalon;
    }

    @ManyToMany
    @JoinTable(name = "Car_Detail_has_Cars1", catalog = "", schema = "Lab_5_b_Hibernate", joinColumns = @JoinColumn(name = "Cars_idCars", referencedColumnName = "idCars", nullable = false), inverseJoinColumns = @JoinColumn(name = "Car_Detail_idCar_Detail", referencedColumnName = "idCar_Detail", nullable = false))
    public Set<CarDetailEntity> getDetails() {
        return details;
    }

    public void setDetails(Set<CarDetailEntity> details) {
        this.details = details;
    }
}
