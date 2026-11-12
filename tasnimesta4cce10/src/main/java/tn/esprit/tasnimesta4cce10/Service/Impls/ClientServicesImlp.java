package tn.esprit.tasnimesta4cce10.Service.Impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tasnimesta4cce10.domain.Client;
import tn.esprit.tasnimesta4cce10.repository.IClientRepository;
import tn.esprit.tasnimesta4cce10.Service.IClientService;
import tn.esprit.tasnimesta4cce10.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServicesImlp implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    public Client create(Client client) {
        if (client.getIdClient() != null) {
            throw new IllegalArgumentException("Un nouvel élément ne doit pas avoir d'identifiant");
        }
        verifier(client);
        client.setReservations(null);
        return clientRepository.save(client);
    }

    @Override
    public Client findById(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable avec l'identifiant " + id));
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client introuvable avec l'identifiant " + id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    public Client update(long id, Client client) {
        verifier(client);
        Client existant = findById(id);
        existant.setNom(client.getNom());
        existant.setPrenom(client.getPrenom());
        existant.setEmail(client.getEmail());
        existant.setTelephone(client.getTelephone());
        existant.setNumPermis(client.getNumPermis());
        existant.setDateInscription(client.getDateInscription());
        return clientRepository.save(existant);
    }

    private void verifier(Client client) {
        if (client.getEmail() == null || client.getEmail().isBlank()) {
            throw new IllegalArgumentException("L'email du client est obligatoire");
        }
        if (client.getNumPermis() == null || client.getNumPermis().isBlank()) {
            throw new IllegalArgumentException("Le numéro de permis du client est obligatoire");
        }
    }
}
