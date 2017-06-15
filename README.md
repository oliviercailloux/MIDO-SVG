## **MIDO-SVG Project **  
***********************

[![Build Status](https://travis-ci.org/Cocolollipop/mido-svg.svg?branch=master)](https://travis-ci.org/Cocolollipop/mido-svg)  

Subject: https://github.com/oliviercailloux/projets/blob/master/mido_svg.adoc  

= MIDO SVG
Le département MIDO propose plusieurs parcours, contenant pour certains des cours à option, avec des passerelles entre certains parcours. Il est difficile pour un enseignant extérieur (ou débutant) de se repérer dans l’offre de cours et de voir comment son cours s’articule avec les cours existants. Une manière de pallier ce problème consiste à représenter graphiquement l’offre de cours et les relations entre les cours. Cependant, de tels dessins requièrent du temps pour être bien faits, et deviennent rapidement obsolète. Ce projet vise à génération automatique de diagrammes au format SVG représentant des organigramme des cours et autres informations liées.

== Récits utilisateur
- [x] Génération d’un SVG avec données statiques : voir dessin à la main, se concentrer sur partie Informatique des Organisations, dessin à partir d’une liste de cours en mémoire.
- [x] Pouvoir ajouter les responsables aux parcours au dessin.
- [x] Pouvoir ajouter les enseignants responsables de chaque cours au dessin.
- [x] Différents types d’informations peuvent être colorées différemment.
- [ ] Différents types d’informations se trouvent dans des couches différentes.
- [ ] Récupérer les données depuis le web.
- [x] Liens sur les parcours renvoient au programme des cours.
- [x] Graphique équivalent pour la license.
- [x] Paramètres : license seulement, master seulement, A3 ou A4.
- [x] Mots clés sur cours. Utilisateur peut associer des mots-clés aux cours via interface graphique.
- [x] Mots clés enregistrés dans un fichier.
- [x] Lecture des mots clés depuis un fichier.
- [in progress] Lecture des mots clés depuis plusieurs fichiers.
- [x] Entrée et affichage de pré-requis entre cours.
- [in progress] Bibliothèque générale permettant de générer des SVG (sans interface graphique).
- [in progress] Bibliothèque particulière permettant de générer des SVG MIDO (sans interface graphique).
- [in progress] Compromis taille police min / espacement / nb données affichées

== Refs
* Voir application link:Test-SVG-Gen[].
* Voir link:MIDO-Svg/MIDO.svg[dessin] à la main.
* CDM-fr : https://fr.wikipedia.org/wiki/Course_description_metadata https://cdm-fr.fr/
* link:Voeux/OF_MEA5STI.xml[]

 
