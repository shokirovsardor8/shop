package uz.pdp.magazine.mvc.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.magazine.entity.category.CategoryDatabase;
import uz.pdp.magazine.entity.product.ProductDatabase;
import uz.pdp.magazine.model.response.product.ProductResponseModel;
import uz.pdp.magazine.repository.CategoryRepository;
import uz.pdp.magazine.repository.ProductRepository;
import uz.pdp.magazine.service.category.CategoryService;
import uz.pdp.magazine.service.product.ProductService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersMainMenu {

    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;

    @Autowired
    public UsersMainMenu(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping()
    public String getHomePage(
            Model model
    ) {
        List<ProductResponseModel> productList = new ProductService(productRepository, categoryRepository).getProductList();
        List<CategoryDatabase> categoryList = new CategoryService(categoryRepository).getSubCategories1();
        model.addAttribute("categoryList", categoryList);
        return "admin/user/MainMenu";
    }

    @GetMapping("/category/{id}")
    public String getHomePage(
            @PathVariable("id") Integer categoryId,
            Model model
    ) {
        List<CategoryDatabase> categoryList = new CategoryService(categoryRepository).getCategoryByParent(categoryId);
        List<ProductDatabase> productList;
        productList = new ProductService(productRepository, categoryRepository).getProductByCategory(categoryId);
        model.addAttribute("productList", productList);
        model.addAttribute("categoryList", categoryList);
        return "admin/user/MainMenu";
    }
}
