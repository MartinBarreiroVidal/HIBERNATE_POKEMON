/*/
Main de la Parte 1

Parte 1: «Usa os métodos para executar a seguinte secuencia:
Inserir 10 pokemons en la tabla pokedex
Listar pokemons na pokedex
Modificar duas entradas na pokedex
Listar pokemons na pokedex
Eliminar tódo-los pokemons na pokedex»
 */

import model.Pokedex;
import services.PokedexService;

import java.util.List;

public class Main {
    public static void main (String[] args) {
        PokedexService pokServ = new PokedexService(); // este objeto tiene los métodos crearPokedex, listarPokedex, etc.

        //Bloque 1 insertar: «Inserir 10 pokemons en la tabla pokedex»

        Pokedex pikachu = new Pokedex("Pikachu", 6.0, "Pokemon electrico");
        Pokedex squitle = new Pokedex("Squirtle", 8.0, "Inicial de agua");

        pokServ.crearPokedex(pikachu);
        pokServ.crearPokedex(squitle);

        //Bloque 2: listar «Listar pokemons na pokedex»

        List<Pokedex> pokedexList = pokServ.listarPokedex();
        if (pokedexList != null){
            for (Pokedex p : pokedexList){
                System.out.println("ID: " + p.getId());
                System.out.println("Nome: " + p.getNome());
                System.out.println("Peso: " + p.getPeso());
                System.out.println("Misc: " + p.getMisc());
            }
        }
        //Bloque 3: modificar dos entradas «Modificar duas entradas na pokedex»

        Pokedex raichu = new Pokedex("Raichu", 30.0, "Evolución de Pikachu");
        pokServ.actualizarPokedex(1L, raichu); //cambia la fila con id 1 por esos datos. La L indica el número es un Long.

        // Bloque 4: listar otra vez «Listar pokemons na pokedex»

        pokedexList = pokServ.listarPokedex();

        if (pokedexList != null){
            for (Pokedex p : pokedexList){
                System.out.println("ID: " + p.getId());
                System.out.println("Nome: " + p.getNome());
                System.out.println("Peso: " + p.getPeso());
                System.out.println("Misc: " + p.getMisc());
            }
        }
        //Bloque 5: eliminar todo «Eliminar tódo-los pokemons na pokedex»

        pokedexList = pokServ.listarPokedex();
        if (pokedexList != null) {
            for (Pokedex p : pokedexList) {
                pokServ.eliminarPokedex(p.getId());
            }
        }
    }
}
