package com.my;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "detail", schema = "db_hibernate", catalog = "")
public class DetailEntity {
    private int idDetail;
    private String detailName;
    private String author;
    private int amount;
    private List<VlasnykEntity> vlasnyks;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDDetail", nullable = false)
    public int getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    @Basic
    @Column(name = "DetailName", nullable = false, length = 45)
    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    @Basic
    @Column(name = "Author", nullable = false, length = 45)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "Amount", nullable = false)
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetailEntity that = (DetailEntity) o;

        if (idDetail != that.idDetail) return false;
        if (amount != that.amount) return false;
        if (detailName != null ? !detailName.equals(that.detailName) : that.detailName != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDetail;
        result = 31 * result + (detailName != null ? detailName.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + amount;
        return result;
    }

    @ManyToMany
    @JoinTable(name = "vlasnykdetail", catalog = "", schema = "db_hibernate", joinColumns = @JoinColumn(name = "IDDetail", referencedColumnName = "IDDetail", nullable = false), inverseJoinColumns = @JoinColumn(name = "IDVlasnyk", referencedColumnName = "IDVlasnyk", nullable = false))
    public List<VlasnykEntity> getVlasnyks() {
        return vlasnyks;
    }

    public void setVlasnyks(List<VlasnykEntity> persons) {
        this.vlasnyks = persons;
    }
}
