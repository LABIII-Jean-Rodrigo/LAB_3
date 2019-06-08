package hello;


import java.util.List;
import java.util.LinkedList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class Model {
	
	ObjectContainer usuariosbd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/usuarios.db4o");
	ObjectContainer conteudosbd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/conteudos.db4o");
	ObjectContainer caracteristicasbd = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bd/caracteristicas.db4o");

		
	
	public boolean cadastroUsuario(Usuario usuario){
		if(isUsuarioDisponivel(usuario.getEmail())) {
			usuariosbd.store(usuario);
			usuariosbd.commit();
			return true;
		}
		return false;
	}
	
	public void cadastrarUsuario(String username, String email, String senha, String descricao, String genero) {

		cadastroUsuario(new Usuario (username, email, senha, descricao, genero));
	}
	
	public boolean isUsuarioDisponivel(String email){
		Query query = usuariosbd.query();
		query.constrain(Usuario.class);
		ObjectSet<Usuario> Usuariosbd = query.execute();
		
;		for(Usuario usuario:Usuariosbd){
			if(usuario.getEmail().equals(email)) {
				return false; 
			}
		}
		return true;
	}
	
	public List<Usuario> loginUser(String email, String senha){
		List<Usuario> result = new LinkedList<Usuario>();
		
		Query query =  usuariosbd.query();
		query.constrain(Usuario.class);
		
		ObjectSet<Usuario> allUsuariosbd = query.execute();
		
		for(Usuario usuario:allUsuariosbd) {
			if(usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) result.add(usuario);
			
		}
		
		 return result;
	}
	
	
	public boolean cadastroConteudo(Conteudo conteudo){
		if(isConteudoDisponivel(conteudo.getTipo(), conteudo.getCaract().getNome(), conteudo.getCaract().getAno())) {
			conteudosbd.store(conteudo);
			conteudosbd.commit();
			return true;
		}
		return false;
	}
	
	public void cadastrarConteudo(String tipo, String nome, String nota, String ano) {

		cadastroConteudo(new Conteudo (tipo, new Caracteristicas(nome, nota, ano)));
	}
	
	
	public boolean isConteudoDisponivel(String tipo, String nome, String ano){
		Query query = conteudosbd.query();
		query.constrain(Conteudo.class);
		ObjectSet<Conteudo> Conteudosbd = query.execute();
		
;		for(Conteudo conteudo:Conteudosbd){
			if(conteudo.getTipo().equals(tipo) && conteudo.getCaract().getNome().equals(nome) && conteudo.getCaract().getAno().equals(ano)) {
				return false; 
			}
		}
		return true;
	}
	
	
	public List<Conteudo> buscarConteudo(String tipo, String nome){
		List<Conteudo> result = new LinkedList<Conteudo>();
		
		Query query =  conteudosbd.query();
		query.constrain(Conteudo.class);
		
		ObjectSet<Conteudo> allConteudosbd = query.execute();
		
		for(Conteudo conteudo:allConteudosbd) {
			if(conteudo.getTipo().equals(tipo) && conteudo.getCaract().getNome().equals(nome)) result.add(conteudo);
			
		}
		
		 return result;
	}
	
	
	public List<Conteudo> buscarConteudoPorTipo(String tipo){
		List<Conteudo> result = new LinkedList<Conteudo>();
		
		Query query =  conteudosbd.query();
		query.constrain(Conteudo.class);
		
		ObjectSet<Conteudo> allConteudosbd = query.execute();
		
		for(Conteudo conteudo:allConteudosbd) {
			if(conteudo.getTipo().equals(tipo)) result.add(conteudo);
			
		}
		
		 return result;
	}
	
	
	public List<Conteudo> buscarConteudoPorAno(String tipo, String ano){
		List<Conteudo> result = new LinkedList<Conteudo>();
		
		Query query =  conteudosbd.query();
		query.constrain(Conteudo.class);
		
		ObjectSet<Conteudo> allConteudosbd = query.execute();
		
		for(Conteudo conteudo:allConteudosbd) {
			if(conteudo.getTipo().equals(tipo) && conteudo.getCaract().getAno().equals(ano)) result.add(conteudo);
			
		}
		
		 return result;
	}
	

}
