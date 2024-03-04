package server.spring.api.common.config.database;//package server.spring.base.common.config.database;
//
//import java.util.List;
//import java.util.Optional;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserAuditorAware implements AuditorAware<String> {
//  // @Override
//  // public Optional<String> getCurrentAuditor() {
//  // 	return Optional.of("test");
//  // }
//
//  @Override
//  public Optional<String> getCurrentAuditor() {
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//    if (authentication == null || authentication.getPrincipal() == null) {
//      return Optional.of(RoleType.ANONYMOUS.getLabel());
//      // return Optional.of(RoleType.SERVER.getLabel());
//    }
//
//    // by pass 요청인 경우
//    if (authentication.getPrincipal().equals("AnonymousUser")) {
//      return Optional.of(RoleType.ANONYMOUS.getLabel());
//    }
//
//    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//    // basic authorization으로 로그인 시
//    if (userDetails.getUsername() != null) {
//      return Optional.of(RoleType.ANONYMOUS.getLabel());
//    }
//
//    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//
//    List<String> roleList = principalDetails.getRole();
//
//    // guest 일 떄
//    if (roleList.contains(RoleType.GUEST.getCode())) {
//      return Optional.of(RoleType.GUEST.getName());
//    }
//
//    if (principalDetails.getIntegrationMember() != null) {
//      return Optional.of(principalDetails.getIntegrationMember().getUserId());
//    }
//
//    return Optional.of(roleList.get(0).replace("ROLE_", ""));
//  }
//}
