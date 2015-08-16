package mk.ukim.finki.emk.shop.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nadica-PC on 7/29/2015.
 */

@Entity
@Table(name = "cart_invoice")
public class CartInvoice extends BaseEntity {

    private Double totalAmount;

    private Double subTotal;

    private Double taks;

    private String transactionId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    private Date dateInserted;

    @ManyToOne
    private User user;

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getTaks() {
        return taks;
    }

    public void setTaks(Double taks) {
        this.taks = taks;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDateInserted() {
        return dateInserted;
    }

    public void setDateInserted(Date dateInserted) {
        this.dateInserted = dateInserted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDateString() {
        return new SimpleDateFormat("dd/MM/yy").format(dateInserted);
    }
}
