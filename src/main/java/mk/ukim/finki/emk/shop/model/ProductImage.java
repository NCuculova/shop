package mk.ukim.finki.emk.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    private Blob image;

    @JsonIgnore
    private Blob thumbnail;

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

    public Blob getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Blob thumbnail) {
        this.thumbnail = thumbnail;
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
