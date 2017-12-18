import com.my.DetailEntity;
import com.my.AvtosalonEntity;
import com.my.VlasnykEntity;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;


public class Main {

    private static SessionFactory ourSessionFactory;
    static {
        try { // Create the SessionFactory from hibernate.cfg.xml
            ourSessionFactory =  new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) { throw new ExceptionInInitializerError(ex); }
    }
    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession(); //return opened session
    }
    //---------------------------------------------------------------------------
    public static void main(final String[] args) throws Exception {
        // get opened session
        Session session = getSession();
        try {
            ReadAllTable(session);
//            insertVlasnyk(session);
//            ReadAllTable(session);
//            insertAvtosalon(session);
//            updateAvtosalon(session);
//            ReadAllTable(session);

            System.out.println("Finish work!");
        } finally { session.close(); System.exit(0); }
    }

    private static void ReadAllTable(Session session){

//region Read Person
        Query query = session.createQuery("from " + "VlasnykEntity");
        System.out.format("\nTable Vlasnyk --------------------\n");
        System.out.format("%3s %-12s %-12s  %s\n","ID",  "Surname", "Name", "Email");
        for (Object obj : query.list()) {
            VlasnykEntity vlasnyk = (VlasnykEntity) obj;
            System.out.format("%3s %-12s %-12s  %s\n",vlasnyk.getIdVlasnyk(),
                    vlasnyk.getSurname(), vlasnyk.getName(),  vlasnyk.getEmail());
        }
        //endregion

//region Read Book
        query = session.createQuery("from " + "DetailEntity");
        System.out.format("\nTable Detail --------------------\n");
        System.out.format("%3s %-18s %-18s %s\n", "ID", "DetailName", "Author", "Amount");
        for (Object obj : query.list()) {
            DetailEntity detail = (DetailEntity) obj;
            System.out.format("%3d %-18s %-18s %s\n", detail.getIdDetail(), detail.getDetailName(), detail.getAuthor(), detail.getAmount());
        }
        //endregion

//region Read City
        query = session.createQuery("from " + "AvtosalonEntity");
        System.out.format("\nTable Avtosalon --------------------\n");
        for (Object obj : query.list()) {
            AvtosalonEntity avtosalonEntity = (AvtosalonEntity) obj;
            System.out.format("%s\n", avtosalonEntity.getAvtosalon());
        }
        query = session.createQuery("from " + "VlasnykEntity ");

        //endregion

    }



   private static void insertAvtosalon(Session session){
        Scanner input = new Scanner(System.in);
        System.out.println("Input a new name avtosalon: ");
        String newcompany = input.next();

        session.beginTransaction();
        AvtosalonEntity avtosalonEntity =new AvtosalonEntity(newcompany);
        session.save(avtosalonEntity);
        session.getTransaction().commit();

        System.out.println("end insert avtosalon");
    }

    private static void insertVlasnyk(Session session){
        Scanner input = new Scanner(System.in);
        System.out.println("Input new Vlasnyk Surname: ");
        String surname_new = input.next();
        System.out.println("Input new Vlasnyk Name: ");
        String name_new = input.next();
        System.out.println("Input the Avtosalon for Person: ");
        String avtosalon = input.next();
        System.out.println("Input new Person Email: ");
        String email = input.next();

        session.beginTransaction();
        VlasnykEntity vlasnykEntity =new VlasnykEntity(surname_new,name_new,avtosalon,email);
        session.save(vlasnykEntity);
        session.getTransaction().commit();
        System.out.println("end insert avtosalon");
    }

    private static void updateAvtosalon(Session session){
        Scanner input = new Scanner(System.in);
        System.out.println("\nInput a name Avtosalon: ");
        String avtosalon = input.next();
        System.out.println("Input new name Avtosalon: ");
        String newAvtosalon = input.next();

        AvtosalonEntity avtosalonEntity = (AvtosalonEntity) session.load( AvtosalonEntity.class, avtosalon);
        if(avtosalonEntity !=null){
            session.beginTransaction();
            Query query = session.createQuery("update AvtosalonEntity set avtosalon=:code1  where avtosalon = :code2");
            query.setParameter("code1", newAvtosalon);
            query.setParameter("code2", avtosalon);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("end update avtosalon: "+ result);
        }
        else System.out.println("There is no the avtosalon");
    }

}