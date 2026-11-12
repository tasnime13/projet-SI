package tn.esprit.tasnimesta4cce10.Service.Imple;

import tn.esprit.tasnimesta4cce10.domaine.Client;

import java.util.List;

public interface IClientService {
    Client create(Client client);
    Client findById(long id);
    List<Client> findAll();
    void deleteById(long id);
    Client update(Client client);
}
