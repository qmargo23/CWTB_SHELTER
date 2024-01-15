package pro.sky.CWTBshelter.dto.mapper;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sky.CWTBshelter.dto.ShelterInfoDTO;
import pro.sky.CWTBshelter.model.ShelterInfo;

@NoArgsConstructor
@Component
public class ShelterInfoDTOMapper {
    public ShelterInfoDTO.Response.Detail toDetailDTO(ShelterInfo shelterInfo) {
        if (shelterInfo == null) {
            return null;
        }
        return new ShelterInfoDTO.Response.Detail(
                shelterInfo.getId(),
                shelterInfo.getAboutShelter(),
                shelterInfo.getAddressSchedule(),
                shelterInfo.getContactForCarPass(),
                shelterInfo.getSafetyOnTerritory(),
                shelterInfo.getFirstMeetRecommendation(),
                shelterInfo.getDocuments(),
                shelterInfo.getTransportationAdvice(),
                shelterInfo.getHouseRulesForSmallAnimal(),
                shelterInfo.getHouseRulesForAdultAnimal(),
                shelterInfo.getRulesForAnimalWithDisability(),
                shelterInfo.getCynologistAdvice(),
                shelterInfo.getCynologists(),
                shelterInfo.getRefuseReasons()
        );
    }

    public ShelterInfo toShelterInfo(ShelterInfoDTO.Request.Create dto) {
        if (dto == null) {
            return null;
        }
        return new ShelterInfo(
                null,
                dto.getAboutShelter(),
                dto.getAddressSchedule(),
                dto.getContactForCarPass(),
                dto.getSafetyOnTerritory(),
                dto.getFirstMeetRecommendation(),
                dto.getDocuments(),
                dto.getTransportationAdvice(),
                dto.getHouseRulesForSmallAnimal(),
                dto.getHouseRulesForAdultAnimal(),
                dto.getRulesForAnimalWithDisability(),
                dto.getCynologistAdvice(),
                dto.getCynologists(),
                dto.getRefuseReasons()
        );
    }
}
