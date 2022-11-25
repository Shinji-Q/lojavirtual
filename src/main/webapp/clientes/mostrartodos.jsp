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
		<title>Listar todos os Clientes</title>
	</head>
	<body>
		<h3>Clientes</h3>
		
		<table class="table">
			<tr>
				<th scope="col">nome</th>
				<th scope="col">email</th>
				<th scope="col">cpf</th>
			</tr>
			<%
				List<Cliente> clientes = LojaVirtualDB.consultarTodosClientes();
				for(Cliente c: clientes) {
					out.println("<tr>");
					out.println("<td>" + c.getNome() + "</td>");
					out.println("<td>" + c.getEmail() + "</td>");
					out.println("<td>" + c.getCpf() + "</td>");
					out.println("</tr>");
				}
			%>
		</table>
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