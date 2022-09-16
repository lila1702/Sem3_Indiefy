package indiefy;


import java.util.Scanner;
import java.util.ArrayList;

public class Ouvinte extends Usuario{
      

      protected ArrayList<Playlist> playlists;


      public Ouvinte(int id, String email, String username, String password) {
    	  super(username,password,email,id);
    	  this.playlists = new ArrayList<Playlist>();
    	  
      }

   

   
//-------------------------------------

    // aqui o ouvinte ira executar a musica que pesquisou
    
      
      
    
    public void menu(Scanner sc, Armazenamento db) {
    	ArrayList<Musica> musicas = db.getMusicas_cadastradas();
    	
    	boolean parou = false;
    	while(!parou) {
	    	
        	System.out.println("\n----------------------------------------"

	    			+ "\nBem Vindo "+this.getUsername()+ "\nQual funcao quer realizar?\n" 
	               + "[1] Ouvir musica/playlist\n"
	               + "[2] Criar Playlist\n"
	               + "[3] Editar Playlist\n"
	               + "[0] Sair\n\n");
	   			
	   	
	    	String escolha = sc.next();
	   	
	    	switch(escolha) {
		    	case "0":
		    		return;
		    	case "1":
		    		if(musicas.size() != 0) this.ouvir(musicas, sc);
		    		else System.out.println("Nao ha musicas no sistema");
		    		break;
		    	case "2":
		    		if(musicas.size() != 0) this.criarPlaylist(musicas,sc);
		    		else System.out.println("Nao ha musicas no sistema");
		    		break;
		    	case "3":
					if(playlists.size() != 0) this.criarPlaylist(musicas,sc);
					else System.out.println("Nao ha musicas no sistema");
				
		    		break;
		    		    		
		    	default:
		    		System.out.println("opcao invalida");
	    	}
	    	

    	}
    	
    	
    }
    
    // ouvir do ouvinte é diferente do ouvir do Artista, um invoca uma sobrecarga do metodo tocar da musica
    
    public void ouvir(ArrayList<Musica> musicas, Scanner sc){
    	
    	Tocavel controle = null;
    	String escolha = ""; 
    	do {
    		System.out.println("Deseja ouvir uma playlist ou uma musica somente? \n [1] playlist do usuario \n [2] Procurar musica pelo titulo \n [3] Voltar");
	    	escolha = sc.next();
	    	
	    	try {
		    	switch(escolha){
		    	case "1":
		    		if(this.playlists.size()>0) {
		        	System.out.println("----------------------------------------"
			    				+ "Qual playlist sua, voce quer ouvir? ");
			    		for(int i = 0; i < this.playlists.size(); i++) {
			    			Playlist temp = this.playlists.get(i);
			    			System.out.println("playlist "+(i+1)+" : " + temp.getTitulo() + "\n");
			    			
			    			
			    			for(int a = 0; a < temp.getMusicas().size(); a ++){ 
			    				Musica atual = temp.getMusicas().get(a);
			                	System.out.println( (a+1) + " - " + atual.getTitulo() +"  duracao: "+( atual.getDuracao() / 60) +":"+(atual.getDuracao() % 60) +"  Artista: "+atual.getArtista().getUsername());
			    			}
			    		}
			    		
			    		int esc = sc.nextInt();
			            controle = this.playlists.get(esc - 1);
		    		}
		    		else {
		    			System.out.println("nao ha plalistas criadas");
		    		}
			    		break;
			    	case "2":
			    		sc.nextLine();
			    		controle = this.pesquisar_musica( musicas, sc);
			    		break;
			    	case "3":
			    		return;
			    	default:
			    		System.out.println("\nopcao invalida");
	    	}
	    	
	    	}catch(Exception e) {
	    		System.out.println("\nAlgo deu errado, tente entrar com um inteiro dentro do limite quando for escolher musica");
		    	}
	    	
    	}while(controle == null);
    	  	
    	controle.tocar(sc);
    
    }
      
    public Musica pesquisar_musica(ArrayList<Musica> musicas, Scanner sc){
    	
    	Musica musica = null;
    	int escolha = -1; 
    	
    	
    	ArrayList<Musica> temp = new ArrayList<Musica>();
    	System.out.println(musicas.size());
    	
        if (musicas.size() != 0){
            String nome = "";
            System.out.println("Qual o titulo da musica? ");
            nome = sc.nextLine();
            
            System.out.println("Musicas com o titulo: " + nome);
            for(int i = 0; i < musicas.size(); i ++){

                if(musicas.get(i).getTitulo().equals(nome)){
                	temp.add(musicas.get(i));
                }
            }
                
            for(int i = 0; i < temp.size(); i ++){    
                	System.out.println(i+1 + " - " + temp.get(i).getTitulo() +"  duracao: "+( temp.get(i).getDuracao() / 60) +":"+(temp.get(i).getDuracao() % 60) +"  Artista: "+temp.get(i).getArtista().getUsername());
            }
             
            System.out.println("Qual musica quer escolher(pelo numero)?");
            if(sc.hasNextInt()) {
            	escolha = sc.nextInt() - 1;
            }
            
            musica = temp.get(escolha);
            
               
               
            }
        else {
        	System.out.println("Nao ha musicas no sistema.");
        }
            
        
        return musica;
    }

