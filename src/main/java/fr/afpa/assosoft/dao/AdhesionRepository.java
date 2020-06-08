/* Seconde partie du mapping objet relationnel
 * Cette interface permet d'interagir avec les entités JPA
 * en effectuant un CRUD ainsi que des recherches personnalisées
 * 
 */
package fr.afpa.assosoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.assosoft.entities.Adhesion;

@Repository
//Précision de la classe ciblée par le CRUD ainsi que le type de l'id
public interface AdhesionRepository extends JpaRepository<Adhesion, Long> {

}
