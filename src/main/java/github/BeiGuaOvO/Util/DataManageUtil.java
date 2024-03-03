package github.BeiGuaOvO.Util;

import github.BeiGuaOvO.PlayTimeRank;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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

    public static long getPlayerHavePlayed(@NotNull Player player){
        long res=0;
        res += getTime(player.getName());
        LocalDateTime now = LocalDateTime.now();
        if (PlayTimeRank.data.containsKey(player)){
            LocalDateTime join = PlayTimeRank.data.get(player);
            Duration duration = Duration.between(join,now);
            res += duration.toMinutes();
        }
        return res;
    }
}
