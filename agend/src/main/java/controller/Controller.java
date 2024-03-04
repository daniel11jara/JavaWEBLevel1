package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;


@WebServlet(urlPatterns = {"/Controller", "/main", "/insert"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	DAO dao = new DAO();
    JavaBeans contato = new JavaBeans();   
   
    public Controller() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//dao.testeConexao();
		
		//variavel action vai armazenar o caminho da requisicao
		String action = request.getServletPath();
		
		if (action.equals("/main")) { //o main vem do botao acessar do index.html (página 1)
			contatos(request, response);//quando clicado vai para o agenda.jsp (página 2)
		}else if(action.equals("/insert")) {
			novoContato(request, response);//se o conteudo da variável action for insert vai redirecionar ao método responsável por encaminhar essa requisição a camada model (página 3)
		}else {
			response.sendRedirect("index.html");//se não for neunhum dos dois volta para página inicial
		}
	}
	
	//listar contatos 
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("agenda.jsp");
	}
	
	//novo contato - método que vai pegar as informações do formulário 
		protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			//setando na classe javabeans onde estão os atributos
			contato.setNome(request.getParameter("nome"));
			contato.setFone(request.getParameter("fone"));
			contato.setEmail(request.getParameter("email"));
			
			//chamando o método inserirContato
			dao.inserirContato(contato);
			
			//redirecionar para agenda.jsp
			response.sendRedirect("main");
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
