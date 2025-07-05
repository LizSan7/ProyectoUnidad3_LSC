package utez.edu.mx.unidad3.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import utez.edu.mx.unidad3.modules.user.BeanUser;
import utez.edu.mx.unidad3.modules.user.UserRepository;

import java.util.Collections;

//Tercer Paso: Crear nuestro servicio de gestión de autoridades
public class UDService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BeanUser found = userRepository.findByUsername(username).orElse(null);
        //Buscar primero al usuario
        if (found == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        //Generar las autoridades para el contexto de seguridad
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + found.getRol().getName());

        //Retornar el objeto de usuario para registrar en el contexto de seguridad
        return new User(
                found.getUsername(),
                found.getPassword(),
                Collections.singleton(authority)
        );
    }
}
