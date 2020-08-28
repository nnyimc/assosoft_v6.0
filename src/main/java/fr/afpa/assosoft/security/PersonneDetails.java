package fr.afpa.assosoft.security;

import fr.afpa.assosoft.entities.Personne;
import fr.afpa.assosoft.entities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonneDetails implements UserDetails {
    private Personne personne;

    public PersonneDetails(Personne personne) {
        this.personne = personne;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role r = personne.getRole();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(r.getRoleIntitule()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return personne.getPersonneMdp();
    }

    @Override
    public String getUsername() {
        return personne.getPersonneLogin();
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
