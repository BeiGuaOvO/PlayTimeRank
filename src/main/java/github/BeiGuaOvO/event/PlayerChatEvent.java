package github.BeiGuaOvO.event;

import github.BeiGuaOvO.PlayTimeRank;
import github.BeiGuaOvO.Util.DataManageUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class PlayerChatEvent implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        int time = (int) DataManageUtil.getPlayerHavePlayed(player);
        Map<String,String>map = PlayTimeRank.levels;
        String prefix=null;
        for (Map.Entry<String,String> entry:map.entrySet()){
            prefix = entry.getValue();
            if (Integer.parseInt(entry.getKey())*60>=time) {
                break;
            }
        }
        String message = event.getMessage();
        event.setMessage(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + prefix + ChatColor.GRAY + "]" + ChatColor.WHITE + " " + message);
    }
}
