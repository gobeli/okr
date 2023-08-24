package ch.puzzle.okr.service.persistence;

import ch.puzzle.okr.models.Objective;
import ch.puzzle.okr.models.Quarter;
import ch.puzzle.okr.models.Team;
import ch.puzzle.okr.models.User;
import ch.puzzle.okr.test.SpringIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringIntegrationTest
public class ObjectivePersistenceServiceIT {
    Objective createdObjective;
    @Autowired
    private ObjectivePersistenceService objectivePersistenceService;

    private static Objective createObjective(Long id) {
        return Objective.Builder.builder().withId(id).withTitle("title")
                .withCreatedBy(User.Builder.builder().withId(1L).build())
                .withTeam(Team.Builder.builder().withId(5L).build())
                .withQuarter(Quarter.Builder.builder().withId(1L).build()).withDescription("This is our description")
                .withProgress(null).withModifiedOn(LocalDateTime.MAX).build();
    }

    @AfterEach
    void tearDown() {
        try {
            if (createdObjective != null) {
                objectivePersistenceService.findById(createdObjective.getId());
                objectivePersistenceService.deleteById(createdObjective.getId());
            }
        } catch (ResponseStatusException ex) {
            // created key result already deleted
        } finally {
            createdObjective = null;
        }
    }

    @Test
    void findAll_ShouldReturnListOfObjectives() {
        List<Objective> objectives = objectivePersistenceService.findAll();

        assertEquals(7, objectives.size());
    }

    @Test
    void findById_ShouldReturnObjectiveProperly() {
        Objective objective = objectivePersistenceService.findById(5L);

        assertEquals(5L, objective.getId());
        assertEquals("Wir wollen das leiseste Team bei Puzzle sein", objective.getTitle());
    }

    @Test
    void findById_ShouldThrowExceptionWhenObjectiveNotFound() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> objectivePersistenceService.findById(321L));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Objective with id 321 not found", exception.getReason());
    }

    @Test
    void findById_ShouldThrowExceptionWhenObjectiveIdIsNull() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> objectivePersistenceService.findById(null));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals("Missing attribute objective id", exception.getReason());
    }

    @Test
    void saveObjective_shouldSaveNewObjectiv() {
        Objective objective = createObjective(null);

        createdObjective = objectivePersistenceService.save(objective);

        assertNotNull(createdObjective.getId());
        assertEquals(objective.getDescription(), createdObjective.getDescription());
        assertEquals(objective.getProgress(), createdObjective.getProgress());
        assertEquals(objective.getDescription(), createdObjective.getDescription());
        assertEquals(objective.getModifiedOn(), createdObjective.getModifiedOn());
    }

    @Test
    void updateObjective_shouldUpdateObjective() {
        Objective objective = createObjective(null);
        createdObjective = objectivePersistenceService.save(objective);
        createdObjective.setProgress(5L);

        Objective updatedObjective = objectivePersistenceService.save(createdObjective);

        assertEquals(createdObjective.getId(), updatedObjective.getId());
        assertEquals(5L, updatedObjective.getProgress());
    }

    @Test
    void deleteObjective_ShouldThrowExceptionWhenKeyResultNotFound() {
        Objective objective = createObjective(321L);
        createdObjective = objectivePersistenceService.save(objective);
        objectivePersistenceService.deleteById(createdObjective.getId());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> objectivePersistenceService.findById(createdObjective.getId()));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals(String.format("Objective with id %d not found", createdObjective.getId()), exception.getReason());
    }
}
