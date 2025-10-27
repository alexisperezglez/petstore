package es.home.petstore.service.jparepository.adapters;

import es.home.petstore.service.domain.model.pet.PetTag;
import es.home.petstore.service.domain.model.pet.PetTagId;
import es.home.petstore.service.domain.model.pet.PetTagRepository;
import es.home.petstore.service.jparepository.mappers.TagMOMapper;
import es.home.petstore.service.jparepository.model.TagMO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class PetTagRepositoryAdapter implements PetTagRepository {

  // TODO: private final TagMOJpaRepository jpaRepository;
  private final TagMOMapper mapper;

  @Override
  public PetTag saveOrUpdate(PetTag petTag) {
    final TagMO entity = mapper.fromDomain(petTag);
    // TODO: jpaRepository.save(entity);
    return mapper.toDomain(entity);
  }

  @Override
  public Optional<PetTag> findById(PetTagId id) {
    // TODO: return jpaRepository.findById(id.id()).map(mapper::toDomain);
    return Optional.empty();
  }

  @Override
  public void deleteById(PetTagId id) {
    // TODO: jpaRepository.deleteById(id.id());
  }
}
