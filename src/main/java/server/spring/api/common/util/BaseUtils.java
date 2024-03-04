package server.spring.api.common.util;


import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

public class BaseUtils {

  // https://www.baeldung.com/spring-uricomponentsbuilder
  // 인코딩된 url 작성
  public static String convertEncodedURI(
      String url, String path, MultiValueMap<String, Object> params) {
    UriComponentsBuilder uc = UriComponentsBuilder
        // 지정된 HTTP URL 문자열에서 URI 구성 요소 빌더를 만든다. ("www.google.com")
        .fromHttpUrl(url)
        // 경로 ("/")
        .path(path);
    params.forEach(
        (key, value) -> {
          uc.queryParam(key, value.get(0));
        });
    return uc.build().encode().toUriString();
  }

  // 기존 uri 에 value 추가
  public static String addQueryParamURI(String url, String value){
     // String urlString="https://example.com/hotels/42?filter={value}";
    return UriComponentsBuilder.fromHttpUrl(url)
      .buildAndExpand(value).toUriString();
  }

  // 빈값검사
  public static boolean isNullOrEmpty(String str) {
    if (str == null || str.isEmpty()) {
      return false;
    }

    return true;
  }
}
