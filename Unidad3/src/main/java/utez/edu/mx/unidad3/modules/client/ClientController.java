package utez.edu.mx.unidad3.modules.client;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.unidad3.utils.APIResponse;

@RestController
@RequestMapping("/api/client")
@Tag(name = "Controlador de clientes", description = "Operaciones relacionadas con clientes")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("")
    @Operation(summary = "Traer clientes", description = "Trae a todos los clientes registrados en el sistema.")
    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "200",
                    description ="Traer todos los clientes",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            )
    })
    public ResponseEntity<APIResponse> findAll(){
        APIResponse response = clientService.findAll();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Traer cliente solicitado", description = "Trae a todos los clientes registrados en el sistema.")
    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "200",
                    description ="Traer al cliente solicitado",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description ="No se encontró el cliente",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description ="No se pudo consultar al cliente",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            )
    })

    public ResponseEntity<APIResponse> findByID(@PathVariable("id") Long id){
        APIResponse response = clientService.findById(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("")
    @Operation(summary = "Registrar cliente", description = "Registrar la informacion de un cliente.")
    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "201",
                    description ="Registro exitoso de un cliente",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description ="No se pudo guardar al cliente",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            )
    })
    public ResponseEntity<APIResponse> save(@RequestBody Client payload){
        APIResponse response = clientService.save(payload);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
