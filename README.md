# Travaux pratiques - Java

Le but de ce TP est d'implémenter le jeu du [Mastermind](https://fr.wikipedia.org/wiki/Mastermind) dans une application en ligne de commandes. La bonne nouvelle, c'est que c'est l'ordinateur qui devra faire deviner au joueur et pas l'inverse... Tu n'auras donc pas besoin de programmer une intelligence artificielle! 🥳

## Règles du jeu

Le but du jeu, pour l'utilisateur, est de deviner une combinaison de 4 couleurs, parmi 6 possibles (**R**ouge, **V**ert, **B**leu, **J**aune, **C**yan et **M**agenta).

Tout d'abord, l'ordinateur choisit une combinaison au hasard.

Ensuite, à chaque tour de jeu, l'utilisateur propose une combinaison de 4 couleurs. L'ordinateur répond alors en indiquant à l'utilisateur:

- Combien de couleurs sont bien placées par rapport à la solution (mais sans préciser lesquelles)
- Combien de couleurs sont présentes dans la solution, mais mal placées (mais sans préciser lesquelles)

L'utilisateur doit alors extrapoler les réponses successives de l'ordinateur pour déduire quelles sont les couleurs bien placées, jusqu'à obtenir la combinaison exacte. Il faut essayer de trouver la solution avec le moins de coups possibles, et on peut fixer une limite au nombre de coups permis.

### Exemples de réponses données par l'ordinateur

| Solution | Proposition de l'utilisateur | Réponse de l'ordinateur |
| --- | --- | --- |
| **M M C R** | **M M M M** | O O - - |
| **M M C R** | **C C C C** | O - - - |
| **M M C R** | **R R R R** | O - - - |
| **M M C R** | **V V V V** | - - - - |
| **M M C R** | **M C M C** | O X X - |
| **M M C R** | **M R M R** | O O X - |
| **M M C R** | **R C R C** | X X - - |

> _Légende:_
> - O = couleur bien placée
> - X = couleur mal placée
> - \- = couleur absente

### Exemple de partie complète

| Proposition de l'utilisateur | Réponse de l'ordinateur | Commentaire |
| --- | --- | --- |
| **R R R R** | O - - - | On sait qu'il y a exactement un **R**ouge dans la solution, mais on ne sait pas où. |
| **R R V V** | X - - - | On sait qu'il y a exactement un **R**ouge dans la solution, donc un seul "mal placé" nous indique qu'il n'est ni en 1, ni en 2. On sait de plus qu'il n'y a aucun **V**ert dans la solution (sinon l'ordinateur nous donnerait au moins un autre "mal placé"). |
| **B B R B** | X - - - | On sait qu'il y a exactement un **R**ouge dans la solution, donc un seul "mal placé" nous indique qu'il n'est pas en 3. On sait qu'il n'est pas non plus ni en 1, ni en 2, il est donc en 4. On sait de plus qu'il n'y a aucun **B**leu dans la solution (sinon l'ordinateur nous donnerait au moins un autre "mal placé"). |
| **J J C C** | O - - - | On sait qu'il y a soit un **J**aune placé en 1 ou en 2, soit un **C**yan placé en 3 (puisqu'on est sûr du **R**ouge en 4). |
| **J J M M** | X X - - | Une hypothèse possible étant qu'il y avait un seul **J**aune en 1 ou en 2, puisque l'ordinateur nous donne 2 "mal placés", c'est donc que les 2 "mal placés" sont les 2 **M**agenta. Les 2 **M**agenta sont donc en 1 et en 2. Il n'y a donc pas de **J**aune dans la combinaison. Et comme l'hypothèse d'un **J**aune en 1 ou en 2 est rejetée, c'est donc qu'au coup précédent, c'est le **C**yan en 3 qui était bien placé. |
| **M M C R** | O O O O | Victoire! |

## Bon, et sinon, on code?

Ben oui! Allez!

### 1. Demander une proposition à l'utilisateur

L'utilisateur doit pouvoir écrire une proposition, sur le modèle des exemples founis ci-dessus. L'application doit valider que la proposition contient bien 4 lettres, et qu'il s'agit uniquement d'une combinaison des lettres autorisées (**R** pour rouge, **V** pour vert, etc.).

<details>
  <summary>[INDICE] Demander une proposition à l'utilisateur</summary>

  [Utilisation de la classe Scanner](https://koor.fr/Java/Tutorial/java_regular_expression_scanner.wp)
</details>

<details>
  <summary>[INDICE] Valider la proposition de l'utilisateur</summary>

  [Utilisation de l'API Regex](https://cyberzoide.developpez.com/tutoriels/java/regex/)
</details>

<details>
  <summary>[SOLUTION] Valider la proposition de l'utilisateur</summary>

  `^[RVBJCM]{4}$`
</details>

### 2. Trouver les couleurs bien placées

L'application doit déterminer quelles sont les couleurs, dans la proposition de l'utilisateur, qui sont bien placées par rapport à une solution fixe (par exemple: **M M C R**).

<details>
  <summary>[INDICE] Déterminer les couleurs bien placées</summary>

  Puisque l'ordinateur répond uniquement par un nombre de couleurs bien placées, il suffit de les compter.
</details>

### 3. Trouver les couleurs absentes

L'application doit déterminer quelles sont les couleurs, dans la proposition de l'utilisateur, qui sont absentes de la solution fixe.

<details>
  <summary>[INDICE] Déterminer les couleurs absentes</summary>

  Puisque l'ordinateur répond uniquement par un nombre de couleurs absentes, il suffit de les compter.
</details>

<details>
  <summary>[INDICE] Compter les couleurs absentes</summary>

  Attention: une couleur est considérée comme "absente" s'il n'y a pas suffisamment d'exemplaires de celle-ci dans la solution. Par exemple, si la solution est **R V B C**, la proposition **V V R R** doit répondre par un bien placé (un **V**ert), un mal placé (un **R**ouge), et deux absents (car il n'y a qu'un seul **V**ert et qu'un seul **R**ouge dans la solution).
</details>

### 4. Trouver les couleurs mal placées

L'application doit déterminer quelles sont les couleurs, dans la proposition de l'utilisateur, qui sont présentes dans la solution fixe, mais mal placées.

<details>
  <summary>[INDICE] Déterminer les couleurs mal placées</summary>

  Toutes les couleurs qui ne sont ni bien placées, ni absentes, sont donc mal placées.
</details>

### 5. Donner la réponse de l'ordinateur

Connaissant le nombre de couleurs bien placées, mal placées et absentes, l'application doit répondre à la proposition de l'utilisateur.

### 6. Recommencer jusqu'à la victoire

L'application doit répéter le tour de jeu jusqu'à ce que l'utilisateur propose la combinaison correspondant exactement à la réponse. Dans ce cas, l'application demande au joueur s'il souhaite jouer une nouvelle partie, et si oui, le jeu recommence à zéro.

### Bonus (facultatifs)

- La solution doit être choisie aléatoirement par l'ordinateur.
- La saisie de l'utilisateur doit être validée à chaque caractère (par exemple, si l'utilisateur appuie sur Z, le caractère n'est pas pris en compte).
- Le nombre d'essais est limité: si on n'a toujours pas trouvé la solution au bout de 10 coups (par exemple), la partie se termine en défaite.
- Un menu "options" est disponible, permettant de faire varier la quantité de couleurs disponibles et/ou le nombre de couleurs dans chaque combinaison et/ou le nombre d'essais avant défaite.
