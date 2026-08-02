package pe.com.relari.dao.ws.model;

import lombok.Data;

/**
 * <b>Class:</b> UserResponse.<br/>
 * <b>Description:</b> DTO that represents the user payload returned by the external
 * JSON placeholder API. Contains nested types for address, geo and company details.
 *
 * @author Relari
 */
@Data
public class UserResponse {

  private String website;
  private Address address;
  private String phone;
  private String name;
  private Company company;
  private Integer id;
  private String email;
  private String username;

  /**
   * <b>Class:</b> Address.<br/>
   * <b>Description:</b> Nested DTO representing the address structure for a user.
   */
  @Data
  public static class Address {

    private String zipcode;
    private Geo geo;
    private String suite;
    private String city;
    private String street;

  }

  /**
   * <b>Class:</b> Geo.<br/>
   * <b>Description:</b> Nested DTO representing geographical coordinates.
   */
  @Data
  public static class Geo {

    private Double lng;
    private Double lat;

  }

  /**
   * <b>Class:</b> Company.<br/>
   * <b>Description:</b> Nested DTO representing company information for a user.
   */
  @Data
  public static class Company {

    private String bs;
    private String catchPhrase;
    private String name;

  }

}
