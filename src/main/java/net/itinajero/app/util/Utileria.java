package net.itinajero.app.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Utileria {

	public static List<String> getNextDays(int count)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
		System.out.println(sdf);

		Date start = new Date();
		System.out.println(start);

		Calendar cal = Calendar.getInstance();
		System.out.println(cal);
		cal.add(Calendar.DAY_OF_MONTH, count);
		Date endDate = cal.getTime();

		GregorianCalendar gcal = new GregorianCalendar();
		System.out.println(gcal);
		gcal.setTime(start);
		List<String> nextDays = new ArrayList<String>();
		System.out.println(nextDays);
		while(!gcal.getTime().after(endDate))
		{
			Date d = gcal.getTime();
			gcal.add(Calendar.DATE, 1);
			nextDays.add(sdf.format(d));
		}
		System.out.println(nextDays);
		return nextDays;

	}

	public static String guardarImagen(MultipartFile multiPart, HttpServletRequest request) {
// Obtenemos el nombre original del archivo
		String nombreOriginal = multiPart.getOriginalFilename();
		// Reemplazamos en el nombre de archivo los espacios por guiones
		nombreOriginal = nombreOriginal.replace(" ", "-");
		// Agregamos al nombre del archivo 8 caracteres aleatorios para evitar duplicados.
		nombreOriginal = randomAlphaNumeric(8)+nombreOriginal;

// Obtenemos la ruta ABSOLUTA del directorio images
// apache-tomcat/webapps/cineapp/resources/images/
		String rutaFinal = request.getServletContext().getRealPath("/resources/images/");
		try {
// Formamos el nombre del archivo para guardarlo en el disco duro
			File imageFile = new File(rutaFinal + nombreOriginal);
			System.out.println("RUTA DE LA IMAGEN: " + imageFile.getAbsolutePath());
// Aqui se guarda fisicamente el archivo en el disco duro
			multiPart.transferTo(imageFile);
			return nombreOriginal;
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			return null;
		}
	}

	// Metodo para generar una cadena de longitud N de caracteres aleatorios.
	public static String randomAlphaNumeric(int count) {
		String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * CARACTERES.length());
			builder.append(CARACTERES.charAt(character));
		}
		return builder.toString();
	}
}
