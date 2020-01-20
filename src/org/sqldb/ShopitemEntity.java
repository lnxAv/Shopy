package org.sqldb;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "shopitem", schema = "sqldb", catalog = "")
public class ShopitemEntity {
    private int idItem;
    private Double priceItem;
    private String nameItem;
    private byte[] pictureItem;

    @Id
    @Column(name = "iditem")
    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    @Basic
    @Column(name = "priceitem")
    public Double getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(Double priceItem) {
        this.priceItem = priceItem;
    }

    @Basic
    @Column(name = "nameitem")
    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    @Basic
    @Column(name = "pictureitem")
    public byte[] getPictureItem() {
        return pictureItem;
    }

    public void setPictureItem(byte[] pictureItem) {
        this.pictureItem = pictureItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopitemEntity that = (ShopitemEntity) o;
        return idItem == that.idItem &&
                Objects.equals(priceItem, that.priceItem) &&
                Objects.equals(nameItem, that.nameItem) &&
                Arrays.equals(pictureItem, that.pictureItem);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(idItem, priceItem, nameItem);
        result = 31 * result + Arrays.hashCode(pictureItem);
        return result;
    }
}
