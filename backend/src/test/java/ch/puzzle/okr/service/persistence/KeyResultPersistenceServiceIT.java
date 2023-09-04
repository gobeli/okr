package ch.puzzle.okr.service.persistence;

import ch.puzzle.okr.models.Objective;
import ch.puzzle.okr.models.State;
import ch.puzzle.okr.models.User;
import ch.puzzle.okr.models.keyresult.KeyResult;
import ch.puzzle.okr.models.keyresult.KeyResultMetric;
import ch.puzzle.okr.models.keyresult.KeyResultOrdinal;
import ch.puzzle.okr.test.SpringIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringIntegrationTest
public class KeyResultPersistenceServiceIT {
    KeyResult createdKeyResult;
    User user;
    Objective objective;
    KeyResult metricKeyResult;
    KeyResult ordinalKeyResult;
    @Autowired
    private KeyResultPersistenceService keyResultPersistenceService;

    private static KeyResult createKeyResult(Long id) {
        User user = User.Builder.builder().withId(1L).withUsername("johnny").withFirstname("Johnny")
                .withLastname("Appleseed").withEmail("appleseed@puzzle.ch").build();
        Objective objective = Objective.Builder.builder().withId(4L).withCreatedOn(LocalDateTime.MIN)
                .withState(State.DRAFT).withTitle("Objective").build();

        return KeyResultMetric.Builder.builder().withBaseline(4.0).withStretchGoal(7.0).withUnit("PERCENT").withId(id)
                .withTitle("Keyresult 1").withObjective(objective).withOwner(user).withCreatedBy(user)
                .withModifiedOn(LocalDateTime.now()).withCreatedOn(LocalDateTime.now()).build();
    }

    @BeforeEach
    void setup() {
        this.user = User.Builder.builder().withId(1L).withEmail("newMail@tese.com").build();

        this.objective = Objective.Builder.builder().withId(5L).withTitle("Objective 1").build();

        this.metricKeyResult = KeyResultMetric.Builder.builder().withBaseline(4.0).withStretchGoal(7.0).withId(5L)
                .withTitle("Keyresult Metric").withObjective(this.objective).withOwner(this.user).build();
        this.ordinalKeyResult = KeyResultOrdinal.Builder.builder().withCommitZone("Baum").withStretchZone("Wald")
                .withId(7L).withTitle("Keyresult Ordinal").withObjective(this.objective).withOwner(this.user).build();
    }

    @AfterEach
    void tearDown() {
        try {
            if (createdKeyResult != null) {
                keyResultPersistenceService.findById(createdKeyResult.getId());
                keyResultPersistenceService.deleteById(createdKeyResult.getId());
            }
        } catch (ResponseStatusException ex) {
            // created key result already deleted
        } finally {
            createdKeyResult = null;
        }
    }

    @Test
    void saveKeyResult_ShouldSaveNewKeyResult() {
        KeyResult keyResult = createKeyResult(null);

        createdKeyResult = keyResultPersistenceService.save(keyResult);

        assertNotNull(createdKeyResult.getId());
        assertEquals(keyResult.getModifiedOn(), createdKeyResult.getModifiedOn());
        assertEquals(keyResult.getTitle(), createdKeyResult.getTitle());
        assertEquals(keyResult.getObjective(), createdKeyResult.getObjective());
        assertEquals(keyResult.getOwner(), createdKeyResult.getOwner());
        assertEquals(keyResult.getDescription(), createdKeyResult.getDescription());
    }

    @Test
    void getKeyResultById_ShouldReturnKeyResultProperly() {
        KeyResult keyResult = keyResultPersistenceService.findById(3L);

        assertEquals(3L, keyResult.getId());
        assertEquals("Steigern der URS um 25%", keyResult.getTitle());
    }

    @Test
    void getKeyResultById_ShouldThrowExceptionWhenKeyResultNotFound() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> keyResultPersistenceService.findById(321L));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Key result with id 321 not found", exception.getReason());
    }

    @Test
    void getKeyResultById_ShouldThrowExceptionWhenKeyResultIdIsNull() {
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> keyResultPersistenceService.findById(null));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals("Missing attribute key result id", exception.getReason());
    }

    @Test
    void updateKeyResult_ShouldUpdateKeyResult() {
        KeyResult keyResult = createKeyResult(null);
        createdKeyResult = keyResultPersistenceService.save(keyResult);
        createdKeyResult.setTitle("Updated Key Result");

        KeyResult updatedKeyResult = keyResultPersistenceService.save(createdKeyResult);

        assertEquals(createdKeyResult.getId(), updatedKeyResult.getId());
        assertEquals("Updated Key Result", updatedKeyResult.getTitle());
    }

    @Test
    void updateKeyResult_ShouldThrowExceptionWhenKeyResultNotFound() {
        KeyResult keyResult = createKeyResult(321L);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> keyResultPersistenceService.save(keyResult));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Could not find key result with id 321", exception.getReason());
    }

    @Test
    void getKeyResultsByObjective_ShouldReturnListOfKeyResults() {
        List<KeyResult> keyResultsByObjective = keyResultPersistenceService
                .getKeyResultsByObjective(Objective.Builder.builder().withId(3L).build());

        assertEquals(3, keyResultsByObjective.size());
    }

    @Test
    void deleteKeyResultById_ShouldDeleteExistingKeyResult() {
        KeyResult keyResult = createKeyResult(null);
        createdKeyResult = keyResultPersistenceService.save(keyResult);
        keyResultPersistenceService.deleteById(createdKeyResult.getId());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> keyResultPersistenceService.findById(createdKeyResult.getId()));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals(String.format("Key result with id %d not found", createdKeyResult.getId()), exception.getReason());
    }
}
