package com.persistence;

import com.domain.customer.Avatar;
import com.domain.customer.AvatarRepository;
import com.sun.istack.internal.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Stanislav Kurilin
 */

public class AvatarRepositoryInMemoryImpl implements AvatarRepository {
    private final Map<Long, Avatar> avatars = new HashMap<Long, Avatar>();

    @Override
    public void assign(Long user, @Nullable Avatar avatar) {
        if (avatar == null) {
            if (exist(user)) {
                avatars.remove(user);
            }
            return;
        }
        avatars.put(user, avatar);
    }

    @Override
    public Avatar load(Long user) {
        if (!exist(user)) {
            return null;
        }
        return avatars.get(user);
    }

    @Override
    public boolean exist(Long userId) {
        return avatars.containsKey(userId);
    }
}
