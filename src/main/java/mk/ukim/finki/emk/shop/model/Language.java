package mk.ukim.finki.emk.shop.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Nadica-PC on 7/19/2015.
 */

@Entity
@Table(name="language")
public class Language extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
