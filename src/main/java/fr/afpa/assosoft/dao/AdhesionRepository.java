/* Seconde partie du mapping objet relationnel
 * Cette interface permet d'interagir avec les entités JPA
 * en effectuant un CRUD ainsi que des recherches personnalisées
 * 
 */
package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Adhesion;

import java.util.List;

@Repository
//Précision de la classe ciblée par le CRUD ainsi que le type de l'id
public interface AdhesionRepository extends JpaRepository<Adhesion, Long> {
    @Query("FROM Adhesion a " +
           "INNER JOIN FETCH a.association " +
           "INNER JOIN FETCH a.personne")
    public List<Adhesion> findAll();
}
