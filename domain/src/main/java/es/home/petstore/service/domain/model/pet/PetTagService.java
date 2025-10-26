package es.home.petstore.service.domain.model.pet;

import es.home.petstore.service.domain.exceptions.PetTagNotFoundException;
import es.home.petstore.service.domain.shared.annotations.DomainService;

import java.util.Optional;

@DomainService
public class PetTagService {

  private final PetTagRepository repository;

  public PetTagService(PetTagRepository repository) {
    this.repository = repository;
  }

  public PetTag saveOrUpdate(PetTag petTag) {
    if (petTag == null)
      throw new IllegalArgumentException("PetTag cannot be null");
    return repository.saveOrUpdate(petTag);
  }

  public Optional<PetTag> findBy(PetTagId id) {
    if (id == null)
      throw new IllegalArgumentException("PetTagId cannot be null");
    return repository.findById(id);
  }

  @SuppressWarnings("all")
  public void deleteBy(PetTagId id) {
    if (id == null)
      throw new IllegalArgumentException("PetTagId cannot be null");
    repository.findById(id)
        .ifPresentOrElse(
          _petTag-> repository.deleteById(id),
          () -> { throw new PetTagNotFoundException(id); });
  }

}
