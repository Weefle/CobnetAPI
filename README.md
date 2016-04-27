Introduction
============
Cette api a été développé par [creart_](https://twitter.com/DevCreart) et [iCroque](https://twitter.com/iCroque_com) pour Cobnet. Le projet a malheureusement été stoppé c'est pour cela que nous décidé de divulguer l'api. Vous trouverez une petite documentation dans le dossier [docs](https://github.com/remicaumette/cobnetapi/tree/master/docs).

Utilisation de l'api
====================

Pour vos mini-jeux, vous devez :

* Pour votre GameManager, faire le étendre de AbstractGameHandle, afin d'utiliser le countdown par défaut de l'API (qui demande encore des modifications, afin de pouvoir customiser le temps de countdown) qui se situe dans le BasicGameManager.

```
public class Example extends GamePlugin

```

* Pour les gains de coins ou special coins, passer par la methode ```CobPlayer.modifyCoins()```. En paramètre, insérez une instance de CoinsModifier pour faire l'opération nécessaire et envoyer le message au joueur. Si la CoinsModifierReason n'apparaît pas dans la liste, ajoutez la.

Conseils pour votre plugin
==========================

* Pour rendre votre plugin plus maintenanble, il est préférable de commenter le code ;
* Utiliser [maven] (https://maven.apache.org/) pour gérez vos dépendances ;
* Organiser vos packages :
	* ```listeners``` 
	* ```commands```
	* ```schedulers```
	* ```utils```
	* ```nms```
* Respecter les conventions exemple : nom du plugin = nom de la classe héritant de la JavaPlugin

Fonctionnalités déjà disponible
===============================
 
* Utilitaires de location ;
* Utilitaires de trade de villageois ;
* Utilitaires de zones ;
* Utilitaires d'attributs NMS ;
* Utilitaires de commandes ;
* Utilitaires de blocs ;
* Utilitaires d'animations de coffres ;
* Utilitaires d'entités ;
* Utilitaires de NMS ;
* Utilitaires de pets ;
* Utilitaires d'events ;
* Utilitaires d'I18N ;
* Utilitaires d'inventaires ;
* Utilitaires d'items ;
* Utilitaires de réflection ;
* Utilitaires de chaines de charactère (```String```) ;
* Utilitaires de random ;
* Utilitaires de maths ;
* Utilitaires de vecteurs/vélocité ;
* Utilitaires de conversions ;
* Utilitaires de particules ;
* Utilitaires d'hologrammes ;
* Utilitaires de scoreboard personnels ;
* Utilitaires de joueurs en général ;
* Utilitaires de vue/vision des joueurs ;
* Utilitaires de TabList ;
* Utilitaires de Teams ;
* Utilitaires de texte ;
* Utilitaires de titres ;
* Utilitaires de tâches (```Scheduler```) ;
* Exécuteur de tâche synchronisé ;
* Gestion automatique des teams ;
* Effets de particules ;
* Cuboid ;
* Ecoute de packets...

