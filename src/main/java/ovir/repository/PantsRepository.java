package ovir.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import ovir.entity.Pants;

@RepositoryRestResource(collectionResourceRel = "pants", path = "pants")
public interface PantsRepository extends CrudRepository<Pants, Long> {

    @Override
    @PreAuthorize("(authentication != null) and (authentication.principal == #pants.user.id)")
    <S extends Pants> S save(@Param("pants") S pants);

    @Override
    @RestResource(exported = false)
    <S extends Pants> Iterable<S> save(Iterable<S> var1);

    @Override
    @PreAuthorize("(authentication != null) and (authentication.principal == @pantsRepository.findOne(#id).user.id)")
    void delete(@Param("id") Long id);

    @Override
    @PreAuthorize("(authentication != null) and (authentication.principal == #pants.user.id)")
    void delete(@Param("pants") Pants pants);

    @Override
    @RestResource(exported = false)
    void delete(Iterable<? extends Pants> var1);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}