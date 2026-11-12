# Atelier 5 — Notes sur l'API REST AutoLoc

Application : `http://localhost:8081/api` (port 8081, `context-path=/api`)
Swagger UI : `http://localhost:8081/api/swagger-ui.html` — OpenAPI JSON : `http://localhost:8081/api/v3/api-docs`

## A. Endpoints

| Contrôleur | Verbe et route | Service appelé | Succès | Erreurs |
|---|---|---|---|---|
| AgenceController | GET /api/agences | IAgenceService.findAll() | 200 | — |
| AgenceController | GET /api/agences/{id} | IAgenceService.findById(id) | 200 | 404 |
| AgenceController | POST /api/agences | IAgenceService.create(x) | 201 | 400 (nom vide) |
| AgenceController | PUT /api/agences/{id} | IAgenceService.update(id, x) | 200 | 404, 400 (nom vide) |
| AgenceController | DELETE /api/agences/{id} | IAgenceService.deleteById(id) | 204 | 404 (409 si la ressource est encore référencée) |
| EmployeController | GET /api/employes | IEmployeService.findAll() | 200 | — |
| EmployeController | GET /api/employes/{id} | IEmployeService.findById(id) | 200 | 404 |
| EmployeController | POST /api/employes | IEmployeService.create(x) | 201 | 400 (agence inexistante / rôle invalide) |
| EmployeController | PUT /api/employes/{id} | IEmployeService.update(id, x) | 200 | 404, 400 (agence inexistante / rôle invalide) |
| EmployeController | DELETE /api/employes/{id} | IEmployeService.deleteById(id) | 204 | 404 (409 si la ressource est encore référencée) |
| VehiculeController | GET /api/vehicules | IVehiculeServices.findAll() | 200 | — |
| VehiculeController | GET /api/vehicules/{id} | IVehiculeServices.findById(id) | 200 | 404 |
| VehiculeController | POST /api/vehicules | IVehiculeServices.create(x) | 201 | 400 (tarif négatif) |
| VehiculeController | PUT /api/vehicules/{id} | IVehiculeServices.update(id, x) | 200 | 404, 400 (tarif négatif) |
| VehiculeController | DELETE /api/vehicules/{id} | IVehiculeServices.deleteById(id) | 204 | 404 (409 si la ressource est encore référencée) |
| EquipementController | GET /api/equipements | IEquipementService.findAll() | 200 | — |
| EquipementController | GET /api/equipements/{id} | IEquipementService.findById(id) | 200 | 404 |
| EquipementController | POST /api/equipements | IEquipementService.create(x) | 201 | 400 (libellé vide) |
| EquipementController | PUT /api/equipements/{id} | IEquipementService.update(id, x) | 200 | 404, 400 (libellé vide) |
| EquipementController | DELETE /api/equipements/{id} | IEquipementService.deleteById(id) | 204 | 404 (409 si la ressource est encore référencée) |
| ClientController | GET /api/clients | IClientService.findAll() | 200 | — |
| ClientController | GET /api/clients/{id} | IClientService.findById(id) | 200 | 404 |
| ClientController | POST /api/clients | IClientService.create(x) | 201 | 400 (email ou permis vide) |
| ClientController | PUT /api/clients/{id} | IClientService.update(id, x) | 200 | 404, 400 (email ou permis vide) |
| ClientController | DELETE /api/clients/{id} | IClientService.deleteById(id) | 204 | 404 (409 si la ressource est encore référencée) |
| ReservationController | GET /api/reservations | IReservationService.findAll() | 200 | — |
| ReservationController | GET /api/reservations/{id} | IReservationService.findById(id) | 200 | 404 |
| ReservationController | POST /api/reservations | IReservationService.create(x) | 201 | 400 (date de fin antérieure, client/véhicule absent ou inexistant) |
| ReservationController | PUT /api/reservations/{id} | IReservationService.update(id, x) | 200 | 404, 400 (date de fin antérieure, client/véhicule absent ou inexistant) |
| ReservationController | DELETE /api/reservations/{id} | IReservationService.deleteById(id) | 204 | 404 (409 si la ressource est encore référencée) |
| MaintenanceController | GET /api/maintenances | IMaintenanceService.findAll() | 200 | — |
| MaintenanceController | GET /api/maintenances/{id} | IMaintenanceService.findById(id) | 200 | 404 |
| MaintenanceController | POST /api/maintenances | IMaintenanceService.create(x) | 201 | 400 (date de fin antérieure, véhicule absent ou inexistant) |
| MaintenanceController | PUT /api/maintenances/{id} | IMaintenanceService.update(id, x) | 200 | 404, 400 (date de fin antérieure, véhicule absent ou inexistant) |
| MaintenanceController | DELETE /api/maintenances/{id} | IMaintenanceService.deleteById(id) | 204 | 404 (409 si la ressource est encore référencée) |
| ContratController | GET /api/contrats | IContratService.findAll() | 200 | — |
| ContratController | GET /api/contrats/{id} | IContratService.findById(id) | 200 | 404 |
| ContratController | POST /api/contrats | IContratService.create(x) | 201 | 400 (identifiant fourni, montant négatif) |
| ContratController | PUT /api/contrats/{id} | IContratService.update(id, x) | 200 | 404, 400 (identifiant fourni, montant négatif) |
| ContratController | DELETE /api/contrats/{id} | IContratService.deleteById(id) | 204 | 404 (409 si la ressource est encore référencée) |
| PaiementController | GET /api/paiements | IPaiementService.findAll() | 200 | — |
| PaiementController | GET /api/paiements/{id} | IPaiementService.findById(id) | 200 | 404 |
| PaiementController | POST/PUT/DELETE /api/paiements… | (aucun) | — | 405 Method Not Allowed |

