package commons;

import helpers.MethodHelper;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class ServerManager {

    private final AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
    private AppiumDriverLocalService server;
    private int port;

    public ServerManager() {
        this.port = MethodHelper.getAvailablePort();
        this.serviceBuilder.usingPort(port);
        this.serviceBuilder.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/");
        this.server = AppiumDriverLocalService.buildService(serviceBuilder);

        this.server.start();
    }

    public AppiumDriverLocalService getServer() {
        return this.server;
    }

    public void stopServer() {
        this.server.stop();
    }
}
