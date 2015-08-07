package mk.ukim.finki.emk.shop.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Nadica-PC on 6/7/2015.
 */

@Entity
@Table(name="shopping_cart_items")
public class ShoppingCartItem extends BaseEntity {

    @ManyToOne
    private Product product;

    private Integer quantity;

    private String token;

    @ManyToOne
    private User user;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice(){
        return product.getPrice() * quantity;
    }
}
