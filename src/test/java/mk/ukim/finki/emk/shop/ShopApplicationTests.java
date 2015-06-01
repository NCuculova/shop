package mk.ukim.finki.emk.shop;

import mk.ukim.finki.emk.shop.model.Category;
import mk.ukim.finki.emk.shop.repository.CategoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShopApplication.class)
@WebAppConfiguration
public class ShopApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Before
    public void setup() {
        System.out.println("Setup");

    }

    @After
    public void destroy() {
        System.out.println("Destroy");
    }

    @Test
       public void simpleTest() {
        System.out.println("Test");
        Category category = new Category();
        category.setName("Nadica");
        category.setDescription("loves Tomche");
        categoryRepository.save(category);
        assertNotNull(category.getId());
    }

    @Test
    public void secondTest() {
        System.out.println("Test2");
    }

}
