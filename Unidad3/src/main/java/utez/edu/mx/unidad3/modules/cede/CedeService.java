package utez.edu.mx.unidad3.modules.cede;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.unidad3.utils.APIResponse;
import utez.edu.mx.unidad3.utils.ClaveGenerator;

import java.sql.SQLException;
import java.util.List;

@Service
public class CedeService {
    @Autowired
    private CedeRepository cedeRepository;

    @Transactional(readOnly= true)
    public APIResponse findAll(){
        List<Cede> list = cedeRepository.findAll();
         return new APIResponse("Operación exitosa", list, false, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public APIResponse findById(Long id){
        try{
            Cede found = cedeRepository.findById(id).orElse(null);
            if(found == null){
                return new APIResponse("Cede no encontrada", true, HttpStatus.NOT_FOUND);
            }

            return new APIResponse("Operacion exitosa", found, false, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new APIResponse("No se pudo consultar la cede", true, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public APIResponse save(Cede payload){
        try{
            payload.setClave("pending...");
            Cede saved = cedeRepository.save(payload);

            saved.setClave(ClaveGenerator.generateCedeClave(saved.getId()));
            cedeRepository.save(saved);

            return new APIResponse("Operacion exitosa", false, HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new APIResponse("No se pudo registrar la cede", true, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public APIResponse update(Cede payload){
        try{
            if(cedeRepository.findById(payload.getId()).isEmpty()){
                return new APIResponse("Cede no encontrada", true, HttpStatus.NOT_FOUND);
            }

            cedeRepository.save(payload);
            return new APIResponse("Operación exitosa", false, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new APIResponse("No se pudo actualizar la cede", true, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public APIResponse remove(Cede payload){
        try{
            if(cedeRepository.findById(payload.getId()).isEmpty()){
                return new APIResponse("Cede no encontrada", true, HttpStatus.NOT_FOUND);
            }

            cedeRepository.deleteById(payload.getId());
            return new APIResponse("Operación exitosa", false, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new APIResponse("No se pudo eliminar la cede", true, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
