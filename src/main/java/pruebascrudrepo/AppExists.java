package pruebascrudrepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import net.gerr.app.repository.NoticiasRepository;

public class AppExists {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/spring/root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		// M�todo para verificar si una entidad existe en la base de datos [metodo existsById del repositorio]
		int idNoticia=100;
		System.out.println(repo.existsById(idNoticia));
		
		context.close();
	}

}
