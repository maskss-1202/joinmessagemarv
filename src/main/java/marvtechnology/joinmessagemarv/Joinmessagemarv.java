package marvtechnology.joinmessagemarv;

import org.bukkit.plugin.java.JavaPlugin;

public class Joinmessagemarv extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new JoinQuitListener(this), this);
        getLogger().info("JoinMessageMarv enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("JoinMessageMarv disabled.");
    }
}
