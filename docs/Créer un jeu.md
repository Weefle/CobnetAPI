Introduction
============

Vous vous devez d'utiliser la classe GamePlugin pour votre jeu.
La classe GamePlugin remplace votre JavaPlugin et tout ce que vous faites habituellement (plugin.yml, par exemple) ne change pas.

Exemple
=======
Votre classe principale doit suivre le modèle suivant :

```
import fr.cobnet.api.events.Listener;
import fr.cobnet.api.games.GamePlugin;
import fr.cobnet.api.tasks.TaskRunner;

public class Example extends GamePlugin { // héritage de GamePlugin

    public Example() {
        // on appelle le constructeur de la superclasse qui est GamePlugin
        super("Mini Jeu"); // Nom du mini-jeu
    }

    @Override
    public void onEnable() {
        super.onEnable(); // Obligatoire !! Sinon votre mini-jeu ne marchera pas.

        Listener.register(this, new GameListener()); // On register un nouveau listener
        getGameCooldown().setUntilStart(15); // Temps avant que le jeu démarre par défaut 90 secondes
        getGameCooldown().setRunner(new TaskRunner() {
            @Override
            public void run() {
                // cette tâche sera lancée à la fin du Countdown
            }
        });
    }
}
```

Les préfixes
============
Vous avez besoin des prefixes ?

* Par défaut : ``` Example.getInstance().getPrefix() ```;
* Succès : ``` Example.getInstance().getSuccessPrefix() ```;
* Erreur : ``` Example.getInstance().getErrorPrefix() ```;

Annonce
=======
Pour faire une annonce globale dans votre jeu, il faut :
``` CobnetCore.getInstance().broadcast("votre clef i18n", "les valeurs i18n à remplacer"); ```
 
Définir les conditions
======================
Pour modifier la condition, "si le jeu peut démarrer" il suffit de modifier la méthode ```canStart()```. Exemple :

Il me faut au minimum 2 joueurs.

```
@Override
public boolean canStart() {
    return Bukkit.getOnlinePlayers().size() > 1;
}
```

Désactiver le mode spectateur : 

```
public Example() {
    super("Mini Jeu"); // Nom du mini-jeu
    this.setAuthorizeSpectator(false); // Désactivé les spectateurs
}
```

Définir les conditions pour que le joueur puissent se reconnecter :

```
@Override
public boolean playerCanReconnect(Player player) {
    return getStatus() == GameStatus.LOBBY;
}
```

Les messages en général
============
Pour tous vos messages, vous vous devrez d'utiliser l'i18n (<a href="Utiliser l'API.md">Tutoriel sur l'i18n</a>), qui permet d'envoyer des messages selon la langue des joueurs