package fr.afpa.assosoft.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fr.afpa.assosoft.entities.Media;

//@RepositoryRestResource
@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
	@Query("SELECT m FROM Media m ORDER BY m.id DESC")
	public Page<Media> recupererDernierMedia(Pageable pageable);
}
