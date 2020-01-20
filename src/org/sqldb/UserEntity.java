package org.sqldb;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "sqldb", catalog = "")
public class UserEntity {
    private int idUser;
    private String nameUser;
    private String passwordUser;

    @Id
    @Column(name = "iduser")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "nameuser")
    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    @Basic
    @Column(name = "passworduser")
    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return idUser == that.idUser &&
                Objects.equals(nameUser, that.nameUser) &&
                Objects.equals(passwordUser, that.passwordUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, nameUser, passwordUser);
    }
}
