package utez.edu.mx.unidad3.modules.cede;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.unidad3.utils.APIResponse;

@RestController
@RequestMapping("/api/cede")
@Tag(name = "Controlador de cedes", description = "Operaciones relacionadas con cedes")
@SecurityRequirement(name = "bearerAuth")

public class CedeController {
    @Autowired
    private CedeService cedeService;

    @GetMapping("")
    @Operation(summary = "Traer cedes", description = "Trae a todas las cedes registrados en el sistema.")
    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "200",
                    description ="Traer todas las cedes",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            )
    })
    public ResponseEntity<APIResponse> findAll(){
        APIResponse response = cedeService.findAll();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Traer cede solicitada", description = "Trae a la cede solicitada.")
    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "200",
                    description ="Traer a la cede solicitada",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description ="No se encontró la cede",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description ="No se pudo consultar a la cede",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            )
    })
    public ResponseEntity<APIResponse> findById(@PathVariable("id") Long id){
        APIResponse response = cedeService.findById(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("")
    @Operation(summary = "Registrar cede", description = "Registrar cedes")
    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "200",
                    description ="Traer a la cede solicitada",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description ="No se pudo realizar la solicitud",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description ="No se pudo registrar a la cede",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            )
    })
    public ResponseEntity<APIResponse> save(@RequestBody Cede payload){
        APIResponse response = cedeService.save(payload);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("")
    @Operation(summary = "Actualizar cede", description = "Actualizar la informacion de una cede.")
    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "200",
                    description ="Actualización exitosa de una cede",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description ="No se pudo actualizar la cede",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            )
    })
    public ResponseEntity<APIResponse> update(@RequestBody Cede payload){
        APIResponse response = cedeService.update(payload);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("")
    @Operation(summary = "Eliminar cede", description = "Elimina la informacion de una cede.")
    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "201",
                    description ="Eliminacion exitosa de una cede",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description ="No se pudo eliminar la cede",
                    content={
                            @Content(mediaType = "application/json", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema =@Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/html", schema =@Schema(implementation = APIResponse.class))
                    }
            )
    })
    public ResponseEntity<APIResponse> remove(@RequestBody Cede payload){
        APIResponse response = cedeService.remove(payload);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
