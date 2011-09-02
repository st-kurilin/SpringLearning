package com.persistence;

import com.domain.customer.Avatar;
import com.domain.customer.AvatarRepository;
import com.sun.istack.internal.Nullable;

import javax.jnlp.FileSaveService;
import java.io.*;
import java.util.Map;

/**
 * @author: Andrey Loboda
 * @date : 02.09.11
 */
/*
* and throws FileNotFound exception Access is denied
* */
public class AvatarRepositoryOnHardDrive implements AvatarRepository {
    public final int DIR_WIDTH = 10;
    public final int DIR_DEPTH = 3;

    @Override
    public void assign(Long user, @Nullable Avatar avatar) {
        if (avatar == null) {
            return;
        }
        try {
            final File file = createPathOfAvatar(user);
            ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream(file));
            objectStream.writeObject(avatar);
            objectStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File createPathOfAvatar(long id) throws IOException {
        String dirPath = getDirPathOfAvatar(id);
        boolean success = (new File(dirPath.toString())).mkdirs();
        if (!success) {
            throw new IOException(dirPath + " hasn't been created");
        }
        File file = new File(dirPath + id);
        success = file.mkdir();
        if (!success) {
            throw new IOException(dirPath + " hasn't been created");
        }
        return file;
    }

    private String getDirPathOfAvatar(long id) {
        StringBuilder builder = new StringBuilder("avatars/");
        for (int i = 0; i < DIR_DEPTH; i++) {
            builder.append(id % (DIR_WIDTH + i) + "/");
        }
        return builder.toString();
    }

    @Override
    public Avatar load(Long user) {
        Avatar avatar = null;
        try {
            final File file = createPathOfAvatar(user);
            ObjectInputStream objectStream = new ObjectInputStream(new FileInputStream(file));
            avatar = (Avatar) objectStream.readObject();
            objectStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return avatar;
    }
}
