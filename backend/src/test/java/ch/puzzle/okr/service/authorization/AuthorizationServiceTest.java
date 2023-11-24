package ch.puzzle.okr.service.authorization;

import ch.puzzle.okr.TestHelper;
import ch.puzzle.okr.converter.JwtConverterFactory;
import ch.puzzle.okr.converter.JwtUserConverter;
import ch.puzzle.okr.dto.ErrorDto;
import ch.puzzle.okr.models.*;
import ch.puzzle.okr.models.authorization.AuthorizationUser;
import ch.puzzle.okr.models.checkin.CheckIn;
import ch.puzzle.okr.models.checkin.CheckInMetric;
import ch.puzzle.okr.models.keyresult.KeyResult;
import ch.puzzle.okr.models.keyresult.KeyResultMetric;
import ch.puzzle.okr.service.persistence.ObjectivePersistenceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

import static ch.puzzle.okr.TestConstants.ORGANISATION_FIRST_LEVEL;
import static ch.puzzle.okr.TestHelper.*;
import static ch.puzzle.okr.models.authorization.AuthorizationRole.*;
import static ch.puzzle.okr.service.authorization.AuthorizationService.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTest {
    @InjectMocks
    private AuthorizationService authorizationService;
    @Mock
    AuthorizationRegistrationService authorizationRegistrationService;
    @Mock
    ObjectivePersistenceService objectivePersistenceService;
    @Mock
    JwtConverterFactory jwtConverterFactory;
    @Mock
    JwtUserConverter jwtUserConverter;

    private final User user = defaultUser(null);

    @Test
    void hasRoleReadTeamsDraftShouldReturnTrueWhenContainsRole() {
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(1L), 5L, List.of(READ_TEAMS_DRAFT));
        assertTrue(hasRoleReadTeamsDraft(authorizationUser));
    }

    @Test
    void hasRoleReadTeamsDraftShouldReturnFalseWhenDoesNotContainsRole() {
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(1L), 5L, List.of(READ_TEAM_DRAFT));
        assertFalse(hasRoleReadTeamsDraft(authorizationUser));
    }

    @Test
    void hasRoleReadTeamDraftShouldReturnTrueWhenContainsRole() {
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(1L), 5L, List.of(READ_TEAM_DRAFT));
        assertTrue(hasRoleReadTeamDraft(authorizationUser));
    }

    @Test
    void hasRoleReadTeamDraftShouldReturnFalseWhenDoesNotContainsRole() {
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(1L), 5L, List.of(READ_ALL_PUBLISHED));
        assertFalse(hasRoleReadTeamDraft(authorizationUser));
    }

    @Test
    void hasRoleReadAllPublishedShouldReturnTrueWhenContainsRole() {
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(1L), 5L, List.of(READ_ALL_PUBLISHED));
        assertTrue(hasRoleReadAllPublished(authorizationUser));
    }

    @Test
    void hasRoleReadAllPublishedShouldReturnFalseWhenDoesNotContainsRole() {
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(1L), 5L, List.of());
        assertFalse(hasRoleReadAllPublished(authorizationUser));
    }

    @Test
    void hasRoleReadAllDraftShouldReturnTrueWhenContainsRole() {
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(1L), 5L, List.of(READ_ALL_DRAFT));
        assertTrue(hasRoleReadAllDraft(authorizationUser));
    }

    @Test
    void hasRoleReadAllDraftShouldReturnFalseWhenDoesNotContainsRole() {
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(1L), 5L, List.of());
        assertFalse(hasRoleReadAllDraft(authorizationUser));
    }

    @Test
    void hasRoleWriteAllShouldReturnTrueWhenContainsRole() {
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(1L), 5L, List.of(WRITE_ALL));
        assertTrue(hasRoleWriteAll(authorizationUser));
    }

    @Test
    void hasRoleWriteAllShouldReturnFalseWhenDoesNotContainsRole() {
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(1L), 5L, List.of(WRITE_ALL_TEAMS));
        assertFalse(hasRoleWriteAll(authorizationUser));
    }

    @Test
    void hasRoleWriteAllTeamsShouldReturnTrueWhenContainsRole() {
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(1L), 5L, List.of(WRITE_ALL_TEAMS));
        assertTrue(hasRoleWriteAllTeams(authorizationUser));
    }

    @Test
    void hasRoleWriteAllTeamsShouldReturnFalseWhenDoesNotContainsRole() {
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(1L), 5L, List.of(WRITE_TEAM));
        assertFalse(hasRoleWriteAllTeams(authorizationUser));
    }

    @Test
    void hasRoleWriteTeamShouldReturnTrueWhenContainsRole() {
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(1L), 5L, List.of(WRITE_TEAM));
        assertTrue(hasRoleWriteTeam(authorizationUser));
    }

    @Test
    void hasRoleWriteTeamShouldReturnFalseWhenDoesNotContainsRole() {
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(1L), 5L, List.of());
        assertFalse(hasRoleWriteTeam(authorizationUser));
    }

    @Test
    void getAuthorizationUserShouldReturnAuthorizationUser() {
        User user = defaultUser(null);
        Jwt token = mockJwtToken(user, List.of(ORGANISATION_FIRST_LEVEL));
        AuthorizationUser authorizationUser = defaultAuthorizationUser();
        setSecurityContext(token);

        when(jwtConverterFactory.getJwtUserConverter()).thenReturn(jwtUserConverter);
        when(jwtUserConverter.convert(token)).thenReturn(user);
        when(authorizationRegistrationService.registerAuthorizationUser(user, token)).thenReturn(authorizationUser);

        assertNotNull(authorizationService.getAuthorizationUser());
    }

    @Test
    void hasRoleReadByObjectiveIdShouldPassThroughWhenPermitted() {
        Long id = 13L;
        AuthorizationUser authorizationUser = defaultAuthorizationUser();
        String reason = "not authorized to read objective";
        ErrorDto error = new ErrorDto(reason, List.of());
        when(objectivePersistenceService.findObjectiveById(id, authorizationUser, error)).thenReturn(new Objective());

        authorizationService.hasRoleReadByObjectiveId(id, authorizationUser);
    }

    @Test
    void hasRoleReadByKeyResultIdShouldThrowExceptionWhenObjectiveNotFound() {
        Long id = 13L;
        AuthorizationUser authorizationUser = defaultAuthorizationUser();
        String reason = "not authorized to read key result";
        ErrorDto error = new ErrorDto(reason, List.of());

        when(objectivePersistenceService.findObjectiveByKeyResultId(id, authorizationUser, error))
                .thenThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED, reason));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> authorizationService.hasRoleReadByKeyResultId(id, authorizationUser));
        assertEquals(UNAUTHORIZED, exception.getStatus());
        assertEquals(reason, exception.getReason());
    }

    @Test
    void hasRoleReadByKeyResultIdShouldPassThroughWhenPermitted() {
        Long id = 13L;
        AuthorizationUser authorizationUser = defaultAuthorizationUser();
        String reason = "not authorized to read key result";
        ErrorDto error = new ErrorDto(reason, List.of());
        when(objectivePersistenceService.findObjectiveByKeyResultId(id, authorizationUser, error))
                .thenReturn(new Objective());

        authorizationService.hasRoleReadByKeyResultId(id, authorizationUser);
    }

    @Test
    void hasRoleReadByCheckInIdShouldThrowExceptionWhenObjectiveNotFound() {
        Long id = 13L;
        AuthorizationUser authorizationUser = defaultAuthorizationUser();
        String reason = "not authorized to read check in";
        ErrorDto error = new ErrorDto(reason, List.of());
        when(objectivePersistenceService.findObjectiveByCheckInId(id, authorizationUser, error))
                .thenThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED, reason));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> authorizationService.hasRoleReadByCheckInId(id, authorizationUser));
        assertEquals(UNAUTHORIZED, exception.getStatus());
        assertEquals(reason, exception.getReason());
    }

    @Test
    void hasRoleReadByCheckInIdShouldPassThroughWhenPermitted() {
        Long id = 13L;
        AuthorizationUser authorizationUser = defaultAuthorizationUser();
        String reason = "not authorized to read check in";
        ErrorDto error = new ErrorDto(reason, List.of());

        when(objectivePersistenceService.findObjectiveByCheckInId(id, authorizationUser, error))
                .thenReturn(new Objective());

        authorizationService.hasRoleReadByCheckInId(id, authorizationUser);
    }

    @Test
    void hasRoleCreateOrUpdateShouldPassThroughWhenAuthorizedForAllObjectives() {
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(5L).build()).build();
        AuthorizationUser authorizationUser = defaultAuthorizationUser();

        authorizationService.hasRoleCreateOrUpdate(objective, authorizationUser);
    }

    @Test
    void hasRoleCreateOrUpdateShouldPassThroughWhenAuthorizedForAllTeamsKeyResults() {
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(1L).build()).build();
        KeyResult keyResult = KeyResultMetric.Builder.builder().withObjective(objective).build();
        AuthorizationUser authorizationUser = mockAuthorizationUser(defaultUser(null), List.of(1L), 5L,
                List.of(WRITE_ALL_TEAMS));
        ErrorDto error = new ErrorDto("NOT_AUTHORIZED_TO_READ", List.of("KeyResult"));

        when(objectivePersistenceService.findObjectiveById(keyResult.getObjective().getId(), authorizationUser, error))
                .thenReturn(objective);

        authorizationService.hasRoleCreateOrUpdate(keyResult, authorizationUser);
    }

    @Test
    void hasRoleCreateOrUpdateShouldPassThroughWhenAuthorizedForTeamCheckIns() {
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(1L).build()).build();
        KeyResult keyResult = KeyResultMetric.Builder.builder().withObjective(objective).build();
        CheckIn checkIn = CheckInMetric.Builder.builder().withKeyResult(keyResult).build();
        AuthorizationUser authorizationUser = mockAuthorizationUser(defaultUser(null), List.of(1L), 5L,
                List.of(WRITE_TEAM));
        ErrorDto error = new ErrorDto("NOT_AUTHORIZED_TO_READ", List.of("Check-in"));

        when(objectivePersistenceService.findObjectiveByKeyResultId(checkIn.getKeyResult().getObjective().getId(),
                authorizationUser, error)).thenReturn(objective);

        authorizationService.hasRoleCreateOrUpdate(checkIn, authorizationUser);
    }

    @Test
    void hasRoleCreateOrUpdateShouldThrowExceptionWhenNotAuthorizedForFirstLevelObjectives() {
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(5L).build()).build();
        AuthorizationUser authorizationUser = mockAuthorizationUser(defaultUser(null), List.of(1L), 5L,
                List.of(WRITE_ALL_TEAMS));

        OkrResponseStatusException exception = assertThrows(OkrResponseStatusException.class,
                () -> authorizationService.hasRoleCreateOrUpdate(objective, authorizationUser));

        List<ErrorDto> expectedErrors = List.of(new ErrorDto("NOT_AUTHORIZED_TO_WRITE", List.of("Objective")));

        assertEquals(UNAUTHORIZED, exception.getStatus());
        assertThat(expectedErrors).hasSameElementsAs(exception.getErrors());
        assertTrue(TestHelper.getAllErrorKeys(expectedErrors).contains(exception.getReason()));
    }

    @Test
    void hasRoleCreateOrUpdateShouldThrowExceptionWhenNotAuthorizedForOtherTeamObjectives() {
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(5L).build()).build();
        AuthorizationUser authorizationUser = mockAuthorizationUser(defaultUser(null), List.of(1L), 5L,
                List.of(WRITE_TEAM));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> authorizationService.hasRoleCreateOrUpdate(objective, authorizationUser));
        assertEquals(UNAUTHORIZED, exception.getStatus());
        assertEquals("not authorized to create or update objective", exception.getReason());
    }

    @Test
    void hasRoleCreateOrUpdateByObjectiveIdShouldPassThroughWhenAuthorizedForAllObjectives() {
        Long id = 13L;
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(5L).build()).build();
        AuthorizationUser authorizationUser = defaultAuthorizationUser();
        ErrorDto error = new ErrorDto("NOT_AUTHORIZED_TO_READ", List.of("Objective"));

        when(objectivePersistenceService.findObjectiveById(id, authorizationUser, error)).thenReturn(objective);

        authorizationService.hasRoleCreateOrUpdateByObjectiveId(id, authorizationUser);
    }

    @Test
    void isWriteableShouldReturnTrueWhenAuthorizedToWriteAllObjectives() {
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(5L).build()).build();
        AuthorizationUser authorizationUser = defaultAuthorizationUser();

        assertTrue(authorizationService.isWriteable(objective, authorizationUser));
    }

    @Test
    void isWriteableShouldReturnFalseWhenNotAuthorizedToWriteObjectives() {
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(5L).build()).build();
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(4L), 13L, List.of(WRITE_TEAM));

        assertFalse(authorizationService.isWriteable(objective, authorizationUser));
    }

    @Test
    void isWriteableShouldReturnTrueWhenAuthorizedToWriteAllKeyResults() {
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(4L).build()).build();
        KeyResult keyResult = KeyResultMetric.Builder.builder().withObjective(objective).build();
        AuthorizationUser authorizationUser = defaultAuthorizationUser();
        ErrorDto error = new ErrorDto("NOT_AUTHORIZED_TO_READ", List.of("KeyResult"));

        when(objectivePersistenceService.findObjectiveById(keyResult.getObjective().getId(), authorizationUser, error))
                .thenReturn(objective);

        assertTrue(authorizationService.isWriteable(keyResult, authorizationUser));
    }

    @Test
    void isWriteableShouldReturnFalseWhenNotAuthorizedToWriteKeyResults() {
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(5L).build()).build();
        KeyResult keyResult = KeyResultMetric.Builder.builder().withObjective(objective).build();
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(4L), 13L, List.of(WRITE_TEAM));
        String reason = "not authorized to read key result";
        ErrorDto error = new ErrorDto("NOT_AUTHORIZED_TO_READ", List.of("KeyResult"));

        when(objectivePersistenceService.findObjectiveById(keyResult.getObjective().getId(), authorizationUser, error))
                .thenReturn(objective);

        assertFalse(authorizationService.isWriteable(keyResult, authorizationUser));
    }

    @Test
    void isWriteableShouldReturnTrueWhenAuthorizedToWriteAllCheckIns() {
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(5L).build()).build();
        KeyResult keyResult = KeyResultMetric.Builder.builder().withObjective(objective).build();
        CheckIn checkIn = CheckInMetric.Builder.builder().withKeyResult(keyResult).build();
        AuthorizationUser authorizationUser = defaultAuthorizationUser();

        when(objectivePersistenceService.findObjectiveByKeyResultId(eq(checkIn.getKeyResult().getId()),
                eq(authorizationUser), any())).thenReturn(objective);

        assertTrue(authorizationService.isWriteable(checkIn, authorizationUser));
    }

    @Test
    void isWriteableShouldReturnFalseWhenNotAuthorizedToWriteCheckIns() {
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(5L).build()).build();
        KeyResult keyResult = KeyResultMetric.Builder.builder().withObjective(objective).build();
        CheckIn checkIn = CheckInMetric.Builder.builder().withKeyResult(keyResult).build();
        AuthorizationUser authorizationUser = mockAuthorizationUser(user, List.of(4L), 13L, List.of(WRITE_TEAM));

        when(objectivePersistenceService.findObjectiveByKeyResultId(eq(checkIn.getKeyResult().getId()),
                eq(authorizationUser), any())).thenReturn(objective);

        assertFalse(authorizationService.isWriteable(checkIn, authorizationUser));
    }

    @Test
    void hasRoleDeleteByObjectiveIdShouldPassThroughWhenAuthorizedForAllObjectives() {
        Long id = 13L;
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(5L).build()).build();
        AuthorizationUser authorizationUser = defaultAuthorizationUser();

        when(objectivePersistenceService.findObjectiveById(eq(id), eq(authorizationUser), any())).thenReturn(objective);

        authorizationService.hasRoleDeleteByObjectiveId(id, authorizationUser);
    }

    @Test
    void hasRoleDeleteByKeyResultIdShouldPassThroughWhenAuthorizedForAllTeamsKeyResults() {
        Long id = 13L;
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(1L).build()).build();
        AuthorizationUser authorizationUser = mockAuthorizationUser(defaultUser(null), List.of(1L), 5L,
                List.of(WRITE_ALL_TEAMS));
        String reason = "not authorized to read key result";

        when(objectivePersistenceService.findObjectiveByKeyResultId(eq(id), eq(authorizationUser), any()))
                .thenReturn(objective);

        authorizationService.hasRoleDeleteByKeyResultId(id, authorizationUser);
    }

    @Test
    void hasRoleDeleteByCheckInIdShouldPassThroughWhenAuthorizedForTeamCheckIns() {
        Long id = 13L;
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(1L).build()).build();
        AuthorizationUser authorizationUser = mockAuthorizationUser(defaultUser(null), List.of(1L), 5L,
                List.of(WRITE_TEAM));
        String reason = "not authorized to read check in";

        when(objectivePersistenceService.findObjectiveByCheckInId(eq(id), eq(authorizationUser), any()))
                .thenReturn(objective);

        authorizationService.hasRoleDeleteByCheckInId(id, authorizationUser);
    }

    @Test
    void hasRoleDeleteByKeyResultIdShouldThrowExceptionWhenNotAuthorizedForFirstLevelObjectives() {
        Long id = 13L;
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(5L).build()).build();
        AuthorizationUser authorizationUser = mockAuthorizationUser(defaultUser(null), List.of(1L), 5L,
                List.of(WRITE_ALL_TEAMS));
        String reason = "not authorized to read key result";
        when(objectivePersistenceService.findObjectiveByKeyResultId(eq(id), eq(authorizationUser), any()))
                .thenReturn(objective);

        OkrResponseStatusException exception = assertThrows(OkrResponseStatusException.class,
                () -> authorizationService.hasRoleDeleteByKeyResultId(id, authorizationUser));

        List<ErrorDto> expectedErrors = List.of(new ErrorDto("NOT_AUTHORIZED_TO_DELETE", List.of("KeyResult")));

        assertEquals(UNAUTHORIZED, exception.getStatus());
        assertThat(expectedErrors).hasSameElementsAs(exception.getErrors());
        assertTrue(TestHelper.getAllErrorKeys(expectedErrors).contains(exception.getReason()));
    }

    @Test
    void hasRoleDeleteByCheckInIdShouldThrowExceptionWhenNotAuthorizedForOtherTeamObjectives() {
        Long id = 13L;
        Objective objective = Objective.Builder.builder().withTeam(Team.Builder.builder().withId(5L).build()).build();
        AuthorizationUser authorizationUser = mockAuthorizationUser(defaultUser(null), List.of(1L), 5L,
                List.of(WRITE_TEAM));

        when(objectivePersistenceService.findObjectiveByCheckInId(eq(id), eq(authorizationUser), any()))
                .thenReturn(objective);

        OkrResponseStatusException exception = assertThrows(OkrResponseStatusException.class,
                () -> authorizationService.hasRoleDeleteByCheckInId(id, authorizationUser));

        List<ErrorDto> expectedErrors = List.of(new ErrorDto("NOT_AUTHORIZED_TO_DELETE", List.of("Check-in")));

        assertEquals(UNAUTHORIZED, exception.getStatus());
        assertThat(expectedErrors).hasSameElementsAs(exception.getErrors());
        assertTrue(TestHelper.getAllErrorKeys(expectedErrors).contains(exception.getReason()));
    }

    private void setSecurityContext(Jwt token) {
        SecurityContextHolder.setContext(new SecurityContextImpl(new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return token;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "unit test authentication";
            }
        }));
    }
}
