package marvtechnology.joinmessagemarv;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    private final Joinmessagemarv plugin;

    public JoinQuitListener(Joinmessagemarv plugin) {
        this.plugin = plugin;
    }

    private Component getLocalizedMessage(Player player, String type) {
        String lang = player.locale().getLanguage(); // ex: "ja", "en"
        FileConfiguration config = plugin.getConfig();

        String msg = config.getString("messages." + type + "." + lang);
        if (msg == null) {
            msg = config.getString("messages." + type + ".en");
        }

        if (msg == null) return null;

        msg = msg.replace("%player%", player.getName());
        return LegacyComponentSerializer.legacyAmpersand().deserialize(msg);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.joinMessage(getLocalizedMessage(event.getPlayer(), "join"));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.quitMessage(getLocalizedMessage(event.getPlayer(), "quit"));
    }
}
