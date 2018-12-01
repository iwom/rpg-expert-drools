import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


import sample.Frame;

/**
 * This is a sample class to launch a rule.
 */
public class Main {

    public static void main(String[] args) {
        try {
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");
            Frame.init();
            kSession.fireAllRules();
            Frame.frameDispose();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
