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
            
video 86

    Recordatorio @ModelAttribute
    
        1. Reemplaza el envio de modelo a la vista con formulario, de esta manera se cargan automaticamente.
       
video 91

    Renderizar  atributo option de un SELECT automaticamente
    
video 129

    @Transient // sirve para pasar por alto un atributo en el mapeo.
    
    
Relaciones: Spring Data JPA

video 131
    
        1. Anotacion @OneToOne
            
         @Entity
         @Table(name = "Peliculas")
         public class Pelicula {
         
             @Id
             @GeneratedValue(strategy = GenerationType.IDENTITY)// valor auto_increment generado por MySql
             private int id;
             private String titulo;
             private String clasificacion;
             private int duracion;
             private String genero;
             private String imagen = "cinema.png";
             private Date fechaEstreno;
             private String estatus = "activa";
         
             @OneToOne
             @JoinColumn(name = "idDetalle")
             private Detalle detalle;
      
video 132
    
        1. Anotacion @OneToMuch
            
         @Entity
         @Table(name = "Horarios")
         public class Horarios {
         
             @Id
             @GeneratedValue(strategy = GenerationType.IDENTITY)// valor auto_increment generado por MySql
             private int id;
             private String titulo;
             private String clasificacion;
             private int duracion;
             private String genero;
             private String imagen = "cinema.png";
             private Date fechaEstreno;
             private String estatus = "activa";
         
             @OneToMuch
             @JoinColumn(name = "Horarios")
             private Horario horario;                         
             
video 134

        1. Anotacion ManyToOne
        
            @Entity
            @Table(name = "Horarios")
            public class Horario {
            
                @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                private int id;
                private Date fecha;
                private String hora;
                private String sala;
                private Double precio;
            
                @JoinColumn(name = "idPelicula")
                @ManyToOne
                private Pelicula pelicula;
                
video 135

        1. Anotacion OneToMany
        
            @Entity
            @Table(name = "Peliculas")
            public class Pelicula {
            
                @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)// valor auto_increment generado por MySql
                private int id;
                private String titulo;
                private String clasificacion;
                private int duracion;
                private String genero;
                private String imagen = "cinema.png";
                private Date fechaEstreno;
                private String estatus = "activa";
                
                @OneToMany(mappedBy = "pelicula", fetch = FetchType.EAGER)
                private List<Horario> horarios;
                
video 144

    1. anotacion @ModelAtribute a nivel metodo
        
        al agregar un metodo con esta anotacion es visible para todos los metodos
        
        @ModelAttribute(value = "generos")
            public List<String> generos(){
                return peliculasService.buscarGeneros();
            }
video 146

    1. Eliminar pelicula: repaso de RedirectAtributes->flashAtribute
    
        @GetMapping("/delete/{id}")
            public String eliminar(@PathVariable("id") int idPelicula,  ==>>>> RedirectAttributes attributes <<<<==){
        ==>>>>  attributes.addFlashAttribute("mensaje", "La pelicula fue eliminada"); <<<<==
                peliculasService.eliminar(idPelicula);
                return "redirect:/pelicula/index";
            }
   
MODULO: SPRING SECURITY
    AUTENTICACION: VALIDACION DE USUARIOS
    AUTORIZACION: PERMISOS QUE TIENE EL USUARIO
    FILTROS O INTERCEPTORES: SON EJECUTADOS ANTES Y DESPUES DEL PROCESO DE LA PETICION.
    
video 157

    1. Primer paso: agregar depedencias al POM
    
        <dependency>
        	<groupId>org.springframework.security</groupId>
        	<artifactId>spring-security-web</artifactId>
        	<version>5.1.3.RELEASE</version>
        </dependency>
        <dependency>
        	<groupId>org.springframework.security</groupId>
        	<artifactId>spring-security-config</artifactId>
        	<version>5.1.2.RELEASE</version>
        </dependency>
        
        ======================================================
        para agregar anotaciones en jsp
        ======================================================
        <dependency>
        	<groupId>org.springframework.security</groupId>
        	<artifactId>spring-security-taglibs</artifactId>
        	<version>5.1.3.RELEASE</version>
        </dependency>
        
video 158

    1. Segundo paso: Configurar el filter en el archivo web.xml
          
          <!-- Spring Security Filter -->
          <filter>
            <filter-name>springSecurityFilterChain</filter-name>
            <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
          </filter>
          <filter-mapping>
            <filter-name>springSecurityFilterChain</filter-name>
            ==========================================================================
            con la siguiente linea dejo al filter oyendo todas las peticiones a la app
            ==========================================================================
            <url-pattern>/*</url-pattern> 
          </filter-mapping>
          
 video 159
 
    1. Tercer paso: agregar el directorio donde se va a encontrar security.xml
    
        <context-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>
                /WEB-INF/spring/root-context.xml,
                /WEB-INF/spring/security.xml      <========== esta linea;
            </param-value>
        </context-param>
        
    2.  Crear archivo security.xml
    
            <?xml version="1.0" encoding="UTF-8"?>
            
            <b:beans xmlns="http://www.springframework.org/schema/security"
                     xmlns:b="http://www.springframework.org/schema/beans"
                     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                     xsi:schemaLocation="http://www.springframework.org/schema/beans
                 http://www.springframework.org/schema/beans/spring-beans.xsd
                 http://www.springframework.org/schema/security
                 http://www.springframework.org/schema/security/spring-security.xsd">
            
                <http auto-config="true"> ============= aca se configura los permisos 
                    <!-- Declaramos todos los recursos que estaran protegidos -->
                    <intercept-url pattern="/peliculas/*" access="hasAnyAuthority('USER', 'ADMIN')" />
                    <intercept-url pattern="/horarios/*" access="hasAnyAuthority('USER', 'ADMIN')" />
                    <intercept-url pattern="/noticias/*" access="hasAnyAuthority('USER', 'ADMIN')" />
                    <intercept-url pattern="/banners/*" access="hasAnyAuthority('ADMIN')" />
            
                    <!-- Custom login form --> =============== aca se define el login
                    <form-login login-page="/formLogin" />
                </http>
            
                <authentication-manager>
            
                    <authentication-provider>
                        <user-service>
                            <user name="admin" password="{noop}123" authorities="ADMIN" />
                            <user name="gerr" password="{noop}123" authorities="USER" />
                        </user-service>
                    </authentication-provider>
            
                </authentication-manager>
            
            </b:beans>
            
            
video 161
    
    Personalizacion de acceso mediante intercept-url
    
        1. primero se agrega la configuracion en el archivo security.xml
        
             <http auto-config="true"> ============= aca se configura los permisos 
                                <!-- Declaramos todos los recursos que estaran protegidos -->
                                <intercept-url pattern="/peliculas/*" access="hasAnyAuthority('USER', 'ADMIN')" />
                                <intercept-url pattern="/horarios/*" access="hasAnyAuthority('USER', 'ADMIN')" />
                                <intercept-url pattern="/noticias/*" access="hasAnyAuthority('USER', 'ADMIN')" />
                                <intercept-url pattern="/banners/*" access="hasAnyAuthority('ADMIN')" />
                        
                                <!-- Custom login form --> =============== aca se define el login
                                <form-login login-page="/formLogin" />
                            </http>
                            
video 163 
    
    Destruir sesion de user
    
        @GetMapping(value="/logout")
            public String logout(HttpServletRequest request){
                SecurityContextLogoutHandler logoutHandler =
                        new SecurityContextLogoutHandler();
                logoutHandler.logout(request, null, null);
                return "redirect:formLogin";
            }                        
            
video 164 y 165

    Seguridad en formularios
    
        1. Especificar en el archivo security.xml donde se encuentra el formulario de login
        
            <!-- Custom login form -->
            <form-login login-page="/formLogin" />
        
        2. agregar al controller de usuario el controlador para enviarlo
        
            @RequestMapping(value = "/formLogin")
            public String loguinIndex(){
                return "formLogin";
            }   
            
        3. Agregar el atributo csrf de tipo hidden al form
        
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>                     
            
video 166

    Capturar el error de login en la pagina de loguin: spring manda automatico el parametro error
    y solo hay que capturarlo en el html
    
    <c:if test="${param.error != null}">
       <img src="${urlPublic}/images/error.png" width="48" height="48" class="center">
       <h4 class="form-signin-heading" style="color:red">Acceso denegado</h4>
    </c:if>
    
video 168

    Renderizar menu segun el rol del usuario
    
        1. Primero agregar al pom el tag de spring-security-taglibs
        
            <dependency>
                <groupId>org.springframework.security</groupId>
                <artifactId>spring-security-taglibs</artifactId>
                <version>5.1.3.RELEASE</version>
             </dependency>         
        
        2. Segundo: agregar la dependencia en el archivo jsp esoa, menu.jsp
        
            <%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
            
        3. Tercero: en el archivo html jsp, agregar las distintas opciones de menu
        solo queda agregar los hasAnyAuthority
        
            <nav class="navbar navbar-inverse navbar-fixed-top">
                <sec:authorize access="isAnonymous()">
                    <div class="container">
                        <div class="navbar-header">
                         
                </sec:authorize>
            
                <sec:authorize access="hasAnyAuthority('ADMIN')">
                      <div class="container">
                        </div>
                </sec:authorize>
                <sec:authorize access="hasAnyAuthority('USER')">
                   <div class="container">
                          </div>
                </sec:authorize>
            
            </nav>
            
video 169

    Redirecccionar usuario al index luego de ingresar
    
        1. Primero declarar en el archivo security default-target-url
        
            <form-login login-page="/formLogin" default-target-url="admin/index" />
            
        2. Segundo : en UserControler agregamos un metodo para mostrar la pagina principal
        
            @GetMapping(value = "/index")
                public String mostrarPrincipalAdmin(){
                    return "admin";
                }
        3. en el archivo jsp ej. admin.jsp se agragan el tag de spring security
        
            <%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
            
        4. Ahora se puede utilizar para capturar el usuario de que devuel spring
        
            <sec:authentication property="username"/>
            
ERROR: causado en el archivo security.xml
ERROR ENCONTRADO: fue porque no agregue "/" en la dire por defecto

con error
    
    <form-login login-page="/formLogin" default-target-url="admin/index" />
    
sin error

    <form-login login-page="/formLogin" default-target-url="/admin/index" />        
        
        
video 170 y 171

    Recuperar usuario en el controller con un objeto de tipo authentication
    
        1. para recupera el username
        
            @GetMapping(value = "/index")
            public String mostrarPrincipalAdmin(Authentication authentication){
            
                String name = authentication.getName();
            
                System.out.println("USUARIO: " + name);

                return "admin";
            }
    
        2. para obtener los roles a travez del GrantedAuthority
        
             @GetMapping(value = "/index")
             public String mostrarPrincipalAdmin(Authentication authentication){
                 LOG.info("CLASS '" + clase + "' ====> " + "Usuario: " + authentication.getName());
                 System.out.println("USUARIO: " + authentication.getName());
            
                 for(GrantedAuthority rol: authentication.getAuthorities()){
                     System.out.println("ROL: " + rol.getAuthority());
                 }
                 return "admin";
             }
          
       
video 174 a 175

    Manejo de usuarios y roles desde base de datos
    
        1. agregar en security.xml el authentication manager
        
            <!-- Autenticacion desde una base de datos -->
                <authentication-manager >
                    <authentication-provider>
                        <jdbc-user-service data-source-ref="dataSource" />
                    </authentication-provider>
                </authentication-manager>
                
        2. el data source es el mismo que esta declarado en el root-contex.xml
        
        3. estructura por defecto para esta configuracion:
        
            -- Crear tabla de usuarios
            CREATE TABLE `users` (
            	`username` varchar(50) NOT NULL,
            	`password` varchar(50) NOT NULL,
            	`enabled` tinyint(1) NOT NULL,
            	PRIMARY KEY (`username`)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
            
            -- Crear tabla de roles
            CREATE TABLE `authorities` (
            	`username` varchar(50) NOT NULL,
            	`authority` varchar(50) NOT NULL,
            	UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
            	CONSTRAINT `authorities_ibfk_1`
            	FOREIGN KEY (`username`)
            	REFERENCES `users` (`username`)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
            
video 176

    manejo de usuarios y roles con tablas personalizadas
    
        1. agregar en security.xml
        
            <!--Autenticacion desde una base de datos personalizada -->
            <authentication-manager >
                 <authentication-provider>
                    <jdbc-user-service data-source-ref="dataSource"
                    users-by-username-query="select cuenta,pwd,activo from Usuarios where cuenta = ? "
                    authorities-by-username-query="select cuenta,perfil from Perfiles where cuenta = ? " />
                 </authentication-provider>
            </authentication-manager>
            
video 178

    Encriptar contraseñas
    
        1.  Declarar un nuevo bean en el archivo security.xml
        
            <b:bean id="passwordEncoder" class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder" />
            
        2. agregar la linea password-encode dentro de <authentication-provider>, y el ID debe corresponder con la REF

            <password-encoder ref="passwordEncoder"/>   