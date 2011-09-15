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

    @Value("#{avatar_prop.dir_width}")
    public int DIR_WIDTH;

    @Value("#{avatar_prop.dir_depth}")
    public int DIR_DEPTH;

    @Value("#{avatar_prop.init_folder}")
    private String INIT_FOLDER;

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

    @Override
    public boolean exist(Long userId) {
        final String location = location(userId);
        File file = new File(location);
        return file.exists();
    }

    private void save(String location, Avatar avatar) {
        ObjectOutputStream objectStream = null;
        try {
            if (avatar == null) {
                File fileToDelete = new File(location);
                if (fileToDelete.exists()) {
                    fileToDelete.delete();
                }
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
            File file = new File(location);
            if (!file.exists()) {
                return null;
            }
            objectStream = new ObjectInputStream(new FileInputStream(file));
            return (Avatar) objectStream.readObject();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            close(objectStream);
        }
    }

    private String location(Long user) {
        StringBuilder builder = new StringBuilder(INIT_FOLDER);
        for (int i = 0; i < DIR_DEPTH; i++) {
            final int module = DIR_WIDTH + i;
            builder.append(user % module).append(File.separatorChar);
        }
        builder.append(user);
        return builder.toString();
    }
}
