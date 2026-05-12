package Test;

import java.io.File;

/**
 * Единый путь к файлу пользователей относительно рабочей директории процесса.
 */
public final class UserDatabase {

    private UserDatabase() {
    }

    public static File getUsersFile() {
        File dir = new File(System.getProperty("user.dir"), "DataBase");
        if (!dir.exists() && !dir.mkdirs()) {
            System.err.println("Could not create directory: " + dir.getAbsolutePath());
        }
        return new File(dir, "Users.bin");
    }
}
