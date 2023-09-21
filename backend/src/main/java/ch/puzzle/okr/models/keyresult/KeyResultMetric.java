package ch.puzzle.okr.models.keyresult;

import ch.puzzle.okr.models.checkin.CheckInOrdinal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@DiscriminatorValue("metric")
public class KeyResultMetric extends KeyResult {
    @NotNull(message = "Baseline must not be null")
    private Double baseline;

    @NotNull(message = "StretchGoal must not be null")
    private Double stretchGoal;

    @NotNull(message = "Unit must not be null")
    private String unit;

    public Double getBaseline() {
        return baseline;
    }

    public void setBaseline(Double baseline) {
        this.baseline = baseline;
    }

    public Double getStretchGoal() {
        return stretchGoal;
    }

    public void setStretchGoal(Double stretchGoal) {
        this.stretchGoal = stretchGoal;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public KeyResultMetric() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof KeyResultMetric) {
            return super.equals(o) && Objects.equals(this.baseline, ((KeyResultMetric) o).baseline)
                    && Objects.equals(this.stretchGoal, ((KeyResultMetric) o).stretchGoal)
                    && Objects.equals(this.unit, ((KeyResultMetric) o).unit);
        }
        return false;
    }

    public KeyResultMetric(Builder builder) {
        super(builder);
        setBaseline(builder.baseline);
        setStretchGoal(builder.stretchGoal);
        setUnit(builder.unit);
    }

    public static class Builder extends KeyResult.Builder {
        private Double baseline;
        private Double stretchGoal;
        private String unit;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder withBaseline(Double baseline) {
            this.baseline = baseline;
            return this;
        }

        public Builder withStretchGoal(Double stretchGoal) {
            this.stretchGoal = stretchGoal;
            return this;
        }

        public Builder withUnit(String unit) {
            this.unit = unit;
            return this;
        }

        @Override
        public KeyResult build() {
            return new KeyResultMetric(this);
        }
    }

}
