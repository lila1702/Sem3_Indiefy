package indiefy;

import java.util.ArrayList;
import java.util.Scanner;

// classe que possui onde foi implementada a sobrecarga do metodo tocar, podendo ser entre Ouvinte ou Artista(podendo adcionar o metodo tocar do ouvinte a pilha através da identificacao de Id unico);

public class Musica implements Tocavel{
	
    private int id;
    private String titulo;
    private Artista artista; 
    private String genero;
    private int duracao;
    private int vezesReproduzida = 0;
    
    
    
    public Musica(int id, String titulo, Artista artista, int duracao, String genero){
            this.id = id;
            this.titulo = titulo;
            this.artista = artista;
            this.duracao = duracao;
            this.genero = genero;
        }

    public void tocar(Scanner sc){
    	this.vezesReproduzida++;
    	System.out.println("----------------------------------------"
    					+ "\ntocando: "+this.getArtista().getUsername()+"- "+this.getTitulo());
    	System.out.println("Aperte enter para parar");
    	sc.nextLine();
    	
    	
    	
    	
        }

    public void tocar( ArrayList<Musica> musicasArtista, Scanner sc ){
        boolean achar = false;
        for(int i = 0; i < musicasArtista.size(); i++ ){
            Musica temp = musicasArtista.get(i);

            if(this.id == temp.id){
                achar = true;
                this.vezesReproduzida++;
            	System.out.println("----------------------------------------"
                				+ "\nVezes reproduzida: "+this.vezesReproduzida);
                System.out.println("tocando: das suas musicas: "+"- "+this.getTitulo());
                System.out.println("\nAperte enter para parar");
            	sc.nextLine();
                break;
            }   
        }
        if(achar == false){
            this.tocar(sc);
        }
    }

    public void setGenero(String genero){
        this.genero = genero;
    }
    
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public int getId(){
        return this.id;
    }

    public int getDuracao(){
        return this.duracao;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public String getGenero(){
        return this.genero;
    }

    public Artista getArtista(){
        return this.artista;
    }
}