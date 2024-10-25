import java.util.concurrent.Semaphore;

public class Matchmaker extends Thread {
    String nome;
    static Lobby[] lobbies = new Lobby[1];
    private static Semaphore sem = new Semaphore(1);
    public Matchmaker(String nome) {
        this.nome = nome;
        lobbies[0] = new Lobby();
    }
    public void run() {
        try {
            sem.acquire();
            if (lobbies[lobbies.length-1].setGiocatori(nome)==1) {
                lobbies[lobbies.length-1].start();
                lobbies = new Lobby[lobbies.length+1];
                lobbies[lobbies.length-1] = new Lobby();
            } else if (lobbies[lobbies.length-1].setGiocatori(nome)==0) {
                lobbies[lobbies.length-1].setGiocatori(nome);
            } else {
                System.out.println("Sono terminate le lobby disponibili...");
            }
            sem.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
