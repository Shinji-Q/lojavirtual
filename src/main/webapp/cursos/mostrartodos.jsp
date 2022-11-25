<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ page import="com.netbiis.lojavirtual.api.LojaVirtualDB" %>
<%@ page import="com.netbiis.lojavirtual.model.*" %>
<%@ page import="java.util.List" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script type="../text/javascript" src="lib/js/jquery.min.js"></script>
	    <script type="../text/javascript" src="lib/js/bootstrap.min.js"></script>
	    <link href="../lib/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href="../lib/css/bootstrap.css" rel="stylesheet" type="text/css">
	    <link href="../lib/css/padrao.css" rel="stylesheet" type="text/css">
		<title>Listar todos os Cursos</title>
	</head>
	<body>
		<div class="section">
	      <div class="container">
	        <div class="row">
	          <div class="col-md-12 text-center">
	            <div class="col-md-12  btn-group btn-group-lg btn-group-vertical">
					<h3>Cursos</h3>
					
					<table class="table">
						<tr>
							<th scope="col">id</th>
							<th scope="col">titulo</th>
							<th scope="col">descrição</th>
							<th scope="col">valor</th>
						</tr>
						<%
							List<Curso> cursos = LojaVirtualDB.consultarTodosCursos();
							for(Curso c: cursos) {
								out.println("<tr>");
								out.println("<th scope=\"row\">" + c.getCursoId() + "</td>");
								out.println("<td>" + c.getTitulo() + "</td>");
								out.println("<td>" + c.getDescricao() + "</td>");
								out.println("<td>R$ %.2f</td>".formatted(c.getValor().doubleValue()));
								out.println("</tr>");
							}
						%>
					</table>
	            </div>
	          </div>
	        </div>
	      </div>
	    </div>
		<div class="section">
	      <div class="container">
	        <div class="row">
	          <div class="col-md-12 text-center corrigir">
	            <a class="btn btn-default" href="javascript:window.history.go(-1)">Voltar</a>
	          </div>
	        </div>
	      </div>
	    </div>
	
	</body>
</html>