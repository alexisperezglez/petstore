package es.home.petstore.service.domain.model.pet;

import es.home.petstore.service.domain.exceptions.PetTagNotFoundException;
import es.home.petstore.service.domain.shared.annotations.DomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@DomainService
public class PetTagService {

  private static final Logger log = LoggerFactory.getLogger(PetTagService.class);

  private final PetTagRepository repository;

  public PetTagService(PetTagRepository repository) {
    this.repository = repository;
  }

  public PetTag saveOrUpdate(PetTag petTag) {
    log.info("Saving or updating pet tag: {}", petTag);
    if (petTag == null)
      throw new IllegalArgumentException("PetTag cannot be null");
    return repository.saveOrUpdate(petTag);
  }

  public Optional<PetTag> findBy(PetTagId id) {
    log.info("Finding pet tag by id: {}", id);
    if (id == null)
      throw new IllegalArgumentException("PetTagId cannot be null");
    return repository.findById(id);
  }

  @SuppressWarnings("all")
  public void deleteBy(PetTagId id) {
    log.info("Deleting pet tag by id: {}", id);
    if (id == null)
      throw new IllegalArgumentException("PetTagId cannot be null");
    repository.findById(id)
        .ifPresentOrElse(
          _petTag-> repository.deleteById(id),
          () -> {
            log.error("Pet tag not found with id: {}", id);
            throw new PetTagNotFoundException(id);
          }
        );
  }

}
