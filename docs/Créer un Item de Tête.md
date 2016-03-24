Introduction
============

Afin d'obtenir un ItemStack de tête simplement il suffit d'utiliser le HeadBuilder (voire le tutoriel sur les <a href="Builders.md">Builders</a>)

Créer un item
===========
Je veux une tête de télé : 
```
new HeadBuilder(Head.TV.getName()).withDisplayName("§aMa télé").withLore(new String[] { "Y a tf1 :) "}).build();
```