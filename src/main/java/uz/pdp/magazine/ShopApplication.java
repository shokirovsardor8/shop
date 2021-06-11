package uz.pdp.magazine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import uz.pdp.magazine.bot.Main;
import uz.pdp.magazine.service.botService.CategoryBotService;
import uz.pdp.magazine.service.botService.MainMenu;
import uz.pdp.magazine.service.botService.ProductBotService;
import uz.pdp.magazine.service.category.CategoryService;
import uz.pdp.magazine.service.product.ProductService;


@SpringBootApplication
public class ShopApplication implements WebMvcConfigurer, CommandLineRunner {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryBotService categoryBotService;

    @Autowired
    ProductBotService productBotService;

    @Autowired
    MainMenu mainMenu;

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        telegramBotsApi.registerBot(new Main(
                categoryService,
                categoryBotService,
                productService,
                productBotService,
                mainMenu
        ));
    }

}
