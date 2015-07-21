package mk.ukim.finki.emk.shop.web;

import mk.ukim.finki.emk.shop.model.Product;
import mk.ukim.finki.emk.shop.model.ProductImage;
import mk.ukim.finki.emk.shop.service.ProductImageService;
import mk.ukim.finki.emk.shop.service.ProductService;
import mk.ukim.finki.emk.shop.specifications.Specifications;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Nadica-PC on 6/4/2015.
 */

@RestController
@RequestMapping("/api/product_images")
public class ProductImageResource {

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ProductImage create(@RequestBody @Valid ProductImage entity) {
        productImageService.save(entity);
        return entity;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<ProductImage> getAll() {
        Collection<ProductImage> images = productImageService.findAll();
        return new ArrayList<ProductImage>(images);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ProductImage get(@PathVariable Long id, HttpServletResponse response) {
        ProductImage productImage = productImageService.findOne(id);
        if (productImage == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return productImage;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(@PathVariable Long id, HttpServletResponse response) {
        productImageService.delete(id);
    }

    /**
     * Method for uploading images
     */
    @RequestMapping(value = "/upload/{id}", method = RequestMethod.POST, produces = "application/json")
    // the method return value should be bound to the web response body
    @ResponseBody
    public ProductImage uploadFile(MultipartFile file, @PathVariable Long id)
            throws IOException, SerialException, SQLException {
        Product product = productService.findOne(id);
        ProductImage productImage = new ProductImage();
        productImage.setProduct(product);
        productImage.setImage(new SerialBlob(file.getBytes()));
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        Thumbnails.of(file.getInputStream()).size(200, 200).toOutputStream(ostream);
        productImage.setThumbnail(new SerialBlob(ostream.toByteArray()));
        productImage.setFileName(file.getOriginalFilename());
        productImage.setFileType(file.getContentType());
        productImageService.save(productImage);
        return productImage;
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = "application/json")
    public List<ProductImage> getImagesByProductId(@PathVariable Long id) {
        return productImageService.findAll(Specifications.product(id));
    }


    @RequestMapping("/image/{id}")
    public String downloadImageById(@PathVariable("id") Long id,
                                    HttpServletResponse response) {
        ProductImage productImage = productImageService.findOne(id);
        writeFileToResponse(productImage, productImage.getImage(), response);
        return null;
    }

    @RequestMapping("/thumbnail/{id}")
    public String downloadThumbnailById(@PathVariable("id") Long id,
                                        HttpServletResponse response) {
        ProductImage productImage = productImageService.findOne(id);
        writeFileToResponse(productImage, productImage.getThumbnail(), response);
        return null;
    }

    @RequestMapping("/product_thumbnail/{id}")
    public String downloadFirstThumbnailById(@PathVariable("id") Long id,
                                             HttpServletResponse response) {
        List<ProductImage> images = productImageService.findAll(Specifications.product(id));
        if (images.size() != 0) {
            writeFileToResponse(images.get(0), images.get(0).getThumbnail(), response);
        }
        return null;
    }


    @RequestMapping("/first_image/{id}")
    public String downloadFirstImageById(@PathVariable("id") Long id,
                                             HttpServletResponse response) {
        List<ProductImage> images = productImageService.findAll(Specifications.product(id));
        if (images.size() != 0) {
            writeFileToResponse(images.get(0), images.get(0).getImage(), response);
        }
        return null;
    }


    private void writeFileToResponse(ProductImage productImage, Blob image,
                                     HttpServletResponse response) {
        try {
            OutputStream out = response.getOutputStream();
            response.setContentType(productImage.getFileType());
            response.setContentLength((int) image.length());
            IOUtils.copy(image.getBinaryStream(), out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
