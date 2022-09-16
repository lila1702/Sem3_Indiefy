package indiefy;

//aqui ficara o administrador do sistema que podera ver todos os dados do sistema  e tbm imprimir em txt

import java.io.FileWriter;   // classe que escreve
import java.io.IOException;  // Classe que apressenta os erros/ excessoes de arquivo IOException
import java.io.File;         // classe que cria arquivo
import java.util.Scanner;   // Scanner ja existente
import java.util.ArrayList; // Classe para as listas 



public class Administrador extends Usuario{
	
	// so existe um administrador no sistema logo construtor é unico e é adicionado na toda vez que o programa roda;
	
    public Administrador(){
          super("Adm101", "c2=b2+a2","Pitts@gmail.com", 0);
    }

	public void menu(Scanner sc, Armazenamento db) {
		
		 ArrayList<Musica> musicas = db.getMusicas_cadastradas(); // armazenamento dos dados do sistea
		 ArrayList<Usuario> users = db.getUsuarios_cadastrados();
		
		 
		 boolean parou = false;
		   	
	 	while(!parou) {
			System.out.println("Bem Vindo Administrador \nQual funcaoo quer realizar?\n" 
		            + "[1] Ver lista de dados:\n"
		            + "[2] Salvar arquivo com os dados\n"
		            + "[0] Sair\n");
					
			
			String escolha = sc.next();
			
			switch(escolha) {
			case "0":
				return;
				
			case "1":
				
				this.ver_lista(musicas, users);
				break;
			case "2":
				this.imprimir(sc,musicas,users);
				break;
			default:
				System.out.println("opcao invalida");
				
			}
	 	}
	}	
	      
	void ver_lista(ArrayList<Musica> musicas, ArrayList<Usuario> users ) {
		
		System.out.println("Total de Usuarios: "+users.size() + "\n lista de Usuarios: ");
		
		for(int i = 0; i < users.size();i++) {
			Usuario atual = users.get(i);
			System.out.println("|"+atual.getId()+"|     |"+atual.getUsername()+"|     |"+atual.getEmail());
		}
		
		System.out.println("Total de Musicas: "+musicas.size() + "\n lista de Musicas: ");
		for(int i = 0; i < musicas.size();i++) {
			Musica atual = musicas.get(i);
			System.out.println("|"+atual.getId()+"|     |"+atual.getGenero()+"|     |"+atual.getTitulo()+"|     |"+atual.getArtista().getUsername()+"|     |"+atual.getDuracao());
		}
	}
	
	void imprimir(Scanner sc , ArrayList<Musica> musicas, ArrayList<Usuario> users ) {
	
		
		String nome_do_arquivo = "";
		
		System.out.println("qual o nome do arquivo?" );
		nome_do_arquivo = sc.next();
		
		
	  try {
	    File arq = new File(nome_do_arquivo+".txt");
	    if (arq.createNewFile()) {
	      System.out.println(" Arquivo criado: Novo arquivo: " + arq.getName());
	    } else {
	      System.out.println("Arquivo ja existe");
	    }
	 
	  } catch (IOException e) {
	    System.out.println("Um erro ocorreu durante a criacao do arquivo.");
	    e.printStackTrace();
	  }
	
	
	
	
	  try {
		    
	    FileWriter myWriter = new FileWriter(nome_do_arquivo + ".txt");
	    
	    myWriter.write("Total de Usuarios: "+users.size() +"\r\n"+ "lista de Usuarios: \r\n");
		
		for(int i = 0; i < users.size();i++) {
			Usuario atual = users.get(i);
			myWriter.write("|"+atual.getId()+"|\t|"+atual.getUsername()+"|\t |"+atual.getEmail()+"|\r\n");
		}
	    
	    
		myWriter.write("Total de Musicas: "+musicas.size() +"\r\n" + "lista de Musicas: ");
		for(int i = 0; i < musicas.size();i++) {
			Musica atual = musicas.get(i);
			myWriter.write("|"+atual.getId()+"|\t|"+atual.getGenero()+"|\t|"+atual.getTitulo()+"|\t|"+atual.getArtista()+"|\t|"+atual.getDuracao()+"|\r\n");
		}
		
	
	    myWriter.close();
	
	    System.out.println("Escrita feita com sucesso.");
	
	  } catch (IOException e) {
	    System.out.println("Um erro ocorreu durante a escrita dos dados");
	    e.printStackTrace();
	  }
	}

}