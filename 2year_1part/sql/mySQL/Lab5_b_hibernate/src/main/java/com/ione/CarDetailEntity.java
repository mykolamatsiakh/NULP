package com.ione;

import javax.persistence.*;

@Entity
@Table(name = "Car_Detail", schema = "Lab_5_b_Hibernate", catalog = "")
public class CarDetailEntity {
    private int idCarDetail;
    private Integer maxSpeed;
    private Integer maxDistanc;
    private String model;
    private String clazz;

    @Id
    @Column(name = "idCar_Detail", nullable = false)
    public int getIdCarDetail() {
        return idCarDetail;
    }

    public void setIdCarDetail(int idCarDetail) {
        this.idCarDetail = idCarDetail;
    }

    @Basic
    @Column(name = "maxSpeed", nullable = true)
    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Basic
    @Column(name = "maxDistanc", nullable = true)
    public Integer getMaxDistanc() {
        return maxDistanc;
    }

    public void setMaxDistanc(Integer maxDistanc) {
        this.maxDistanc = maxDistanc;
    }

    @Basic
    @Column(name = "model", nullable = true, length = 45)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "class", nullable = true, length = 45)
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarDetailEntity that = (CarDetailEntity) o;

        if (idCarDetail != that.idCarDetail) return false;
        if (maxSpeed != null ? !maxSpeed.equals(that.maxSpeed) : that.maxSpeed != null) return false;
        if (maxDistanc != null ? !maxDistanc.equals(that.maxDistanc) : that.maxDistanc != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (clazz != null ? !clazz.equals(that.clazz) : that.clazz != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCarDetail;
        result = 31 * result + (maxSpeed != null ? maxSpeed.hashCode() : 0);
        result = 31 * result + (maxDistanc != null ? maxDistanc.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
        return result;
    }
}
