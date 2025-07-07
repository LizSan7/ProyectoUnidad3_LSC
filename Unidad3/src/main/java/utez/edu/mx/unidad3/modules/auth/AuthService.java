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
        BeanUser found = userRepository. findByUsernameAndPassword(
                payload.getUsername(),
                payload.getPassword()
        ).orElse(null);

        try{
            if(found == null) return new APIResponse("Usuario no econtrado", true, HttpStatus.NOT_FOUND);
            UserDetails ud = udService.loadUserByUsername(found.getUsername());
            String token = jwtUtils.genereteToken(ud);
            return new APIResponse("Operación exitosa", token,false, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new APIResponse("Error al iniciar sesión", true, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
