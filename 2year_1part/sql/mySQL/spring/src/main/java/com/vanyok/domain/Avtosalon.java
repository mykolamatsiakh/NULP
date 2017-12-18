package com.vanyok.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "avtosalon")
public class Avtosalon {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avtosalon_id", nullable = false)
    private Long id;
    @Column(name = "avtosalon_name", nullable = false, length = 45)
    private String avtosalonName;
    @Column(name = "author", nullable = false, length = 45)
    private String author;
    @Column(name = "seller", nullable = true, length = 50)
    private String seller;
    @Column(name = "year_of_creating", nullable = true)
    private Integer year_of_creating;
    @Column(name = "amount", nullable = false)
    private Integer amount;
    @ManyToMany(mappedBy = "avtosalons")
    private Set<Vlasnyk> vlasnyks;

    Avtosalon(){}
    Avtosalon(String avtosalonName, String author, String seller, Integer year_of_creating){
        this.avtosalonName=avtosalonName;
        this.author=author;
        this.seller=seller;
        this.year_of_creating=year_of_creating;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long idAvtosalon) {
        this.id = idAvtosalon;
    }

    public String getAvtosalonName() {
        return avtosalonName;
    }
    public void setAvtosalonName(String avtosalonName) {
        this.avtosalonName = avtosalonName;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSeller() {
        return seller;
    }
    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Integer getYear_of_creating() {
        return year_of_creating;
    }
    public void setYear_of_creating(Integer year_of_creating) {
        this.year_of_creating = year_of_creating;
    }

    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Set<Vlasnyk> getVlasnyks() {
        return vlasnyks;
    }
    public void setVlasnyks(Set<Vlasnyk> vlasnyks) {
        this.vlasnyks = vlasnyks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avtosalon that = (Avtosalon) o;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (avtosalonName != null ? !avtosalonName.equals(that.avtosalonName) : that.avtosalonName != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (seller != null ? !seller.equals(that.seller) : that.seller != null) return false;
        if (year_of_creating != null ? !year_of_creating.equals(that.year_of_creating) : that.year_of_creating != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (avtosalonName != null ? avtosalonName.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (seller != null ? seller.hashCode() : 0);
        result = 31 * result + (year_of_creating != null ? year_of_creating.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
