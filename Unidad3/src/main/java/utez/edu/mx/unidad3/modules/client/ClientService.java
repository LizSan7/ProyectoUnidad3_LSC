package utez.edu.mx.unidad3.modules.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.unidad3.utils.APIResponse;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    /*
     *private final ClientRepository clientRepository;
     *
     * public ClientService(ClientRepository clientRepository){
     *  this.clientRepository = clientRepository;
     * }
     *
     */
    @Transactional(readOnly = true)
    public APIResponse findAll(){
        List<Client> list = clientRepository.findAll();
        return new APIResponse("Operacion exitosa",list,false, HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    public APIResponse findById(Long id){
        try{
            Client found = clientRepository.findById(id).orElse(null);
            if(found == null){
                return new APIResponse("No se encontró al cliente solicitado", true, HttpStatus.NOT_FOUND);
            }

            return new APIResponse("Operacion exitosa", found, false, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            return new APIResponse("No se pudo consultar al cliente", true, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @Transactional
    public APIResponse save(Client payload){
        try{
            clientRepository.save(payload);
            return new APIResponse("Operacion exitosa", false, HttpStatus.CREATED);

        }catch(Exception e){
            e.printStackTrace();
            return new APIResponse("No se pudo encontrar al cliente", true, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @Transactional
    public APIResponse update(){
        return null;
    }
    @Transactional
    public APIResponse remove(){
        return null;
    }
}