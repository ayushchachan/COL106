import java.util.HashMap;

import HelperClasses.Pair;
import HelperClasses.sha256;

public class CRF extends sha256 {

    // Stores the output size of the function Fn()
    public int outputsize;

    CRF(int size) {
        outputsize = size;
        assert outputsize <= 64;
    }

    // Outputs the mapped outputSize characters long string s' for an input string s
    public String Fn(String s) {
        String shasum = encrypt(s);
        return shasum.substring(0,outputsize);
    }

    /*==========================
    |- To be done by students -|
    ==========================*/

    public Pair<String, String> FindCollDeterministic() {
        HashMap<String, String> dgstMap = new HashMap<>();

        String x = "0".repeat(outputsize + 1);
        String dgst = Fn(x);
        dgstMap.put(dgst, x);

        for (int i = 0; i < Math.pow(16, outputsize); i++) {
            x = dgst;
            dgst = Fn(x);
            if (dgstMap.containsKey(dgst)) {
                return new Pair<>(x, dgstMap.get(dgst));
            }
            dgstMap.put(dgst, x);
        }
        return null;
    }

    public void FindCollRandomized() {
        
        String attempt_filename = "FindCollRandomizedAttempts.txt";
        String outcome_filename = "FindCollRandomizedOutcome.txt";

        
    }
}