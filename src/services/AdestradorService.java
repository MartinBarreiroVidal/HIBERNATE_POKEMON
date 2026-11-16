package services;

import config.HibernateConfig;
import model.Adestrador;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class AdestradorService {
    //Parte 2: «unha clase por entidade que permita, usando hibernate, crear, modificar, ler e eliminar rexistros»

    public void crearAdestrador (Adestrador adestrador) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(adestrador);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao crear o Adestrador: " + e.getMessage());
        }

    }
    //ler

    public Adestrador lerAdestrador (Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            return session.get(Adestrador.class, id);

        } catch (Exception e){
            System.out.println("Erro ao ler a pokedex do Pokedex: " + e.getMessage());
            return null;
        }
    }
    //actualizar

    public void actualizarAdestrador (Long id, Adestrador adestradorUpdate) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            Adestrador adestrador = session.get(Adestrador.class,id);
            if (adestrador != null){
                adestrador.setNome(adestradorUpdate.getNome());
                adestrador.setNacemento(adestradorUpdate.getNacemento());
                session.update(adestrador);
                transaction.commit();
            } else {
                System.out.println("Non se atopou o Adestrador co id " + id);
                transaction.rollback();
            }
        } catch (Exception e) {
            System.out.println("Erro ao actualizar o adestrador " + e.getMessage());
        }
    }
    //eliminar

    public void eliminarAdestrador (Long id) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            Adestrador adestrador = session.get(Adestrador.class, id);

            if (adestrador != null){
                session.delete(adestrador);
                transaction.commit();
            } else {
                System.out.println("Non se atopou o Adestrador co id " + id);
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction != null & transaction.isActive()){
                transaction.rollback();
            }
            System.out.println("Erro ao eliminar o adestrador de Adestrador " + e.getMessage());
        }
    }
    //listar

    public List<Adestrador> listarAdestradores() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            List <Adestrador> adestradorList = session.createQuery("FROM Adestrador", Adestrador.class).list();
            return adestradorList;
        } catch (Exception e) {
            System.out.println("Erro ao listar os adestradores " + e.getMessage());
            return null;
        }
    }

    // Parte 2: «implementa un método toXML que permita exportar os datos obtidos das táboas,
    //  debes chamalo entre o paso Listar e Eliminar da secuencia anterior»

    public void toXML(List<Adestrador> lista, String ruta) {
        try (FileWriter fw = new FileWriter(ruta)) {
            XMLStreamWriter xsw = XMLOutputFactory.newInstance().createXMLStreamWriter(fw);

            xsw.writeStartDocument("UTF-8", "1.0");
            xsw.writeStartElement("adestrador");

            for (Adestrador a : lista) {
                xsw.writeStartElement("entrada");
                xsw.writeAttribute("id", String.valueOf(a.getId()));

                xsw.writeStartElement("nome");
                xsw.writeCharacters(a.getNome());
                xsw.writeEndElement();

                xsw.writeStartElement("nacemento");
                xsw.writeCharacters(String.valueOf(a.getNacemento()));
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
