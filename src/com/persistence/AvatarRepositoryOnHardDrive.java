package com.persistence;

import com.domain.customer.Avatar;
import com.domain.customer.AvatarRepository;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;

import static com.util.StreamCloser.close;

/**
 * @author: Andrey Loboda
 * @date : 02.09.11
 */

public class AvatarRepositoryOnHardDrive implements AvatarRepository {
    //TODO: move to property file. Hint: take look at @Value annotation.
    //if it necessary, remove static attribute
    //@Value("#{avatar_prop.dir_width}")
    public final static int DIR_WIDTH=10;

    //@Value("#{avatar_prop.dir_depth}")
    public final static int DIR_DEPTH=3;

    //@Value("#{avatar_prop.init_folder}")
    private final static String INIT_FOLDER="D://avatars/";

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

            File file = new File(location);
            new File(file.getParent()).mkdirs();
            file.createNewFile();

            objectStream = new ObjectOutputStream(new FileOutputStream(file));
            objectStream.writeObject(avatar);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
