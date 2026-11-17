/*
Main de la Parte 1 y Parte 2

Parte 1: «Usa os métodos para executar a seguinte secuencia:
Inserir 10 pokemons en la tabla pokedex
Listar pokemons na pokedex
Modificar duas entradas na pokedex
Listar pokemons na pokedex
Eliminar tódo-los pokemons na pokedex»

Parte 2: «Usa os métodos para executar a seguinte secuencia:
Inserir 10 pokemons en la tabla pokedex
Inserir 2 adestradores na taboa adestrador
Listar pokemons na pokedex
Listar adestradores
Modificar dous adestradores
Modificar duas entradas na pokedex
Listar pokemons na pokedex
Listar adestradores
(toXML pokemons)
(toXML adestradores)
Eliminar tódo-los datos das táboas»
 */

import model.Adestrador;
import model.Pokedex;
import services.AdestradorService;
import services.PokedexService;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PokedexService pokServ = new PokedexService();
        AdestradorService adestServ = new AdestradorService();

        // PARTE 1
        System.out.println("-------------PARTE 1: POKEDEX-------------");

        // Bloque 1 insertar: «Inserir 10 pokemons en la tabla pokedex»
        Pokedex pikachu = new Pokedex("Pikachu", 6.0, "Pokemon electrico");
        Pokedex squirtle = new Pokedex("Squirtle", 8.0, "Inicial de agua");
        Pokedex bulbasaur = new Pokedex("Bulbasaur", 6.9, "Inicial de planta");
        Pokedex charmander = new Pokedex("Charmander", 8.5, "Inicial de fuego");
        Pokedex pidgey = new Pokedex("Pidgey", 1.8, "Pokemon pajaro");
        Pokedex rattata = new Pokedex("Rattata", 3.5, "Pokemon raton");
        Pokedex jigglypuff = new Pokedex("Jigglypuff", 5.5, "Pokemon globo");
        Pokedex meowth = new Pokedex("Meowth", 4.2, "Pokemon gato");
        Pokedex psyduck = new Pokedex("Psyduck", 19.6, "Pokemon pato");
        Pokedex snorlax = new Pokedex("Snorlax", 460.0, "Pokemon dormir");

        pokServ.crearPokedex(pikachu);
        pokServ.crearPokedex(squirtle);
        pokServ.crearPokedex(bulbasaur);
        pokServ.crearPokedex(charmander);
        pokServ.crearPokedex(pidgey);
        pokServ.crearPokedex(rattata);
        pokServ.crearPokedex(jigglypuff);
        pokServ.crearPokedex(meowth);
        pokServ.crearPokedex(psyduck);
        pokServ.crearPokedex(snorlax);

        // Bloque 2: listar «Listar pokemons na pokedex»
        System.out.println("-------------LISTADO INICIAL POKEDEX-------------");
        List<Pokedex> pokedexList = pokServ.listarPokedex();
        if (pokedexList != null) {
            for (Pokedex p : pokedexList) {
                System.out.println("ID: " + p.getId());
                System.out.println("Nome: " + p.getNome());
                System.out.println("Peso: " + p.getPeso());
                System.out.println("Misc: " + p.getMisc());
                System.out.println("--------------------");
            }
        }

        // Bloque 3: modificar dos entradas «Modificar duas entradas na pokedex»
        System.out.println("-------------MODIFICAR 2 ENTRADAS POKEDEX-------------");
        Pokedex raichu = new Pokedex("Raichu", 30.0, "Evolución de Pikachu");
        pokServ.actualizarPokedex(pikachu.getId(), raichu);

        Pokedex wartortle = new Pokedex("Wartortle", 22.5, "Evolución de Squirtle");
        pokServ.actualizarPokedex(squirtle.getId(), wartortle);

        // Bloque 4: listar otra vez «Listar pokemons na pokedex tras modificar»
        System.out.println("-------------LISTADO TRAS MODIFICAR POKEDEX-------------");
        pokedexList = pokServ.listarPokedex();
        if (pokedexList != null) {
            for (Pokedex p : pokedexList) {
                System.out.println("ID: " + p.getId());
                System.out.println("Nome: " + p.getNome());
                System.out.println("Peso: " + p.getPeso());
                System.out.println("Misc: " + p.getMisc());
                System.out.println("--------------------");
            }
        }

        // Bloque 5: eliminar todo «Eliminar tódo-los pokemons na pokedex»
        System.out.println("-------------ELIMINO POKEDEX-------------");
        pokedexList = pokServ.listarPokedex();
        if (pokedexList != null) {
            for (Pokedex p : pokedexList) {
                System.out.println("ELIMINO: " + p.getNome());
                pokServ.eliminarPokedex(p.getId());
            }
        }

        // PARTE 2
        System.out.println("\n-------------PARTE 2: POKEDEX E ADESTRADOR-------------");

        // Bloque 1: «Inserir 10 pokemons en la tabla pokedex»
        Pokedex p1 = new Pokedex("Bulbasaur", 6.9, "Planta");
        Pokedex p2 = new Pokedex("Ivysaur", 13.0, "Planta/Veneno");
        Pokedex p3 = new Pokedex("Venusaur", 100.0, "Planta/Veneno");
        Pokedex p4 = new Pokedex("Charmander", 8.5, "Fuego");
        Pokedex p5 = new Pokedex("Charmeleon", 19.0, "Fuego");
        Pokedex p6 = new Pokedex("Charizard", 90.5, "Fuego/Volador");
        Pokedex p7 = new Pokedex("Squirtle", 9.0, "Agua");
        Pokedex p8 = new Pokedex("Wartortle", 22.5, "Agua");
        Pokedex p9 = new Pokedex("Blastoise", 85.5, "Agua");
        Pokedex p10 = new Pokedex("Caterpie", 2.9, "Bicho");

        pokServ.crearPokedex(p1);
        pokServ.crearPokedex(p2);
        pokServ.crearPokedex(p3);
        pokServ.crearPokedex(p4);
        pokServ.crearPokedex(p5);
        pokServ.crearPokedex(p6);
        pokServ.crearPokedex(p7);
        pokServ.crearPokedex(p8);
        pokServ.crearPokedex(p9);
        pokServ.crearPokedex(p10);

        // Bloque 2: «Inserir 2 adestradores na taboa adestrador»
        Adestrador ash = new Adestrador("Ash", LocalDate.of(1997, 4, 1));
        Adestrador misty = new Adestrador("Misty", LocalDate.of(1998, 5, 15));
        adestServ.crearAdestrador(ash);
        adestServ.crearAdestrador(misty);

        // Bloque 3: «Listar pokemons na pokedex» e «Listar adestradores»
        System.out.println("-------------LISTADO INICIAL POKEDEX-------------");
        pokedexList = pokServ.listarPokedex();
        if (pokedexList != null) {
            for (Pokedex p : pokedexList) {
                System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome());
            }
        }

        System.out.println("-------------LISTADO INICIAL ADESTRADORES-------------");
        List<Adestrador> adestradorList = adestServ.listarAdestradores();
        if (adestradorList != null) {
            for (Adestrador a : adestradorList) {
                System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome() + " | Nacemento: " + a.getNacemento());
            }
        }

        // Bloque 4: «Modificar dous adestradores»
        System.out.println("-------------MODIFICAR 2 ADESTRADORES-------------");
        adestServ.actualizarAdestrador(ash.getId(), new Adestrador("Ash Ketchum", LocalDate.of(1997, 4, 1)));
        adestServ.actualizarAdestrador(misty.getId(), new Adestrador("Misty Waterflower", LocalDate.of(1998, 5, 15)));

        // Bloque 5: «Modificar duas entradas na pokedex»
        System.out.println("-------------MODIFICAR 2 ENTRADAS POKEDEX-------------");
        pokServ.actualizarPokedex(p1.getId(), new Pokedex("Bulbasaur_Mod", 7.0, "Planta"));
        pokServ.actualizarPokedex(p4.getId(), new Pokedex("Charmander_Mod", 9.0, "Fuego"));

        // Bloque 6: «Listar pokemons na pokedex» e «Listar adestradores»
        System.out.println("-------------LISTADO TRAS MODIFICAR POKEDEX-------------");
        pokedexList = pokServ.listarPokedex();
        if (pokedexList != null) {
            for (Pokedex p : pokedexList) {
                System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome());
            }
        }

        System.out.println("-------------LISTADO TRAS MODIFICAR ADESTRADORES-------------");
        adestradorList = adestServ.listarAdestradores();
        if (adestradorList != null) {
            for (Adestrador a : adestradorList) {
                System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome() + " | Nacemento: " + a.getNacemento());
            }
        }

        // Bloque 7: «(toXML pokemons)» e «(toXML adestradores)»
        System.out.println("-------------EXPORTANDO A XML-------------");
        pokServ.toXML(pokedexList, "pokemons.xml");
        adestServ.toXML(adestradorList, "adestradores.xml");

        // Bloque 8: «Eliminar tódo-los datos das táboas»
        System.out.println("-------------ELIMINO TODO-------------");
        if (adestradorList != null) {
            for (Adestrador a : adestradorList) {
                System.out.println("ELIMINO: " + a.getNome());
                adestServ.eliminarAdestrador(a.getId());
            }
        }
        if (pokedexList != null) {
            for (Pokedex p : pokedexList) {
                System.out.println("ELIMINO: " + p.getNome());
                pokServ.eliminarPokedex(p.getId());
            }
        }
    }
}
