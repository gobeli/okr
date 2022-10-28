package ch.puzzle.okr.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "keyResult")
public class KeyResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_keyResult")
    @NotNull
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "objective_id")
    private Objective objective;

    @NotBlank
    @Size(min = 3, max = 20)
    private String title;

    @Size(max = 400)
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "quarter_id")
    private Quarter quarter;

    private Integer expectedEvolution;

    @NotNull
    @NotBlank
    private String unit;

    @NotNull
    private Integer basisValue;

    @NotNull
    private Integer targetValue;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @NotNull
    private LocalDateTime createdOn;

    public KeyResult() {
    }

    private KeyResult(Builder builder) {
        id = builder.id;
        objective = builder.objective;
        title = builder.title;
        description = builder.description;
        owner = builder.owner;
        quarter = builder.quarter;
        expectedEvolution = builder.expectedEvolution;
        unit = builder.unit;
        basisValue = builder.basisValue;
        targetValue = builder.targetValue;
        createdBy = builder.createdBy;
        createdOn = builder.createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyResult keyResult = (KeyResult) o;
        return Objects.equals(id, keyResult.id) && Objects.equals(objective, keyResult.objective) && Objects.equals(title, keyResult.title) && Objects.equals(description, keyResult.description) && Objects.equals(owner, keyResult.owner) && Objects.equals(quarter, keyResult.quarter) && Objects.equals(expectedEvolution, keyResult.expectedEvolution) && Objects.equals(unit, keyResult.unit) && Objects.equals(basisValue, keyResult.basisValue) && Objects.equals(targetValue, keyResult.targetValue) && Objects.equals(createdBy, keyResult.createdBy) && Objects.equals(createdOn, keyResult.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, objective, title, description, owner, quarter, expectedEvolution, unit, basisValue, targetValue, createdBy, createdOn);
    }

    @Override
    public String toString() {
        return "KeyResult{" +
                "id=" + id +
                ", objective=" + objective +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", quarter=" + quarter +
                ", expectedEvolution=" + expectedEvolution +
                ", unit='" + unit + '\'' +
                ", basisValue=" + basisValue +
                ", targetValue=" + targetValue +
                ", createdBy=" + createdBy +
                ", createdOn=" + createdOn +
                '}';
    }


    public static final class Builder {
        private @NotNull Long id;
        private @NotNull Objective objective;
        private @NotBlank @Size(min = 3, max = 20) String title;
        private @Size(max = 400) String description;
        private @NotNull User owner;
        private @NotNull Quarter quarter;
        private Integer expectedEvolution;
        private @NotNull @NotBlank String unit;
        private @NotNull Integer basisValue;
        private @NotNull Integer targetValue;
        private @NotNull User createdBy;
        private @NotNull LocalDateTime createdOn;

        public Builder() {
        }

        public Builder id(@NotNull Long val) {
            id = val;
            return this;
        }

        public Builder objective(@NotNull Objective val) {
            objective = val;
            return this;
        }

        public Builder title(@NotBlank @Size(min = 3, max = 20) String val) {
            title = val;
            return this;
        }

        public Builder description(@Size(max = 400) String val) {
            description = val;
            return this;
        }

        public Builder owner(@NotNull User val) {
            owner = val;
            return this;
        }

        public Builder quarter(@NotNull Quarter val) {
            quarter = val;
            return this;
        }

        public Builder expectedEvolution(Integer val) {
            expectedEvolution = val;
            return this;
        }

        public Builder unit(@NotNull @NotBlank String val) {
            unit = val;
            return this;
        }

        public Builder basisValue(@NotNull Integer val) {
            basisValue = val;
            return this;
        }

        public Builder targetValue(@NotNull Integer val) {
            targetValue = val;
            return this;
        }

        public Builder createdBy(@NotNull User val) {
            createdBy = val;
            return this;
        }

        public Builder createdOn(@NotNull LocalDateTime val) {
            createdOn = val;
            return this;
        }

        public KeyResult build() {
            return new KeyResult(this);
        }
    }
}
