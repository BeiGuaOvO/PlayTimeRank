package github.BeiGuaOvO;

import github.BeiGuaOvO.Util.DataManageUtil;
import github.BeiGuaOvO.command.GetPlayTimeCommander;
import github.BeiGuaOvO.command.TimeRankCommander;
import github.BeiGuaOvO.event.PlayerChatEvent;
import github.BeiGuaOvO.event.PlayerJoinEvent;
import github.BeiGuaOvO.event.PlayerLeftEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public final class PlayTimeRank extends JavaPlugin {
    public static PlayTimeRank instance;
    public static Map<Player, LocalDateTime> data;
    public static List<String> levels;
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[PlayTimeRank]已加载！作者：北瓜sakura");
        instance=this;
        data=new HashMap<>();
        levels=new ArrayList<>();
        checkOnlinePlayers();
        saveDefaultConfig();
        autoSave();
        initLevels();
        //new TimeExpansion().register();
        saveResource("data.yml",false);
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(),this);
        getServer().getPluginManager().registerEvents(new PlayerLeftEvent(),this);
        if (getConfig().getBoolean("level-enable"))getServer().getPluginManager().registerEvents(new PlayerChatEvent(),this);
        getCommand("playtime").setExecutor(new GetPlayTimeCommander());
        getCommand("playrank").setExecutor(new TimeRankCommander());
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
        levels=null;
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
                        data.put(k,now);
                    });
                    if (getConfig().getBoolean("auto-save-debug-info")) System.out.println("[PlayTimeRank] 自动保存了" + data.size() + "名玩家数据.");
                }
            }.runTaskTimerAsynchronously(this,0,getConfig().getLong("auto-save-period")*60*20);
        }
    }

    private void checkOnlinePlayers(){
        Collection<? extends Player> playerCollection= Bukkit.getOnlinePlayers();
        playerCollection.forEach(k ->{
            LocalDateTime now = LocalDateTime.now();
            data.put(k,now);
        });
        if (getConfig().getBoolean("auto-save-debug-info")) System.out.println("[PlayTimeRank] 目前有"+playerCollection.size()+"名玩家在线，已加入缓存中");
    }

    private void initLevels(){
        if (getConfig().getBoolean("level-enable")){
            levels = getConfig().getStringList("level-names");
        }
    }
}
