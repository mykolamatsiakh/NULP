import com.ione.*;
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

//            ReadDetailsOfCar(session);    //work!!!
//            insertAvtosalon(session);     //work!!!
//            ReadAvtosalonTable(session);  //work!!!
//            insertCar(session);           //notwork!!!
            updateCity(session);
//            ReadAvtosalonFilter(session);

//            ReadAvtosalonTable(session);

//            ReadAvtosalonTable(session);



//            ReadAvtosalonFilter(session);

//            AddBookForPerson(session);
//            ReadAllTable(session);

//            ReadAvtosalonTable(session);
//            updateCity(session);
//            ReadAllTable(session);

//            ReadDetailsOfCar(session);
//            AddPairPersonBookWithProcedure(session);
//            ReadDetailsOfCar(session);

            System.out.println("Finish work!");
        } finally { session.close(); System.exit(0); }
    }

    private static void ReadAllTable(Session session){

        Query query = session.createQuery("from " + "AvtosalonEntity ");
        System.out.format("\nTable Avtosalon --------------------\n");
        System.out.format("%3s %-12s  %s\n", "ID", "Name", "Owner Name");
        for (Object obj : query.list()) {
            AvtosalonEntity avtosalon = (AvtosalonEntity) obj;
            System.out.format("%3d %-12s %s\n", avtosalon.getIdAvtosalon(),
                    avtosalon.getName(), avtosalon.getOwnerName());
        }

        query = session.createQuery("from " + "CarsEntity ");
        System.out.format("\nTable Cars --------------------\n");
        System.out.format("%3s %-18s  %s\n", "ID", "Avtosalon Name", "Sellers Name");
        for (Object obj : query.list()) {
            CarsEntity cars = (CarsEntity) obj;
            System.out.format("%3d %-18s  %s\n", cars.getIdCars(), cars.getAvtosalon().getName(), cars.getSeler());
        }

        query = session.createQuery("from " + "CarDetailEntity ");
        System.out.format("\nTable Connection Table --------------------\n");
        System.out.format("%-18s %-18s %-18s %s\n", "ID", "Class", "Max Distance", "Max Speed","Model");
        for (Object obj : query.list()) {
            CarDetailEntity details = (CarDetailEntity) obj;
            System.out.format("%-18s %-18s %-18s %s\n", details.getIdCarDetail(),details.getClazz(),
                    details.getMaxDistanc(),details.getMaxSpeed(),details.getModel());
        }

    }


    //neWORK!!!
    private static void ReadAvtosalonFilter(Session session){

        Scanner input = new Scanner(System.in);
        System.out.println("Input name Avtosalon for Car: ");
        String car_in = input.next();

        AvtosalonEntity cityEntity = (AvtosalonEntity) session.load( AvtosalonEntity.class, car_in);
        if(cityEntity!=null){
            System.out.format("\n%s: %s\n", car_in, "ID CAR:");
            for (CarsEntity obj : cityEntity.getCars())
                System.out.format("    %s\n", obj.getAvtosalon());
        }
        else System.out.println("invalid name of Avtosalon");
    }
    //neWORK!!!
    private static void ReadDetailsOfCar(Session session){
        Query query = session.createQuery("from " + "CarsEntity ");
        System.out.format("\nTable Person --------------------\n");
        System.out.format("%3s %-12s \n","ID", "Seller");
        for (Object obj : query.list()) {
            CarsEntity carsEntity = (CarsEntity) obj;
            System.out.format("%3s %-12s->\n", carsEntity.getIdCars(), carsEntity.getSeler());
            for (CarDetailEntity carDetailEntity : carsEntity.getDetails()) {
                System.out.format("\t\t%s // %s\n", carDetailEntity.getClazz(),  carDetailEntity.getMaxDistanc());
            }
        }
    }

    private static void ReadAvtosalonTable(Session session){

        Query query = session.createQuery("from " + "AvtosalonEntity ");
        System.out.format("\nTable Avtosalon --------------------\n");
        for (Object obj : query.list()) {
            AvtosalonEntity city = (AvtosalonEntity) obj;
            System.out.format("%s\n", city.getName());
        }
    }

    private static void insertAvtosalon(Session session){
        Scanner input = new Scanner(System.in);
//        int idAvtosalon = 4;
        System.out.println("Input new : ");
        String name = input.next();
        System.out.println("Input the City for Person: ");
        String ownerName = input.next();

        session.beginTransaction();
        AvtosalonEntity cityEntity=new AvtosalonEntity(name,ownerName);
        session.save(cityEntity);
        session.getTransaction().commit();

        System.out.println("end insert Avtosalon");
    }
    //notwork!!!
    private static void insertCar(Session session){
        Scanner input = new Scanner(System.in);
        System.out.println("Input new Sellers Name for Car: ");
        String surname_new = input.next();


        session.beginTransaction();
        CarsEntity personEntity=new CarsEntity(surname_new);
        session.save(personEntity);
        session.getTransaction().commit();
        System.out.println("end insert car");
    }
    //notwork!!!
    private static void updateCity(Session session){
        Scanner input = new Scanner(System.in);
        System.out.println("\nInput a name of Seller: ");
        String seler = input.next();
        System.out.println("Input new name Seller: ");
        String newCity = input.next();

        CarsEntity cityEntity = (CarsEntity) session.load( CarsEntity.class, seler);
        if(cityEntity!=null){
            session.beginTransaction();
            Query query = session.createQuery("update CarsEntity set seler=:code1  where seler = :code2");
            query.setParameter("code1", newCity);
            query.setParameter("code2", seler);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("end update Seller: "+ result);
        }
        else System.out.println("There is no the Seller");
    }
