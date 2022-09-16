package indiefy;
import java.util.ArrayList;
import java.util.Scanner;

//interface simples utilizada para escolher qual tipo de execucao para tocar, playlist ou musica

public interface Tocavel{

    public abstract void tocar(Scanner sc);
    public abstract void tocar(ArrayList<Musica> musicas_artista, Scanner sc);
}