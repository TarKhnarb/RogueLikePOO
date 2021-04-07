package Plateau.Salles.Cases;

import Plateau.Jeu;

public enum TypeCase{

    Mur,            // 0: Mur, non Tranversable
    Normale,        // 1: Case normale, traversable
    Vide,           // 2: Case de vide, non traversable ormi si le saut est intégré
    PorteFerme,     // 3: Porte, traversable uniquement si le Hero à au moins un clé
    Coffre,         // 4: Coffre, traversable uniquement si le Hero à au moins un clé
    Cle,            // 5: Clé, case normale avec une clé dessus
    Unique,         // 6: Case à usage unique traversable une seul fois après s'enflamme
    Feu,            // 7: Case à usage unique enflammée, nécessite une capsule d'eau pour la retraverser
    PorteOuvert,    // 8: Porte ouverte
    PorteEtage,     // 9: Porte de changement d'étage
    CoffreOuvert,   // 10: Coffre déja fouillé ne peut être instancié dans un fichier .salle
    All
}

/*
*   Il serait intéressant de représenter les dallages de chaques salles avec des entier allant
*   de 0 à 7 dans des fichiers .txt (ou .salle, etc). Si le nombre de cases dépasse 7 alors il faudra passer
*   sur des lettres et il faudra une fonction de convertion lettre -> TypeCase
*/
