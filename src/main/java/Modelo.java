/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
<<<<<<< HEAD
 * 
=======
 * @author Usuario
>>>>>>> cc2a5695396e76ec584e7e13f7c7316f4d6a7926
 */
public class Modelo {
    private String descricao;
    private int id;
    private Marca marca;

    public Modelo(String descricao, int id, Marca marca) {
        this.descricao = descricao;
        this.id = id;
        this.marca = marca;
    }

    // Getters e Setters
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}
