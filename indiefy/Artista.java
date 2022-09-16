package indiefy;
import java.util.Scanner;
import java.util.ArrayList;

public class Artista extends Ouvinte{
    
    private ArrayList<Musica> musicas;

    public Artista(int id, String email, String username, String password){
        super(id, email, username, password);
        this.musicas = new ArrayList<Musica>();
    }

    
    //como não dá pra acessar facilmente os metodos a partir de usuário, aqui temos um menu proprio
    public void menu(Scanner sc, Armazenamento db){
    	ArrayList<Musica> musicas = db.getMusicas_cadastradas();
    	boolean parou = false;
   	
    	while(!parou) {
        	System.out.println("\n----------------------------------------"
	    			+ "\nBem Vindo "+this.getUsername()+ "\n\nQual funcao quer realizar?\n\n" 
	               + "[1] Ouvir musica/playlist\n"
	               + "[2] Criar Playlist\n"
	               + "[3] Editar Playlist\n"
	               + "[4] Publicar musica\n"
	               + "[5] Editar alguma musica\n"
	               
	               + "[0] Sair\n");
	   			
	   	
	    	String escolha = sc.next();
	   	
	    	switch(escolha) {
	    	case "0":
	    		return;
	    	case "1":
	    		if(musicas.size() != 0) this.ouvir(musicas, sc);
	    		else System.out.println("Nao ha musicas no sistema");
	    		break;
	    	case "2":
	    		this.criarPlaylist(musicas,sc);
	    		break;
	    		
	    	case "3":
	    		this.editarMusica(sc, musicas);
	    		break;
	    		
	    	case "4":
	    		boolean criou = this.publicarMusica(sc, db.getIdMusica(), this, db.getMusicas_cadastradas());
	    		
	    		if(criou) {
	    			db.setMusicas_cadastradas(musicas);;
	    			db.setIdMusica(db.getIdMusica() + 1);
	    		}
	    		break;
	    		
	    	case "5":
	    		editarMusica(sc, db.getMusicas_cadastradas());
	    		break;
	    		
	    	default:
	    		System.out.println("opcao invalida");
	    	}
    	}
    }
    
    public void ouvir(ArrayList<Musica> musicas, Scanner sc){ // sobreescrito pelo artista para poder acesar uma sobreecarga de musica
    	Tocavel controle = null;
    	String escolha = ""; 
    	do {
	    	System.out.println("\nDeseja ouvir uma playlist ou uma musica somente? \n [1] playlist do usuario \n [2] Procurar musica pelo titulo \n [3] Voltar");
	    	escolha = sc.next();
	    	
	    	try {
		    	switch(escolha){
		    	case "1":
		    		if(this.playlists.size()>0) {
			    		System.out.println("Qual playlist sua, voce quer ouvir? ");
			    		for(int i = 0; i < this.playlists.size(); i++) {
			    			Playlist temp = this.playlists.get(i);
			    			System.out.println("playlist "+(i+1)+" : " + temp.getTitulo());
			    			
			    			
			    			for(int a = 0; a < temp.getMusicas().size(); a ++){ 
			    				Musica atual = temp.getMusicas().get(a);
			                	System.out.println( (a+1) + " - " + atual.getTitulo() +"  duracao: "+( atual.getDuracao() / 60) +":"+(atual.getDuracao() % 60) +"  Artista: "+atual.getArtista().getUsername());
			    			}
			    		}
		    			
			    		
			    		int esc = sc.nextInt();
			            controle = this.playlists.get(esc - 1);
		    		}
		    		else {
		    			System.out.println("nao ha playlists cadastradas");
		    		}
			    		break;
			    	case "2":
			    		sc.nextLine();
			    		controle = this.pesquisar_musica( musicas, sc);
			    		break;
			    	case "3":
			    		return;
			    	default:
			    		System.out.println("opcao invalida");
	    	}
	    	
	    	}catch(Exception e) {
	    		
	    		System.out.println("Algo deu errado, tente entrar com um inteiro dentro do limite quando for escolher musica");
		    	}
	    	
    	}while(controle == null);
    	
    	controle.tocar(this.musicas,sc);
    
        
        
        
    }
    
