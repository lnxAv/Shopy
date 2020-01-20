package org.sqldb;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "history", schema = "sqldb", catalog = "")
public class HistoryEntity {
    private int idhistory;
    private int iduser;
    private int iditem;
    private int quantity;
    private double price;
    private byte purchased;

    @Id
    @Column(name = "idhistory")
    public int getIdhistory() {
        return idhistory;
    }

    public void setIdhistory(int idhistory) {
        this.idhistory = idhistory;
    }

    @Basic
    @Column(name = "iduser")
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Basic
    @Column(name = "iditem")
    public int getIditem() {
        return iditem;
    }

    public void setIditem(int iditem) {
        this.iditem = iditem;
    }

    @Basic
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "purchased")
    public byte getPurchased() {
        return purchased;
    }

    public void setPurchased(byte purchased) {
        this.purchased = purchased;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryEntity that = (HistoryEntity) o;
        return idhistory == that.idhistory &&
                iduser == that.iduser &&
                iditem == that.iditem &&
                quantity == that.quantity &&
                Double.compare(that.price, price) == 0 &&
                purchased == that.purchased;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idhistory, iduser, iditem, quantity, price, purchased);
    }
}
