package com.my;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "avtosalon", schema = "db_hibernate_vanyok", catalog = "")
public class AvtosalonEntity {
    private String avtosalon;
    private Collection<VlasnykEntity> vlasnyksByAvtosalon;

    public AvtosalonEntity(){}
    public AvtosalonEntity(String c){
        avtosalon=c;
    }

    @Id
    @Column(name = "AvtosalonName", nullable = false, length = 25)
    public String getAvtosalon() {
        return avtosalon;
    }

    public void setAvtosalon(String avtosalon) {
        this.avtosalon = avtosalon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvtosalonEntity that = (AvtosalonEntity) o;

        if (avtosalon != null ? !avtosalon.equals(that.avtosalon) : that.avtosalon != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return avtosalon != null ? avtosalon.hashCode() : 0;
    }

    @OneToMany(mappedBy = "avtosalonByAvtosalon")
    public Collection<VlasnykEntity> getVlasnyksByAvtosalon() {
        return vlasnyksByAvtosalon;
    }

    public void setVlasnyksByAvtosalon(Collection<VlasnykEntity> vlasnyksByAvtosalon) {
        this.vlasnyksByAvtosalon = vlasnyksByAvtosalon;
    }
}
