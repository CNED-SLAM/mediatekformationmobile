# mediatekformationmobile
Cette application mobile permet d'accéder aux vidéos des formations proposées par la médiathèque.
## Fonctionnalités
L'application permet de consulter la liste des titres des formations mises à disposition (récupérées dans la base de données distante, via une API REST).<br>
En sélectionnant un titre, une page présente plus de détails sur la formation et le clic sur l'image permet d'accéder à la page de diffusion de la vidéo à partir de son adresse en ligne.
### Page 1 : l'accueil
La page d'accueil présente le menu :<br>
. "les formations" : avec en dessous, une image cliquable qui permet d'accéder à la page des formations.<br>
. "mes favoris" : avec en dessous, une image cliquable, non encore opérationnelle.<br>
![img1](https://github.com/CNED-SLAM/mediatekformationmobile/assets/100127886/652598f7-42cd-4bac-a14f-ee1857242f92)
<br>
Le code se trouve dans MainActivity lié au layout activity_main.<br>	 
### Page 2 : les formations
La page des formations affiche la liste des formations avec, sur chaque ligne :<br>
. un cœur actuellement non opérationnel, qui permettra de sélectionner les favoris ;<br>
. la date de la formation et, en dessous, son titre en plus gros.<br>
La liste est triée sur la date de parution, dans l'ordre inverse (la plus récente en premier).<br>
Au-dessus de la liste, une zone de saisie et un bouton "filtrer" doit permettre de filtrer les lignes sur le titre (en tapant une partie du titre, la casse n'est pas prise en compte). Si le bouton "filtrer" est utilisé alors que la zone de saisie est vide, toutes les formations devront être à nouveau affichées. La fonctionnalité n'est pas encore implémentée.<br>
Le clic sur une date ou un titre de formation permet d'accéder à la page suivante de la présentation du détail d'une formation.<br>
Le clic sur la flèche à gauche du titre permet de revenir au menu (cette possibilité est définie dans le manifest).<br>
![img2](https://github.com/CNED-SLAM/mediatekformationmobile/assets/100127886/6ff9c17a-3b2f-4d8d-a2a0-ddb2cb7a4006)
<br>
Le code se trouve dans FormationsActivity lié au layout activity_formations. FormationActivity fait appel à FormationListAdapter pour gérer la liste interactive. Chaque ligne de la liste interactive est construite avec le layout layout_liste_formations.	 
### Page 3 : une formation
La page de présentation d'une formation contient, de haut en bas :<br>
. l'image de la formation (correspondant à l'attribut "picture") ;<br>
. le titre de la formation (en gros) ;<br>
. la date de parution ;<br>
. le sous-titre "description" avec, en dessous, la description détaillée dans une zone déroulante (dans le cas où elle dépasse la taille de la zone d'affichage).<br>
Le clic sur l'image permet d'accéder à la page suivante contenant la vidéo. Si la formation n'a pas d'image, une image standard s'affiche, cependant, la fonctionnalité d'accès à la page de la vidéo est opérationnelle.<br>
Le clic sur la flèche à gauche du titre permet de revenir à la page de la liste des formations.<br>
![img3](https://github.com/CNED-SLAM/mediatekformationmobile/assets/100127886/abc37199-a02c-4653-9c0c-983cb88bcce9)
<br>
Le code se trouve dans UneFormationActivity lié au layout activity_une_formation.	
### Page 4 : la vidéo
La page de la vidéo affiche la vidéo en pleine page et permet de la lancer. Cette page peut être gérée verticalement ou horizontalement (suivant le format de la vidéo, la version horizontale est souvent plus confortable).<br>
Pour que la vidéo soit la plus grande possible, la barre du haut a été supprimée. Pour revenir à la page précédente, il suffit d'utiliser la flèche gauche de navigation en bas du smartphone.<br>
![img4](https://github.com/CNED-SLAM/mediatekformationmobile/assets/100127886/5708c193-3753-47e6-887c-c4defc3a073f)
<br>
Le code se trouve dans VideoActivity lié au layout activity_video qui se décline en 2 versions.
## Base de données
La base de données 'mediatekformation' est au format MySQL. Elle est aussi utilisée pour l'application web.<br>
Elle contient plusieurs tables mais une seule est utile pour l'application mobile : la table 'formation' dont voici la structure :<br>
<pre><code>CREATE TABLE formation (
  id int(11) NOT NULL,
  published_at datetime DEFAULT NULL,
  title varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  description longtext COLLATE utf8mb4_unicode_ci,
  video_id varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  playlist_id int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;</code></pre><br>
Explication des champs :<br>
. id : identifiant de la formation<br>
. published_at : date de parution<br>
. title : titre<br>
. description : description<br>
. video_id : id de la vidéo sur YouTube. Cet id correspond à la fin du chemin obtenu en cliquant sur le bouton "partager" d'une vidéo sur YouTube. Par exemple, l'id " Z4yTTXka958" a été récupéré dans le lien de partage "https://youtu.be/Z4yTTXka958"<br>
. playlist_id : id de la playlist qui contient cette formation (non utilisé ici).
## Contenu de l'API REST
L'API REST qui permet d'accéder à la base de données est composée de 5 fichiers :<br>
. .htaccess : contient la route redéfinie pour récupérer les formations.<br>
. mediatekformationmobile.php : fichier d'entrée dans l'API (récupère les variables en GET et appelle les méthodes de Controle, suivant le verbe http.<br>
. Controle.php : sollicite AccessBDD pour exécuter les demandes et retourne l'information demandée en l'affichant au format JSON.<br>
. AccessBDD.php : prépare les requêtes et demande à ConnexionPDO de les exécuter.<br>
 . ConnexionPDO.php : se connecte à la BDD et exécute les requêtes.<br>
L'application n'utilise que le verbe http "GET" mais L'API permet de gérer les 4 verbes standards.
## Installation des applications
### API REST
Pour tester l'application mediatekformationmobile local, il faut d'abord installer l'API. Voici le mode opératoire :<br>
. Installer les outils nécessaires (WampServer ou équivalent, Netbeans ou équivalent, Postman pour les tests).<br>
. Récupérer le zip du code de l'API (en racine du dépôt) et le dézipper dans le dossier www de wampserver (renommer le dossier en "rest_mediatekformationmobile", donc en enlevant "_master").<br>
. Récupérer le script mediatekformation.sql dans le zip précédent, avec phpMyAdmin, créer la BDD mediatekformation et, dans cette BDD, exécuter le script pour remplir la BDD.<br>
. Ouvrir l'API dans Netbeans (ou autre IDE) pour pouvoir analyser le code et le faire évoluer suivant les besoins.
### Application Android
Pour installer et tester l'application Android en local, consulter l'article "Préparer l'environnement de travail" dans le wiki.
