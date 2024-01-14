package pro.sky.CWTBshelter.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.CWTBshelter.model.Avatar;

import java.io.IOException;
import java.nio.file.Path;

public interface AvatarService {
    /**
     * Создание нового аватара и сохранение его в базу данных <br>
     * @param id уникальный идентификатор аватара <br>
     * @param avatar - загруженный файл
     * @throws IOException
     */
    void uploadAvatar(Long id, MultipartFile avatar) throws IOException;

    /**
     * Создание предварительного просмотра изображения
     * @param filePath изображение
     * @return содержимое выходного потока в виде массива байтов
     * @throws IOException
     */

    byte[] generateImagePreview(Path filePath) throws IOException;

    /**
     * Поиск аватара по его {@code id}<br>
     * @param id идентификатор искомого аватара, не может быть {@code null}
     * @return найденный объект класса{@link Avatar}
     * @see JpaRepository#findById(Object)
     */

    Avatar findAvatar(Long id);

    /**
     * Получает индекс
     * @param fileName имя файла
     * @return индекс следующего знака после точки в имени файла
     */

    String getExtension(String fileName);
}
