package indiefy;
import java.util.Scanner;
import java.util.ArrayList; // criar/simular o armazenamento dos dados de ouvintes, artistas (provÃ¡vel de ser igual a de ouvintes) e musicas
/*
 * Turma - 2
 * Lila Maria Salvador Frazão  M-510809
 * Paulo Victor Alves Fabrício M-511013
 */

// aqui está o sistema em si onde começa a main!

public class Indiefy{
	
    // instancia o armazenamento do sistema
	static Armazenamento db = new Armazenamento();
    
	
	// funcao login retorna um dos 3 possiveis usuarios do sistema, podendo tbm retornar null caso não ache o usuario
    static Usuario login(Scanner lapiseira, ArrayList<Usuario> listaUsers){
        String usernameAlvo = "";
        String senhaAlvo = "";
        String username = "";
        String password = "";
        
        do{
            System.out.print("Digite seu nome de usuario: ");
            username = lapiseira.nextLine();
        } while(username.isEmpty());

        do{
            System.out.print("Digite sua senha: ");
            password = lapiseira.nextLine();
        } while(password.isEmpty());

        // for pra procurar usuario na lista de usuarios
        for (int i = 0; i < listaUsers.size(); i++){
            Usuario temp = listaUsers.get(i);
            usernameAlvo = temp.getUsername();
            senhaAlvo = temp.getPassword();
            
            
            if(senhaAlvo.equals(password) && usernameAlvo.equals(username)){
                System.out.println("Login realizado com sucesso.");
                return temp;  // retornou o usuario correto
            }
            
        }
        System.out.println("Usuario nao encontrado.");
        return null;
    }

    
    // a função cadastro retorna um usuario, porem ele retorna apenas Ouvinte ou a subclasse dela Artista
    static Ouvinte cadastro(Scanner lapiseira, int id){
        String email = "";
        String username = "";
        String password = "";
        String confirmarSenha = "";
        Ouvinte novoUsuario = null;
        do{
            System.out.print("Digite seu e-mail: ");
            email = lapiseira.nextLine();
        } while(email.isEmpty());
        
        do{
            System.out.print("Digite seu nome de usuario: ");
            username = lapiseira.nextLine();
        } while(username.isEmpty());
        
        do{
            System.out.print("Digite sua senha: ");
            password = lapiseira.nextLine();
        } while(password.isEmpty());

        do{
            if(confirmarSenha.isEmpty()){
                System.out.print("Confirme sua senha: ");
            }
            else{
                System.out.println(password);
                System.out.print("Erro ao confirmar a senha, tente novamente: ");
            }
            confirmarSenha = lapiseira.nextLine();
        } while(!(confirmarSenha.equals(password)));

        String tipo = "";
        do {
        	System.out.println("Qual o tipo de usuario?:\n"
        			+"[1] Ouvinte comum do sistema\n"
        			+"[2] Artista participante do sistema");
        	
        	tipo = lapiseira.next();
        }while(tipo.isEmpty());
        
        int idUser = db.getIdUser();
        
        
        // dependendo da escolha vai ser adcionado um dos dois tipos de usuarios que podem ser criados pelo usuario inicial
        if(tipo.equals("1")) {
        	 db.setIdUser(idUser+1);
        	 novoUsuario = new Ouvinte(id++, email, username, password);
        }
        else if(tipo.equals("2")){
        	 db.setIdUser(idUser + 1);
        	 novoUsuario = new Artista(id++, email, username, password);
        }
        return novoUsuario;
    }

    
    public static void main(String[] args ){

    	
    	
    	
    	
    	
        Scanner pencil = new Scanner(System.in);  // sempre a mesma caneta
        int escolha = -1;      
        boolean roda = true;
        
        
        
        ArrayList<Usuario> usuarios = db.getUsuarios_cadastrados();
        int idUsers = db.getIdUser();
        
        // o usuario adm é sempre o mesmo e é instanciado pelo prorio sistema uma unica vez    
        Administrador adm = new Administrador();
        usuarios.add(adm);
        
        
        
        while(roda){
            //clearScreen();

            System.out.println("*-----------------------------*\nBem Vindo ao Indiefy \nQual opcao quer realizar?\n\n" 
            + "[1] Logar\n"
            + "[2] Cadastrar\n"
            + "[0] Sair\n");
            
            // usar um try aqui para casos de entrada de nao inteiros
            try{
                
                escolha = pencil.nextInt();

            }catch(Exception erro ){

                System.out.println("apenas numeros");
                escolha = -1;
                
            }
            pencil.nextLine();

            switch(escolha){
                case 0:
                    roda = false;
                    pencil.close(); // unica vez que o scanner é fechado
                    break;   
                             
                case 1:
                	 
                    Usuario usuario = login(pencil, usuarios);
                    
                    try {
                    	usuario.menu(pencil, db);   // caso o usuario volte nulo algo aconteceu ( não encontrou ou cancelou em algum dado)
                    	
                    } catch(Exception erro){
                    	System.out.println("Houve um problema no login, tente novamente.\n");
                    };
                    
                    break;
                case 2:
                    Usuario novoUsuario = cadastro(pencil, idUsers);
                    
                    //  como o erro já foi reportado antes, aqui não há necessidade de reportalo novamente
                    if(novoUsuario != null ) {
                    	usuarios.add(novoUsuario);
                    	System.out.println("Cadastro realizado com sucesso!\n");
                    	
                    }
                    else {
                    	System.out.println("Cadastro não pode ser realizado com sucesso!\n");
                    }
                    pencil.nextLine();
                    break;

                default:
                    break;
            }
        }
    }
}