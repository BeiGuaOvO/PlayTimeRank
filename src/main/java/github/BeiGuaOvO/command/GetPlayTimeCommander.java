package github.BeiGuaOvO.command;

import github.BeiGuaOvO.PlayTimeRank;
import github.BeiGuaOvO.Util.DataManageUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;


public class GetPlayTimeCommander implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length==1){
            String name = strings[0];
            if (DataManageUtil.getTime(name) != 0){
                long time = DataManageUtil.getTime(name);
                for (Map.Entry<Player, LocalDateTime> entry : PlayTimeRank.data.entrySet()){
                    if (entry.getKey().getName().equals(name)){
                        LocalDateTime now = LocalDateTime.now();
                        Duration duration = Duration.between(entry.getValue(),now);
                        time+=duration.toMinutes();
                        break;
                    }
                }
                commandSender.sendMessage("[PlayTimeRank] 玩家" + name + "的游玩时长：" + ChatColor.GOLD + (int)time/60 + "h" + time%60 + "m");
            }else{
                commandSender.sendMessage("[PlayTimeRank] 未找到该玩家或改玩家游戏时长为0！");
            }
        }else{
            commandSender.sendMessage("[PlayTimeRank] 用法：/playtime <PlayerName>");
        }
        return false;
    }
}
