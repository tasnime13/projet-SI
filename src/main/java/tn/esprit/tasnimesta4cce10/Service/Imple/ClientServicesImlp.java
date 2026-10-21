package tn.esprit.tasnimesta4cce10.Service.Imple;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tasnimesta4cce10.domaine.Client;
import tn.esprit.tasnimesta4cce10.exception.ResourceNotFoundException;
import tn.esprit.tasnimesta4cce10.repository.IClientRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientServicesImlp implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    public Client create(Client client) {
        if (client.getIdClient() != null) {
            throw new IllegalArgumentException("Une nouvelle ressource ne doit pas avoir d'identifiant");
        }
        verifier(client);
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
        findById(id); // 404 si absent
        clientRepository.deleteById(id);
    }

    @Override
    public Client update(Client client) {
        findById(client.getIdClient()); // 404 si absent
        verifier(client);
        return clientRepository.save(client);
    }

    private void verifier(Client client) {
        if (client.getNumPermis() == null || client.getNumPermis().isBlank()) {
            throw new IllegalArgumentException("Le numero de permis du client est obligatoire");
        }
    }
}