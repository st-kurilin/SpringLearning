package com.security;

import com.domain.Role;

/**
 * Contains all available rules for permission evaluation
 *
 * @author Stanislav Kurilin
 */
//TODO: [stas] try implement it using enums
interface PermissionRule<T> {
    boolean hasPermission(Principal current, T target);

    class AlwaysPermit implements PermissionRule<Object> {
        @Override
        public boolean hasPermission(Principal current, Object additional) {
            return true;
        }
    }

    class EditUser implements PermissionRule<Long> {
        @Override
        public boolean hasPermission(Principal current, Long editableUserId) {
            return current.hasRole(Role.ADMINISTRATOR)
                    || (current.hasRole(Role.USER) && current.getPerson().getId().equals(editableUserId));
        }
    }
}

