<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido a CineApp</title>
<spring:url value="/resources" var="urlPublic" />
<link rel="stylesheet" href="${urlPublic}/bootstrap/css/bootstrap.min.css">

</head>
<body>
	<div class="container card ">
		<div class="card-body">
			<h5 class="card-title">Lista de peliculas</h5>
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Titulo</th>
						<th>Duracion</th>
						<th>Clasificacion</th>
						<th>Genero</th>
						<th>imagen</th>
						<th>Estado</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ peliculas }" var="pelicula">
						<tr>
							<td>${ pelicula.id }</td>
							<td>${ pelicula.titulo }</td>
							<td>${ pelicula.duracion }</td>
							<td>${ pelicula.clasificacion }</td>
							<td>${ pelicula.genero }</td>
							<td><img src="${urlPublic}/images/${pelicula.imagen}"
								width="80" heigth="160" alt="" /></td>
							<td><c:choose>
									<c:when test="${pelicula.estatus == 'activa' }">
										<span class="btn btn-success">Activa</span>
									</c:when>
									<c:otherwise>
										<span class="btn btn-danger">Inactiva</span>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>