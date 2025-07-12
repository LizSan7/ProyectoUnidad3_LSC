package utez.edu.mx.unidad3.modules.cede;

/*
* 1.- Crear los atributos propios de la entidad
* 2.- Crear los atributos de relación
* 3.- Crear los constructores
* 4.- Crear getters y setters
* */

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import utez.edu.mx.unidad3.modules.warehouse.Warehouse;

import java.util.List;

@Entity
@Table(name = "cede")
public class Cede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Pattern(regexp = "^C\\d+-\\d{8}-\\d{4}$", message = "Solamente se acepta el formato de la clave")
    @Column(name = "clave", nullable = false)
    private String clave;

    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]{2,50}$", message = "Solo se permiten letras y espacios, mínimo 2 y máximo 50 caracteres")
    @NotNull(message = "El estado es obligatorio")
    @NotBlank(message = "El estado no puede estar en blanco")
    @Column(name = "state", nullable = false)
    private String state;

    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]{2,50}$", message = "Solo se permiten letras y espacios, mínimo 2 y máximo 50 caracteres")
    @NotNull(message = "La ciudad es obligatoria")
    @NotBlank(message = "La ciudad no puede estar en blanco")
    @Column(name = "city", nullable = false)
    private String city;

    @OneToMany(mappedBy = "cede")
    @JsonIgnore
    private List<Warehouse> warehouses;

    public Cede() {
    }

    public Cede(Long id, String clave, String state, String city, List<Warehouse> warehouses) {
        this.id = id;
        this.clave = clave;
        this.state = state;
        this.city = city;
        this.warehouses = warehouses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
