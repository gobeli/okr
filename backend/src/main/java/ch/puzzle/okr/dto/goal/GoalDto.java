package ch.puzzle.okr.dto.goal;

import ch.puzzle.okr.models.ExpectedEvolution;
import ch.puzzle.okr.models.Unit;

public class GoalDto {
    private ObjectiveDto objective;
    private KeyresultDto keyresult;
    private Long teamId;
    private String teamName;
    private Double progress;
    private Integer quarterNumber;
    private Integer quarterYear;
    private ExpectedEvolution expectedEvolution;
    private Unit unit;
    private Long basicValue;
    private Long targetValue;

    public GoalDto(ObjectiveDto objective, KeyresultDto keyresult, Long teamId, String teamName, Double progress,
                   Integer quarterNumber, Integer quarterYear, ExpectedEvolution expectedEvolution, Unit unit,
                   Long basicValue, Long targetValue) {
        this.objective = objective;
        this.keyresult = keyresult;
        this.teamId = teamId;
        this.teamName = teamName;
        this.progress = progress;
        this.quarterNumber = quarterNumber;
        this.quarterYear = quarterYear;
        this.expectedEvolution = expectedEvolution;
        this.unit = unit;
        this.basicValue = basicValue;
        this.targetValue = targetValue;
    }

    public ObjectiveDto getObjective() {
        return objective;
    }

    public void setObjective(ObjectiveDto objective) {
        this.objective = objective;
    }

    public KeyresultDto getKeyresult() {
        return keyresult;
    }

    public void setKeyresult(KeyresultDto keyresult) {
        this.keyresult = keyresult;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Integer getQuarterNumber() {
        return quarterNumber;
    }

    public void setQuarterNumber(Integer quarterNumber) {
        this.quarterNumber = quarterNumber;
    }

    public Integer getQuarterYear() {
        return quarterYear;
    }

    public void setQuarterYear(Integer quarterYear) {
        this.quarterYear = quarterYear;
    }

    public ExpectedEvolution getExpectedEvolution() {
        return expectedEvolution;
    }

    public void setExpectedEvolution(ExpectedEvolution expectedEvolution) {
        this.expectedEvolution = expectedEvolution;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Long getBasicValue() {
        return basicValue;
    }

    public void setBasicValue(Long basicValue) {
        this.basicValue = basicValue;
    }

    public Long getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(Long targetValue) {
        this.targetValue = targetValue;
    }
}
