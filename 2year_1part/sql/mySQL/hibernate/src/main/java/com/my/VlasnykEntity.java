package com.my;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vlasnyk", schema = "db_hibernate_vanyok", catalog = "")
public class VlasnykEntity {
    private int idVlasnyk;
    private String surname;
    private String name;
    private String email;
    private AvtosalonEntity avtosalonByAvtosalon;
    private List<DetailEntity> details;

    public VlasnykEntity()
    {}
    public VlasnykEntity(String s, String n, String avtosalon, String e){
        surname=s;
        name=n;
        avtosalonByAvtosalon=new AvtosalonEntity(avtosalon);
        email=e;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDVlasnyk", nullable = false)
    public int getIdVlasnyk() {
        return idVlasnyk;
    }

    public void setIdVlasnyk(int idVlasnyk) {
        this.idVlasnyk = idVlasnyk;
    }

    @Column(name = "Surname", nullable = false, length = 25)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "Name", nullable = false, length = 25)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Email", nullable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VlasnykEntity that = (VlasnykEntity) o;

        if (idVlasnyk != that.idVlasnyk) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVlasnyk;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "AvtosalonName", referencedColumnName = "AvtosalonName", nullable = false)
    public AvtosalonEntity getAvtosalonByAvtosalon() {
        return avtosalonByAvtosalon;
    }

    public void setAvtosalonByAvtosalon(AvtosalonEntity avtosalonByAvtosalon) {
        this.avtosalonByAvtosalon = avtosalonByAvtosalon;
    }

    @ManyToMany(mappedBy = "vlasnyks")
    public List<DetailEntity> getDetails() {
        return details;
    }

    public void addDetailEntity(DetailEntity detailEntity){
        if(!getDetails().contains(detailEntity)){
            getDetails().add(detailEntity);
        }
        if(!detailEntity.getVlasnyks().contains(this)){
            detailEntity.getVlasnyks().add(this);
        }
    }

    public void setDetails(List<DetailEntity> details) {
        this.details = details;
    }
}
