package pro.gravit.launchermodules.osslsigncode;

import pro.gravit.launchserver.config.LaunchServerConfig;

import java.util.ArrayList;
import java.util.List;

public class OSSLSignCodeConfig {
    public String timestampServer = "http://timestamp.globalsign.com/scripts/timstamp.dll";
    public String osslsigncodePath = "osslsigncode";
    public List<String> customArgs = new ArrayList<>();
    public LaunchServerConfig.JarSignerConf customConf;

    public boolean checkSignSize = true;
    public boolean checkCorrectSign = true;
    public boolean checkCorrectJar = true;
}
