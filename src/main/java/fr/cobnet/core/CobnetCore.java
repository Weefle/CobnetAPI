package fr.cobnet.core;

import fr.cobnet.api.events.Listener;
import fr.cobnet.api.i18n.I18n;
import fr.cobnet.api.i18n.Lang;
import fr.cobnet.api.lang.ClassUtils;
import fr.cobnet.api.npc.Npc;
import fr.cobnet.api.packets.PacketHandler;
import fr.cobnet.core.commands.LagCommand;
import fr.cobnet.core.commands.PluginLoadCommand;
import fr.cobnet.core.commands.SilenceCommand;
import fr.cobnet.core.commands.SlowCommand;
import fr.cobnet.core.database.FakeDatabase;
import fr.cobnet.core.listeners.ChatSendListener;
import fr.cobnet.core.listeners.CommandSpamListener;
import fr.cobnet.core.listeners.InvisblePlayerFixListener;
import fr.cobnet.core.listeners.ProjectileListener;
import fr.cobnet.core.players.CobPlayer;
import fr.cobnet.core.servers.FakeServer;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class CobnetCore extends JavaPlugin {
    private static CobnetCore instance;
    private boolean localServer;
    private FakeServer fakeServer;

    public static CobnetCore getInstance() {
        return instance;
    }

    public boolean isLocalServer() {
        return localServer;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        localServer = !Bukkit.getPluginManager().isPluginEnabled("CobnetServer");

        if (isLocalServer()) {
            Bukkit.getLogger().log(Level.INFO, "Le serveur est en mode local.");
            fakeServer = new FakeServer();
            fakeServer.enable();
            new FakeDatabase();
        }

		/* Listeners */
        Listener.register(this, new ChatSendListener(), new CommandSpamListener());
        Listener.register(this, new InvisblePlayerFixListener(this), new ProjectileListener());

		/* Commands */
        new SlowCommand();
        new SilenceCommand();
        new LagCommand();

        if (isLocalServer()) {
            new PluginLoadCommand();
        }

        PacketHandler.getInstance();

        try {
			I18n.supportTranslate(this, Lang.FRENCH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			I18n.supportTranslate(this, Lang.ENGLISH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        loadClasses();

        for (World world : Bukkit.getWorlds()) {
            world.setKeepSpawnInMemory(false);
            world.setAutoSave(false);
        }
    }

    @Override
    public void onDisable() {
        if (isLocalServer()) {
            fakeServer.disable();
            Collection<Npc> npcs = Npc.all();

            if (!npcs.isEmpty()) {
                npcs.stream().forEach(Npc::remove);
                Npc.clear();
            }
        }
    }

    // bug fix de ClassNotFoundException avec les NPCs
    private void loadClasses() {
        ClassUtils.loadClass("fr.cobnet.api.npc.Npc");
    }

    public void broadcast(String key, String... args) {
        CobPlayer.all().stream().forEach(player -> player.getPlayer().sendMessage(I18n.tl(player.getLang(), key, args)));
    }

}