Les 404 viennent de `ResourceNotFoundException`, les 400 de `IllegalArgumentException`, les 409 de
`DataIntegrityViolationException` : tous traduits par le `GlobalExceptionHandler` (corps JSON : `statut`,
`message`, `horodatage`). Le contrôleur ne produit jamais ces codes lui-même.

## B. Annotations Jackson

| Association | Annotation retenue | Justification |
|---|---|---|
| `Contrat.paiements` ↔ `Paiement.contrat` | `@JsonManagedReference("contrat-paiements")` côté liste, `@JsonBackReference("contrat-paiements")` côté parent | Vraie composition (`cascade = ALL`) : les paiements sont créés dans le JSON du contrat, et le lien enfant → parent est reconstruit à la désérialisation. |
| `Contrat.reservation` (inverse de `Reservation.contrat`) | `@JsonIgnore` | Côté inverse, l'enfant est créé seul et désigne son parent par identifiant. |
| `Agence.vehicules`, `Agence.employes` | `@JsonIgnore` | Côté inverse : évite la boucle Agence → Vehicule → Agence. |
| `Client.reservations` | `@JsonIgnore` | Côté inverse. Pas de `@JsonBackReference` sur `Reservation.client` : la propriété serait ignorée à la lecture et le POST créerait une réservation sans client. |
| `Equipement.vehicules` | `@JsonIgnore` | Côté inverse du `@ManyToMany`. |
| `Agence`, `Vehicule` (cibles LAZY) | `@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})` | Les proxys Hibernate ne sont pas sérialisables (`ByteBuddyInterceptor`). |

Différence : `@JsonBackReference` retire entièrement la propriété du JSON (ni lecture ni écriture) et
exige son pendant `@JsonManagedReference` : la propriété est reconstruite à la désérialisation.
`@JsonIgnore` ignore simplement la propriété dans les deux sens, sans reconstruction : le lien est
alors porté par l'attribut propriétaire (`{ "agence": {"idAgence": 1} }`).

