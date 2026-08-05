//package pe.com.relari.support.headers;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import pe.com.relari.commons.model.ApiHeaders;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * class: HeaderInterceptor.
// *
// * @author Relari
// */
//
//@Component
//@RequiredArgsConstructor
//public class HeaderInterceptor implements HandlerInterceptor {
//
//  private final HeaderService headerService;
//
//  // Lista de headers que queremos capturar
//  private static final List<String> ALLOWED_HEADERS = List.of(
//          ApiHeaders.HEADER_REQUEST_ID,
//          ApiHeaders.HEADER_SESSION_ID,
//          ApiHeaders.HEADER_USER_ID
//  );
//
//  @Override
//  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//    List<HeaderValues> values = new ArrayList<>();
//
//    // 1. Capturamos los headers de la lista
//    ALLOWED_HEADERS.forEach(name -> {
//      String value = request.getHeader(name);
//      if (value != null) {
//        values.add(new HeaderValues(name, value));
//      }
//    });
//
//    // 2. Caso especial para la IP (que no viene en un header estándar siempre)
//    String ip = request.getHeader(ApiHeaders.HEADER_X_FORWARDED_FOR); // Intenta obtener IP real tras proxy
//    if (ip == null) ip = request.getRemoteAddr();
//    values.add(new HeaderValues(ApiHeaders.HEADER_X_FORWARDED_FOR, ip));
//
//    // 3. Guardamos en el servicio (que ahora es seguro por hilo/petición)
//    headerService.setHeaderValues(values);
//
//    return true;
//  }
//}