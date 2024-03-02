package github.BeiGuaOvO.event;

import github.BeiGuaOvO.PlayTimeRank;
import github.BeiGuaOvO.Util.DataManageUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.time.Duration;
import java.time.LocalDateTime;

public class PlayerLeftEvent implements Listener {
    @EventHandler
    public void onLeft(PlayerQuitEvent event){
        LocalDateTime leftTime = LocalDateTime.now();
        Player player = event.getPlayer();
        LocalDateTime joinTime = PlayTimeRank.data.get(player);
        PlayTimeRank.data.remove(player);
        long timeEverPlayed = DataManageUtil.getTime(player.getName());
        Duration duration = Duration.between(joinTime,leftTime);
        long minutes = duration.toMinutes();
        DataManageUtil.setTime(player.getName(), timeEverPlayed+minutes);
    }
}
