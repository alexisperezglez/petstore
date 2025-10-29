package es.home.petstore.service.jparepository.adapters;

import es.home.petstore.service.domain.model.pet.PetTag;
import es.home.petstore.service.domain.model.pet.PetTagId;
import es.home.petstore.service.domain.model.pet.PetTagRepository;
import es.home.petstore.service.domain.shared.pagination.CursorPagedData;
import es.home.petstore.service.jparepository.mappers.TagMOMapper;
import es.home.petstore.service.jparepository.model.TagMO;
import es.home.petstore.service.jparepository.repositories.TagMOJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class PetTagRepositoryAdapter implements PetTagRepository {

  private final TagMOJpaRepository jpaRepository;
  private final TagMOMapper mapper;

  @Override
  public PetTag saveOrUpdate(PetTag petTag) {
    final TagMO entity = mapper.fromDomain(petTag);
    final TagMO savedEntity = jpaRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public Optional<PetTag> findById(PetTagId id) {
    return jpaRepository.findById(id.id())
    .map(mapper::toDomain);
  }

  @Override
  public void deleteById(PetTagId id) {
    jpaRepository.deleteById(id.id());
  }

  @Override
  public CursorPagedData<PetTag> findAllBy(String filter, Integer pageSize, UUID cursor) {
    Pageable pageable = Pageable.ofSize(pageSize);
    Page<TagMO> pagedEntities = jpaRepository.findAllBy(filter, cursor, pageable);
    return mapper.toDomain(pagedEntities, pageSize);
  }
  
}
