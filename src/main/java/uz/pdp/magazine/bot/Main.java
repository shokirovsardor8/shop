package uz.pdp.magazine.bot;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.magazine.entity.category.CategoryDatabase;
import uz.pdp.magazine.service.botService.CategoryBotService;
import uz.pdp.magazine.service.botService.MainMenu;
import uz.pdp.magazine.service.botService.ProductBotService;
import uz.pdp.magazine.service.category.CategoryService;
import uz.pdp.magazine.service.product.ProductService;

public class Main extends TelegramLongPollingBot {


    @Value("${bot_username}")
    private String username;

    @Value("${bot_token}")
    private String token;

    Long userChatId;
    String userMessage;

    static CategoryService categoryService;
    static CategoryBotService categoryBotService;
    static ProductService productService;
    static ProductBotService productBotService;
    static MainMenu mainMenu;


    public Main(CategoryService categoryService, CategoryBotService categoryBotService, ProductService productService, ProductBotService productBotService,MainMenu mainMenu) {
        this.categoryService = categoryService;
        this.categoryBotService = categoryBotService;
        this.productService = productService;
        this.productBotService = productBotService;
        this.mainMenu =mainMenu;
    }

    public void main() {

    }


    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            this.userChatId = update.getMessage().getChatId();
            this.userMessage = update.getMessage().getText();
            switch (userMessage) {
                case "/start":
                    ReplyKeyboardMarkup replyKeyboardMarkup = mainMenu.mainMenu();
                    execute(replyKeyboardMarkup, null);
                    break;
                case "Buy product":
                    execute(null, categoryBotService.getCategoryList(0));
                    break;

            }
        } else if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            if (data.startsWith("p")) {

                SendPhoto sendPhoto=productBotService.getproduct(Integer.parseInt(data.substring(1)));

            } else {

                InlineKeyboardMarkup categoryList = categoryBotService.getCategoryList(
                        Integer.parseInt(data.substring(1)));
                if (categoryList.getKeyboard().size()==0) {
                    CategoryDatabase category = categoryBotService.getCategory(Integer.parseInt(data.substring(1)));
                    execute(null,productBotService.getProductList(category));
                }
            }
        }
    }


    @Override
    public String getBotUsername() {
        return "t.me/Behruzs_last_tg_bot";

    }

    @Override
    public String getBotToken() {
        return "1662568750:AAFSSKdO1Qi5Cc0ww2yFOgJkiXs--475xeA";
    }

    public void execute(
            ReplyKeyboardMarkup replyKeyboardMarkup,
            InlineKeyboardMarkup inlineKeyboardMarkup
    ) {
        SendMessage sendMessage = new SendMessage();

        if (replyKeyboardMarkup!=null) {
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }
        if (inlineKeyboardMarkup!=null)
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        sendMessage.setChatId(userChatId);
        sendMessage.setText(userMessage);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
