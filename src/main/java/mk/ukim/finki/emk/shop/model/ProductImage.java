package mk.ukim.finki.emk.shop.model;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by Nadica-PC on 6/1/2015.
 */
@Entity
@Table(name="product_images")
public class ProductImage extends BaseEntity{

    @ManyToOne
    private Product product;

    private Blob image;

    private String fileName;

    private String fileType;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
