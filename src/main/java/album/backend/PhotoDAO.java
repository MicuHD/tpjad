package album.backend;

import album.common.domain.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoDAO extends CrudRepository<Photo,Long> {
}
