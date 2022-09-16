package indiefy;

import java.util.ArrayList;

public class Armazenamento {

	private static ArrayList<Musica> musicas_cadastradas = new ArrayList<Musica>();
	private static ArrayList<Usuario> usuarios_cadastrados = new ArrayList<Usuario>();
	private static ArrayList<Playlist> playlists_cadastradas = new ArrayList<Playlist>();
	private static int idUser = 0;
	private static int idMusica = 0;
	
	
	
	
	
	
	
	
	public ArrayList<Musica> getMusicas_cadastradas() {
		return musicas_cadastradas;
	}
	
	
	
	public void setMusicas_cadastradas(ArrayList<Musica> musicas_cadastradas) {
		Armazenamento.musicas_cadastradas = musicas_cadastradas;
	}
	
	
	public ArrayList<Usuario> getUsuarios_cadastrados() {
		return usuarios_cadastrados;
	}
	
	
	public void setUsuarios_cadastrados(ArrayList<Usuario> usuarios_cadastrados) {
		Armazenamento.usuarios_cadastrados = usuarios_cadastrados;
	}
	
	
	
	public static ArrayList<Playlist> getPlaylists_cadastradas() {
		return playlists_cadastradas;
	}



	public static void setPlaylists_cadastradas(ArrayList<Playlist> playlists_cadastradas) {
		Armazenamento.playlists_cadastradas = playlists_cadastradas;
	}



	public int getIdUser() {
		return idUser;
	}



	public void setIdUser(int idUser) {
		Armazenamento.idUser = idUser;
	}



	public int getIdMusica() {
		return idMusica;
	}



	public void setIdMusica(int idMusica) {
		Armazenamento.idMusica = idMusica;
	}


	
	
	
}
