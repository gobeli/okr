package ch.puzzle.okr.controller;

import ch.puzzle.okr.dto.KeyResultDto;
import ch.puzzle.okr.dto.KeyResultMeasureDto;
import ch.puzzle.okr.dto.MeasureDto;
import ch.puzzle.okr.dto.ObjectiveDto;
import ch.puzzle.okr.mapper.KeyResultMapper;
import ch.puzzle.okr.mapper.MeasureMapper;
import ch.puzzle.okr.mapper.ObjectiveMapper;
import ch.puzzle.okr.models.*;
import ch.puzzle.okr.service.business.KeyResultBusinessService;
import ch.puzzle.okr.service.business.ObjectiveBusinessService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(value = "spring")
@ExtendWith(MockitoExtension.class)
@WebMvcTest(ObjectiveController.class)
class ObjectiveControllerIT {

    static Objective objective1 = Objective.Builder.builder().withId(5L).withTitle("Objective 1").build();
    static Objective objective2 = Objective.Builder.builder().withId(7L).withTitle("Objective 2").build();
    static User user = User.Builder.builder().withId(1L).withFirstname("Bob").withLastname("Kaufmann")
            .withUsername("bkaufmann").withEmail("kaufmann@puzzle.ch").build();
    static Team team = Team.Builder.builder().withId(1L).withName("Team1").build();
    static Quarter quarter = Quarter.Builder.builder().withId(1L).withLabel("GJ 22/23-Q2").build();
    static Objective fullObjective = Objective.Builder.builder().withId(42L).withTitle("FullObjective")
            .withCreatedBy(user).withTeam(team).withQuarter(quarter).withDescription("This is our description")
            .withProgress(33L).withModifiedOn(LocalDateTime.MAX).build();
    static ObjectiveDto objective1Dto = new ObjectiveDto(5L, "Objective 1", 1L, 1L, "This is a description",
            State.DRAFT, LocalDateTime.MAX, LocalDateTime.MAX);
    static ObjectiveDto objective2Dto = new ObjectiveDto(7L, "Objective 2", 1L, 1L, "This is a description",
            State.DRAFT, LocalDateTime.MIN, LocalDateTime.MIN);
    static KeyResult keyResult1 = KeyResult.Builder.builder().withId(5L).withTitle("Keyresult 1").build();
    static KeyResult keyResult2 = KeyResult.Builder.builder().withId(7L).withTitle("Keyresult 2").build();
    static MeasureDto measure1Dto = new MeasureDto(1L, 5L, 10D, "foo", "boo", 1L, null,
            Instant.parse("2022-08-12T01:01:00.00Z"));
    static MeasureDto measure2Dto = new MeasureDto(2L, 7L, 10D, "foo", "boo", 1L, null,
            Instant.parse("2022-08-12T01:01:00.00Z"));
    static List<KeyResultMeasureDto> keyResultsMeasureList = List.of(
            new KeyResultMeasureDto(5L, 1L, "Keyresult 1", "Description", 1L, "Paco", "Egiman",
                    ExpectedEvolution.CONSTANT, Unit.PERCENT, 20D, 100D, measure1Dto, 0L),
            new KeyResultMeasureDto(7L, 1L, "Keyresult 2", "Description", 1L, "Robin", "Papier",
                    ExpectedEvolution.CONSTANT, Unit.PERCENT, 20D, 100D, measure2Dto, 0L));
    static KeyResultDto keyresult1Dto = new KeyResultDto(5L, 1L, "Keyresult 1", "Description", 1L, "Alice",
            "Wunderland", ExpectedEvolution.CONSTANT, Unit.PERCENT, 20D, 100D, 0L);
    static KeyResultDto keyresult2Dto = new KeyResultDto(7L, 1L, "Keyresult 2", "Description", 1L, "Alice",
            "Wunderland", ExpectedEvolution.CONSTANT, Unit.PERCENT, 20D, 100D, 0L);

