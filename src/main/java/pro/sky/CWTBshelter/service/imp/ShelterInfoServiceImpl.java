package pro.sky.CWTBshelter.service.imp;

import org.springframework.stereotype.Service;
import pro.sky.CWTBshelter.exception.ShelterInfoNotFoundException;
import pro.sky.CWTBshelter.model.ShelterInfo;
import pro.sky.CWTBshelter.repository.ShelterInfoRepository;
import pro.sky.CWTBshelter.service.ShelterInfoService;

import java.util.List;
@Service
public class ShelterInfoServiceImpl implements ShelterInfoService {
    private final ShelterInfoRepository shelterInfoRepository;

    public ShelterInfoServiceImpl(ShelterInfoRepository shelterInfoRepository) {
        this.shelterInfoRepository = shelterInfoRepository;
    }



    //CREATE
    @Override
    public ShelterInfo createShelterInfo(ShelterInfo shelterInfo) {
        shelterInfoRepository.save(shelterInfo);
        return shelterInfo;
    }


    //READ
    @Override
    public ShelterInfo findShelterInfoById(Long id) {
        return shelterInfoRepository.findById(id).orElseThrow(ShelterInfoNotFoundException::new);
    }
    //UPDATE
    @Override
    public ShelterInfo editShelterInfo(ShelterInfo shelterInfo) {
        if (shelterInfoRepository.findById(shelterInfo.getId()).isPresent()){
            shelterInfoRepository.save(shelterInfo);
            return shelterInfo;
        }else {
            throw new ShelterInfoNotFoundException();
        }
    }
    //DELETE
    @Override
    public boolean deleteShelterInfoById(Long id) {
        if(shelterInfoRepository.findById(id).isPresent()){
          shelterInfoRepository.deleteById(id);
          return true;
        }else {
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
