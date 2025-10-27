package es.home.petstore.service.restapi.adapters;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.home.petstore.service.application.ports.driving.FindPetTagByIdPort;
import es.home.petstore.service.application.ports.driving.FindPetTagsByFilterPort;
import es.home.petstore.service.application.ports.driving.ModifyPetTagByIdPort;
import es.home.petstore.service.application.ports.driving.RegisterPetTagPort;
import es.home.petstore.service.application.ports.driving.RemovePetTagByIdPort;
import es.home.petstore.service.application.ports.driving.cqs.commands.ModifyPetTagByIdCommand;
import es.home.petstore.service.application.ports.driving.cqs.commands.RegisterPetTagCommand;
import es.home.petstore.service.application.ports.driving.cqs.commands.RemovePetTagByIdCommand;
import es.home.petstore.service.application.ports.driving.cqs.queries.FindPetTagByIdQuery;
import es.home.petstore.service.application.ports.driving.cqs.queries.FindPetTagsByFilterQuery;
import es.home.petstore.service.domain.model.pet.PetTag;
import es.home.petstore.service.domain.shared.pagination.CursorPagedData;
import es.home.petstore.service.driving.restapi.server.TagAPI;
import es.home.petstore.service.driving.restapi.server.model.PagedTagsResource;
import es.home.petstore.service.driving.restapi.server.model.TagResource;
import es.home.petstore.service.driving.restapi.server.model.TagValueResource;
import es.home.petstore.service.restapi.mappers.PetTagMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class PetTagRestAdapter implements TagAPI {

  private final RegisterPetTagPort registerPetTagPort;
  private final FindPetTagByIdPort findPetTagByIdPort;
  private final RemovePetTagByIdPort removePetTagByIdPort;
  private final ModifyPetTagByIdPort modifyPetTagByIdPort;
  private final FindPetTagsByFilterPort findPetTagsByFilterPort;

  private final PetTagMapper petTagMapper;

  @Override
  public ResponseEntity<TagResource> registerTag(@Valid TagValueResource tagValueResource) {
    log.info("Registering new pet tag: {}", tagValueResource);
    RegisterPetTagCommand command = new RegisterPetTagCommand(tagValueResource.getValue());
    final PetTag petTag = registerPetTagPort.register(command);
    log.info("Registered new pet tag: {}", petTag);
    final TagResource tagResource = petTagMapper.toTagResource(petTag);
    return ResponseEntity.ok(tagResource);
  }

  @Override
  public ResponseEntity<TagResource> findTagById(@NotNull UUID id) {
    log.info("Finding pet tag by id: {}", id);
    final FindPetTagByIdQuery query = new FindPetTagByIdQuery(id);
    final PetTag petTag = findPetTagByIdPort.findBy(query);
    log.info("Found pet tag: {}", petTag);
    final TagResource tagResource = petTagMapper.toTagResource(petTag);
    return ResponseEntity.ok(tagResource);
  }

  @Override
  public ResponseEntity<Void> deleteTagById(@NotNull UUID id) {
    log.info("Deleting pet tag by id: {}", id);
    final RemovePetTagByIdCommand command = new RemovePetTagByIdCommand(id);
    removePetTagByIdPort.removeById(command);
    log.info("Deleted pet tag by id: {}", id);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<TagResource> updateTagById(@NotNull UUID id, @Valid TagValueResource tagValueResource) {
    log.info("Updating pet tag by id: {} with value: {}", id, tagValueResource);
    final ModifyPetTagByIdCommand command = new ModifyPetTagByIdCommand(id, tagValueResource.getValue());
    final PetTag petTag = modifyPetTagByIdPort.modifyById(command);
    log.info("Updated pet tag: {}", petTag);
    final TagResource tagResource = petTagMapper.toTagResource(petTag);
    return ResponseEntity.ok(tagResource);
  }

  @Override
  public ResponseEntity<PagedTagsResource> findTags(
      @Size(min = 3, max = 30) @Valid String q,
      @Min(5) @Max(50) @Valid Integer pageSize, 
      @Valid UUID cursor
  ) {
    log.info("Finding pet tags with filter: {}, pageSize: {}, cursor: {}", q, pageSize, cursor);
    final FindPetTagsByFilterQuery query = new FindPetTagsByFilterQuery(q, pageSize, cursor);
    final CursorPagedData<PetTag> pagedData = findPetTagsByFilterPort.findAllBy(query);
    log.info("Found pet tags: {}", pagedData);
    final PagedTagsResource pagedTagsResource = petTagMapper.toPagedTagsResource(pagedData);
    return ResponseEntity.ok(pagedTagsResource);
  }

}
