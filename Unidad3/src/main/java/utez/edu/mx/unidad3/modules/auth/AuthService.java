package utez.edu.mx.unidad3.modules.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.unidad3.modules.auth.dto.LoginRequestDTO;
import utez.edu.mx.unidad3.modules.user.BeanUser;
import utez.edu.mx.unidad3.modules.user.UserRepository;
import utez.edu.mx.unidad3.security.jwt.JWTUtils;
import utez.edu.mx.unidad3.security.jwt.UDService;
import utez.edu.mx.unidad3.utils.APIResponse;
import utez.edu.mx.unidad3.utils.PasswordEncoder;

import java.sql.SQLException;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UDService udService;

    @Autowired
    private JWTUtils jwtUtils;

    @Transactional(readOnly = true)
    public APIResponse doLogin(LoginRequestDTO payload){

        try{
            BeanUser found = userRepository. findByUsername(payload.getUsername()).orElse(null);
            if(found == null) return new APIResponse("Usuario no econtrado", true, HttpStatus.NOT_FOUND);

            if(!PasswordEncoder.verifyPassword(payload.getPassword(), found.getPassword()))
                return new APIResponse("Las contraseñas no coinciden", true, HttpStatus.BAD_REQUEST);

            UserDetails ud = udService.loadUserByUsername(found.getUsername());
            String token = jwtUtils.genereteToken(ud);
            return new APIResponse("Operación exitosa", token,false, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new APIResponse("Error al iniciar sesión", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = {SQLException.class,Exception.class})
    public APIResponse register(BeanUser payload){
        try {
            BeanUser found = userRepository.findByUsername(payload.getUsername()).orElse(null);
            if (found != null) return new APIResponse("Usuario ya existente",true,HttpStatus.BAD_REQUEST); {
                payload.setPassword(PasswordEncoder.encodePassword(payload.getPassword()));
                userRepository.save(payload);

                return new APIResponse("Operacion exitosa",true,HttpStatus.CREATED);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            return new APIResponse(
                    "Error al iniciar sesion",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
