Anotaciones importantes de lo aprendido:

video 66 

    imprimir fecha con formato.
    
        1. agregar el tags
            <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
            
        2. usar el prefix fmt
            <td><fmt:formatDate pattern="dd-MM-yyyy" value="${pelicula.fechaEstreno}" />  </td>
            
    obtener la path de la ruta
    
        1. agregar el tags
            <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
            
        2. declarar las variables
            <spring:url value="/resources" var="urlPublic" />
            <spring:url value="/peliculas/create" var="urlCreate" />
            
        3. utilizar las variables, por ejemplo en el link css
            <link href="${urlPublic}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
            <link href="${urlPublic}/bootstrap/css/theme.css" rel="stylesheet">
      
video 69
    
    Atributo flash y BindinfResult: envio de mensaje a pagina redireccionada y captura de errores de carga en el formulario
    
        1. agregar al controller RedirectAttributes y BindingResult
        
            @PostMapping("/save")
            public String guardar(Pelicula pelicula, BindingResult result, RedirectAttributes redirectAttributes){ //BindingResult captura el error del binding
        
                if (result.hasErrors()){ 
                    System.out.println("errores");
                    //imprimo el error en consola, pero se envia automaticamente a la vista al usarlo
                }
                
                servicePelicula.insertar(pelicula);
        
                //aca se carga el atributo para la redireccion.
                redirectAttributes.addFlashAttribute("mensaje", "La pelicula se cargo de manera correcta");
        
                return "redirect:/peliculas/index";
            } 
video 70

    Configuracion para Upload de imagenes
    
        1. configurar DispatcherServlet(web.xml)
        
            windows:
            
            <servlet>
                <servlet-name>springmvc</servlet-name>
                <load-on-startup>1</load-on-startup>
                . . .
                <multipart-config>
                <location>c:\tmp</location>
                </multipart-config>
            </servlet>
            
            linux:
            
            <servlet>
                <servlet-name>springmvc</servlet-name>
                <load-on-startup>1</load-on-startup>
                . . .
                <multipart-config>
                <location>/tmp</location>
                </multipart-config>
            </servlet>
            
        2. Declarar el bean en el dispatcher
            <bean id="multipartResolver" class="org.springframework.web.multipart.support.StandardServletMultipartResolver" />
            
        3. agregar atributo enctype al formulario
        
            <form action="/save" enctype="multipart/form-data">
                <input type="file" name="archivoImagen" />
                <input type="submit">
            </form> 
            
video 71 

    Subir y Guardar la imagen en el disco
    
        1. Agregar al controlador @RequestParam de tipo MultipartFile y httpServletRequest para capturar la imagen y la url
        
            @PostMapping(value = "/save")
            public String guardar(... @RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request)
            {
                if (!multiPart.isEmpty()) {
                    String nombreImagen = guardarImagen(multiPart,request);
                }
            ...
            }
        
        2. metodo para guardar la imagen
        
            private String guardarImagen(MultipartFile multiPart, HttpServletRequest request) {
            
                // Obtenemos el nombre original del archivo
                String nombreOriginal = multiPart.getOriginalFilename();
                
                // Obtenemos la ruta ABSOLUTA del directorio images
                // apache-tomcat/webapps/cineapp/resources/images/
                String rutaFinal = request.getServletContext().getRealPath("/resources/images/");
                
                try {
                    // Formamos el nombre del archivo para guardarlo en el disco duro
                    File imageFile = new File(rutaFinal + nombreOriginal);
                    // Aqui se guarda fisicamente el archivo en el disco duro
                    multiPart.transferTo(imageFile);
                    return nombreOriginal;
                } catch (IOException e) {
                    System.out.println("Error " + e.getMessage());
                    return null;
                }
            }