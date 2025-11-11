package es.home.petstore.service.application.usecases;

import es.home.petstore.service.application.ports.driving.cqs.queries.FindPetTagsByFilterQuery;
import es.home.petstore.service.domain.model.pet.PetTagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindPetTagsByFilterUseCaseTest {

  @InjectMocks
  private FindPetTagsByFilterUseCase useCase;

  @Mock
  private PetTagService petTagService;

  private FindPetTagsByFilterQuery query;

  @BeforeEach
  void setUp() {
    query = new FindPetTagsByFilterQuery("test", 10, null);
  }
}
