package es.home.petstore.service.restapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import es.home.petstore.service.domain.model.pet.PetTag;
import es.home.petstore.service.domain.shared.pagination.CursorPagedData;
import es.home.petstore.service.driving.restapi.server.model.PagedTagsResource;
import es.home.petstore.service.driving.restapi.server.model.TagResource;

@Mapper(componentModel = "spring", uses = {PaginationMapper.class})
public interface PetTagMapper {

  @Mapping(target = "id", source = "id.id")
  TagResource toTagResource(PetTag petTag);

  @Mapping(target = "data", source = "data")
  @Mapping(target = "pagination", source = "pagination")
  PagedTagsResource toPagedTagsResource(CursorPagedData<PetTag> source);

}
