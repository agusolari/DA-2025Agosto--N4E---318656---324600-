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
import obligatorioAraujoSolari.Obligatorio.dominio.EstadoDeshabilitado;
import obligatorioAraujoSolari.Obligatorio.dominio.EstadoHabilitado;
import obligatorioAraujoSolari.Obligatorio.dominio.EstadoPenalizado;
import obligatorioAraujoSolari.Obligatorio.dominio.EstadoSuspendido;
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

			Tarifa tarifa1 = new Tarifa(150, puesto1, categorias1P1);
			Tarifa tarifa2 = new Tarifa(200, puesto1, categorias2P1);
			Tarifa tarifa3 = new Tarifa(80, puesto1, categorias3P1);

			FachadaServicio.getInstancia().agregarTarifa(tarifa1);
			FachadaServicio.getInstancia().agregarTarifa(tarifa2);
			FachadaServicio.getInstancia().agregarTarifa(tarifa3);
			
			puesto1.agregarTarifas(tarifa1);
			puesto1.agregarTarifas(tarifa2);
			puesto1.agregarTarifas(tarifa3);

			//Puesto 2
			ArrayList<CategoriaVehiculo> categorias1P2 = new ArrayList<>();
			categorias1P2.add(categoriaAuto);
			categorias1P2.add(categoriaMoto);
			categorias1P2.add(categoriaCamioneta);
			ArrayList<CategoriaVehiculo> categorias2P2 = new ArrayList<>();
			categorias2P2.add(categoriaCamion);
			
			Tarifa tarifa4 = new Tarifa(250, puesto2, categorias1P2);
			Tarifa tarifa5 = new Tarifa(400, puesto2, categorias2P2);
			puesto2.agregarTarifas(tarifa4);
			puesto2.agregarTarifas(tarifa5);
			FachadaServicio.getInstancia().agregarTarifa(tarifa4);
			FachadaServicio.getInstancia().agregarTarifa(tarifa5);

			//Puesto 3
			ArrayList<CategoriaVehiculo> categorias1P3 = new ArrayList<>();
			categorias1P3.add(categoriaMoto);
			ArrayList<CategoriaVehiculo> categorias2P3 = new ArrayList<>();
			categorias2P3.add(categoriaAuto);
			ArrayList<CategoriaVehiculo> categorias3P3 = new ArrayList<>();
			categorias3P3.add(categoriaCamioneta);
			ArrayList<CategoriaVehiculo> categorias4P3 = new ArrayList<>();
			categorias4P3.add(categoriaCamion);

			Tarifa tarifa6 = new Tarifa(120, puesto3, categorias1P3);
			Tarifa tarifa7 = new Tarifa(180, puesto3, categorias2P3);
			Tarifa tarifa8 = new Tarifa(220, puesto3, categorias3P3);
			Tarifa tarifa9 = new Tarifa(280, puesto3, categorias4P3);

			puesto3.agregarTarifas(tarifa6);
			puesto3.agregarTarifas(tarifa7);
			puesto3.agregarTarifas(tarifa8);
			puesto3.agregarTarifas(tarifa9);
			FachadaServicio.getInstancia().agregarTarifa(tarifa6);
			FachadaServicio.getInstancia().agregarTarifa(tarifa7);
			FachadaServicio.getInstancia().agregarTarifa(tarifa8);
			FachadaServicio.getInstancia().agregarTarifa(tarifa9);

			//Puesto 4
			ArrayList<CategoriaVehiculo> categorias1P4 = new ArrayList<>();
			categorias1P4.add(categoriaAuto);
			categorias1P4.add(categoriaCamioneta);
			categorias1P4.add(categoriaCamion);
			ArrayList<CategoriaVehiculo> categorias2P4 = new ArrayList<>();
			categorias2P4.add(categoriaMoto);

			Tarifa tarifa10 = new Tarifa(300, puesto4, categorias1P4);
			Tarifa tarifa11 = new Tarifa(150, puesto4, categorias2P4);	

			puesto4.agregarTarifas(tarifa10);
			puesto4.agregarTarifas(tarifa11);
			FachadaServicio.getInstancia().agregarTarifa(tarifa10);
			FachadaServicio.getInstancia().agregarTarifa(tarifa11);


			// Creación de vehículos
			Propietario propietario1 = FachadaServicio.getInstancia().obtenerPropietarioPorCedula("23456789");
			Propietario propietario2 = FachadaServicio.getInstancia().obtenerPropietarioPorCedula("23456111");

			Vehiculo vehiculo1 = new Vehiculo("ABC123", "Rojo", "Sedan", propietario1, categoriaAuto);
			Vehiculo vehiculo2 = new Vehiculo("DEF456", "Azul", "SUV", propietario1, categoriaCamioneta);
			Vehiculo vehiculo3 = new Vehiculo("MNO345", "Blanco", "Camion", propietario1, categoriaCamion);
			Vehiculo vehiculo4 = new Vehiculo("GHI789", "Verde", "Deportivo", propietario2, categoriaMoto);
			Vehiculo vehiculo5 = new Vehiculo("JKL012", "Negro", "Camioneta", propietario2, categoriaCamion);

			FachadaServicio.getInstancia().crearVehiculo(vehiculo1);
			FachadaServicio.getInstancia().crearVehiculo(vehiculo2);
			FachadaServicio.getInstancia().crearVehiculo(vehiculo3);
			FachadaServicio.getInstancia().crearVehiculo(vehiculo4);
			FachadaServicio.getInstancia().crearVehiculo(vehiculo5);

			// Creacion de bonificaciones
			BonificacionTrabajadores bonificacion1 = new BonificacionTrabajadores(propietario1, puesto1, LocalDate.parse("2025-11-09"));
			BonificacionExonerados bonificacion2 = new BonificacionExonerados(propietario2, puesto2, LocalDate.parse("2025-11-09"));
			BonificacionFrecuentes bonificacion3 = new BonificacionFrecuentes(propietario1, puesto3, LocalDate.parse("2025-11-11"));
			BonificacionFrecuentes bonificacion4 = new BonificacionFrecuentes(propietario2, puesto4, LocalDate.parse("2025-11-12"));
			FachadaServicio.getInstancia().agregarBonificacion(bonificacion1);
			FachadaServicio.getInstancia().agregarBonificacion(bonificacion2);
			FachadaServicio.getInstancia().agregarBonificacion(bonificacion3);
			FachadaServicio.getInstancia().agregarBonificacion(bonificacion4);
			propietario1.agregarBonificacion(bonificacion1);
			propietario1.agregarBonificacion(bonificacion3);
			propietario2.agregarBonificacion(bonificacion2);
			propietario2.agregarBonificacion(bonificacion4);

			// Creacion de estados
			FachadaServicio.getInstancia().agregarEstado(new EstadoHabilitado());
			FachadaServicio.getInstancia().agregarEstado(new EstadoDeshabilitado());
			FachadaServicio.getInstancia().agregarEstado(new EstadoSuspendido());
			FachadaServicio.getInstancia().agregarEstado(new EstadoPenalizado());
		}
		catch(PeajeException e){
			System.out.println("Error cargando datos de prueba: " + e.getMessage());
		}
		
	}

}