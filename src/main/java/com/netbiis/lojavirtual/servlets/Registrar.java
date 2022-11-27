package com.netbiis.lojavirtual.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


import com.netbiis.lojavirtual.api.LojaVirtualDB;
import com.netbiis.lojavirtual.model.*;

/**
 * Servlet implementation class Registrar
 */

public class Registrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String servletPath = request.getServletPath();
		PrintWriter out = response.getWriter();
		Map<String, String[]> parametros = request.getParameterMap();

		String cpf, titulo;
		int cursoId;

		switch(servletPath) {

		case "/registrarcliente" :

			cpf =parametros.get("cpf")[0];
			out.println(LojaVirtualDB.consultarCliente(cpf));
			out.println("<br/>");

			break;
		case "/registrarcurso" :

			// usar o método "getAttribute" faz o server não poder iniciar por alguma razão
			//cursoId = (Integer) request.getAttribute("cursoId");
			titulo = parametros.get("titulo")[0];
			out.println(titulo);
			out.println(LojaVirtualDB.consultarCursoByTitulo(titulo));
			
			break;
		case "/registrarpagamento" :

			cursoId = Integer.parseInt(parametros.get("cursoId")[0]);
			cpf = parametros.get("cpf")[0];

			out.println(cpf + " " + cursoId);
			out.println("<br/>");
			out.println(LojaVirtualDB.consultarPagamento(new PagamentoId(cursoId, cpf)));

			break;

		case "/consultarcliente" :

			cpf = parametros.get("cpf")[0];
			Cliente cliente = LojaVirtualDB.consultarCliente(cpf);
			out.println(cliente);

			break;
		case "/consultarcurso" :

			cursoId = Integer.parseInt(parametros.get("cursoId")[0]);
			Curso curso = LojaVirtualDB.consultarCurso(cursoId);
			out.println(curso);
			out.println(parametros.get("cursoId")[0]);
			
			break;
		case "/consultarpagamento" :
			cursoId = Integer.parseInt(parametros.get("cursoId")[0]);
			cpf = parametros.get("cpf")[0];
			PagamentoId pid = new PagamentoId(cursoId, cpf);
			Pagamento pagamento = LojaVirtualDB.consultarPagamento(pid);
			out.println(pagamento);
			out.println(cursoId);
			out.println(cpf);
			break;
		default:
			System.out.println("badrequest");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//o tipo de registro a ser guardado será inferido 
		//pelo caminho utilizado para acessar este servlet

		Map<String, String[]> parametros = request.getParameterMap();
		String servletPath = request.getServletPath();
		parametros.forEach((key, value) -> {System.out.println(key + ": " + value[0]);});

		String nome, cpf, email, titulo, descricao, dataString;
		int cursoId, dia, mes, ano;
		Date data;
		
		System.out.println("inicio post");
		//TODO avaliar e tratar exceções
		switch(servletPath) {

		case "/registrarcliente" :

			nome = parametros.get("nome")[0];
			cpf = parametros.get("cpf")[0];
			email = parametros.get("email")[0];
			
			Cliente novoCliente = new Cliente(nome, email, cpf);
			
			try {
				LojaVirtualDB.criarCliente(novoCliente);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			System.out.println("cadastrar cliente");

			break;
		case "/registrarcurso" :
			
			titulo = parametros.get("titulo")[0];
			descricao = parametros.get("descricao")[0];
			// criando um novo big decimal a partir de um valor string
			// algumas vezes, o imput vem com "," ao invés de "." decimal.
			BigDecimal preco = new BigDecimal(parametros.get("preco")[0].replace(",", "."));
			
			Curso novoCurso = new Curso(titulo, descricao, preco);
			
			try { 
				novoCurso = LojaVirtualDB.criarCurso(novoCurso);
				//request.setAttribute("cursoId", novoCurso.getCursoId());
			} catch ( Exception e ) {
				System.out.println(e.getMessage());
			}

			break;
		case "/registrarpagamento" :
			cursoId = Integer.parseInt(parametros.get("cursoId")[0]);
			cpf = parametros.get("cpf")[0];
			
			Cliente cliente = LojaVirtualDB.consultarCliente(cpf);
			Curso curso = LojaVirtualDB.consultarCurso(cursoId);
			
			
			//processamento da data//
			//a data enviada pelo formulário está no formato "yyyy-MM-dd"
			dataString = parametros.get("data")[0];
			dia = Integer.parseInt(dataString.substring(8));
			mes = Integer.parseInt(dataString.substring(5,7));
			ano = Integer.parseInt(dataString.substring(0,4));
			
			//criando uma nova classe calendário para gerar a data da compra
			// método de criação com o construtor Date() está deprecated

			Calendar cal = Calendar.getInstance();

			//a classe calendário começa a contar os meses a partir de "0"

			cal.set(ano, mes - 1 , dia, 0, 0);
			data = cal.getTime();
			System.out.println(data);
			System.out.println();
			
			PagamentoId pid = new PagamentoId(cursoId, cpf);
			Pagamento novoPagamento = new Pagamento(pid, cliente, curso, data);

			LojaVirtualDB.criarPagamento(novoPagamento);
			break;

		default:
			System.out.println("badrequest");
		}

		
		
		doGet(request, response);
	}

}
