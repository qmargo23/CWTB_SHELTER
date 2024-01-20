package pro.sky.CWTBshelter.model;


import jakarta.persistence.*;
import lombok.*;

/**
 * Сущность описывающая фото(картинку)
 * <i>содержит следующие поля</i><br>
 * <br>
 * id - идентификатор аватара<br>
 * filePath - путь к файлу
 * fileSize - размер файла
 * mediaType - тип файла
 * data - массив данных о файле имеет аннотацию lob (подсказывает Hibernate, что в поле хранится Large Object)
 * shelterInfo - приют (имеет зависимость один к одному с {@link ShelterInfo}
 *
 *
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "avatar")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;
    @OneToOne
    private ShelterInfo shelterInfo;

}
