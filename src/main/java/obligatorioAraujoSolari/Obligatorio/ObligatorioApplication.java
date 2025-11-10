package obligatorioAraujoSolari.Obligatorio;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import obligatorioAraujoSolari.Obligatorio.dominio.Administrador;
import obligatorioAraujoSolari.Obligatorio.dominio.BonificacionExonerados;
import obligatorioAraujoSolari.Obligatorio.dominio.BonificacionFrecuentes;
import obligatorioAraujoSolari.Obligatorio.dominio.BonificacionTrabajadores;
import obligatorioAraujoSolari.Obligatorio.dominio.CategoriaVehiculo;
import obligatorioAraujoSolari.Obligatorio.dominio.Estado;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
import obligatorioAraujoSolari.Obligatorio.dominio.PuestoPeaje;
import obligatorioAraujoSolari.Obligatorio.dominio.Tarifa;
import obligatorioAraujoSolari.Obligatorio.dominio.Vehiculo;
import obligatorioAraujoSolari.Obligatorio.excepciones.PeajeException;
import obligatorioAraujoSolari.Obligatorio.servicios.fachada.FachadaServicio;

@SpringBootApplication
public class ObligatorioApplication {

	public static void main(String[] args) throws PeajeException {
		cargarDatosDePrueba();
		SpringApplication.run(ObligatorioApplication.class, args);
	}

