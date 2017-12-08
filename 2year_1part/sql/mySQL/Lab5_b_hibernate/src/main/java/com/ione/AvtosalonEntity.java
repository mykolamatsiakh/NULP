package com.ione;

import javax.persistence.*;

@Entity
@Table(name = "Avtosalon", schema = "Lab_5_b_Hibernate", catalog = "")
public class AvtosalonEntity {
    private int idAvtosalon;
    private String name;
    private String ownerName;

    @Id
    @Column(name = "idAvtosalon", nullable = false)
    public int getIdAvtosalon() {
        return idAvtosalon;
    }

    public void setIdAvtosalon(int idAvtosalon) {
        this.idAvtosalon = idAvtosalon;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ownerName", nullable = true, length = 50)
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvtosalonEntity that = (AvtosalonEntity) o;

        if (idAvtosalon != that.idAvtosalon) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (ownerName != null ? !ownerName.equals(that.ownerName) : that.ownerName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAvtosalon;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ownerName != null ? ownerName.hashCode() : 0);
        return result;
    }
}
