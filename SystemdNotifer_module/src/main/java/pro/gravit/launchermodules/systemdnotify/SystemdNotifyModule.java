package pro.gravit.launchermodules.systemdnotify;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pro.gravit.launcher.modules.LauncherInitContext;
import pro.gravit.launcher.modules.LauncherModule;
import pro.gravit.launcher.modules.LauncherModuleInfo;
import pro.gravit.launchserver.modules.events.LaunchServerFullInitEvent;
import pro.gravit.launchserver.modules.impl.LaunchServerInitContext;
import pro.gravit.utils.Version;
import pro.gravit.utils.helper.LogHelper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SystemdNotifyModule extends LauncherModule {
    private transient final Logger logger = LogManager.getLogger(SystemdNotifyModule.class);
    public static final Version version = new Version(1, 0, 0, 1, Version.Type.LTS);

    public SystemdNotifyModule() {
        super(new LauncherModuleInfo("SystemdNotifer", version, new String[]{"LaunchServerCore"}));
    }


    public void finish(LaunchServerFullInitEvent event) {
        notifySystemd();
    }

    public void notifySystemd() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("systemd-notify", "--ready");
        processBuilder.inheritIO();
        try {
            var process = processBuilder.start();
            logger.debug("Systemd notify successful");
        } catch (IOException e) {
            logger.error("Systemd-notify error", e);
        }
    }

    @Override
    public void init(LauncherInitContext initContext) {
        registerEvent(this::finish, LaunchServerFullInitEvent.class);
        if (initContext instanceof LaunchServerInitContext) {
            notifySystemd();
        }
    }
}
