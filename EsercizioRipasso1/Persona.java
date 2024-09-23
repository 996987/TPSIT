package EsercizioRipasso1;


import java.util.Random;

public class Persona implements Runnable{
    private String nome;
    private String cognome;
    private ContoBancario contobancario;

    public Persona(String nome, String cognome,ContoBancario contobancario) {
        this.nome = nome;
        this.cognome = cognome;
        this.contobancario = contobancario;
    }


    public void run(){
        Random rand = new Random();
        for(int i = 0; i<10; i++){
            if(i%2==0){
                contobancario.preleva(rand.nextInt(1000));
                System.out.println(nome + "ha prelevato!");
            }else{
                contobancario.deposita(rand.nextInt(1000));
                System.out.println(nome + "ha depositato!");

            }
        }
    }



}
