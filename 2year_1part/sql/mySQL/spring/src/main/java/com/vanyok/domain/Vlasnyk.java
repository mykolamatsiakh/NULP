package com.vanyok.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vlasnyk")
public class Vlasnyk {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vlasnyk_id", nullable = false)
    private Long id;
    @Column(name = "surname", nullable = false, length = 25)
    private String surname;
    @Column(name = "name", nullable = false, length = 25)
    private String name;
    @Column(name = "email", nullable = true, length = 45)
    private String email;
    @Column(name = "street", nullable = true, length = 30)
    private String street;
    @Column(name = "avtosalonname", nullable = true, length = 10)
    private String avtosalonname;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    private City city;
    @ManyToMany
    @JoinTable(name = "vlasnyk_avtosalon",
            joinColumns = @JoinColumn(name = "vlasnyk_id", referencedColumnName = "vlasnyk_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "avtosalon_id", referencedColumnName = "avtosalon_id", nullable = false))
    private Set<Avtosalon> avtosalons;

    Vlasnyk(){}
    Vlasnyk(String surname, String name, String email, String street, String avtosalonname){
        this.surname=surname;
        this.name=name;
        this.email=email;
        this.street=street;
        this.avtosalonname=avtosalonname;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long idVlasnyk) {
        this.id = idVlasnyk;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getAvtosalonname() {
        return avtosalonname;
    }
    public void setAvtosalonname(String avtosalonname) {
        this.avtosalonname = avtosalonname;
    }

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }

    public Set<Avtosalon> getAvtosalons() {
        return avtosalons;
    }
    public void setAvtosalons(Set<Avtosalon> avtosalons) {
        this.avtosalons = avtosalons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vlasnyk that = (Vlasnyk) o;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (avtosalonname != null ? !avtosalonname.equals(that.avtosalonname) : that.avtosalonname != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (avtosalonname != null ? avtosalonname.hashCode() : 0);
        return result;
    }
}
