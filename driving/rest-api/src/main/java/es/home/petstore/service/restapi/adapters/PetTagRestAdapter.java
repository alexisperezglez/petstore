package es.home.petstore.service.restapi.adapters;

import es.home.petstore.service.application.ports.driving.FindPetTagByIdPort;
import es.home.petstore.service.application.ports.driving.RegisterPetTagPort;
import es.home.petstore.service.application.ports.driving.cqs.commands.RegisterPetTagCommand;
import es.home.petstore.service.application.ports.driving.cqs.queries.FindPetTagByIdQuery;
import es.home.petstore.service.domain.model.pet.PetTag;
import es.home.petstore.service.restapi.mappers.PetTagMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
@Slf4j
public class PetTagRestAdapter {

  private final RegisterPetTagPort registerPetTagPort;
  private final FindPetTagByIdPort findPetTagByIdPort;

  private final PetTagMapper petTagMapper;

  @PostMapping
  public ResponseEntity<PetTag> registerPetTag(@RequestBody Map<String, Object> request) {
    log.info("Registering new pet tag: {}", request);
    RegisterPetTagCommand command = new RegisterPetTagCommand((String) request.get("value"));
    final PetTag petTag = registerPetTagPort.register(command);
    return ResponseEntity.ok(petTag);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PetTag> findPetTagById(@PathVariable UUID id) {
    log.info("Finding pet tag by id: {}", id);
    final FindPetTagByIdQuery query = new FindPetTagByIdQuery(id);
    final PetTag petTag = findPetTagByIdPort.findBy(query);
    return ResponseEntity.ok(petTag);
  }

}
