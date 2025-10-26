package es.home.petstore.service.domain.shared.utils;

public class StringUtils {

  private StringUtils() {}

  public static boolean isBlank(String str) {
    return str == null || str.trim().isBlank();
  }

  public static String toUpperCase(String str) {
    return str == null ? null : str.toUpperCase();
  }

  public static String trim(String str) {
    return str == null ? null : str.trim();
  }

  public static String trimAndUpperCase(String str) {
    return toUpperCase(trim(str));
  }
}
