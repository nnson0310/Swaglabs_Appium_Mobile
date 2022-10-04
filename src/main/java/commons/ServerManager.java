package commons;

import helpers.MethodHelper;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import java.io.File;

public class ServerManager {

    private final AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
    private AppiumDriverLocalService server;
    private int port;

    public ServerManager() {
        this.port = MethodHelper.getAvailablePort();
        this.serviceBuilder.usingPort(port);
        this.serviceBuilder.withLogFile(new File(GlobalConstants.pathToTestResource + File.separator + "logs" + File.separator + "server.logs"));
        this.serviceBuilder.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/");
        this.server = AppiumDriverLocalService.buildService(serviceBuilder);

        this.server.start();
        this.server.clearOutPutStreams();
    }

    public AppiumDriverLocalService getServer() {
        return this.server;
    }

    public void stopServer() {
        this.server.stop();
    }
}
