package commons;

import helpers.MethodHelper;

import java.io.File;
import java.nio.file.Paths;

public class GlobalConstants {
    public static final long longTimeout = 20;
    public static final long shortTimeout = 10;
    public static final String projectPath = System.getProperty("user.dir");
    public static final String osName = System.getProperty("os.name");
    public static final String osVersion = System.getProperty("os.version");
    public static final String javaVersion = System.getProperty("java.version");

    public static final String pathToMainResource =  Paths.get("src", "main", "resources").toFile().getAbsolutePath();
    public static final String pathToTestResource = Paths.get("src", "test", "resources").toFile().getAbsolutePath();

    public static final String pathToRecordVideos = projectPath + File.separator + "record_videos";

}