    // Recebe a caneta, o id global, e o Artista que está logado
    public boolean publicarMusica(Scanner pencil, int id, Artista artista, ArrayList<Musica> msc_armazenadas){
        String tituloMsc = "";
        int duracaoMsc = -1;
        String generoMsc = "";

        pencil.nextLine();// colocar pra resolver os problemas de entrada de inteiros
        do{
            System.out.print("Digite o titulo da musica: ");
            tituloMsc = pencil.nextLine();
        } while(tituloMsc.isEmpty());
        
        do{
	        try {
	        		System.out.print("Digite a duracao da musica: ");
	        		duracaoMsc = pencil.nextInt();
	        
	        }catch ( Exception e) {
	        	System.out.print("Erro na entrada da duracao da musica \n");
	        	duracaoMsc = -1;
	        	pencil.nextLine();
	        }
        } while(duracaoMsc < 0);
        
        pencil.nextLine(); // Limpa o buffer
        do{
            System.out.print("Digite o genero da musica: ");
            generoMsc = pencil.nextLine();
        } while(generoMsc.isEmpty());


        // tentar usar this ao invez de falar o objeto real.
        Musica musica = new Musica(id, tituloMsc, artista, duracaoMsc, generoMsc);
        
        musicas.add(musica);
        msc_armazenadas.add(musica);
        
        System.out.println("Nova musica adicionada.");
        return true;
    }
    
    public void editarMusica(Scanner pencil, ArrayList<Musica> msc_armazenadas) {
    	Musica mscEscolhida = null;
    	int escolhaMsc = -1;
    	int oqMudar = -1;
    	
    	if(!(this.musicas.isEmpty())) {
    		
    		for(int i = 0; i < this.musicas.size(); i++){
        		String tituloMsc = this.musicas.get(i).getTitulo();
        		int duracaoMsc = this.musicas.get(i).getDuracao();
        		System.out.println("["+i+"] " + tituloMsc + " - " + duracaoMsc);
        	}
        	System.out.print("Digite o numero de qual musica deseja editar: ");
        	escolhaMsc = pencil.nextInt();
        	
        	try {
        		mscEscolhida = this.musicas.get(escolhaMsc);
        	} catch (Exception error) {
        		System.out.println("Musica nao encontrada, tente novamente mais tarde.");
        	}
        	if(mscEscolhida != null) {
        		System.out.println(
            			"Titulo: "+ mscEscolhida.getTitulo()+
            			" - Duracao: "+mscEscolhida.getDuracao()+
            			" - Genero: "+mscEscolhida.getGenero());
            	System.out.println(
            			"[1] Editar Titulo\n"+
            			"[2] Editar Genero\n"+
            			"[3] Excluir Musica\n"+
            			"[4] Canelar"
            			);
            	System.out.print("Opcao: ");
            	oqMudar = pencil.nextInt();
            	pencil.nextLine(); //Limpa buffer
            	switch(oqMudar) {
            		case 1:
            			System.out.print("Digite o novo titulo: ");
            			mscEscolhida.setTitulo(pencil.nextLine());
            			break;
            		case 2:
            			System.out.print("Digite o novo genero: ");
            			mscEscolhida.setGenero(pencil.nextLine());
            			break;
            		case 3:
            			for(int i = 0; i < msc_armazenadas.size(); i++) {
            				if(mscEscolhida.equals(msc_armazenadas.get(i))) {
            					msc_armazenadas.remove(msc_armazenadas.get(i));
            					break;
            				}
            			}
            			this.musicas.remove(escolhaMsc);
            			System.out.println("Musica excluida com sucesso.");
            			break;
            		default:
            			if(oqMudar != 4) System.out.println("Opcao nao reconhecida,tente novamente, por favor.\n");
            			break;
            	}
        	}
    	}
    }

    
    
    
}


	