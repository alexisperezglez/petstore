package es.home.petstore.service.application.usecases;

import es.home.petstore.service.application.ports.driving.RegisterPetTagPort;
import es.home.petstore.service.application.ports.driving.cqs.commands.RegisterPetTagCommand;
import es.home.petstore.service.domain.model.pet.PetTag;
import es.home.petstore.service.domain.model.pet.PetTagService;
import es.home.petstore.service.domain.shared.annotations.UseCase;

@UseCase
public class RegisterPetTagUseCase implements RegisterPetTagPort {

  private final PetTagService petTagService;

  public RegisterPetTagUseCase(PetTagService petTagService) {
    this.petTagService = petTagService;
  }

  @Override
  public PetTag register(RegisterPetTagCommand command) {
    if (command == null)
      throw new IllegalArgumentException("RegisterPetTagCommand cannot be null");

    final PetTag petTag = PetTag.createFromValue(command.getValue());
    return petTagService.saveOrUpdate(petTag);
  }

}
