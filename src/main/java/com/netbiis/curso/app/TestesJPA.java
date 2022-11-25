package com.netbiis.curso.app;


import java.util.List;
import com.netbiis.lojavirtual.api.LojaVirtualDB;
import com.netbiis.lojavirtual.model.*;
public class TestesJPA {

	public static void main(String[] args) {
		
		List<Pagamento> pagamentos = LojaVirtualDB.consultarTodosPagamentos();
		
		pagamentos.forEach(System.out::println);
		System.out.println(pagamentos.size());
		System.out.println(pagamentos.get(0).getCliente().toString());


	}

}