    public void criarPlaylist(ArrayList<Musica> musicas, Scanner sc){

        

        String tituloPl = "";
        String escolha = "";
        ArrayList<Musica> temp = new ArrayList<Musica>();

        boolean parou = false;

        sc.nextLine();
        System.out.println("Qual o nome da Playlist? ");
        tituloPl = sc.nextLine();
        
        Playlist nova_playlist = new Playlist(tituloPl);
        
        do{
        	System.out.println("Que musicas quer adicionar a Playlist? ");
        
        
        	Musica musica = pesquisar_musica(musicas, sc);
        
        	try {
	        	temp.add(musica);
	
	        	nova_playlist.setMusicas(temp);
	        	
	        	
	        	sc.nextLine(); // pegar o /n que sobrou
	        	System.out.println("Quer adcionar mais musicas a playlist?\n[S]im / [N]ao ");
	        	escolha = sc.nextLine();
	        	
	        	if(escolha.toUpperCase().equals("S")) {
	        		continue;
	        	}
	        	else {
	        		parou = true;
	        	}
        	}
        	catch(Exception e) {
        		System.out.println("Ouve um erro ao adcionar musica a playlist ");
        	}
        	
        }while(!parou && (nova_playlist.getMusicas().size() != 0));

            

    
        playlists.add(nova_playlist);
    }

    
    public void editar_playlist(Scanner pencil, ArrayList<Musica> msc_armazenadas) {
    	Playlist plEscolhida = null;
    	int escolhaPl = -1;
    	int oqMudar = -1;
    	
    	if(!(this.playlists.isEmpty())) {
    		
    		for(int i = 0; i < this.playlists.size(); i++){
        		String titulopl = this.playlists.get(i).getTitulo();
        		
        		ArrayList<Musica> temp = this.playlists.get(i).getMusicas();
        		
        		System.out.println(titulopl);
        		
        		for(int a = 0; a < temp.size(); a++) {
        			System.out.println("["+a+"] " + temp.get(a).getTitulo() + " - " + temp.get(a).getArtista().getUsername()+ " - " + temp.get(a).getDuracao());
        		}
        	}
        	System.out.print("Digite o numero de qual playlist deseja editar: ");
        	escolhaPl = pencil.nextInt();
        	
        	try {
        		plEscolhida = this.playlists.get(escolhaPl);
        	} catch (Exception error) {
        		System.out.println("Playlist nao encontrada, tente novamente mais tarde.");
        	}
        	if(plEscolhida != null) {
        		System.out.println(
            			"Titulo: "+ plEscolhida.getTitulo());
        		for(int a = 0; a< plEscolhida.getMusicas().size(); a++) {
        			Musica atual = plEscolhida.getMusicas().get(a);
        			System.out.println("["+a+"] " + atual.getTitulo() + " - " + atual.getArtista().getUsername()+ " - " + atual.getDuracao());
        		}
            			
            	System.out.println(
            			"[1] Editar Titulo\n"+
            			"[2] Adcionar Musica\n"+
            			"[3] Excluir Musica\n"+
            			"[4] Excluir Playlist\n"+
            			"[5] Cancelar"
            			);
            	oqMudar = pencil.nextInt();
            	pencil.nextLine(); //Limpa buffer
            	switch(oqMudar) {
            		case 1:
            			plEscolhida.setTitulo(pencil.nextLine());
            			break;
            		case 2:
            			Musica msc_nova = pesquisar_musica(msc_armazenadas, pencil);
            			
            			if(msc_nova != null)
            				plEscolhida.getMusicas().add(msc_nova);
            			
            			
            			break;
            		case 3:
            			
            			Musica msc_escolhida = null;
            			
            			
            			System.out.println("Qual musica quer Excluir ( pelos numeros mostrados anteriormente)");
            			int escolha = pencil.nextInt();
            			pencil.nextLine();
            			
            			
            			ArrayList<Musica> plmusicas = plEscolhida.getMusicas();
            			msc_escolhida = plmusicas.get(escolha);
            			
            			for(int i = 0; i < plmusicas.size(); i++) {
            				if(msc_escolhida.equals(plmusicas.get(i))) {
            					plmusicas.remove(plmusicas.get(i));
            					break;
            				}
            			}
            			
            			break;
            			
            		case 4:	
            			this.playlists.remove(escolhaPl);
            			break;
            		default:
            			System.out.println("Opcao nao reconhecida,tente novamente, por favor.\n");
            			break;
            	}
        	}
    	}
    	
    	
    	
    	
    	
    	
    	
    }


    }