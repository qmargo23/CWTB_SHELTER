package pro.sky.CWTBshelter.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "photo")
    private String photo;
    @Column(name = "local_date")
    private LocalDate localDate;
    @Column(name = "report_text_under_photo")
    private String reportTextUnderPhoto;

    public Report() {
    }

    public Report(long id, String photo, LocalDate localDate, String reportTextUnderPhoto) {
        this.id = id;
        this.photo = photo;
        this.localDate = localDate;
        this.reportTextUnderPhoto = reportTextUnderPhoto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getReportTextUnderPhoto() {
        return reportTextUnderPhoto;
    }

    public void setReportTextUnderPhoto(String reportTextUnderPhoto) {
        this.reportTextUnderPhoto = reportTextUnderPhoto;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report that = (Report) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "report{" +
                "id=" + id +
                ", photo=" + photo +
                ", local_date=" + localDate +
                ", report_text_under_photo=" + reportTextUnderPhoto +
                '}';
    }
}
