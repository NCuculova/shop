package mk.ukim.finki.emk.shop.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Nadica-PC on 8/5/2015.
 */
@Entity
@Table(name="transaction_products")
public class TransactionProduct extends BaseEntity {

    @ManyToOne
    private CartInvoice cartInvoice;

    private Integer quantity;

    private Double unitPrice;

    @ManyToOne
    private Product product;

    public CartInvoice getCartInvoice() {
        return cartInvoice;
    }

    public void setCartInvoice(CartInvoice cartInvoice) {
        this.cartInvoice = cartInvoice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
