package core.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
public class MusiksAuthenticationToken extends AbstractAuthenticationToken {
    private String login;
    private String password;
    private Set<String> permissions;

    public MusiksAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return login;
    }
}
