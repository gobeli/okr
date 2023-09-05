package ch.puzzle.okr.service.validation;

import ch.puzzle.okr.models.Quarter;
import ch.puzzle.okr.service.persistence.QuarterPersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuarterValidationServiceTest {
    @MockBean
    QuarterPersistenceService quarterPersistenceService = Mockito.mock(QuarterPersistenceService.class);

    @Spy
    @InjectMocks
    private QuarterValidationService validator;

     @Test
     void validateOnSave_ShouldBeSuccessfulWhenValidQuarter() {
         validator.validateOnSave(Quarter.Builder.builder().withId(1L).withLabel("GJ 22/23-Q4").withStartDate(LocalDate.of(2023, 4, 1)).withEndDate(LocalDate.of(2023, 6, 30)).build());
         verify(validator, times(1)).throwExceptionIfModelIsNull(Quarter.Builder.builder().withId(1L).withLabel("GJ 22/23-Q4").withStartDate(LocalDate.of(2023, 4, 1)).withEndDate(LocalDate.of(2023, 6, 30)).build());
         verify(validator, times(1)).validate(Quarter.Builder.builder().withId(1L).withLabel("GJ 22/23-Q4").withStartDate(LocalDate.of(2023, 4, 1)).withEndDate(LocalDate.of(2023, 6, 30)).build());
     }

    @Test
    void validateOnSave_ShouldThrowExceptionWhenAttrsAreNull() {
        Quarter invalidQuarter = Quarter.Builder.builder().withId(null).withLabel(null).withStartDate(null).withEndDate(null).build();
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> validator.validateOnSave(invalidQuarter));

        String errorLabel = "Attribute label can not be null when saving quarter.";
        String errorStartDate = "Attribute startDate can not be null when saving quarter.";
        String errorEndDate = "Attribute endDate can not be null when saving quarter.";

        assertThat(responseStatusException.getReason().strip()).contains(errorLabel);
        assertThat(responseStatusException.getReason().strip()).contains(errorStartDate);
        assertThat(responseStatusException.getReason().strip()).contains(errorEndDate);
     }

    @Test
    void validateActiveQuarterOnGet_ShouldThrowExceptionWhenDateIsNull() {
        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> validator.validateActiveQuarterOnGet(null));

        verify(validator, times(1)).validateActiveQuarterOnGet(null);
        assertEquals("LocalDate can not be null", responseStatusException.getReason());
    }
}
