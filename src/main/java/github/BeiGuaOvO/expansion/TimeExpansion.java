package github.BeiGuaOvO.expansion;

import github.BeiGuaOvO.Util.DataManageUtil;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class TimeExpansion extends PlaceholderExpansion {
    @Override
    public String getAuthor() {
        return "北瓜sakura";
    }

    @Override
    public String getIdentifier() {
        return "timerank";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {

        if(params.equalsIgnoreCase("playtime")) {
            long minutes = DataManageUtil.getPlayerHavePlayed((Player) player);
            return minutes + " minutes";
        }

        return null; // Placeholder is unknown by the Expansion
    }
}