	private static void cargarDatosDePrueba() throws PeajeException {
		// Datos de prueba
		try{
			// Creación de administradores
			FachadaServicio.getInstancia().registrarAdministrador(new Administrador("12345678", "admin.123", "Usuario Administrador"));
			FachadaServicio.getInstancia().registrarAdministrador(new Administrador("11145678", "123", "Agustin Solari"));

			// Creación de propietarios
			FachadaServicio.getInstancia().registrarPropietario(new Propietario("Usuario Propietario", "prop.123", "23456789", 2000));
			FachadaServicio.getInstancia().registrarPropietario(new Propietario("Hernan Araujo", "321", "23456111", 1000));
			FachadaServicio.getInstancia().registrarPropietario(new Propietario("Juan Perez", "juan.456", "29876543", 1500));

			// Creación de puestos de peaje
			FachadaServicio.getInstancia().agregarPuestoPeaje((new PuestoPeaje("Puesto 1", "Ruta 1 Km 10")));
			FachadaServicio.getInstancia().agregarPuestoPeaje((new PuestoPeaje("Puesto 2", "Ruta 1 Km 60")));
			FachadaServicio.getInstancia().agregarPuestoPeaje((new PuestoPeaje("Puesto 3", "Ruta 5 Km 30")));
			FachadaServicio.getInstancia().agregarPuestoPeaje((new PuestoPeaje("Puesto 4", "Ruta 5 Km 90")));

			// Creación de categorías de vehículos
			FachadaServicio.getInstancia().crearCategoriaVehiculo(new CategoriaVehiculo("Auto"));
			FachadaServicio.getInstancia().crearCategoriaVehiculo(new CategoriaVehiculo("Camioneta"));
			FachadaServicio.getInstancia().crearCategoriaVehiculo(new CategoriaVehiculo("Camion"));
			FachadaServicio.getInstancia().crearCategoriaVehiculo(new CategoriaVehiculo("Moto"));

			// Creación de tarifas
			PuestoPeaje puesto1 = FachadaServicio.getInstancia().getPuestoPeajePorNombre("Puesto 1");
			PuestoPeaje puesto2 = FachadaServicio.getInstancia().getPuestoPeajePorNombre("Puesto 2");
			PuestoPeaje puesto3 = FachadaServicio.getInstancia().getPuestoPeajePorNombre("Puesto 3");
			PuestoPeaje puesto4 = FachadaServicio.getInstancia().getPuestoPeajePorNombre("Puesto 4");
			CategoriaVehiculo categoriaAuto = new CategoriaVehiculo("Auto");
			CategoriaVehiculo categoriaCamioneta = new CategoriaVehiculo("Camioneta");
			CategoriaVehiculo categoriaCamion = new CategoriaVehiculo("Camion");
			CategoriaVehiculo categoriaMoto = new CategoriaVehiculo("Moto");

			//Puesto 1
			ArrayList<CategoriaVehiculo> categorias1P1 = new ArrayList<>();
			categorias1P1.add(categoriaAuto);
			categorias1P1.add(categoriaCamioneta);
			ArrayList<CategoriaVehiculo> categorias2P1 = new ArrayList<>();
			categorias2P1.add(categoriaCamion);
			ArrayList<CategoriaVehiculo> categorias3P1 = new ArrayList<>();
			categorias3P1.add(categoriaMoto);

			FachadaServicio.getInstancia().agregarTarifa(new Tarifa(150, puesto1, categorias1P1));
			FachadaServicio.getInstancia().agregarTarifa(new Tarifa(200, puesto1, categorias2P1));
			FachadaServicio.getInstancia().agregarTarifa(new Tarifa(100, puesto1, categorias3P1));

			//Puesto 2
			ArrayList<CategoriaVehiculo> categorias1P2 = new ArrayList<>();
			categorias1P2.add(categoriaAuto);
			categorias1P2.add(categoriaMoto);
			categorias1P2.add(categoriaCamioneta);
			ArrayList<CategoriaVehiculo> categorias2P2 = new ArrayList<>();
			categorias2P2.add(categoriaCamion);

			FachadaServicio.getInstancia().agregarTarifa(new Tarifa(250, puesto2, categorias1P2));
			FachadaServicio.getInstancia().agregarTarifa(new Tarifa(300, puesto2, categorias2P2));

			//Puesto 3
			ArrayList<CategoriaVehiculo> categorias1P3 = new ArrayList<>();
			categorias1P3.add(categoriaMoto);
			ArrayList<CategoriaVehiculo> categorias2P3 = new ArrayList<>();
			categorias2P3.add(categoriaAuto);
			ArrayList<CategoriaVehiculo> categorias3P3 = new ArrayList<>();
			categorias3P3.add(categoriaCamioneta);
			ArrayList<CategoriaVehiculo> categorias4P3 = new ArrayList<>();
			categorias4P3.add(categoriaCamion);

			FachadaServicio.getInstancia().agregarTarifa(new Tarifa(120, puesto3, categorias1P3));
			FachadaServicio.getInstancia().agregarTarifa(new Tarifa(180, puesto3, categorias2P3));
			FachadaServicio.getInstancia().agregarTarifa(new Tarifa(220, puesto3, categorias3P3));
			FachadaServicio.getInstancia().agregarTarifa(new Tarifa(280, puesto3, categorias4P3));

			//Puesto 4
			ArrayList<CategoriaVehiculo> categorias1P4 = new ArrayList<>();
			categorias1P4.add(categoriaAuto);
			categorias1P4.add(categoriaCamioneta);
			categorias1P4.add(categoriaCamion);
			ArrayList<CategoriaVehiculo> categorias2P4 = new ArrayList<>();
			categorias2P4.add(categoriaMoto);

			FachadaServicio.getInstancia().agregarTarifa(new Tarifa(300, puesto4, categorias1P4));
			FachadaServicio.getInstancia().agregarTarifa(new Tarifa(150, puesto4, categorias2P4));

			// Creación de vehículos
			Propietario propietario1 = FachadaServicio.getInstancia().obtenerPropietarioPorCedula("23456789");
			Propietario propietario2 = FachadaServicio.getInstancia().obtenerPropietarioPorCedula("23456111");

			FachadaServicio.getInstancia().crearVehiculo(new Vehiculo("ABC123", "Rojo", "Sedan", propietario1, categoriaAuto));
			FachadaServicio.getInstancia().crearVehiculo(new Vehiculo("DEF456", "Azul", "SUV", propietario1, categoriaCamioneta));
			FachadaServicio.getInstancia().crearVehiculo(new Vehiculo("MNO345", "Blanco", "Camion", propietario1, categoriaCamion));
			FachadaServicio.getInstancia().crearVehiculo(new Vehiculo("GHI789", "Verde", "Deportivo", propietario2, categoriaMoto));
			FachadaServicio.getInstancia().crearVehiculo(new Vehiculo("JKL012", "Negro", "Camioneta", propietario2, categoriaCamion));

			// Creacion de bonificaciones
			FachadaServicio.getInstancia().agregarBonificacion(new BonificacionTrabajadores(propietario1, puesto1, LocalDate.parse("2025-11-09")));
			FachadaServicio.getInstancia().agregarBonificacion(new BonificacionExonerados(propietario2, puesto2, LocalDate.parse("2025-11-09")));
			FachadaServicio.getInstancia().agregarBonificacion(new BonificacionFrecuentes(propietario1, puesto3, LocalDate.parse("2025-11-11")));
			FachadaServicio.getInstancia().agregarBonificacion(new BonificacionFrecuentes(propietario2, puesto4, LocalDate.parse("2025-11-12")));

			// Creacion de estados
			FachadaServicio.getInstancia().agregarEstado(new Estado("Habilitado", "Es el estado por defecto de los propietarios cuando se dan de alta en el sistema. El propietario tiene todas las funcionalidades habilitadas."));
			FachadaServicio.getInstancia().agregarEstado(new Estado("Deshabilitado", "El usuario no puede ingresar al sistema ni puede realizar tránsitos. Tampoco se le pueden asignar bonificaciones."));
			FachadaServicio.getInstancia().agregarEstado(new Estado("Suspendido", "El usuario puede ingresar al sistema, pero no puede realizar tránsitos."));
			FachadaServicio.getInstancia().agregarEstado(new Estado("Penalizado", "El usuario puede ingresar al sistema, pero no se le registran notificaciones. Puede realizar tránsitos, pero no aplican las bonificaciones que tenga asignadas. "));
		}
		catch(PeajeException e){
			System.out.println("Error cargando datos de prueba: " + e.getMessage());
		}
		
	}

}


//TODO: POR HACER MANANA: -Agregar en la precarga de datos, en las listas de cada clase 
// correspondiente y no solo en las listas de los sistemas.