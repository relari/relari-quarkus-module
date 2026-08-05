package pe.com.relari.fwk.quarkus.support.headers.impl;

import jakarta.enterprise.context.ApplicationScoped;
import pe.com.relari.fwk.quarkus.support.headers.HeaderService;
import pe.com.relari.fwk.quarkus.support.headers.HeaderValues;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import pe.com.relari.employee.headers.HeaderService;
//import pe.com.relari.employee.headers.HeaderValues;

/**
 * Class: HeaderServiceImpl.
 *
 * @author Relari
 */

@ApplicationScoped
public class HeaderServiceImpl implements HeaderService {

  private final Map<String, String> headerValuesMap = new HashMap<>();

  @Override
  public void setHeaderValues(List<HeaderValues> headerValues) {
    headerValues.forEach(hv -> headerValuesMap.put(hv.code(), hv.value()));
  }

  @Override
  public Map<String, String> getHeaderValues() {
    return Collections.unmodifiableMap(headerValuesMap);
  }

  @Override
  public String getHeaderValue(String code) {
    return headerValuesMap.get(code);
  }

}