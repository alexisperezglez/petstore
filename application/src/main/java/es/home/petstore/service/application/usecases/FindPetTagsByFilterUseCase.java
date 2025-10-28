package es.home.petstore.service.application.usecases;

import es.home.petstore.service.application.ports.driving.FindPetTagsByFilterPort;
import es.home.petstore.service.application.ports.driving.cqs.queries.FindPetTagsByFilterQuery;
import es.home.petstore.service.domain.model.pet.PetTag;
import es.home.petstore.service.domain.model.pet.PetTagService;
import es.home.petstore.service.domain.shared.annotations.UseCase;
import es.home.petstore.service.domain.shared.pagination.CursorPagedData;

import java.util.UUID;

@UseCase
public class FindPetTagsByFilterUseCase implements FindPetTagsByFilterPort {

  private final PetTagService petTagService;

  public FindPetTagsByFilterUseCase(PetTagService petTagService) {
    this.petTagService = petTagService;
  }

  @Override
  public CursorPagedData<PetTag> findAllBy(FindPetTagsByFilterQuery query) {
    String filter = query.getValue();
    int pageSize = query.getPageSize();
    UUID cursor = query.getCursor();
    return petTagService.findAllBy(filter, pageSize, cursor);
  }

}
