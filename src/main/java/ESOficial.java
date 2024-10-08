
import java.util.Date;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */
public class ESOficial extends EntradaSaida {
    private int kmRegistrado;

    public ESOficial(int id, Date data, TipoMovimento tipoMovimento, int kmRegistrado) {
        super(id, data, tipoMovimento);
        this.kmRegistrado = kmRegistrado;
    }

    // Getters e Setters
    public int getKmRegistrado() {
        return kmRegistrado;
    }

    public void setKmRegistrado(int kmRegistrado) {
        this.kmRegistrado = kmRegistrado;
    }
}
