package github.BeiGuaOvO.event;

import github.BeiGuaOvO.PlayTimeRank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class PlayerBrokeEvent implements Listener {
    @EventHandler
    public void onBroke(BlockBreakEvent event){
        Player player = event.getPlayer();
        int broke = PlayTimeRank.broke.get(player);
        PlayTimeRank.broke.put(player,broke+1);
    }
}
