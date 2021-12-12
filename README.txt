############################### SPRINT 2 #################################

Backlog S2
	1 - Avoir des vies
	2 - Montrer un compteur de score
	3 - Plusieurs ennemis présents dans le labyrinthe

Répartition des tâches :
Xavier : interface graphique (classe interface, méthode render, )
Clément : level (load, listes d’éléments), boucle
Paul : fonction controller ()
Rémi : gestion du déplacement, 

Boucle :
get control (récupère les commande)
update_position (met à jour les positions en fonction des commandes)
update_state (met à jour le jeux en fonction des positions)
render
_________________________________________________________________________

Review :
Fonctionnalités réalisées et validées (ou pas)
	1 - Avoir des vies -> Valider
	2 - Montrer un compteur de score -> Valider
	3 - Plusieurs ennemis présents dans le labyrinthe -> Valider

Rétrospective:

Ce qui s’est bien passé (ou pas) et les décision pour le sprint suivant
Positif :
	Meilleure communication
	Meilleure gestion du temps
	Meilleur respect du diagramme de classe
	
Négatif :
	Mauvaise répartition du travail
	Bugs résiduels et problèmes d’optimisation

Décisions pour le sprint suivant :
	Augmenter nombre réunions
	Affichage début et fin de partie
	Menu :
	 - deux levels
	 - vitesse modulable des ennemis (x1 ou x2)
	- nombre de vies modulable
    	  - Correction des bugs


_________________________________________________________________________

Recquiert java 1.8
Pour lancer le programme, entrer la commande (Maven) :
java -cp target/Pacman-2.0.jar main.java.Pacman.engine.Main

Si la fenêtre est blanche lors du lancement. Essayer de réduire et ouvrir à nouveau la fenêtre de jeu.