package pe.cibertec.proyemp.jpa.test;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import pe.cibertec.proyemp.jpa.domain.Departamento;
import pe.cibertec.proyemp.jpa.domain.Empleado;

public class JPA_Test{
	
	private EntityManager manager;

	public JPA_Test(EntityManager manager) {
		this.manager = manager;
	}
	
	public static void main(String[] args) {
		EntityManagerFactory factory = 
				Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager manager = 
				factory.createEntityManager();
		JPA_Test test = 
				new JPA_Test(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			//test.crearEmpleados();
			  test.crearEmpleados2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		
		test.listarEmpleados();
		test.mostrarEmpleadoId1();
		System.out.println(".. done");
	}
	
	@SuppressWarnings("unused")
	private void crearEmpleados() {
		int nroDeEmpleados = manager.createQuery(
				//"From Empleado",
				"Select a From Empleado a", // JQL / HQL
				Empleado.class).getResultList().size();
		if (nroDeEmpleados == 0) {
			Departamento departamento = new Departamento("Java");
			manager.persist(departamento);
			
			manager.persist(new Empleado("Bob",departamento));
			manager.persist(new Empleado("Mike",departamento));
		}
	}
	
	private void crearEmpleados2() {
		Departamento departamento = new Departamento(".NET");
		Empleado empleado1 = new Empleado("Bob");
		Empleado empleado2 = new Empleado("Mike");
		
//		List<Empleado> empleados = new ArrayList<Empleado>();
//		empleados.add(empleado1);
//		empleados.add(empleado2);
		
		departamento.setEmpleados(Arrays.asList(empleado1,empleado2));
		manager.persist(departamento);
		
	}
	
	private void listarEmpleados() {
		List<Empleado> resultList = 
				manager.createQuery("Select a From Empleado a", 
						Empleado.class).getResultList();
		System.out.println("nro de empleados:" + resultList.size());
		for (Empleado next : resultList) {
			System.out.println("siguiente empleado: " + next);
		}
	}
	
	private void mostrarEmpleadoId1(){
//		Empleado empleado = 
//				manager.createQuery("Select a From Empleado a where a.id = 1", 
//						Empleado.class).getSingleResult();
		/**
		 * Filtrando con método Find
		 */
		Empleado emp = 
				manager.find(Empleado.class, new Long(1)); 
		System.out.println(emp);
	}

	
	
	
}