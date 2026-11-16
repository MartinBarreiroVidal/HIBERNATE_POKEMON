package model;
import javax.persistence.*;

@Entity
@Table(name = "pokedex")
public class Pokedex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nome", nullable = false)
    private String nome;

    @Column (name = "peso")
    private Double peso;

    @Column (name = "misc")
    private String misc;

    public Pokedex() {}

    public Pokedex(String nome, Double peso, String misc) {
        this.nome = nome;
        this.peso = peso;
        this.misc = misc;
    }

    public Pokedex(Long id, String nome, Double peso, String misc) {
        this.id = id;
        this.nome = nome;
        this.peso = peso;
        this.misc = misc;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
