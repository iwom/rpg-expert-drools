import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import sample.Account;
import sample.Department;
import sample.Employee;

/**
 * This is a sample class to launch a rule.
 */
public class Main {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");

            Department civil = Department.builder().name("Civil").build();
            Department it = Department.builder().name("IT").build();
            Department adm = Department.builder().name("Administation").build();
            Employee john = Employee.builder().department(civil).name("John").manager(true).message("Hi, Im John").build();
            Employee george = Employee.builder().department(civil).name("George").manager(false).message("Hi Im Geo").build();
            Employee stephanie = Employee.builder().department(it).name("Stephanie").manager(true).message("Jo Stephanie").build();
            Employee ian = Employee.builder().department(it).name("Ian").manager(false).message("I'AN").build();


            kSession.insert(adm);
            kSession.insert(civil);
            kSession.insert(it);
            kSession.insert(john);
            kSession.insert(george);
            kSession.insert(stephanie);
            kSession.insert(ian);
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
