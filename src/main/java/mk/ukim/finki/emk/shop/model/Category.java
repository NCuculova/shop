package mk.ukim.finki.emk.shop.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Nadica-PC on 6/1/2015.
 */

@Entity
@Table(name="categories")
public class Category extends BaseEntity{

    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
