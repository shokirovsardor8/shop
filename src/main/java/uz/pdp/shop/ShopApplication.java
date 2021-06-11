package uz.pdp.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import uz.pdp.shop.bot.Main;

@SpringBootApplication
public class ShopApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
//
//        System.getProperties().put("proxySet", "true");
//        System.getProperties().put("socksProxyHost", "127.0.0.1");
//        System.getProperties().put("socksProxyPort", "3399");
//        try{
        try {
            ApiContextInitializer.init();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
            telegramBotsApi.registerBot(new Main());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
