package thrift;

import org.apache.thrift.TException;
import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * @Auther: allanyang
 * @Date: 2019/1/30 11:49
 * @Description:
 */
public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("get username from client:" + username);

        Person p = new Person();
        p.setUsername(username);
        p.setAge(20);
        p.setMarriee(true);
        return p;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("person.name:" + person.getUsername());
        System.out.println("person.age:" + person.getAge());
        System.out.println("person.marriee:" + person.isMarriee());
    }
}