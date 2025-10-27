package es.home.petstore.service.config;

import es.home.petstore.service.application.ports.driving.FindPetTagByIdPort;
import es.home.petstore.service.application.ports.driving.FindPetTagsByFilterPort;
import es.home.petstore.service.application.ports.driving.ModifyPetTagByIdPort;
import es.home.petstore.service.application.ports.driving.RegisterPetTagPort;
import es.home.petstore.service.application.ports.driving.RemovePetTagByIdPort;
import es.home.petstore.service.application.usecases.FindPetTagByIdUseCase;
import es.home.petstore.service.application.usecases.FindPetTagsByFilterUseCase;
import es.home.petstore.service.application.usecases.ModifyPetTagByIdUseCase;
import es.home.petstore.service.application.usecases.RegisterPetTagUseCase;
import es.home.petstore.service.application.usecases.RemovePetTagByIdUseCase;
import es.home.petstore.service.domain.model.pet.PetTagRepository;
import es.home.petstore.service.domain.model.pet.PetTagService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PetTagDIConfig {

  @Bean
  PetTagService petTagService(PetTagRepository petTagRepository) {
    return new PetTagService(petTagRepository);
  }

  @Bean
  RegisterPetTagPort registerPetTagUseCase(PetTagService petTagService) {
    return new RegisterPetTagUseCase(petTagService);
  }

  @Bean
  FindPetTagByIdPort findPetTagByIdUseCase(PetTagService petTagService) {
    return new FindPetTagByIdUseCase(petTagService);
  }

  @Bean
  RemovePetTagByIdPort removePetTagByIdUseCase(PetTagService petTagService) {
    return new RemovePetTagByIdUseCase(petTagService);
  }

  @Bean
  ModifyPetTagByIdPort modifyPetTagByIdUseCase(PetTagService petTagService) {
    return new ModifyPetTagByIdUseCase(petTagService);
  }

  @Bean
  FindPetTagsByFilterPort findPetTagsByFilterUseCase(PetTagService petTagService) {
    return new FindPetTagsByFilterUseCase(petTagService);
  }

}
