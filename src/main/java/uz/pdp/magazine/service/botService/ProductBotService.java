package uz.pdp.magazine.service.botService;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.pdp.magazine.entity.attachment.AttachmentDatabase;
import uz.pdp.magazine.entity.category.CategoryDatabase;
import uz.pdp.magazine.entity.product.ProductDatabase;
import uz.pdp.magazine.repository.ProductRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductBotService {
    private final ProductRepository productRepository;

    public ProductBotService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public InlineKeyboardMarkup getProductList(CategoryDatabase categoryDatabase) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> list = new ArrayList<>();

        List<ProductDatabase> findByCategoryDatabase = productRepository.findByCategoryDatabase(categoryDatabase);

        int count = 0;

        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton;

        for (ProductDatabase productDatabase : findByCategoryDatabase) {
            inlineKeyboardButton = new InlineKeyboardButton().setText(productDatabase.getName())
                    .setCallbackData("p" + productDatabase.getId());
            inlineKeyboardButtons.add(inlineKeyboardButton);
            list.add(inlineKeyboardButtons);
            inlineKeyboardButtons = new ArrayList<>();
        }
        inlineKeyboardMarkup.setKeyboard(list);
        return inlineKeyboardMarkup;
    }
    public SendPhoto getproduct(Integer productId){

        List<AttachmentDatabase> attachmentDatabases = productRepository.findById(productId).get().getAttachmentDatabases();

        SendPhoto message=new SendPhoto();
        try {
            message = new SendPhoto().setPhoto(attachmentDatabases.get(0).getName(), new FileInputStream(Arrays.toString(attachmentDatabases.get(0).getAttachmentContentDatabase().getBytes())));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return message;
    }
}
