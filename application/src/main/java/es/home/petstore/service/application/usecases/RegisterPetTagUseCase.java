package es.home.petstore.service.application.usecases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import es.home.petstore.service.application.ports.driving.RegisterPetTagPort;
import es.home.petstore.service.application.ports.driving.cqs.commands.RegisterPetTagCommand;
import es.home.petstore.service.domain.model.pet.PetTag;
import es.home.petstore.service.domain.model.pet.PetTagService;
import es.home.petstore.service.domain.shared.annotations.UseCase;

@UseCase
@Transactional
public class RegisterPetTagUseCase implements RegisterPetTagPort {

  private static final Logger log = LoggerFactory.getLogger(RegisterPetTagUseCase.class);
  private final PetTagService petTagService;

  public RegisterPetTagUseCase(PetTagService petTagService) {
    this.petTagService = petTagService;
  }

  @Override
  public PetTag register(RegisterPetTagCommand command) {
    log.info("Registering new pet tag: {}", command);
    if (command == null)
      throw new IllegalArgumentException("RegisterPetTagCommand cannot be null");

    final PetTag petTag = PetTag.createFromValue(command.getValue());
    log.info("Saving pet tag: {}", petTag);
    return petTagService.saveOrUpdate(petTag);
  }

}
