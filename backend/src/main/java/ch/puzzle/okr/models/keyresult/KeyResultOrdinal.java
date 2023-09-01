package ch.puzzle.okr.models.keyresult;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("ordinal")
public class KeyResultOrdinal extends KeyResult {
    @NotNull(message = "CommitZone must not be null")
    private String commitZone;

    @NotNull(message = "TargetZone must not be null")
    private String targetZone;

    @NotNull(message = "StretchZone must not be null")
    private String stretchZone;

    public String getCommitZone() {
        return commitZone;
    }

    public void setCommitZone(String commitZone) {
        this.commitZone = commitZone;
    }

    public String getTargetZone() {
        return targetZone;
    }

    public void setTargetZone(String targetZone) {
        this.targetZone = targetZone;
    }

    public String getStretchZone() {
        return stretchZone;
    }

    public void setStretchZone(String stretchZone) {
        this.stretchZone = stretchZone;
    }

    public KeyResultOrdinal() {
        super();
    }

    public KeyResultOrdinal(Builder builder) {
        super(builder);
        setCommitZone(builder.commitZone);
        setTargetZone(builder.targetZone);
        setStretchZone(builder.stretchZone);
    }

    public static class Builder extends KeyResult.Builder {
        private String commitZone;
        private String targetZone;
        private String stretchZone;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder withCommitZone(String commitZone) {
            this.commitZone = commitZone;
            return this;
        }

        public Builder withTargetZone(String targetZone) {
            this.targetZone = targetZone;
            return this;
        }

        public Builder withStretchZone(String stretchZone) {
            this.stretchZone = stretchZone;
            return this;
        }

        @Override
        public KeyResult build() {
            return new KeyResultOrdinal(this);
        }
    }
}
