package services;

import config.HibernateConfig;
import model.Pokedex;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/*/
Parte 1: «e unha clase que permita, usando hibernate, crear, modificar, ler e eliminar rexistros»
 */

public class PokedexService {
    public void crearPokedex (Pokedex p) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            session.save(p);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao crear a pokedex da Pokedex: " + e.getMessage());
        }
    }
    /*/
    Parte 1:debe ter métodos que permitan facelo tanto con obxectos (session.get() por exemplo) como definindo queries (session.createQuery())»
    leer una entrada por id:
    */
    public Pokedex lerPokedex (Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            return session.get(Pokedex.class, id); // busca por id en la tabla "Pokedex"

        } catch (Exception e){
            System.out.println("Erro ao ler a pokedex do Pokedex: " + e.getMessage());
            return null;
        }
    }
    /*/
    Parte 1: «Listar pokemons na pokedex»
    listar todas las entradas (con query):
    */
    public List <Pokedex> listarPokedex() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            List <Pokedex> pokedexList = session.createQuery("FROM Pokedex", Pokedex.class).list();
            /*/
            "FROM Pokedex" es HQL, el lenguaje de queries de Hibernate. Equivale al SELECT * FROM pokedex.
            Pokedex.class indica el tipo de objetos que devuelve la query.
            .list() ejecuta la query y devuelve todas las filas como List<Pokedex>.
            */

            return pokedexList;
        } catch (Exception e) {
            System.out.println("Erro ao listar pokedex da Pokedex: " + e.getMessage());
            return null;
        }
    }
    //buscar por nombre (query con parámetro)

    public Pokedex lerPokemonPorNome(String nome) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pokedex WHERE nome = :nome", Pokedex.class).setParameter("nome", nome).uniqueResult();

            /*/
           WHERE nome = ... filtra por el atributo nome de la clase.
           :nome es un parámetro con nombre. Es el equivalente al ? de un PreparedStatement.
           setParameter sustituye :nome por el valor que llega al metodo.El primer "nome" es el nombre del parámetro y el segundo nome es la variable.
           uniqueResult Devuelve un solo objeto, o null si no hay ninguno. Si hay más de una fila con ese nombre, falla y cae en el catch.
           Eso te puede pasar si repites nombres en la pokedex.
            */

        } catch (Exception e) {
            System.out.println("Erro ao ler o Adestrador polo nome: " + e.getMessage());
            return null;
        }
    }
    /*/
   Parte 1: «Modificar duas entradas na pokedex» y «Eliminar tódo-los pokemons na pokedex»
   Metodo 1: actualizar
    */

    public void actualizarPokedex (Long id, Pokedex pokedexUpdate) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            Pokedex pokedex = session.get(Pokedex.class, id);

            if (pokedex != null) {
                pokedex.setNome(pokedexUpdate.getNome());
                pokedex.setPeso(pokedexUpdate.getPeso());
                pokedex.setMisc(pokedexUpdate.getMisc());

                session.update(pokedex);
                transaction.commit();
            } else {
                System.out.println("Non se atopou a pokedex co id " + id);
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao actualizar a pokedex: " + e.getMessage());
        }
    }
    // Metodo 2: eliminar

    public void eliminarPokedex (Long id) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            Pokedex pokedex = session.get(Pokedex.class, id);

            if (pokedex != null){
                session.delete(pokedex); //Es un DELETE FROM Pokedex WHERE id = ... en PostgreSQL para eliminar esa fila concreta.
                transaction.commit();

            } else {
                System.out.println("Non se atopou a pokedex co id " + id);
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao eliminar a pokedex da Pokedex: " + e.getMessage());
        }
    }
    //xml
    public void toXML(List<Pokedex> lista, String ruta) {
        try (FileWriter fw = new FileWriter(ruta)) {
            XMLStreamWriter xsw = XMLOutputFactory.newInstance().createXMLStreamWriter(fw);

            xsw.writeStartDocument("UTF-8", "1.0");
            xsw.writeStartElement("pokedex");

            for (Pokedex p : lista) {
                xsw.writeStartElement("entrada");
                xsw.writeAttribute("id", String.valueOf(p.getId()));

                xsw.writeStartElement("nome");
                xsw.writeCharacters(p.getNome());
                xsw.writeEndElement();

                xsw.writeStartElement("peso");
                xsw.writeCharacters(String.valueOf(p.getPeso()));
                xsw.writeEndElement();

                xsw.writeStartElement("misc");
                xsw.writeCharacters(String.valueOf(p.getMisc()));
                xsw.writeEndElement();

                xsw.writeEndElement();
            }

            xsw.writeEndElement();
            xsw.writeEndDocument();
            xsw.flush();
            xsw.close();

        } catch (IOException | XMLStreamException e) {
            System.out.println("Erro exportando XML: " + e.getMessage());
        }
    }
}
/*/
Resumen del CRUD de Hibernate
Operación	Lo que escribes
Crear	session.save(obj)
Leer uno	session.get(Clase.class, id)
Leer varios	session.createQuery("FROM Clase", Clase.class).list()
Modificar	get → setters → session.update(obj)
Eliminar	get → session.delete(obj)
 */
