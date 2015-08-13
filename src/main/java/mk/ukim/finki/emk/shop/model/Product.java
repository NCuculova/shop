package mk.ukim.finki.emk.shop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nadica-PC on 6/1/2015.
 */

@Entity
@Indexed
@Table(name = "products")
public class Product extends BaseEntity {

    public Product() {
        dateInserted = new Date();
    }

    @Field
    @NotEmpty
    private String name;

    @Field
    private String description;

    @NotNull
    private Double price;

    private Integer quantity = 1;

    private Double discount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateInserted;

    private boolean hasDiscount;

    private boolean featured;

    @ManyToOne
    @NotNull
    @IndexedEmbedded //Associated objects
    private Category category;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getDateInserted() {
        return dateInserted;
    }

    public void setDateInserted(Date dateInserted) {
        this.dateInserted = dateInserted;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setIsFeatured(boolean isFeatured) {
        this.featured = isFeatured;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDateString() {
        return new SimpleDateFormat("dd/MM/yy").format(dateInserted);
    }
}
