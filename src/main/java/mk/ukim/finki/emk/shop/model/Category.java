package mk.ukim.finki.emk.shop.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Nadica-PC on 6/1/2015.
 */

@Entity
@Indexed
@Table(name="categories")
public class Category extends BaseEntity{

    // You have to mark the fields you want to make searchable annotating them
    // with Field.
    @Field
    @NotEmpty
    private String name;

    @Field
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
