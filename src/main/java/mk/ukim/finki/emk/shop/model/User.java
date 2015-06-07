package mk.ukim.finki.emk.shop.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Nadica-PC on 6/7/2015.
 */
@Entity
@Table(name="users")
public class User  extends BaseEntity {

    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    private  String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
