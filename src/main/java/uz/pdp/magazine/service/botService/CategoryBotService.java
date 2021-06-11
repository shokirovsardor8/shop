package uz.pdp.magazine.service.botService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.pdp.magazine.entity.category.CategoryDatabase;
import uz.pdp.magazine.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryBotService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryBotService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public InlineKeyboardMarkup getCategoryList(int parentId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        inlineKeyboardMarkup.setKeyboard(list);

        List<CategoryDatabase> allByParentId = categoryRepository.findAllByParentId(parentId);

        int count = 0;

        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton;

        for (CategoryDatabase categoryDatabase : allByParentId) {
            inlineKeyboardButton = new InlineKeyboardButton().setText(categoryDatabase.getName())
                    .setCallbackData("c" + categoryDatabase.getId());
            inlineKeyboardButtons.add(inlineKeyboardButton);
            count++;
            if (count % 2 == 0) {
                list.add(inlineKeyboardButtons);
                inlineKeyboardButtons = new ArrayList<>();
            }
        }

        if (!inlineKeyboardButtons.isEmpty())
            list.add(inlineKeyboardButtons);

        return inlineKeyboardMarkup;

    }

    public CategoryDatabase getCategory(Integer categoryId){
        Optional<CategoryDatabase> getCategory = categoryRepository.findById(categoryId);
        return getCategory.orElse(null);

    }


}
