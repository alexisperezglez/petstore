package es.home.petstore.service.jparepository.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.home.petstore.service.domain.model.pet.PetTag;
import es.home.petstore.service.jparepository.model.TagMO;

@Mapper(componentModel = "spring")
public interface TagMOMapper {

  @Mapping(target = "id", source = "id.id")
  TagMO fromDomain(PetTag source);

  @InheritInverseConfiguration
  PetTag toDomain(TagMO source);
  
}
