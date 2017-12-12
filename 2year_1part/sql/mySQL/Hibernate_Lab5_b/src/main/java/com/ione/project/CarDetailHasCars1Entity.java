package com.ione.project;

import javax.persistence.*;

@Entity
@Table(name = "Car_Detail_has_Cars1", schema = "Lab_5_b_Hibernate", catalog = "")
@IdClass(CarDetailHasCars1EntityPK.class)
public class CarDetailHasCars1Entity {
    private int carDetailIdCarDetail;
    private int carsIdCars;

    @Id
    @Column(name = "Car_Detail_idCar_Detail", nullable = false)
    public int getCarDetailIdCarDetail() {
        return carDetailIdCarDetail;
    }

    public void setCarDetailIdCarDetail(int carDetailIdCarDetail) {
        this.carDetailIdCarDetail = carDetailIdCarDetail;
    }

    @Id
    @Column(name = "Cars_idCars", nullable = false)
    public int getCarsIdCars() {
        return carsIdCars;
    }

    public void setCarsIdCars(int carsIdCars) {
        this.carsIdCars = carsIdCars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarDetailHasCars1Entity that = (CarDetailHasCars1Entity) o;

        if (carDetailIdCarDetail != that.carDetailIdCarDetail) return false;
        if (carsIdCars != that.carsIdCars) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = carDetailIdCarDetail;
        result = 31 * result + carsIdCars;
        return result;
    }
}
