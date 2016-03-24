Introduction
============

Pour créer une gui la classe Gui et ItemBuilder sont mise à votre disposition.
La classe Gui doit être étendre une classe et la Item est une classe "builder" qui permet de créer des items facilement.

Créer un item
===========
Pour créer un item de bois foncé avec un nom custom :
```
new ItemBuilder(Material.WOOD, 32).withName("§2Bois !").withData((byte) 1).get();
```

Créer une gui
=============
```
import com.icroque.core.Core;
import org.bukkit.Material;

public class ExampleGui extends Gui {

    public ExampleGui() {
        super(Core.instance, "§eExampleGui", 54);
        setItem(0, new ItemBuilder(Material.WOOD, 32).withName("§2Bois !").withData((byte) 1).get());

        setClickHandler(new ClickEventHandler() {
            public void onClick(ClickEvent e) {
                if(e.getPosition() == 0) {
                    e.getPlayer().sendMessage("Tu veux du bois ?");
                }
            }
        });

        setCloseHandler(new CloseEventHandler() {
            public void onClose(CloseEvent e) {
                e.getPlayer().sendMessage("Vous venez de fermé l'inventaire.");
            }
        });

        setOpenHandler(new OpenEventHandler() {
            public void onOpen(OpenEvent e) {
                e.getPlayer().sendMessage("Vous venez d'ouvrir l'inventaire.");
            }
        });
    }
}
``` 