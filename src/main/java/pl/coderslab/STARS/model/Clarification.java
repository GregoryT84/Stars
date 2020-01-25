package pl.coderslab.STARS.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "clarifications")
public class Clarification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Min(8)
    private long clarificationAccount;

    @NotBlank
    private String hitName;

    @NotBlank
    @Size(min = 1, max = 15)
    private String relatedCaseNumber;

    @NotBlank
    @Size(min = 1, max = 100)
    private String description;

    public Clarification() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClarificationAccount() {
        return clarificationAccount;
    }

    public void setClarificationAccount(long clarificationAccount) {
        this.clarificationAccount = clarificationAccount;
    }

    public String getHitName() {
        return hitName;
    }

    public void setHitName(String hitName) {
        this.hitName = hitName;
    }

    public String getRelatedCaseNumber() {
        return relatedCaseNumber;
    }

    public void setRelatedCaseNumber(String relatedCaseNumber) {
        this.relatedCaseNumber = relatedCaseNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Clarification{" +
                "id=" + id +
                ", clarificationAccount=" + clarificationAccount +
                ", hitName='" + hitName + '\'' +
                ", relatedCaseNumber='" + relatedCaseNumber + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
