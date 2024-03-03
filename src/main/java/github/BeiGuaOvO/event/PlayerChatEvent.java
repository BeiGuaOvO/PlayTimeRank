package github.BeiGuaOvO.event;

import github.BeiGuaOvO.PlayTimeRank;
import github.BeiGuaOvO.Util.DataManageUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatEvent implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        long levelUp = PlayTimeRank.instance.getConfig().getLong("level-up")*60;
        int level = (int) (DataManageUtil.getPlayerHavePlayed(player) / levelUp);
        String prefix = null;
        if (level>=PlayTimeRank.levels.size())prefix=PlayTimeRank.levels.get(PlayTimeRank.levels.size()-1);
        else prefix=PlayTimeRank.levels.get(level);
        String message = event.getMessage();
        event.setMessage(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + prefix + ChatColor.GRAY + "]["
        + ChatColor.GOLD + player.getName() + ChatColor.GRAY + "]" + ChatColor.WHITE + " " + message);
    }
}
