package uz.pdp.shop.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main extends TelegramLongPollingBot {
//
//    @Value("${username}")
//    private String username;
//
//    @Value("${token}")
//    private String token;

    private Long userChatId;
    private String userMessage;

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
            return "t.me/Behruzs_last_tg_bot";
    }

    @Override
    public String getBotToken() {
        return "1662568750:AAFSSKdO1Qi5Cc0ww2yFOgJkiXs--475xeA";
    }

    private void execute(){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(this.userChatId);
        sendMessage.setText(this.userMessage);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
