############################### SPRINT 2 #################################

Backlog S2
	1 - Avoir des vies
	2 - Montrer un compteur de score
	3 - Plusieurs ennemis pr�sents dans le labyrinthe

R�partition des t�ches :
Xavier : interface graphique (classe interface, m�thode render, )
Cl�ment : level (load, listes d��l�ments), boucle
Paul : fonction controller ()
R�mi : gestion du d�placement, 

Boucle :
get control (r�cup�re les commande)
update_position (met � jour les positions en fonction des commandes)
update_state (met � jour le jeux en fonction des positions)
render
_________________________________________________________________________

Review :
Fonctionnalit�s r�alis�es et valid�es (ou pas)
	1 - Avoir des vies -> Valider
	2 - Montrer un compteur de score -> Valider
	3 - Plusieurs ennemis pr�sents dans le labyrinthe -> Valider

R�trospective:

Ce qui s�est bien pass� (ou pas) et les d�cision pour le sprint suivant
Positif :
	Meilleure communication
	Meilleure gestion du temps
	Meilleur respect du diagramme de classe
	
N�gatif :
	Mauvaise r�partition du travail
	Bugs r�siduels et probl�mes d�optimisation

D�cisions pour le sprint suivant :
	Augmenter nombre r�unions
	Affichage d�but et fin de partie
	Menu :
	 - deux levels
	 - vitesse modulable des ennemis (x1 ou x2)
	- nombre de vies modulable
    	  - Correction des bugs


_________________________________________________________________________

Recquiert java 1.8
Pour lancer le programme, entrer la commande (Maven) :
java -cp target/Pacman-2.0.jar main.java.Pacman.engine.Main

Si la fen�tre est blanche lors du lancement. Essayer de r�duire et ouvrir � nouveau la fen�tre de jeu.