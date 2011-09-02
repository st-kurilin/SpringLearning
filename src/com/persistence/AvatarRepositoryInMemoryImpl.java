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
            return;
        }
        avatars.put(user, avatar);
    }

    @Override
    public Avatar load(Long user) {
        return avatars.get(user);
    }
}
