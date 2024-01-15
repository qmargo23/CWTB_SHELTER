package pro.sky.CWTBshelter.service;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.CWTBshelter.exception.ReportNotFoundException;
import pro.sky.CWTBshelter.model.ReportTelegram;

import java.util.List;

public interface ReportTelegramService {
    /**
     * Создание нового отчета и сохранение его в базу данных  <br>
     *
     * @param reportTelegram объект класса {@link ReportTelegram}, не может быть {@code null}
     * @return созданный объект класса {@link ReportTelegram}
     * @see JpaRepository#save(Object)
     */
    ReportTelegram createTelegramReport(ReportTelegram reportTelegram);

    /**
     * Редактирование отчета и сохранение его в базу данных <br>
     *
     * @param reportTelegram объект класса {@link ReportTelegram}, не может быть {@code null}
     * @return отредактированный объект класса {@link ReportTelegram}
     * @throws ReportNotFoundException если отчет не был найден в базе данных
     * @see JpaRepository#save(Object)
     */
    ReportTelegram editTelegramReport(ReportTelegram reportTelegram);

    /**
     * Поиск отчета в базе данных по его {@code id} <br>
     *
     * @param id идентификатор искомого отчета, не может быть {@code null}
     * @return найденный объект класса {@link ReportTelegram}
     * @throws ReportNotFoundException если отчет с указанным {@code id} не был найден в базе данных
     * @see JpaRepository#findById(Object)
     */
    ReportTelegram findTelegramReportById(Long id);

    /**
     * Вывод списка всех отчетов из базы данных<br>
     *
     * @return список объектов класса {@link ReportTelegram}
     * @see JpaRepository#findAll()
     */
    List<ReportTelegram> getAllTelegramReports();

    /**
     * Удаление отчета из базы данных по его {@code id} <br>
     *
     * @param id идентификатор отчета, который нужно удалить из базы данных, не может быть {@code null}
     * @return {@code true} если объект был найден в базе данных, в противном случае {@link ReportNotFoundException}
     * @throws ReportNotFoundException если отчет с указанным {@code id} не был найден в базе данных
     * @see JpaRepository#deleteById(Object)
     */
    boolean deleteTelegramReportById(Long id);

    /**
     * сохраняет отчет в БД
     */
    SendMessage postReport(Long chatId, Update update);
}
