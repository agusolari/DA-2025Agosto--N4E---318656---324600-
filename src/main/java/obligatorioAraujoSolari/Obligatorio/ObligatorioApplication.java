package obligatorioAraujoSolari.Obligatorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import obligatorioAraujoSolari.Obligatorio.dominio.Administrador;
import obligatorioAraujoSolari.Obligatorio.dominio.Propietario;
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
			FachadaServicio.getInstancia().registrarAdministrador(new Administrador("12345678", "admin.123", "Usuario Administrador"));
			FachadaServicio.getInstancia().registrarAdministrador(new Administrador("11145678", "123", "Agustin Solari"));

			FachadaServicio.getInstancia().registrarPropietario(new Propietario("Usuario Propietario", "prop.123", "23456789", 2000));
			FachadaServicio.getInstancia().registrarPropietario(new Propietario("Hernan Araujo", "321", "23456111", 1000));

		}
		catch(PeajeException e){
			System.out.println("Error cargando datos de prueba: " + e.getMessage());
		}
		
	}




// 	Administrador
// Cédula de identidad: 12345678
// Contraseña: admin.123
// Nombre completo: Usuario Administrador
// Propietario
// Cédula de identidad: 23456789
// Contraseña: prop.123
// Nombre completo: Usuario Propietario
// Saldo actual: 2000
// Saldo mínimo para alerta: 500

}
