package helpers;

import commons.GlobalConstants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class MethodHelper {

    public static void sleepInSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getAvailablePort() {
        // default port for Appium Server
        int port = 4723;

        try {
            ServerSocket serverSocket = new ServerSocket(0);
            port = serverSocket.getLocalPort();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return port;
    }

    public static String getCurrentTimeByFormat(String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return dateTimeFormatter.format(now);
    }

    public static void startRecordVideo(AndroidDriver driver) {
        driver.startRecordingScreen(
                new AndroidStartScreenRecordingOptions()
                        .withVideoSize("1280x720")
                        .withTimeLimit(Duration.ofSeconds(200))
        );
    }

    public static void saveRecordVideo(AndroidDriver driver, String testName) {
        String video = driver.stopRecordingScreen();
        byte[] decode = Base64.getDecoder().decode(video);
        try {
            String videoDir = GlobalConstants.pathToRecordVideos + File.separator + MethodHelper.getCurrentTimeByFormat("yyyy-MM-dd");
            Path pathToVideo = Paths.get(videoDir);
            if (Files.exists(pathToVideo)) {
                Files.createDirectories(pathToVideo);
            }
            File videoFile = new File(videoDir + File.separator + testName + ".mp4");
            FileUtils.writeByteArrayToFile(videoFile, decode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get datetime from current datetime. Ex:
     * to get yesterday datetime, just pass "-1" as parameter
     * @param dateFrom
     * @return
     */
    public static String getDateFrom(int dateFrom) {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DATE, dateFrom);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Clean record videos folder except folder of current date
     */
    public static void cleanRecordVideoDir() {
        File recordVideos = new File(GlobalConstants.pathToRecordVideos);
        for (File file: Objects.requireNonNull(recordVideos.listFiles())) {
                if(!file.getName().equals(getCurrentTimeByFormat("yyyy-MM-dd"))) {
                    file.delete();
                }
        }
    }
}
