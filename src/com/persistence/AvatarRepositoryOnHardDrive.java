package com.persistence;

import com.domain.customer.Avatar;
import com.domain.customer.AvatarRepository;
import com.sun.istack.internal.Nullable;

import java.io.*;

import static com.util.StreamCloser.close;

/**
 * @author: Andrey Loboda
 * @date : 02.09.11
 */

public class AvatarRepositoryOnHardDrive implements AvatarRepository {
    //TODO: move to property file. Hint: take look at @Value annotation.
    //if it necessary, remove static attribute
    public static final int DIR_WIDTH = 10;
    public static final int DIR_DEPTH = 3;
    private static final String INIT_FOLDER = "D://avatars/";

    @Override
    public void assign(Long user, @Nullable Avatar avatar) {
        final String location = location(user);
        save(location, avatar);
    }


    @Override
    public Avatar load(Long user) {
        final String location = location(user);
        return retrieve(location);
    }

    private void save(String location, Avatar avatar) {
        ObjectOutputStream objectStream = null;
        try {
            if (avatar == null) {
                //TODO: delete
                return;
            }
            objectStream = new ObjectOutputStream(new FileOutputStream(location));
            objectStream.writeObject(avatar);

        } catch (IOException e) {
            //TODO: log it
        } finally {
            close(objectStream);
        }
    }

    private Avatar retrieve(String location) {
        ObjectInputStream objectStream = null;
        try {
            objectStream = new ObjectInputStream(new FileInputStream(location));
            return (Avatar) objectStream.readObject();
        } catch (Throwable e) {
            //TODO: log exceptions.
            throw new RuntimeException(e);
        } finally {
            close(objectStream);
        }
    }

    private static String location(Long user) {
        StringBuilder builder = new StringBuilder(INIT_FOLDER);
        for (int i = 0; i < DIR_DEPTH; i++) {
            final int module = DIR_WIDTH + i;
            builder.append(user % module).append(File.separatorChar);
        }
        builder.append(user);
        return builder.toString();
    }
}
