package es.home.petstore.service.application.usecases;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import es.home.petstore.service.application.ports.driving.RemovePetTagByIdPort;
import es.home.petstore.service.application.ports.driving.cqs.commands.RemovePetTagByIdCommand;
import es.home.petstore.service.domain.model.pet.PetTagId;
import es.home.petstore.service.domain.model.pet.PetTagService;
import es.home.petstore.service.domain.shared.annotations.UseCase;

@UseCase
@Transactional
public class RemovePetTagByIdUseCase implements RemovePetTagByIdPort {

  private static final Logger log = LoggerFactory.getLogger(RemovePetTagByIdUseCase.class.getName());
  private final PetTagService petTagService;

  public RemovePetTagByIdUseCase(PetTagService petTagService) {
    this.petTagService = petTagService;
  }

  @Override
  public void removeById(RemovePetTagByIdCommand command) {
    log.info("Removing pet tag by id: {}", command);
    if (command == null)
      throw new IllegalArgumentException("Command cannot be null");

    final PetTagId petTagId = PetTagId.of(command.getId());
    log.info("Removed pet tag with id: {}", petTagId);
    petTagService.deleteBy(petTagId);
  }

}
