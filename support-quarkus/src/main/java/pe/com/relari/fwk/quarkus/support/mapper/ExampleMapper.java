package pe.com.relari.fwk.quarkus.support.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pe.com.relari.fwk.quarkus.support.model.ApiHeaders;
import pe.com.relari.fwk.quarkus.support.model.ApiResponse;

/**
 * <b>Interface:</b> StudentMapper.<br/>
 *
 * @author Relari
 * @version 1.0.0
 */

@Mapper
public interface ExampleMapper {

  @IterableMapping(qualifiedByName = "apiResponse")
  List<ApiResponse<?>> mapApiResponses(List<ApiHeaders> apiHeaders);

  @Named("apiResponse")
  @Mapping(target = "status", constant = "200")
  @Mapping(target = "code", expression = "java( request.getAppCode() + ' ' + request.getUserId() )")
  @Mapping(target = "data", defaultValue = "null")
  ApiResponse<?> apiResponse(ApiHeaders request);

  @Mapping(target = "status", constant = "200")
  @Mapping(target = "code", source = "appCode", defaultValue = "API")
  @Mapping(target = "data", defaultValue = "null")
  ApiResponse<?> dataValidateDefaultValue(ApiHeaders request);

  @Mapping(target = "status", constant = "200")
  @Mapping(target = "code", source = "appCode", defaultExpression = "java(\"API\")")
  @Mapping(target = "data", defaultValue = "null")
  ApiResponse<?> dataValidateDefaultExpression(ApiHeaders request);

}
