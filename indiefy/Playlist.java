package indiefy;

import java.util.ArrayList;
import java.util.Scanner;

public class Playlist implements Tocavel{

	private String titulo;
	private ArrayList<Musica> musicas;
	
	
	// contrucao bem parecida com a de musica
	
    public Playlist(String titulo){
        this.titulo = titulo;
        this.musicas = new ArrayList<Musica>();
    }



    
    
    public void tocar(Scanner sc){
    	
        for(int i = 0;i < this.musicas.size(); i++ ) {
        	if(i == 1) {
        		System.out.println("Tocando playlist: "+this.titulo);
        	}
        	System.out.println("musica"+(i+1)+": ");
        	musicas.get(i).tocar(sc);
        	
        	String escolha = ""; 
        	
        	
        	if(i+1 != this.musicas.size()) {
        	System.out.println("O que quer fazer?[0] parar \n[qualquer coisa] tocar a proxima musica: " + this.musicas.get(i+1).getTitulo());
        	
        		escolha = sc.next();
        		if(escolha.equals("0")) {
        			break;
        		}
        	}
        }
    }

    public void tocar(ArrayList<Musica> musicas_artista, Scanner sc) {
    	for(int i = 0;i< this.musicas.size(); i++ ) {
    		if(i == 1) {
        		System.out.println("Tocando playlist: "+this.titulo);
        	}
        	System.out.println("musica"+(i+1)+": ");
    		
        	musicas.get(i).tocar(musicas_artista,sc);
        	
        	
        	
        	
        }
    }
    
    

	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public ArrayList<Musica> getMusicas() {
		return musicas;
	}


	public void setMusicas(ArrayList<Musica> musicas) {
		this.musicas = musicas;
	}

    

}