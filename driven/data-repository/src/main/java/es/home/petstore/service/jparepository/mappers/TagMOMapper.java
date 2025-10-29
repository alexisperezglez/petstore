package es.home.petstore.service.jparepository.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import es.home.petstore.service.domain.model.pet.PetTag;
import es.home.petstore.service.domain.shared.pagination.CursorPagedData;
import es.home.petstore.service.domain.shared.pagination.CursorPagination;
import es.home.petstore.service.jparepository.model.TagMO;

@Mapper(componentModel = "spring")
public interface TagMOMapper {

  @Mapping(target = "id", source = "id.id")
  TagMO fromDomain(PetTag source);

  @InheritInverseConfiguration
  PetTag toDomain(TagMO source);

  @Mapping(target = "data", source = "source.content")
  @Mapping(target = "pagination", expression = "java(toCursorPaginationDomain(source, pageSize))")
  CursorPagedData<PetTag> toDomain(Page<TagMO> source, Integer pageSize);
  
  @Mapping(target = "totalElements", source = "source.totalElements")
  @Mapping(target = "requestedPageSize", source = "pageSize")
  @Mapping(target = "pageSize", source = "source.size")
  CursorPagination toCursorPaginationDomain(Page<TagMO> source, Integer pageSize);
}
