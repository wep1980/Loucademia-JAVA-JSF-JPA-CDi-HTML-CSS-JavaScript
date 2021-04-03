package br.com.wepdev.loucademia.interfaces.aluno.web;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.wepdev.loucademia.application.service.AlunoService;
import br.com.wepdev.loucademia.application.utils.ValidationException;
import br.com.wepdev.loucademia.domain.aluno.Aluno;

@Named
@SessionScoped // Garante que o Bean vai ficar ativo enquanto o navegador do usuario estiver aberto( UTILIZADO PARA MULTIPLAS REQUISI��ES NA MESMA JANELA DO NAVEGADOR )
public class PesquisaAlunoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	private AlunoService alunoService;
	
	@Inject
	private FacesContext facesContext;
	
	
	private String matricula;
	
	private String nome;
	
	private Integer rg;
	
	private Integer telefone;
	
	private List<Aluno> alunos; // Vareiavel que armazena a lista de alunos do metodo pesquisar()
	
	
	public String pesquisar() {
		try {
			alunos = alunoService.listAlunos(matricula, nome, rg, telefone);
			
		} catch (ValidationException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null; // Depois de excluir retorna para a mesma pagina
	}
	
	
	public String excluir(String matricula) {
		alunoService.delete(matricula);
		return pesquisar(); // Depois de excluir e feita um novo pesquisar() para que a tela fique atualizada e o dado excluido continue n�o aparecendo
	}
	

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

}
