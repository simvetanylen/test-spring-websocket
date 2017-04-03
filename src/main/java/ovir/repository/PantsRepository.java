package ovir.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ovir.entity.Pants;

@RepositoryRestResource(collectionResourceRel = "pants", path = "pants")
public interface PantsRepository extends CrudRepository<Pants, Long> {

    @Override
    @RestResource(exported = true)
    Pants findOne(Long id);

    @Override
    @RestResource(exported = true)
    Iterable<Pants> findAll();
}
