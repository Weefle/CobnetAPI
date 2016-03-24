package fr.cobnet.api.players;

import fr.cobnet.core.CobnetCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundUtils {

    public static void playSoundForAll(Sound sound, float volume, float pitch) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), sound, volume, pitch);
        }
    }

    /**
     * @param sound     > Son à jouer
     * @param volume    > Volume du son
     * @param pitch     > Pitch du son
     * @param location  > Endroit à ciblé
     * @param end       > Fin du son (-1 si infini)
     * @param frequence > Fréquence du son (à quelle fréquence faut jouer le son, elle se mesure en ticks)
     * @return int > Task ID
     */
    public static int playSoundForLocation(Sound sound, float volume, float pitch, Location location, long end, int frequence) {
        LocationSoundPlayer soundPlayer = new LocationSoundPlayer(sound, volume, pitch, location, end, frequence);
        //int taskId = TaskManager.scheduleSyncRepeatingTask("locsoundplayer_"+ SerializerUtils.serializeLocation(location), soundPlayer, 0, frequence);
        //soundPlayer.setTaskId(taskId);
        return soundPlayer.taskId;
    }

    static class LocationSoundPlayer implements Runnable {

        Sound sound;
        float volume;
        float pitch;
        Location location;
        long end;
        int taskId;

        LocationSoundPlayer(Sound sound, float volume, float pitch, Location location, long end, int freq) {
            this.sound = sound;
            this.volume = volume;
            this.pitch = pitch;
            this.location = location;
            this.end = end;

            this.taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(CobnetCore.getInstance(), this, 0, freq);
        }

        @Override
        public void run() {
            if (end != -1 && System.currentTimeMillis() > end) {
                Bukkit.getScheduler().cancelTask(taskId);
                return;
            }

            location.getWorld().playSound(location, sound, volume, pitch);
        }

    }

}
