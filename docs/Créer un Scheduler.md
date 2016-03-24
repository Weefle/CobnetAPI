Introduction
============

Permet de créer un scheduler rapidement et simplement.
Grâce a cette classe vous pourrez aussi annuler le scheduler simplement grâce à la fonction ```cancel()```.

:warning: **Si vous utiliser la fonction Bukkit.getScheduler().run... vous ne pourrez pas utilisez la fonction ```cancel()```
dans votre scheduler.**

Exemple
=======
On créé la classe qui contient notre scheduler.

```
import fr.cobnet.api.tasks.TaskRunner;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DireBonjour extends TaskRunner {
    private int i = 0;

    @Override
    public void run() {
        if(i > 2) {
            cancel();
            return;
        }
        Bukkit.getOnlinePlayers().forEach((Player player) -> player.sendMessage("Bonjour"));
        i++;
    }
}
```

On lance le scheduler

```
new DireBonjour().runTaskTimer(this, 0L, 20L);
```
