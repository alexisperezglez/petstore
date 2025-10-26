package es.home.petstore.service.jparepository.adapters;

import es.home.petstore.service.domain.model.pet.PetTag;
import es.home.petstore.service.domain.model.pet.PetTagId;
import es.home.petstore.service.domain.model.pet.PetTagRepository;
import es.home.petstore.service.jparepository.mappers.TagMOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class PetTagRepositoryAdapter implements PetTagRepository {

  private final TagMOMapper tagMOMapper;

  @Override
  public PetTag saveOrUpdate(PetTag petTag) {
    return null;
  }

  @Override
  public Optional<PetTag> findById(PetTagId id) {
    return Optional.empty();
  }

  @Override
  public void deleteById(PetTagId id) {
    // TODO: Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
  }
}
