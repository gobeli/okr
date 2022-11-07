package ch.puzzle.okr.mapper;

import ch.puzzle.okr.dto.goal.GoalDto;
import ch.puzzle.okr.dto.goal.GoalKeyResultDto;
import ch.puzzle.okr.dto.goal.GoalObjectiveDto;
import ch.puzzle.okr.dto.goal.GoalUserDto;
import ch.puzzle.okr.models.KeyResult;
import ch.puzzle.okr.models.Objective;
import org.springframework.stereotype.Service;

@Service
public class GoalMapper {
    public GoalDto toDto(KeyResult keyResult) {
        Objective objective = keyResult.getObjective();
        return new GoalDto(new GoalObjectiveDto(objective.getId(), objective.getTitle(),
                objective.getDescription(), new GoalUserDto(objective.getOwner().getId(),
                objective.getOwner().getFirstname(), objective.getOwner().getFirstname())),
                new GoalKeyResultDto(keyResult.getId(), keyResult.getTitle(), keyResult.getDescription(),
                new GoalUserDto(keyResult.getOwner().getId(), keyResult.getOwner().getFirstname(), keyResult.getOwner().getFirstname())),
                objective.getTeam(), objective.getProgress(),
                keyResult.getQuarter().getNumber(), keyResult.getQuarter().getYear(), keyResult.getExpectedEvolution(),
                keyResult.getUnit(), keyResult.getBasisValue(), keyResult.getTargetValue());
    }
}
