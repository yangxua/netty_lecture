package thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * @Auther: allanyang
 * @Date: 2019/1/30 14:29
 * @Description:
 */
public class ThriftClient {

    public static void main(String[] args) {
        TTransport transport = new TFramedTransport(new TSocket("localhost",8899), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {

            transport.open();

            Person person = client.getPersonByUsername("zs");
            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarriee());

            System.out.println("------------------------------");

            Person p = new Person();
            p.setUsername("ls");
            p.setAge(4);
            p.setMarriee(false);
            client.savePerson(p);
        } catch (Exception e) {

        } finally {
            transport.close();
        }
    }
}