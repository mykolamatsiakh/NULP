package com.ione.project;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CarDetailHasCars1EntityPK implements Serializable {
    private int carDetailIdCarDetail;
    private int carsIdCars;

    @Column(name = "Car_Detail_idCar_Detail", nullable = false)
    @Id
    public int getCarDetailIdCarDetail() {
        return carDetailIdCarDetail;
    }

    public void setCarDetailIdCarDetail(int carDetailIdCarDetail) {
        this.carDetailIdCarDetail = carDetailIdCarDetail;
    }

    @Column(name = "Cars_idCars", nullable = false)
    @Id
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

        CarDetailHasCars1EntityPK that = (CarDetailHasCars1EntityPK) o;

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
