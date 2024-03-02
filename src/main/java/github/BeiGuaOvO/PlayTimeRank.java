package github.BeiGuaOvO;

import github.BeiGuaOvO.Util.DataManageUtil;
import github.BeiGuaOvO.command.GetPlayTimeCommander;
import github.BeiGuaOvO.event.PlayerJoinEvent;
import github.BeiGuaOvO.event.PlayerLeftEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public final class PlayTimeRank extends JavaPlugin {
    public static PlayTimeRank instance;
    public static Map<Player, LocalDateTime> data;
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[PlayTimeRank]已加载！作者：北瓜sakura");
        instance=this;
        data=new HashMap<>();
        saveDefaultConfig();
        autoSave();
        saveResource("data.yml",false);
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(),this);
        getServer().getPluginManager().registerEvents(new PlayerLeftEvent(),this);
        getCommand("playtime").setExecutor(new GetPlayTimeCommander());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[PlayTimeRank]已卸载！作者：北瓜sakura");
        instance=null;
        close();
        saveConfig();
    }

    private void close(){
        data=null;
    }

    private void autoSave(){
        if (getConfig().getBoolean("auto-save")){
            new BukkitRunnable(){
                @Override
                public void run(){
                    LocalDateTime now = LocalDateTime.now();
                    data.forEach((k,v) -> {
                        Duration duration = Duration.between(v,now);
                        long minutes = duration.toMinutes();
                        long played = DataManageUtil.getTime(k.getName());
                        DataManageUtil.setTime(k.getName(),minutes+played);
                    });
                    System.out.println("[PlayTimeRank] 自动保存了" + data.size() + "名玩家数据.");
                }
            }.runTaskTimerAsynchronously(this,0,getConfig().getLong("auto-save-period")*60*20);
        }
    }
}
