package pe.com.relari.dao.repository;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <b>Class:</b> MovieEntity.<br/>
 * <b>Description:</b> Entity class representing a movie in the database.
 *
 * @author Relari
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "movies")
public class MovieEntity extends PanacheEntity {

  private String title;
  private Integer releaseYear;
  private String director;

}
