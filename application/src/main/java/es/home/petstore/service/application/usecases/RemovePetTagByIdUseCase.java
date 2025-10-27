package es.home.petstore.service.application.usecases;

import es.home.petstore.service.application.ports.driving.RemovePetTagByIdPort;
import es.home.petstore.service.application.ports.driving.cqs.commands.RemovePetTagByIdCommand;
import es.home.petstore.service.domain.model.pet.PetTagId;
import es.home.petstore.service.domain.model.pet.PetTagService;
import es.home.petstore.service.domain.shared.annotations.UseCase;

@UseCase
public class RemovePetTagByIdUseCase implements RemovePetTagByIdPort {

  private final PetTagService petTagService;

  public RemovePetTagByIdUseCase(PetTagService petTagService) {
    this.petTagService = petTagService;
  }

  @Override
  public void removeById(RemovePetTagByIdCommand command) {
    if (command == null)
      throw new IllegalArgumentException("Command cannot be null");

    final PetTagId petTagId = PetTagId.of(command.getId());
    petTagService.deleteBy(petTagId);
  }

}
