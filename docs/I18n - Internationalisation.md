Introduction
============

Permet de créer un plugin multi-langue facilement et rapidement.

Comment ça fonctionne ?
============
Dans votre plugin, vous aurez des fichiers qui contiennent les traductions que vous nommerez ainsi : "fr.lang.yml" et "en.lang.yml" (car on ne traduira que dans ces langues). Lorsque vous
lancez votre plugin, vous devrez ajouter chacun de ces fichiers de traduction (voir comment ci-dessous).

Ajouter un fichier de traduction
================================
Dans le même dossier ou se situe le plugin.yml vous pouvez ajouter des fichiers de traduction.
Par exemple si vous voulez créer un fichier de langue pour l'anglais je dois nommer mon fichier "lang.en.yml".
Mais il faut aussi ajouter à notre ```onEnable()``` ceci ```I18n.supportTranslate(this, Lang.ENGLISH);```.
Tout ce que ça fera, c'est que ça prend toutes les clefs et valeurs du fichier YAML et les stocke dans une HashMap avec la même disposition. Ainsi, vous pouvez récupérer la valeur grâce
à sa clef.

Exemple:

On veut créer une traduction qui dit salut puis le pseudo il faut faire: ```say-hello: "Salut {0}"```.

Intégration au plugin
=====================
Pour récupérer la traduction il suffit de faire ```I18n.tl(Lang.FRENCH, "hello");```. 
Reprenons notre cas "say-hello", on aurait besoin d'afficher le pseudo du joueur, pour cela il suffit de faire
```I18n.tl(Lang.FRENCH, "say-hello", player.getName());```. On peut rajouter autant d'arguments que l'on veut après la clef et chaque argument remplacera les "{X}", dans l'ordre
(en commençant par zéro).