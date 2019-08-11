/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crownexponent.booktest.entity;

/**
 *
 * @author ISSAH OJIVO
 */
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ISSAH OJIVO
 */
@Entity
@Table(name = "password_reset")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PasswordReset.findAll", query = "SELECT p FROM PasswordReset p"),
    @NamedQuery(name = "PasswordReset.findByEmail", query = "SELECT p FROM PasswordReset p WHERE p.email = :email"),
    @NamedQuery(name = "PasswordReset.findByToken", query = "SELECT p FROM PasswordReset p WHERE p.token = :token"),
    @NamedQuery(name = "PasswordReset.findByDate", query = "SELECT p FROM PasswordReset p WHERE p.date = :date")})
public class PasswordReset implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "token")
    private String token;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "date")
    private String date;

    public PasswordReset() {
    }

    public PasswordReset(String email) {
        this.email = email;
    }

    public PasswordReset(String email, String token, String date) {
        this.email = email;
        this.token = token;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PasswordReset)) {
            return false;
        }
        PasswordReset other = (PasswordReset) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.jpatest.PasswordReset[ email=" + email + " ]";
    }
    
}
