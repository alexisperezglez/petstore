package es.home.petstore.service.config;

import es.home.petstore.service.application.ports.driving.RegisterPetTagPort;
import es.home.petstore.service.application.usecases.RegisterPetTagUseCase;
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

}
