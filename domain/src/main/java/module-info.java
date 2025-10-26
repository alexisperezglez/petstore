module domain {
  requires org.slf4j;
  exports es.home.petstore.service.domain.model.pet;
  exports es.home.petstore.service.domain.shared.utils;
  exports es.home.petstore.service.domain.shared.annotations;
  exports es.home.petstore.service.domain.exceptions;
}