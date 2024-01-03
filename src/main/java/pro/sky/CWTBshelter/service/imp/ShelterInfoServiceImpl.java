package pro.sky.CWTBshelter.service.imp;

import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.dto.ShelterInfoDTO;
import pro.sky.CWTBshelter.dto.mapper.ShelterInfoDTOMapper;
import pro.sky.CWTBshelter.exception.ShelterInfoNotFoundException;
import pro.sky.CWTBshelter.model.ShelterInfo;
import pro.sky.CWTBshelter.repository.ShelterInfoRepository;
import pro.sky.CWTBshelter.service.ShelterInfoService;

import java.util.List;

@Service
public class ShelterInfoServiceImpl implements ShelterInfoService {
    private final ShelterInfoRepository shelterInfoRepository;
    private final ShelterInfoDTOMapper shelterInfoDTOMapper;

    public ShelterInfoServiceImpl(ShelterInfoRepository shelterInfoRepository, ShelterInfoDTOMapper shelterInfoDTOMapper) {
        this.shelterInfoRepository = shelterInfoRepository;
        this.shelterInfoDTOMapper = shelterInfoDTOMapper;
    }


    //CREATE
    @Override
    public ShelterInfo createShelterInfo(ShelterInfoDTO.Request.Create request) {
        ShelterInfo shelterInfo = shelterInfoDTOMapper.toShelterInfo(request);
        return shelterInfoRepository.save(shelterInfo);
    }


    //READ
    @Override
    public ShelterInfo findShelterInfoById(Long id) {
        return shelterInfoRepository.findById(id).orElseThrow(ShelterInfoNotFoundException::new);
    }

    //UPDATE
    @Override
    public ShelterInfo editShelterInfo(Long id, ShelterInfoDTO.Request.Create request) {
        ShelterInfo shelterInfo = shelterInfoRepository.findById(id).orElseThrow(ShelterInfoNotFoundException::new);
        shelterInfo.setAboutShelter(request.getAboutShelter());
        shelterInfo.setAddressSchedule(request.getAddressSchedule());
        shelterInfo.setContactForCarPass(request.getContactForCarPass());
        shelterInfo.setSafetyOnTerritory(request.getSafetyOnTerritory());
        shelterInfo.setFirstMeetRecommendation(request.getFirstMeetRecommendation());
        shelterInfo.setDocuments(request.getDocuments());
        shelterInfo.setTransportationAdvice(request.getTransportationAdvice());
        shelterInfo.setHouseRulesForSmallAnimal(request.getHouseRulesForSmallAnimal());
        shelterInfo.setHouseRulesForAdultAnimal(request.getHouseRulesForAdultAnimal());
        shelterInfo.setRulesForAnimalWithDisability(request.getRulesForAnimalWithDisability());
        shelterInfo.setCynologistAdvice(request.getCynologistAdvice());
        shelterInfo.setCynologists(request.getCynologists());
        shelterInfo.setRefuseReasons(request.getRefuseReasons());
        return shelterInfoRepository.save(shelterInfo);
    }

    //DELETE
    @Override
    public boolean deleteShelterInfoById(Long id) {
        if (shelterInfoRepository.findById(id).isPresent()) {
            shelterInfoRepository.deleteById(id);
            return true;
        } else {
            throw new ShelterInfoNotFoundException();
        }
    }

    @Override
    public List<ShelterInfo> getAllShelters() {
        return shelterInfoRepository.findAll();
    }

    // Методы Коли
    @Override
    public String getContactForCarPass() {
        ShelterInfo shelterInfo = shelterInfoRepository.findById(1L).orElse(null);
        if (shelterInfo != null) {
            return shelterInfo.getContactForCarPass();
        }
        return null;
}

    @Override
    public String getDocuments() {
        ShelterInfo shelterInfo = shelterInfoRepository.findById(1L).orElse(null);
        if (shelterInfo != null) {
            return shelterInfo.getDocuments();
        }
        return null;
    }
    @Override
    public String getTransportationAdvice() {
        ShelterInfo shelterInfo = shelterInfoRepository.findById(1L).orElse(null);
        if (shelterInfo != null) {
            return shelterInfo.getTransportationAdvice();
        }
        return null;
    }
}
