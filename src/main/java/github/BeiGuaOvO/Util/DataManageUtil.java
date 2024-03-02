package github.BeiGuaOvO.Util;

import github.BeiGuaOvO.PlayTimeRank;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DataManageUtil {
    private static final File file = new File(PlayTimeRank.instance.getDataFolder(),"data.yml");
    private static final FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    public static long getTime(String playerName){
        return config.getLong(playerName);
    }

    public static void setTime(String playerName,long time){
        config.set(playerName,null);
        config.set(playerName,time);
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