    @Autowired
    private MockMvc mvc;
    @MockBean
    private ObjectiveBusinessService objectiveBusinessService;
    @MockBean
    private KeyResultBusinessService keyResultBusinessService;
    @MockBean
    private ObjectiveMapper objectiveMapper;
    @MockBean
    private KeyResultMapper keyResultMapper;
    @MockBean
    private MeasureMapper measureMapper;

    @BeforeEach
    void setUp() {
        BDDMockito.given(objectiveMapper.toDto(objective1)).willReturn(objective1Dto);
        BDDMockito.given(objectiveMapper.toDto(objective2)).willReturn(objective2Dto);
        BDDMockito.given(keyResultMapper.toDto(keyResult1)).willReturn(keyresult1Dto);
        BDDMockito.given(keyResultMapper.toDto(keyResult2)).willReturn(keyresult2Dto);
    }

    @Test
    void shouldReturnErrorWhenObjectiveDoesntExistWhenGettingKeyResults() throws Exception {
        BDDMockito.given(keyResultBusinessService.getAllKeyResultsByObjectiveWithMeasure(1L))
                .willThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Objective with id 1 not found"));

        mvc.perform(get("/api/v2/objectives/1/keyresults").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound()).andExpect(status().isNotFound());
    }

    @Test
    void getObjective() throws Exception {
        BDDMockito.given(objectiveBusinessService.getObjectiveById(5L)).willReturn(objective1);

        mvc.perform(get("/api/v2/objectives/5").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("$.id", Is.is(5)))
                .andExpect(jsonPath("$.title", Is.is("Objective 1")));
    }

