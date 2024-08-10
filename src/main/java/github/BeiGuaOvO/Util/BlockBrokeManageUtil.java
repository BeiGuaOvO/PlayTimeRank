package github.BeiGuaOvO.Util;

import github.BeiGuaOvO.PlayTimeRank;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class BlockBrokeManageUtil {
    private static final File file = new File(PlayTimeRank.instance.getDataFolder(), "blockBroke.yml");
    private static final FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    public static String getBrokeCount(Player player){
        return config.getString(player.getName());
    }

    public static void updateBrokeCount(Player player,int update){
        String broken = getBrokeCount(player);
        BigDecimal bd1 = new BigDecimal(broken);
        BigDecimal bd2 = new BigDecimal(update);
        BigDecimal res = bd1.add(bd2);
        String updateResult = res.toString();
        config.set(player.getName(),null);
        config.set(player.getName(),updateResult);
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
