package pro.sky.CWTBshelter.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.CWTBshelter.model.ShelterInfo;
import pro.sky.CWTBshelter.repository.ShelterInfoRepository;
import pro.sky.CWTBshelter.service.imp.ShelterInfoServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShelterInfoServiceTest {
    @Mock
    private ShelterInfoRepository shelterInfoRepository;
    @InjectMocks
    private ShelterInfoServiceImpl shelterInfoService;
    @BeforeEach
    public void beforeEach(){
        ShelterInfo shelterTest = new ShelterInfo(1L,"Про приют","Адрес",
                "Номер телефона","Безопасность на территории",
                "Знакомство","Документы",
                "Рекомендации к транспортировке",
                "Для маленьких","Для больших",
                "С особенностями","Рекомендации",
                "Кинолог","Отказы");
        lenient().when(shelterInfoRepository.findById(1L)).thenReturn(Optional.of(shelterTest));
    }

    @Test
    void createShelterInfo() {
        ShelterInfo expected =  new ShelterInfo(1L,"Про приют","Адрес",
                "Номер телефона","Безопасность на территории",
                "Знакомство","Документы",
                "Рекомендации к транспортировке",
                "Для маленьких","Для больших",
                "С особенностями","Рекомендации",
                "Кинолог","Отказы");
        ShelterInfo shelterTest = new ShelterInfo(1L,"Про приют","Адрес",
                "Номер телефона","Безопасность на территории",
                "Знакомство","Документы",
                "Рекомендации к транспортировке",
                "Для маленьких","Для больших",
                "С особенностями","Рекомендации",
                "Кинолог","Отказы");
       when(shelterInfoRepository.save(shelterTest)).thenReturn(new ShelterInfo(1L,"Про приют","Адрес",
               "Номер телефона","Безопасность на территории",
               "Знакомство","Документы",
               "Рекомендации к транспортировке",
               "Для маленьких","Для больших",
               "С особенностями","Рекомендации",
               "Кинолог","Отказы"));
       ShelterInfo actual = shelterInfoService.createShelterInfo( new ShelterInfo(1L,"Про приют","Адрес",
               "Номер телефона","Безопасность на территории",
               "Знакомство","Документы",
               "Рекомендации к транспортировке",
               "Для маленьких","Для больших",
               "С особенностями","Рекомендации",
               "Кинолог","Отказы"));
       Assertions.assertEquals(actual,expected);

    }

    @Test
    void findShelterInfoById() {
    }

    @Test
    void editShelterInfo() {
    }

    @Test
    void deleteShelterInfoById() {
    }

    @Test
    void getAllShelters() {
        when(shelterInfoRepository.findAll()).thenReturn(
                List.of(
                        new ShelterInfo(1L,"Про приют","Адрес",
                                "Номер телефона","Безопасность на территории",
                                "Знакомство","Документы",
                                "Рекомендации к транспортировке","Для маленьких",
                                "Для больших","С особенностями",
                                "Рекомендации","Кинолог","Отказы")
                )
        );

        List<ShelterInfo> actual = shelterInfoRepository.findAll();
        List<ShelterInfo> expected = new ArrayList<>();
        expected.add(new ShelterInfo(1L,"Про приют","Адрес",
                "Номер телефона","Безопасность на территории",
                "Знакомство","Документы",
                "Рекомендации к транспортировке",
                "Для маленьких","Для больших",
                "С особенностями","Рекомендации",
                "Кинолог","Отказы"));
        Assertions.assertEquals(actual,expected);

    }

    @Test
    void getFirstMeetRecommendation() {
        String actual = shelterInfoService.getFirstMeetRecommendation();
        String expected = "Знакомство";
        Assertions.assertEquals(actual,expected);
    }

    @Test
    void getRefuseReasons() {
        String actual = shelterInfoService.getRefuseReasons();
        String expected = "Отказы";
        Assertions.assertEquals(actual,expected);
    }

    @Test
    void getSafetyOnTerritory() {
        String actual = shelterInfoService.getSafetyOnTerritory();
        String expected = "Безопасность на территории";
        Assertions.assertEquals(actual,expected);
    }
}