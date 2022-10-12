package configurations;

import commons.GlobalConstants;
import helpers.MethodHelper;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import java.io.File;

public class ServerManager {

    private final AppiumDriverLocalService server;

    public ServerManager() {
        int port = MethodHelper.getAvailablePort();
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingPort(port);
        serviceBuilder.withLogFile(new File(GlobalConstants.pathToTestResource + File.separator + "logs" + File.separator + "server.logs"));
        serviceBuilder.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/");
        this.server = AppiumDriverLocalService.buildService(serviceBuilder);

        this.server.start();
        this.server.clearOutPutStreams();
    }

    public AppiumDriverLocalService getServer() {
        return this.server;
    }
}
