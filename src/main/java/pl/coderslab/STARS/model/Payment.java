package pl.coderslab.STARS.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Min(10000)
    private long alertId;

    @NotEmpty
    private String application;

    @NotNull
    @Min(8)
    private long account;

    @NotEmpty
    private String status;

    @NotNull
    @Min(0)
    private BigDecimal amount;

    @NotEmpty
    @Size(min = 3, max = 3)
    private String currency;

    private String citNumber;

    private LocalDateTime created;

    private String assignedPerson;

    public String getAssignedPerson() {
        return assignedPerson;
    }

    public void setAssignedPerson(String assignedPerson) {
        this.assignedPerson = assignedPerson;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCitNumber() {
        return citNumber;
    }

    public void setCitNumber(String citNumber) {
        this.citNumber = citNumber;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAlertId() {
        return alertId;
    }

    public void setAlertId(long alertId) {
        this.alertId = alertId;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", alertId=" + alertId +
                ", application='" + application + '\'' +
                '}';
    }
}
