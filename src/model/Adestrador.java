package model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "adestrador")
public class Adestrador {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nome", nullable = false)
    private String nome;

    @Column (name = "nacemento")
    private LocalDate nacemento;

    public Adestrador() {}

    public Adestrador(String nome, LocalDate nacemento) {
        this.nome = nome;
        this.nacemento = nacemento;
    }

    public Adestrador(Long id, LocalDate nacemento, String nome) {
        this.id = id;
        this.nacemento = nacemento;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getNacemento() {
        return nacemento;
    }

    public void setNacemento(LocalDate nacemento) {
        this.nacemento = nacemento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
