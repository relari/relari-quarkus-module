package pe.com.relari.model.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Class:</b> Movie.<br/>
 * <b>Description:</b> Data Transfer Object (DTO) representing a movie in the API layer.
 *
 * @author Relari
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

  private Integer id;
  private String title;
  private Integer year;
  private String director;

}