Côté Lombok, `@ToString.Exclude` est posé sur toutes les associations bidirectionnelles pour éviter la
même boucle dans `toString()`.

## C. Postman et Swagger

- **Postman** sert à *tester* : requêtes rejouables, assertions (`pm.test`), enchaînement via des variables
  (`contratId`, `paiementId`…). Exemple : `PUT /api/contrats/{{contratId}}` suivi de `GET /api/paiements`
  vérifie que les paiements sont conservés après la modification ; le POST sur `/api/paiements` doit
  répondre 405.
- **Swagger UI** sert à *documenter et explorer* : contrat synchronisé avec le code (tags, résumés,
  codes de réponse, section Schemas déduite des entités). Exemple : le groupe « Contrats » affiche les
  réponses 200/404 de `GET /api/contrats/{id}` et permet d'exécuter la requête sans client externe.

> À compléter avec tes propres captures d'écran : requête curl relevée dans Swagger UI (étape 9),
> exemple d'erreur réellement rencontré (ex. 500 `StackOverflowError` avant les annotations Jackson).

## D. Anomalies SonarQube

À confirmer/remplacer par le résultat réel de ton analyse (*Analyze with SonarQube for IDE* sur
`web.controller`). Anomalies relevées/corrigées dans le code (l'injection se fait déjà par constructeur, champs `final` + `@RequiredArgsConstructor`) :

| Règle | Explication | Correction |
|---|---|---|
| `java:S1128` — imports inutiles | Imports jamais utilisés (`JsonBackReference` dans `Paiement` avant annotation, `CrudRepository`, `java.util.List` dans les repositories, import `List` dupliqué dans `Equipement`) | Suppression des imports ; remplacement des `import …*` Lombok/JPA inutiles par des imports précis dans les contrôleurs. |
| `java:S4684` — entités persistantes exposées dans un `@RequestMapping` | Les contrôleurs reçoivent et renvoient directement les entités JPA (couplage API/base, champs internes visibles) | Volontaire et provisoire dans cet atelier ; sera corrigé avec les DTO (chapitre 10). Atténué par `@JsonIgnore` / `@JsonManagedReference`. |

## E. Questions de compréhension

1. **Composant d'entrée** : la `DispatcherServlet`. Elle interroge le `HandlerMapping`, qui retrouve la
   méthode du contrôleur à partir du verbe HTTP et de l'URL (`@GetMapping`, `@RequestMapping`…).
2. **Dépendance à l'interface** : `ContratController` dépend de `IContratService` pour rester mince
   (aucune logique métier ni accès aux données) et découplé de l'implémentation, remplaçable sans toucher
   au contrôleur. Passer par `ContratRepository` court-circuiterait la couche Service (règles métier,
   validations, 404).
3. **Codes de retour** : un POST crée une ressource → `201 Created` ; un DELETE réussi n'a rien à
   renvoyer → `204 No Content`. Une ressource absente est une erreur du client (identifiant inconnu) →
   `404`, traduite depuis `ResourceNotFoundException` par le `GlobalExceptionHandler` ; un 500 signalerait
   une erreur serveur non maîtrisée.
4. **Paiement en lecture seule** : le `Contrat` porte ses paiements (`cascade = ALL`) : ils sont créés,
   modifiés et supprimés via lui. `PaiementController` n'expose donc que deux GET. Un `POST /api/paiements`
   reçoit `405 Method Not Allowed`.
5. **Exposition des entités JPA** : couplage entre API et base, champs internes visibles, boucles de
   sérialisation, proxys LAZY, risque de modifier des attributs non voulus (mass assignment). Les DTO
   résolvent ces points. `Contrat.paiements` est une vraie composition (l'enfant se crée dans le JSON de
   son parent) → paire `@JsonManagedReference` / `@JsonBackReference`. `Agence.vehicules` est le côté
   inverse d'une association où l'enfant est créé seul et désigne son parent par identifiant →
   `@JsonIgnore` suffit.
