package fr.cobnet.api.packets;

import fr.cobnet.api.misc.LazyInitialiser;
import fr.cobnet.api.reflection.ObjectAccess;
import net.minecraft.server.v1_12_R1.Packet;
import org.bukkit.entity.Player;

/**
 * Créé par Creart le 15/02/2016.
 */
public abstract class SentPacket {

    private static Object access;
    private static final LazyInitialiser<ObjectAccess> lazyInitialiser = new LazyInitialiser<ObjectAccess>() {
        @Override
        public ObjectAccess initialise() {
            return new ObjectAccess(access);
        }
    };

    private static final ObjectAccess access(Object a) {
        access = a;
        return lazyInitialiser.get();
    }

    protected final Packet<?> rawPacket;
    protected final Player player;
    protected boolean cancelled;
    private ObjectAccess reflection;

    public SentPacket(Packet<?> packet, Player player) {
        this.rawPacket = packet;
        this.player = player;
    }

    protected final ObjectAccess reflection() {
        return reflection == null ? reflection = access(rawPacket) : reflection;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public abstract OutPacketType getType();

}
