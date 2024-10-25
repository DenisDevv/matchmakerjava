import java.util.concurrent.Semaphore;
public class Lobby extends Thread {
    private final int numeroMax = 4;
    private final String[] giocatori = new String[numeroMax];
    private int numeroGiocatori = 0;
    private Semaphore sem = new Semaphore(4);
    public int setGiocatori(String giocatore) throws InterruptedException {
        sem.acquire();
        this.giocatori[numeroGiocatori] = giocatore;
        numeroGiocatori++;
        System.out.println("Giocatore " + giocatore + " entrato in lobby " + this.threadId());
        if (sem.availablePermits()==0) {
            return 1;
        } else if (sem.availablePermits()>0){
            return 0;
        } else {
            return -1;
        }
    }
    public void run() {
        System.out.println("Partita iniziata in lobby " + this.threadId());
    }
}
