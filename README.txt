############################### SPRINT 1 #################################
_________________________________________________________________________

# Backlog S1
	1 - Déplacer un personnage
	2 - Faire évoluer le personnage dans un labyrinthe "infini"
	3 - Interagir avec une interface graphique
	4 - Jouer dans l’objectif de manger toutes les pièces 
	5 - Éviter le monstre se déplaçant aléatoirement 
_________________________________________________________________________

# Répartition des tâches :
Clément : boucles du jeu (classe game)
Paul : isFranchissable + Player + NPC
Xavier : interface graphique (classe interface)
Rémi : getObstacle (classe game), maven
_________________________________________________________________________

# Review :
Fonctionnalités réalisées et validées (ou pas)
	1 - Déplacer un personnage => Validé
	2 - Faire évoluer le personnage dans un labyrinthe "infini" => Validé
	3 - Interagir avec une interface graphique => Validé
	4 - Jouer dans l’objectif de manger toutes les pièces => Validé
	5 - Éviter le monstre se déplaçant aléatoirement => Validé
_________________________________________________________________________

# Rétrospective:
Ce qui s’est bien passé (ou pas) et les décision pour le sprint suivant

Positif :
	1 - Les objectifs fixés au début du sprint ont été respecté	
Négatif :
	1 - Problème de communication (incompréhension du fonctionnement du jeu dans son aspect global entre certains membres de l’équipe)
	2 - Problème de gestion du temps
	3 - Problème technique (apprentissage de maven difficile, découverte de git) 
	4 - Différence notable entre le diagramme de classe et la structure du programme

Décisions pour le sprint suivant:
	1 - Travailler plus régulièrement 
	2 - Se référer plus souvent au Diagramme de classe
	3 - Mettre au clair la direction que le jeu va prendre afin d’éviter toutes incompréhensions
	4 - Lister de façon non exhaustive les tâches à réaliser pour la fin du sprint
_________________________________________________________________________

Pour lancer le programme, entrer la commande (Maven) :
java -cp target/Pacman-1.0.jar main.java.Pacman.engine.Main