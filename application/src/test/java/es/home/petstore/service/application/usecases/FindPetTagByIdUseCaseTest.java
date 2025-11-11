package es.home.petstore.service.application.usecases;

import es.home.petstore.service.application.ports.driving.cqs.queries.FindPetTagByIdQuery;
import es.home.petstore.service.domain.exceptions.PetTagNotFoundException;
import es.home.petstore.service.domain.model.pet.PetTag;
import es.home.petstore.service.domain.model.pet.PetTagId;
import es.home.petstore.service.domain.model.pet.PetTagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindPetTagByIdUseCaseTest {

  @InjectMocks
  private FindPetTagByIdUseCase useCase;

  @Mock
  private PetTagService petTagService;

  private FindPetTagByIdQuery query;

  @BeforeEach
  void setUp() {
    query = new FindPetTagByIdQuery(UUID.randomUUID());
  }

  @Test
  @DisplayName("Find pet tag by id returns pet tag")
  void findPetTagByIdReturnsPetTag() {
    // WHEN
    String tagValue = "test";
    PetTagId petTagId = PetTagId.of(query.getId());
    PetTag tag = new PetTag(petTagId, tagValue);
    when(petTagService.findBy(any(PetTagId.class))).thenReturn(Optional.of(tag));
    PetTag petTag = useCase.findBy(query);

    // THEN
    checkPetTagIdArgument();
    assertEquals(query.getId(), petTag.getId().id(), "Pet tag id should match");
    assertEquals(tagValue.toUpperCase(), petTag.getValue(), "Pet tag name should match");
  }

  private void checkPetTagIdArgument() {
    ArgumentCaptor<PetTagId> petTagIdArgCaptor = ArgumentCaptor.forClass(PetTagId.class);
    verify(petTagService).findBy(petTagIdArgCaptor.capture());

    assertEquals(query.getId(), petTagIdArgCaptor.getValue().id(), "Pet tag id should match");
  }

  @Nested
  @DisplayName("Find pet tag by id throws exception")
  class FindPetTagByIdThrowsException {

    @Test
    @DisplayName("Find pet tag by id throws exception when query is null")
    void findPetTagByIdThrowsExceptionWhenQueryIsNull() {
      assertThrows(IllegalArgumentException.class, () -> useCase.findBy(null));
    }

    @Test
    @DisplayName("Find pet tag by id throws exception when pet tag is not found")
    void findPetTagByIdThrowsExceptionWhenPetTagIsNotFound() {
      // WHEN
      when(petTagService.findBy(any(PetTagId.class))).thenReturn(Optional.empty());
      assertThrows(PetTagNotFoundException.class, () -> useCase.findBy(query));

      // THEN
      checkPetTagIdArgument();
    }

    @Test
    @DisplayName("Find pet tag by id throws exception when find pet tag by id fails")
    void findPetTagByIdThrowsExceptionWhenFindPetTagByIdFails() {
      // WHEN
      when(petTagService.findBy(any(PetTagId.class))).thenThrow(new RuntimeException("Find pet tag by id failed"));
      assertThrows(RuntimeException.class, () -> useCase.findBy(query));

      // THEN
      checkPetTagIdArgument();
    }
  }
}
