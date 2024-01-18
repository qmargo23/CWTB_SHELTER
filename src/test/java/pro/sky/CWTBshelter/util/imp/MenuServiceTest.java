package pro.sky.CWTBshelter.util.imp;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.CWTBshelter.model.ShelterInfo;
import pro.sky.CWTBshelter.repository.ShelterInfoRepository;
import pro.sky.CWTBshelter.util.KeyboardUtil;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static pro.sky.CWTBshelter.init.CallbackDataRequest.*;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {
    @Mock
    private TelegramBot telegramBot;

    @Captor
    private ArgumentCaptor<SendMessage> captor;

    @InjectMocks
    private MenuServiceImp menuService;

    @Mock
    private KeyboardUtil keyboardUtil;
    @Mock
    ShelterInfoRepository shelterInfoRepository;
    static ShelterInfo shelterTestCat = new ShelterInfo(2L, "Про приют", "Адрес",
            "Номер телефона", "Безопасность на территории",
            "Знакомство", "Документы",
            "Рекомендации к транспортировке",
            "Для маленьких", "Для больших",
            "С особенностями", "Рекомендации",
            "Кинолог", "Отказы");

    static ShelterInfo shelterTestDog = new ShelterInfo(1L, "Про приют", "Адрес",
            "Номер телефона", "Безопасность на территории",
            "Знакомство", "Документы",
            "Рекомендации к транспортировке",
            "Для маленьких", "Для больших",
            "С особенностями", "Рекомендации",
            "Кинолог", "Отказы");

    Long id = 402L;

    @Test
    @DisplayName("Вывод приветственного меню")
    void getStartMenuShelter() {
        String text = "Для начала работы надо выбрать приют.";
        menuService.getStartMenuShelter(id);

        InlineKeyboardMarkup keyboardMarkup = keyboardUtil.setKeyboard(CAT, DOG);
        verify(telegramBot, times(1)).execute(captor.capture());

        var sendMessage = captor.getValue();

        assertEquals(sendMessage.getParameters().get("text"), text);
        assertEquals(sendMessage.getParameters().get("chat_id"), id);
        assertEquals(sendMessage.getParameters().get("keyboardMarkup"), keyboardMarkup);
    }

    @Test
    @DisplayName("Вывод меню для приюта кошки")
    void getCatMenu() {
        lenient().when(shelterInfoRepository.findById(2L)).thenReturn(Optional.of(shelterTestCat));
        String text = "Добро пожаловать в наш кошачий приют." +
                "\n" +
                "\n На данном этапе вы можете выбрать:" +
                "\n";
        menuService.getCatMenu(id);

        InlineKeyboardMarkup keyboardMarkup = keyboardUtil.setKeyboard(
                GET_SHELTER_MENU,
                ADOPT_MENU,
                HELP
        );

        verify(telegramBot, times(1)).execute(captor.capture());

        var sendMessage = captor.getValue();

        assertEquals(sendMessage.getParameters().get("text"), shelterTestCat.getAboutShelter() + "\n" + "\n" +  text);
        assertEquals(sendMessage.getParameters().get("chat_id"), id);
        assertEquals(sendMessage.getParameters().get("keyboardMarkup"), keyboardMarkup);
    }

    @Test
    @DisplayName("Вывод меню для приюта собак")
    void getDogMenu() {
        lenient().when(shelterInfoRepository.findById(1L)).thenReturn(Optional.of(shelterTestDog));
        String text = "Добро пожаловать в наш собачий приют." +
                "\n" +
                "\n На данном этапе вы можете выбрать:" +
                "\n";
        menuService.getDogMenu(id);

        InlineKeyboardMarkup keyboardMarkup = keyboardUtil.setKeyboard(
                GET_SHELTER_MENU,
                ADOPT_MENU,
                HELP
        );

        verify(telegramBot, times(1)).execute(captor.capture());

        var sendMessage = captor.getValue();

        assertEquals(sendMessage.getParameters().get("text"), shelterTestDog.getAboutShelter() + "\n" + "\n" +  text);
        assertEquals(sendMessage.getParameters().get("chat_id"), id);
        assertEquals(sendMessage.getParameters().get("keyboardMarkup"), keyboardMarkup);
    }

    @Test
    @DisplayName("Вывод меню Рекомендации по усыновления ")
    void getAdoptMenuShelter() {
        String text = "Усыновление питомца возможно только через волонтера.\n" +
                "\n" +
                "...на данном этапе Вы можете посмотреть какие питомцы ищут новый дом:";
        menuService.getAdoptMenuShelter(id);

        InlineKeyboardMarkup keyboardMarkup = keyboardUtil.setKeyboard(
                CAT_ADOPT_LIST,
                DOG_ADOPT_LIST,
                HELP
        );
        verify(telegramBot, times(1)).execute(captor.capture());

        var sendMessage = captor.getValue();

        assertEquals(sendMessage.getParameters().get("text"), text);
        assertEquals(sendMessage.getParameters().get("chat_id"), id);
        assertEquals(sendMessage.getParameters().get("keyboardMarkup"), keyboardMarkup);
    }

    @Test
    @DisplayName("Вывод меню правил для предоставления отчетов ")
    void getReportMenuShelter() {
        String text = "Обязательным условием ежедневной сдачи отчета является:\n" +
                "- Фото животного.\n" +
                "- Описание. " +
                "\n" +
                "\n...приготовьте фото, напишите про питомца, отправьте сообщение." +
                " \nпри неправильном заполнении отчета, наш волонтер свяжется с Вами и поможет.";;
        menuService.getReportMenuShelter(id);

        InlineKeyboardMarkup keyboardMarkup = keyboardUtil.setKeyboard(
                HELP
        );
        verify(telegramBot, times(1)).execute(captor.capture());

        var sendMessage = captor.getValue();

        assertEquals(sendMessage.getParameters().get("text"), text);
        assertEquals(sendMessage.getParameters().get("chat_id"), id);
        assertEquals(sendMessage.getParameters().get("keyboardMarkup"), keyboardMarkup);
    }

    @Test
    @DisplayName("Вывод меню с инструкцией как забрать кошку из приюта")
    void getCatAdoptMenu() {
        lenient().when(shelterInfoRepository.findById(2L)).thenReturn(Optional.of(shelterTestCat));
        String text = "Важная информация - что нужно знать если Вы хотите взять из приюта КОШКУ!";
        menuService.getCatAdoptMenu(id);

        InlineKeyboardMarkup keyboardMarkup = keyboardUtil.setKeyboard(
                FIRST_MEET_RECOMMENDATION,
                DOCUMENTS,
                TRANSPORTATION_ADVICE,
                HOUSE_RULES_FOR_SMALL_ANIMAL,
                HOUSE_RULES_FOR_ADULT_ANIMAL,
                RULES_FOR_ANIMAL_WITH_DISABILITY,
                REFUSE_REASONS,
                HELP
        );
        verify(telegramBot, times(1)).execute(captor.capture());

        var sendMessage = captor.getValue();

        assertEquals(sendMessage.getParameters().get("text"), shelterTestCat.getAboutShelter()+ "\n" + "\n" + text);
        assertEquals(sendMessage.getParameters().get("chat_id"), id);
        assertEquals(sendMessage.getParameters().get("keyboardMarkup"), keyboardMarkup);
    }

    @Test
    @DisplayName("Вывод меню с инструкцией как забрать собаку из приюта")
    void getDogAdoptMenu() {
        lenient().when(shelterInfoRepository.findById(1L)).thenReturn(Optional.of(shelterTestDog));
        String text = "Первичная информация - что нужно знать если Вы хотите взять из приюта СОБАКУ";
        menuService.getDogAdoptMenu(id);

        InlineKeyboardMarkup keyboardMarkup = keyboardUtil.setKeyboard(
                FIRST_MEET_RECOMMENDATION,
                DOCUMENTS,
                TRANSPORTATION_ADVICE,
                HOUSE_RULES_FOR_SMALL_ANIMAL,
                HOUSE_RULES_FOR_ADULT_ANIMAL,
                RULES_FOR_ANIMAL_WITH_DISABILITY,
                REFUSE_REASONS,
                HELP
        );
        verify(telegramBot, times(1)).execute(captor.capture());

        var sendMessage = captor.getValue();

        assertEquals(sendMessage.getParameters().get("text"), shelterTestDog.getAboutShelter()+ "\n" + "\n" + text);
        assertEquals(sendMessage.getParameters().get("chat_id"), id);
        assertEquals(sendMessage.getParameters().get("keyboardMarkup"), keyboardMarkup);
    }

    @Test
    @DisplayName("Вывод меню с информацией о приюте")
    void getShelterInfoMenu() {
        String text = "...на данном этапе можно получить следующую информацию о приюте:";
        menuService.getShelterInfoMenu(id);

        InlineKeyboardMarkup keyboardMarkup = keyboardUtil.setKeyboard(
                HELP
        );
        verify(telegramBot, times(1)).execute(captor.capture());

        var sendMessage = captor.getValue();

        assertEquals(sendMessage.getParameters().get("text"), text);
        assertEquals(sendMessage.getParameters().get("chat_id"), id);
        assertEquals(sendMessage.getParameters().get("keyboardMarkup"), keyboardMarkup);
    }
}