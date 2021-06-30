import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import domain.User


import lombok.Data;

@Data
public class SecurityUser implements UserDetails {

    private String id;
    private String password;



    public SecurityUser(User systemUser) {
        this.id = systemUser.getId();
        this.password = systemUser.getPassword();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        int userid = this.getId();
        if (userid != null) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userid);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
