package pruebascrudrepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.gerr.app.model.Noticia;
import net.gerr.app.repository.NoticiasRepository;

// Aplicacion para persistir (Crear) en la tabla Noticias un objeto de tipo Noticia
public class AppCreate {

	public static void main(String[] args) {
		
		Noticia noticia = new Noticia();
		noticia.setTitulo("Proximo Estreno: Juego Macabro 8");
		noticia.setDetalle("El mes de septiembre se estrena la nueva entrega de SAW 8");
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/spring/root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		// Operacion CRUD - Create [metodo save del repositorio]
		repo.save(noticia);
		
		context.close();

	}

}