//
//    private static void AddBookForPerson(Session session){
//        System.out.println("Give a book to person--------------");
//        Scanner input = new Scanner(System.in);
//        System.out.println("Choose Person Surname:");
//        String surname_in = input.next();
//        System.out.println("Choose Name Book:");
//        String book_in = input.next();
//
//        Query query = session.createQuery("from " + "PersonEntity where surname = :code");
//        query.setParameter("code", surname_in);
//
//        if(!query.list().isEmpty()){
//            //Give this person entity from query
//            PersonEntity personEntity = (PersonEntity)query.list().get(0);
//            //search the book entity  from query
//            query = session.createQuery("from " + "BookEntity where bookName = :code");
//            query.setParameter("code", book_in);
//            if(!query.list().isEmpty()){
//                //Give this book entity from query
//                BookEntity bookEntity = (BookEntity)query.list().get(0);
//                session.beginTransaction();
//                personEntity.addBookEntity(bookEntity);
//                session.save(personEntity);
//                session.getTransaction().commit();
//                System.out.println("end insert boor for person");
//            }
//            else {System.out.println("There is no the book");}
//        }
//        else {System.out.println("There is no this person");}
//
//    }
//
//    private static void AddPairPersonBookWithProcedure(Session session){
//        Scanner input = new Scanner(System.in);
//        System.out.println("\nInput Surname for Person: ");
//        String surname = input.next();
//        System.out.println("Input NameBook for Book: ");
//        String book = input.next();
//
//        //to JPA 2.0
////        Query query = session.createSQLQuery(
////                "CALL InsertPersonBook(:Person, :Book)")
////                .setParameter("Person", surname)
////                .setParameter("Book", book);
////        System.out.println(query.list().get(0));
//
//        //from JPA 2.1
//        StoredProcedureQuery query = session
//                .createStoredProcedureQuery("InsertPersonBook")
//                .registerStoredProcedureParameter("SurmanePersonIn", String.class, ParameterMode.IN)
//                .registerStoredProcedureParameter("BookNameIN", String.class, ParameterMode.IN)
//                .setParameter("SurmanePersonIn", surname)
//                .setParameter("BookNameIN", book);
//        query.execute();
//        String str = (String) query.getResultList().get(0);
//        System.out.println(str);
//
//        if(str.equals("OK")) {
//            Query query2 = session.createQuery("from " + "PersonEntity");
//            for (Object obj : query2.list()) {
//                session.refresh(obj);
//            }
//            query2 = session.createQuery("from " + "BookEntity ");
//            for (Object obj : query2.list()) {
//                session.refresh(obj);
//            }
//        }
//    }

}