package pruebasjparepo;

import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort;
import net.gerr.app.model.Noticia;
import net.gerr.app.repository.NoticiasRepository;

public class AppSorting {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/spring/root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);	
		// Obtener todas las entidades ordenadas por un campo.
		//List<Noticia> lista = repo.findAll(Sort.by("titulo").descending());
		
		// Obtener todas las entidades ordenadas por 2 campos.
		List<Noticia> lista = repo.findAll(Sort.by("fecha").descending().and(Sort.by("titulo").ascending()));
		
		for(Noticia n : lista) {
			System.out.println(n);
		}
		
		context.close();

	}

}