    @Test
    void getObjectiveFail() throws Exception {
        BDDMockito.given(objectiveBusinessService.getObjectiveById(10L))
                .willThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));
        mvc.perform(get("/api/v2/objectives/10").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void shouldReturnObjectiveWhenCreatingNewObjective() throws Exception {
        ObjectiveDto testObjective = new ObjectiveDto(null, "Program Faster", 1L, 1L, "Just be faster", State.DRAFT,
                LocalDateTime.MAX, LocalDateTime.MAX);

        BDDMockito.given(objectiveMapper.toDto(any())).willReturn(testObjective);
        BDDMockito.given(objectiveBusinessService.createObjective(any(), any())).willReturn(fullObjective);

        mvc.perform(post("/api/v2/objectives").contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.csrf()).content(
                        "{\"title\": \"FullObjective\", \"ownerId\": 1, \"ownerFirstname\": \"Bob\", \"ownerLastname\": \"Kaufmann\", \"teamId\": 1, \"teamName\": \"Team1\", \"quarterId\": 1, \"quarterNumber\": 3, \"quarterYear\": 2020, \"description\": \"This is our description\", \"progress\": 33.3}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string(
                        "{\"id\":null,\"title\":\"Program Faster\",\"ownerId\":1,\"ownerFirstname\":\"Rudi\",\"ownerLastname\":\"Grochde\",\"teamId\":3,\"teamName\":\"PuzzleITC\",\"quarterId\":1,\"quarterLabel\":\"GJ 22/23-Q2\",\"description\":\"Just be faster\",\"progress\":0}"));
        verify(objectiveBusinessService, times(1)).createObjective(any(), any());
    }

    @Test
    void shouldReturnResponseStatusExceptionWhenCreatingObjectiveWithNullValues() throws Exception {
        BDDMockito.given(objectiveBusinessService.createObjective(any(), any())).willThrow(
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing attribute title when creating objective"));

        mvc.perform(post("/api/v2/objectives").contentType(MediaType.APPLICATION_JSON).content(
                "{\"title\": null, \"ownerId\": 1, \"ownerFirstname\": \"Bob\", \"ownerLastname\": \"Kaufmann\", \"teamId\": 1, \"teamName\": \"Team1\", \"quarterId\": 1, \"quarterNumber\": 3, \"quarterYear\": 2020, \"description\": \"This is our description\", \"progress\": 33.3}")
                .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldReturnUpdatedObjective() throws Exception {
        ObjectiveDto testObjective = new ObjectiveDto(1L, "Hunting", 1L, 1L, "Everything Fine", State.NOTSUCCESSFUL,
                LocalDateTime.MIN, LocalDateTime.MAX);
        Objective objective = Objective.Builder.builder().withId(1L).withDescription("Everything Fine").withProgress(5L)
                .withTitle("Hunting").build();

        BDDMockito.given(objectiveMapper.toDto(any())).willReturn(testObjective);
        BDDMockito.given(objectiveBusinessService.updateObjective(anyLong(), any(), any())).willReturn(objective);

        mvc.perform(put("/api/v2/objectives/10").contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"FullObjective\", \"ownerId\": 1, \"ownerFirstname\": \"Bob\", "
                        + "\"ownerLastname\": \"Kaufmann\", \"teamId\": 1, \"teamName\": \"Team1\", "
                        + "\"quarterId\": 1, \"quarterNumber\": 3, \"quarterYear\": 2020, "
                        + "\"description\": \"This is our description\", \"progress\": 33.3}")
                .with(SecurityMockMvcRequestPostProcessors.csrf())).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id", Is.is(1))).andExpect(jsonPath("$.description", Is.is("Everything Fine")))
                .andExpect(jsonPath("$.title", Is.is("Hunting")));
    }

    @Test
    void shouldReturnImUsed() throws Exception {
        ObjectiveDto testObjectiveDto = new ObjectiveDto(1L, "Hunting", 1L, 1L, "Everything Fine", State.SUCCESSFUL,
                LocalDateTime.MAX, LocalDateTime.MAX);
        Objective objective1 = Objective.Builder.builder().withId(1L).withDescription("Everything Fine")
                .withProgress(5L).withQuarter(Quarter.Builder.builder().withId(1L).withLabel("GJ 22/23-Q2").build())
                .withTitle("Hunting").build();

        BDDMockito.given(objectiveMapper.toObjective(any())).willReturn(objective1);
        BDDMockito.given(objectiveMapper.toDto(any())).willReturn(testObjectiveDto);
        BDDMockito.given(objectiveBusinessService.updateObjective(anyLong(), any(), any())).willReturn(objective1);

        mvc.perform(put("/api/v2/objectives/10").contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"FullObjective\", \"ownerId\": 1, \"ownerFirstname\": \"Bob\", "
                        + "\"ownerLastname\": \"Kaufmann\", \"teamId\": 1, \"teamName\": \"Team1\", "
                        + "\"quarterId\": 1, \"quarterNumber\": 3, \"quarterYear\": 2020, "
                        + "\"description\": \"This is our description\", \"progress\": 33.3}")
                .with(SecurityMockMvcRequestPostProcessors.csrf())).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldReturnNotFound() throws Exception {
        BDDMockito.given(objectiveBusinessService.updateObjective(anyLong(), any(), any())).willThrow(
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Failed objective -> Attribut is invalid"));

        mvc.perform(put("/api/v2/objectives/10").contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"FullObjective\", \"ownerId\": 1, \"ownerFirstname\": \"Bob\", "
                        + "\"ownerLastname\": \"Kaufmann\", \"teamId\": 1, \"teamName\": \"Team1\", "
                        + "\"quarterId\": 1, \"quarterNumber\": 3, \"quarterYear\": 2020, "
                        + "\"description\": \"This is our description\", \"progress\": 33.3}")
                .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void shouldReturnBadRequest() throws Exception {
        BDDMockito.given(objectiveBusinessService.updateObjective(anyLong(), any(), any())).willThrow(
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed objective -> Attribut is invalid"));

        mvc.perform(put("/api/v2/objectives/10").with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldDeleteObjective() throws Exception {
        mvc.perform(delete("/api/v2/objectives/10").with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void throwExceptionWhenObjectiveWithIdCantBeFoundWhileDeleting() throws Exception {
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Objective not found")).when(objectiveBusinessService)
                .deleteObjectiveById(anyLong());

        mvc.perform(delete("/api/v2/objectives/1000").with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
