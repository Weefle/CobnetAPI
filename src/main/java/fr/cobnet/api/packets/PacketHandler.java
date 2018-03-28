package fr.cobnet.api.packets;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import fr.cobnet.api.events.Listener;
import fr.cobnet.core.CobnetCore;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Créé par Creart le 25/01/2016.
 */
public class PacketHandler {

    private static PacketHandler instance;

    public static PacketHandler getInstance() {
        return instance == null ? instance = new PacketHandler() : instance;
    }

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private final HashMultimap<InPacketType, PacketListener> inListeners;
    private final HashMultimap<OutPacketType, PacketListener> outListeners;

    private PacketHandler() {
        inListeners = HashMultimap.create();
        outListeners = HashMultimap.create();
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onJoin(PlayerJoinEvent event) {
                handle(event.getPlayer());
            }
        }, CobnetCore.getInstance());

        enabled = true;
    }

    public void registerIncoming(PacketListener listener, InPacketType... types) {
        Preconditions.checkArgument(listener != null);
        Preconditions.checkArgument(types != null && types.length > 0);

        for (InPacketType type : types)
            inListeners.put(type, listener);
    }

    public void unregisterIncoming(PacketListener listener, InPacketType type) {
        Preconditions.checkArgument(listener != null);
        Preconditions.checkArgument(type != null);

        inListeners.remove(type, listener);
    }

    public void registerOutgoing(PacketListener listener, OutPacketType... types) {
        Preconditions.checkArgument(listener != null);
        Preconditions.checkArgument(types != null && types.length > 0);

        for (OutPacketType type : types)
            outListeners.put(type, listener);
    }

    public void unregisterOutgoing(PacketListener listener, OutPacketType type) {
        Preconditions.checkArgument(listener != null);
        Preconditions.checkArgument(type != null);

        inListeners.remove(type, listener);
    }

    public void handle(Player player) {
        Preconditions.checkArgument(player != null && player.isOnline());

        Channel channel = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;

        channel.pipeline().addBefore("packet_handler", "cobnetapi", new ChannelDuplexHandler() {
            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

                if (!player.isOnline()) return;

                if (!enabled) {
                    super.channelRead(channelHandlerContext, o);
                    return;
                }

                if (inListeners.isEmpty())
                    super.channelRead(channelHandlerContext, o);

                else {
                    String message = o.getClass().getSimpleName();
                    String packets[] = message.split("PacketPlayIn");

                    for (String packet : packets) {
                        boolean cancel = false;
                        for (InPacketType type : InPacketType.values()) {
                            if (type.getRawName().equals(packet)) {
                                ReceivedPacket p = type.getConstructor().newInstance( type.getClazz().cast(o), player );
                                for (PacketListener listener : inListeners.get(type))
                                    listener.onPacketReceive(p);

                                cancel = p.isCancelled();
                            }
                        }
                        if (cancel) return;
                    }

                    super.channelRead(channelHandlerContext, o);
                }

            }

            @Override
            public void write(ChannelHandlerContext channelHandlerContext, Object o, ChannelPromise channelPromise) throws Exception {

                if (!player.isOnline()) return;

                if (!enabled) {
                    super.write(channelHandlerContext, o, channelPromise);
                    return;
                }

                if (outListeners.isEmpty())
                    super.write(channelHandlerContext, o, channelPromise);

                else {
                    String message = o.getClass().getName().substring(29);
                    String packets[] = message.split("PacketPlayOut");

                    for (String packet : packets) {
                        boolean cancel = false;

                        for (OutPacketType type : OutPacketType.values()) {
                            if (type.getRawName().equals(packet)) {
                                SentPacket p = type.getConstructor().newInstance( type.getClazz().cast(o), player );
                                for (PacketListener listener : outListeners.get(type))
                                    listener.onPacketSend(p);

                                cancel = p.isCancelled();
                            }
                        }

                        if (cancel) return;
                    }

                    super.write(channelHandlerContext, o, channelPromise);
                }
            }
        });
    }

}
