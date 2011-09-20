package com.security;

import com.google.common.collect.ImmutableMap;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

import static java.lang.String.format;

/**
 * @author Stanislav Kurilin
 */
@Component("permissionEvaluator")
class ApplicationPermissionEvaluator implements PermissionEvaluator {
    private final Map<String, PermissionRule> rules =
            ImmutableMap.<String, PermissionRule>builder()
                    .put("user-edit", new PermissionRule.EditUser())
                    .put("user-create", new PermissionRule.AlwaysPermit())
                    .build();

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (!rules.containsKey(permission)) {
            throw new UnsupportedOperationException(format("Unsupported permission: %s", permission));
        }
        final Principal current = Principal.retrieve(authentication);
        return performRule(current, targetDomainObject, permission);
    }

    @SuppressWarnings("unchecked")  //we can't check target class supporting at compile time
    private boolean performRule(Principal current, Object target, Object permission) {
        return rules.get(permission).hasPermission(current, target);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        throw new UnsupportedOperationException("Unsupported call");
    }
}
