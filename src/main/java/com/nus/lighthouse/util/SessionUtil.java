import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;



public class SessionUtil {

    public User getCurrentUser() { //为了session从获取用户信息,可以配置如下
        SecurityUser user;
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        if(auth != null){
            if (auth.getPrincipal() instanceof UserDetails) {
                user = (SecurityUser) auth.getPrincipal();
                User User = new User();
                User.setId(user.getId());
                User.setPassword(user.getPassword());
//            systemUser.setOeCode(user.getOeCode());
//            systemUser.setOeState(user.getOeState());
//            systemUser.setOeStateStr(user.getOeStateStr());
                return User;
            }
        }

        return null;
    }
}