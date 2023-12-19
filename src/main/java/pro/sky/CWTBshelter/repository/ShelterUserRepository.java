package pro.sky.CWTBshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.CWTBshelter.model.ShelterUser;

public interface ShelterUserRepository extends JpaRepository<ShelterUser, Long> {
}
