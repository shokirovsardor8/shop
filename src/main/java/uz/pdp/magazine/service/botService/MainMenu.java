package uz.pdp.magazine.service.botService;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MainMenu {

    public ReplyKeyboardMarkup mainMenu() {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardButton keyboardButton=new KeyboardButton();
        KeyboardButton keyboardButton2=new KeyboardButton();
        keyboardButton.setText("Buy product");
        keyboardRow.add(keyboardButton);
        keyboardButton2.setText("Card");
        keyboardRow.add(keyboardButton2);
        keyboardRowList.add(keyboardRow);
        return replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }
}
