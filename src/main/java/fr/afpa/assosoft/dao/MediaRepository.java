package fr.afpa.assosoft.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fr.afpa.assosoft.entities.Media;

import java.util.List;

//@RepositoryRestResource
@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
	@Query("FROM Media m INNER JOIN FETCH m.association AS logo "
	+"JOIN FETCH m.personne AS portrait")
	public List<Media> findAll();

	@Query(
			value = "FROM Media m INNER JOIN FETCH m.association AS logo "
			+"JOIN FETCH m.personne AS portrait ORDER BY m.id DESC ",
			countQuery = " FROM Media m INNER JOIN m.association AS logo "
			+"JOIN FETCH m.personne AS portrait ORDER BY m.id DESC "
	)
	public Page<Media> recupererDernierMedia(Pageable pageable);
}
