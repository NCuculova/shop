package mk.ukim.finki.emk.shop.repository;

import mk.ukim.finki.emk.shop.model.CartInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Nadica-PC on 6/1/2015.
 */
public interface CartInvoiceRepository extends JpaRepository<CartInvoice, Long>,
        JpaSpecificationExecutor<CartInvoice> {

}
