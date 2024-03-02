package github.BeiGuaOvO.event;


import github.BeiGuaOvO.PlayTimeRank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.time.LocalDateTime;

public class PlayerJoinEvent implements Listener {
    @EventHandler
    public void onJoin(org.bukkit.event.player.PlayerJoinEvent event){
        LocalDateTime joinTime = LocalDateTime.now();
        Player player = event.getPlayer();
        PlayTimeRank.data.put(player,joinTime);
    }
}
