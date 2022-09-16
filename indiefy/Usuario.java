package indiefy;


import java.util.Scanner;


// classe abstrata que todos os usuarios do sistema herdam, primeira etapa do sistema, possuindo nele os metodos comuns entre todos
// o principal metodo eh menu, pois usuario nao pode acessar peculiaridades atraves apenas da chamada de um objeto Usuario, 
// porem se o metodo comum possuir a chamada de outros metodos que apenas a classe herdada de Usuario possui, ele vai executar esses metodos

public abstract class Usuario {
	
	  	private String username;
	    private String password;
	    private String email;
	    private int id; // id unico pra cada usuario facil de utilizar caso fosse excluir a conta
	    


		

		public Usuario(String username1, String password1, String email1,int id1){
	        this.username = username1;
	        this.password = password1;
	        this.email = email1;
	        this.id = id1;
	    }

	    
		// diferente pra cada tipo de Subclasse.
	     public abstract void menu(Scanner sc, Armazenamento db);
	     
	     
		 public String getEmail() {
			return email;
		 }


		 public void setEmail(String email) {
				this.email = email;
		 }

	     public  String getUsername() {
	    	 return this.username;
	     }
	     public  String getPassword() {
	    	 return this.password;
	     }
	     public void setUsername(String username){
	         this.username = username;
	     }

	     public void setPassword(String password){
	         this.password = password;
	     }
	     public int getId() {
			 return id;
		 }


		 public void setId(int id) {
			 this.id = id;
		 }

}
		
