package EsercizioRipasso1;


import java.util.Random;

public class ContoBancario{
    private Double saldo;

    public ContoBancario(Double saldo) {
        this.saldo = saldo;
    }

    public synchronized void preleva(double prelievo){
        if(saldo-prelievo<0){
            System.out.println("Il tuo saldo è inferiore di ciò che vuoi prelevare. U broke as hell ");
            Thread.interrupted();
        }else{
            saldo-=prelievo;
            System.out.println("Il saldo è:" + saldo);
        }
    }

    public synchronized void deposita(double deposito){
        saldo+=deposito;
        System.out.println("Il saldo è:" + saldo);
    }
}
