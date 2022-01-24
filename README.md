Rapport du projet calculatrice évoluée - Groupe 99
===

## Fonctionnalités

Notre programme est une calculatrice qui utilise la syntaxe polonaise inversée (RPN)

**Extension**
*  Nous avons choisit l'extension qui permet d'avoir la syntaxe algébrique traditionnel en plus de la RPN (Extension 2)
*  Nous avons choisit l'extension qui permet de faire des rappel de valeur, comme l'historique, la pile ou Mème crée des variable (Extension 3)
*  Nous avons choisit l'extension qui permet de rendre notre shell connecté, recevoir des utilisateur ou de se connecté a un autre shell. (Extension 6)

Dans notre online nous avons choisit d'implémenter un chat de discussion.
Nous avons aussi choisit d'implémenter des variable commune entre tout les shells connecté.

Pour simplifier l'utilisation du shell nous avons implémenter un système de commande. Le shell possède ces commandes:
* `=help` affiche toute les commandes disponibles
* `=stop` stop le shell
* `=rpn` change le mode de la calculatrice avec la syntaxe polonaise
* `=cla` change le mode de la calculatrice avec la syntaxe algébrique
* `=ip` recupére l'ip public
* `=open` ouvre le shell aux autres utilisateurs
* `=close` ferme le shell aux autres utilisateurs
* `=connect <ip>` le shell ce connect à un autre shell grâce à <ip> (si l'autre shell se situe sur le mème ordinateur il faut taper `=connect localhost`)
* `=disconnect` le shell ce déconnecte
* `=send <mon message>` le shell envoie <mon message> aux autres shell connecté

## Découpage

#### \"fr.up.calculatrice.expressions\"

Le package `fr.up.calculatrice.expressions` regroupe tout ce qui est lié aux calculatrices tel que le calcul des expressions et le stockage des variables.

#### \"fr.up.calculatrice.shell\"

Le package `fr.up.calculatrice.shell` regroupe tout ce qui est lié au shell tel que les commandes du shell

#### \"fr.up.calculatrice.server\"

Le package `fr.up.calculatrice.server` regroupe tout ce qui est lié au online du shell tel que la connexion à un autre shell, ou même les requêtes

## Stratégies et choix de développement

Pour la syntaxe rpn nous avons choisit de faire une pile pour stocker les valeur rentées par l'utilisateur
Nous empilons chaque valeur dans la pile et nous dépilons quand l'utilisateur fait une opération

En ce qui concerne les commandes du shell nous avons choisit d'utilisé des annotations ce qui rend l'implémentation plus lisible

Pour l'extension 2, Nous avons choisit d'utilisé [exp4j](https://github.com/fasseg/exp4j) afin d'analyser et d'évaluer les expressions algébrique

Pour l'extension 3, Nous avons choisit le même système que la rpn mais la pile de l'historique n'est jamais dépilée

Pour l'extension 6, Nous avons choisit d'utilisé [Kryonet](https://github.com/EsotericSoftware/kryonet). Nous y avons rajoutés les requêtes de chat et d'unification des variables


## Compilation et exécution

#### Compilation

Le projet dispose également d\'un `Makefile`, facilitant la compilation (grâce à Maven), et dont les détails d\'utilisation sont eux aussi spécifiés ci-dessous :

1. Pour compiler le projet, rien de plus simple :
```
make
```

3. Le `Makefile` dispose d\'une option de génération de Javadoc (La variable d'environnement JAVA_HOME doit être valide):
```
make Doc
```

2. Le `Makefile` dispose également d\'une option de nettoyage :
```
make clean
```

#### Exécution

Une fois le projet compilé, vous pouvez l\'exécuter en tapant simplement :
```
java -jar Calculatrice.jar
```

#### Documentation

Une fois la documentation générée, vous pouvez la voir en tapant simplement :
```
xdg-open Doc/index.html
```
