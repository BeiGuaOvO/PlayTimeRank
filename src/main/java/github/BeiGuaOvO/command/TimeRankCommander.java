package github.BeiGuaOvO.command;


import github.BeiGuaOvO.Util.DataManageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TimeRankCommander implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        Map<Player,Long> map = new HashMap<>();
        players.forEach(p -> {
            long time = DataManageUtil.getPlayerHavePlayed(p);
            map.put(p,time);
        });
        Map<Player,Long> res = sort(map);
        sender.sendMessage("------在线时长榜（在线玩家）------");
        int i=1;
        for (Map.Entry<Player,Long> entry : res.entrySet()){
            sender.sendMessage(i+". " + ChatColor.GOLD + entry.getKey().getName() + " : " + ChatColor.AQUA +
                    entry.getValue()/60 + "h" + entry.getValue()%60 + "m");
        }
        return false;
    }

    private Map<Player,Long> sort(Map<Player,Long> map){
        List<Map.Entry<Player,Long>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Player, Long>>() {
            @Override
            public int compare(Map.Entry<Player, Long> o1, Map.Entry<Player, Long> o2) {
                return (int) (o2.getValue() - o1.getValue());
            }
        });
        Map<Player,Long> res = new LinkedHashMap<>();
        for (Map.Entry<Player,Long> item : list){
            res.put(item.getKey(),item.getValue());
        }
        return res;
    }
}
