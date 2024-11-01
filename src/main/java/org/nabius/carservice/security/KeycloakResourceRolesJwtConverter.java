package org.nabius.carservice.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakResourceRolesJwtConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String CLAIM_REALM_ACCESS = "realm_access";
    public static final String PREFIX_REALM_ROLE = "ROLE_realm_";
    private static final String CLAIM_RESOURCE_ACCESS = "resource_access";
    private static final String CLAIM_ROLES = "roles";
    public static final String PREFIX_RESOURCE_ROLE = "ROLE_";

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        Map<String, Collection<String>> realmAccess = source.getClaim(CLAIM_REALM_ACCESS);
        Map<String, Map<String, Collection<String>>> resourceAccess = source.getClaim(CLAIM_RESOURCE_ACCESS);
        if (!realmAccess.isEmpty() && !resourceAccess.isEmpty()) {
            Collection<String> roles = realmAccess.get(CLAIM_ROLES);
            Collection<GrantedAuthority> realmRoles = roles.stream()
                    .map(role -> new SimpleGrantedAuthority(PREFIX_REALM_ROLE + role))
                    .collect(Collectors.toList());
            grantedAuthorities.addAll(realmRoles);

            Collection<String> resourceRoles = resourceAccess.get("carservice").get(CLAIM_ROLES);
            Collection<GrantedAuthority> resourceRolesRoles = resourceRoles
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(PREFIX_RESOURCE_ROLE + role))
                    .collect(Collectors.toList());
            grantedAuthorities.addAll(resourceRolesRoles);
        }
        return grantedAuthorities;
    }
}