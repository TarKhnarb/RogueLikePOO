# RogueLikePOO - projet 2020-21

* BELLEMAIN SAGNARD Léo p1721445
* DOMINGUES Kévin 11607884

## Introduction

Rogue est un jeu d’énigmes dans lequel vous contrôlez Le Professeur qui tente de s'échapper 
d’un labyrinthe en 2D, en vue du dessus.  
Pour cela, il peut utiliser des capsules d'eau pour éteindre les cases en feu et sauter pour éviter les cases vides.

## Comment jouer

Le joueur peut ce déplacé grâce aux flèches directionnelles, la barre d'espace est pour le saut, 
la touche "C" pour le lancer de capsule d'eau et la touche "R" pour relancer la partie si vous êtes bloqué.  
Le joueur devra ouvrir des portes avec un nombre limité de clés, il peut récupérer un nombre aléatoire de clé dans
les salles qu'il traverse.  
Il a aussi la possibilité d'ouvrir des coffres disponible dans certaines salles, cette action utilisera une clé.
Le coffre contient un nombre aléatoire de clé et de capsule, d'un montant minimum de un item chacun.
Le joueur aura le choix de prendre où non le contenu du coffre grâce à un pop-up qui apparait au-dessus de l'inventaire.  

Le saut et le lancer de capsule possèdent certaines contraintes :
- Le saut est possible que pour éviter une seule case vide, c'est-à-dire qu'il ne peut éviter deux cases vide, 
  il lui est aussi impossible d'atterrir sur un mur, un coffre ou une case en feu. Il peut sauter que sur une case Normale ou Unique.  
- Le lancer de capsule s'effectue que sur les cases en feu seulement, tout autres cas est impossible.  

## Lancement du jeu


