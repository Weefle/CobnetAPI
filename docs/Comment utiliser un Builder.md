Introduction
============

Un builder est une classe qui permet d'obtenir un objet quelconque grâce à la fonction ```build()```.

Comment fonctionne un builder ?
============

Un builder c'est tout simplement une configuration de paramètres pour l'objet qui va être retourné par la fonction ```build```

Par exemple, pour l'ItemBuilder, vous pouvez faire :
```
ItemBuilder builder = new ItemBuilder(Material.WOOD); // là, y'a un constructeur, ce qui n'est pas toujours le cas
builder.withData(2); // là, on veut que l'item retourné ait une Data de 2
ItemStack item = builder.build(); // Hop ! On récupère l'Item
```