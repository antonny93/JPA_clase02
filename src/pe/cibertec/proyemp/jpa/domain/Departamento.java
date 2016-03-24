package pe.cibertec.proyemp.jpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departamento {

	@Id
	@GeneratedValue
	// (strategy = GenerationType.AUTO) Por default es auto
	private Long id;

	private String nombre;

	@OneToMany(mappedBy="departamento",cascade = CascadeType.PERSIST)
	private List<Empleado> empleados = 
			new ArrayList<Empleado>();
	
	/**
	 * Crear 1 constructor con parámetros y 1 vacío según JPA (es necesario)
	 * @param nombre
	 */
	public Departamento() {}
	
	public Departamento(String nombre) {
		this.nombre = nombre;
	}
	

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nombre=" + nombre + "]";
	}

}
