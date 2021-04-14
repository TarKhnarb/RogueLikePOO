# RogueLikePOO - projet 2020-2021

* BELLEMAIN SAGNARD Léo 11721445
* DOMINGUES Kévin       11607884

Lien du code: https://github.com/TarKhnarb/RogueLikePOO 
___
## Introduction
>Rogue est un jeu d’énigmes dans lequel vous contrôlez Le Professeur qui tente de s'échapper d’un labyrinthe comportant 4 étages en 2D, en vue du dessus.  
>
>Pour cela, vous serez amené à vous balader de salles en salles pour y trouver la sortie, mais attention aux multiples énigmes qui peuvent s'y trouver.

___
## Comment jouer
### Déplacements
>* Le joueur peut ce déplacé grâce aux flèches directionnelles
>* Sauter avec la barre d'espace
>* Lancer une capsule d'eau avec la touche 'c'
>* Relancer une partie si vous êtes bloqués avec la touche 'r'
>
> Attention :
>* Un saut ne peut se faire qu'au dessus d'une seule case de vide.
> La case d'arrivé du saut ne peut pas être un mur, un coffre ou une case en feu.
>* Vous ne pouvez utiliser les capsules d'eau que sur les cases en feu adjacentes à vous lorsque vous êtes orienté dans sa direction.


### Règles

> Vous pourez utiliser les capsules d'eau mises à votre disposition dans chaque salles pour éteindre les cases en feu  qui vous bloque le passage, ou encore prendre votre courrage
> à deux mains et sauter au dessus des cases de vides.

> Vous devrez utiliser une clé pour ouvrir les portes et les coffres.
> Soyez à l'affût les clés qui peuvent se trouver au sol, elles vous seront très utiles pour ouvrir les coffres et les portes.

> Lors de vos aventures vous serez amené à croiser des coffres qui contiennent au minimum une clé et une capsule.
> Vous aurez le choix de récupérer ou non son contenu via un pop-up qui apparait au dessus de l'inventaire. Notez que si le coffre est vide, le pop-up n'apparaitra pas.

## Modification des fichiers pour les parties

### Les étages:
Vous pouvez modifier à votre convenance le nombre d'étages et le nombre de salles pour chacun d'entre eux.
Pour cela, il vous faudra ouvrir le fichier __data/Parties/Reglages.partie__
> La première ligne représente la taille du tableau 2D dans lequel sera généré l'étage.
>  
> Le nombre doit être impérativement impaire et unique. Il influe directement le nombre de salles possibles par la suite.

> Chacune des lignes suivantes représentent un étage.
> 
> Un étage est repésenté par deux nombres, respectivement le nombre minimum et maximum de salle pour l'étage donné.
> Le nombre maximum de salles ne doit pas dépasser la taille d'un étageau carré. 
> 
> Privilégiez un rapport de deux entre le maximum de salles et la taille d'un étage au carré (exemple: pour maximum 40 salles, mettre au moins 9 pour la taille de l'étage).

### Les salles:
Vous pouvez modifier le paterne des salles en changeant les cases.
Pour cela, vous trouverez toutes les salles possible dans le jeu dans le dossier __data/Salles/__
Cependant pour éviter tout soucis, veuillez respecter ces quelques règles :
> Pour chacune des salles si vous souhaiter modifier leur taille générale, veillez à ce que :
> * Modifier dans le fichier __src/Plateau/Jeu.java__ ligne 17 la variable "SIZE" avec respectivement (nbCasesHorizontale, nbCasesVerticale) __ATTENTION LE NOMBRE DE CASES DOIT IMPERATIVEMENT ETRE IMPAIR__
> * Il faudra donc adapter en conséquence tous les fichiers Salle_*.salle !
>
> Ne modifiez les fichiers Salles_Debut.salle et Salle_Fin.salle seulement si vous avez changé la taille générale des salles.
> Dans ce cas ne mettez aucune porte dans la salle de __Debut__ et de __Fin__. La salle de __Fin__ doit IMPERATIVEMENT contenir une case __'9'__.
>
> Les différents types de cases sont représentés par les nombres suivant:
>> * 0: Mur 
>> * 1: Case normale (rien dessus) 
>> * 2: Case de vide 
>> * 3: Porte fermée (nécessite une clé pour être ouverte)
>> * 4: Coffre fermé (contiendra des objets) 
>> * 5: Clé au sol (à un pourcentage aléatoir d'apparaître)
>> * 6: Case unique (si vous marchez dessus s'enflamme ensuite)
>> * 7: Case en feu (devient unique si vous utilisezune capsule d'eau dessus)
>> * 8: Porte ouverte
>> * 9: Porte qui permet de changer d'étages
> 
> Sous aucun prétexte vous ne devez changer le nom du fichier d'une des salles. Il vous indique le nombre et les position des portes pour chacune d'entre elles.
>> Ils sont sous la forme Salle_XXXXY.salle, X étant la lettre de la position d'une porte, et si il y a Y, cela indique une variante de la salle.
>> * H: porte sur le mur en __haut__ de la salle
>> * D: porte sur le mur à __droite__ de la salle
>> * B: porte sur le mur en __bas__ de la salle
>> * G: porte sur le mur à __gauche__ de la salle
> 
> Pour remplir un fichier de salle veillez à ce que les murs entour toute la salle.
> (Vous pouvez vous référez à titre d'exemple à Salle_Debut.salle)
> 
> Respecter la position relative et le nombre de portes indiqués par le nom du fichier.
