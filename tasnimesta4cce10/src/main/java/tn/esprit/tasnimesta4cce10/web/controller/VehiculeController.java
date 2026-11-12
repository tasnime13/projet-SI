package tn.esprit.tasnimesta4cce10.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.tasnimesta4cce10.Service.IVehiculeServices;
import tn.esprit.tasnimesta4cce10.domain.Vehicule;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
@Tag(name = "Vehicules", description = "Gestion des véhicules")
public class VehiculeController {

    private final IVehiculeServices vehiculeServices;

    @Operation(summary = "Lister les véhicules")
    @ApiResponse(responseCode = "200", description = "Liste des véhicules")
    @GetMapping
    public List<Vehicule> findAll() {
        return vehiculeServices.findAll();
    }

    @Operation(summary = "Consulter un véhicule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Véhicule trouvé"),
            @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    })
    @GetMapping("/{id}")
    public Vehicule findById(@Parameter(description = "Identifiant du véhicule") @PathVariable Long id) {
        return vehiculeServices.findById(id);
    }

    @Operation(summary = "Créer un véhicule")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Véhicule créé"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicule create(@RequestBody Vehicule vehicule) {
        return vehiculeServices.create(vehicule);
    }

    @Operation(summary = "Modifier un véhicule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Véhicule modifié"),
            @ApiResponse(responseCode = "404", description = "Véhicule introuvable"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    @PutMapping("/{id}")
    public Vehicule update(@Parameter(description = "Identifiant du véhicule") @PathVariable Long id,
                      @RequestBody Vehicule vehicule) {
        return vehiculeServices.update(id, vehicule);
    }

    @Operation(summary = "Supprimer un véhicule")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Véhicule supprimé"),
            @ApiResponse(responseCode = "404", description = "Véhicule introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "Identifiant du véhicule") @PathVariable Long id) {
        vehiculeServices.deleteById(id);
    }
    @PutMapping("/{idVehicule}/agence/{idAgence}")
    public Vehicule assignement(@PathVariable Long idVehicule,
                                @PathVariable Long idAgence) {
        return vehiculeServices.assignement(idVehicule, idAgence);
    }
    @PutMapping("/{idVehicule}/equipements/{idEquipement}")
    public Vehicule assignEquipement(@PathVariable Long idVehicule,
                                     @PathVariable Long idEquipement) {
        return vehiculeServices.assignementEquipement(idVehicule, idEquipement);
    }
    @DeleteMapping("/{idVehicule}/equipements/{idEquipement}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unassignEquipement(@PathVariable Long idVehicule,
                                   @PathVariable Long idEquipement) {
        vehiculeServices.unassignementEquipement(idVehicule, idEquipement);
    }
    @PostMapping("/agence/{idAgence}")

    public Vehicule addAndAssignAgence(@RequestBody Vehicule vehicule,
                                       @PathVariable Long idAgence) {
        return vehiculeServices.addAndAssignAgence(vehicule, idAgence);
    }
}
