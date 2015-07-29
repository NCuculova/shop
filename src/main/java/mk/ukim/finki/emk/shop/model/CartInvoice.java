package mk.ukim.finki.emk.shop.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Nadica-PC on 7/29/2015.
 */

@Entity
@Table(name="cart_invoice")
public class CartInvoice extends BaseEntity{

    @ManyToOne
    private Product product;

    private Double quantity;

    private Double price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateInserted;

    @ManyToOne
    private User user;
}
