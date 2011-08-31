package com.persistence;

import com.domain.customer.Avatar;
import com.domain.customer.AvatarRepository;
import com.domain.customer.User;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Stanislav Kurilin
 */
public class AvatarRepositoryInMemoryImpl implements AvatarRepository {
    private final Map<Long, Avatar> avatars = new HashMap<Long, Avatar>();

    @Override
    public void assign(Long user, Avatar avatar) {
        avatars.put(user, avatar);
    }

    @Override
    public Avatar load(Long user) {
        return avatars.get(user);
    }
}
