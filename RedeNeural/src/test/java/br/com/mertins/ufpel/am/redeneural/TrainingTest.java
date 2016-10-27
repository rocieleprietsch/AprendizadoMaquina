package br.com.mertins.ufpel.am.redeneural;

import br.com.mertins.ufpel.am.perceptron.OutPerceptron;
import br.com.mertins.ufpel.am.perceptron.Perceptron;
import br.com.mertins.ufpel.am.perceptron.Sample;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author mertins
 */
public class TrainingTest {

    public TrainingTest() {
    }

    @Test
    public void testBuildSamples() {
        List<Sample> samples = new ArrayList<>();
        for (int j = 0; j < 8; j++) {
            Sample sample = new Sample();
            for (int i = 0; i < 8; i++) {
                sample.addIn(i == j ? 1 : 0);
            }
            samples.add(sample);
        }
        int pos = 0;
        for (Sample sample : samples) {
            for (int i = 0; i < 8; i++) {
                sample.addOut(i == pos ? 1 : 0);
            }
            pos++;
        }
        samples.stream().forEach((sample) -> {
            for (int i = 0; i < 8; i++) {
                Assert.assertEquals(sample.getIn(i), sample.getOut(i));
            }
        });
    }

//    @Test
    public void testWithBackPropagationXOR() {
        List<Sample> lista = new ArrayList<>();
        Sample sampleXOR = new Sample();
        sampleXOR.addIn(0);
        sampleXOR.addIn(0);
        sampleXOR.addOut(0);
        lista.add(sampleXOR);
        sampleXOR = new Sample();
        sampleXOR.addIn(0);
        sampleXOR.addIn(1);
        sampleXOR.addOut(1);
        lista.add(sampleXOR);
        sampleXOR = new Sample();
        sampleXOR.addIn(1);
        sampleXOR.addIn(0);
        sampleXOR.addOut(1);
        lista.add(sampleXOR);
        sampleXOR = new Sample();
        sampleXOR.addIn(1);
        sampleXOR.addIn(1);
        sampleXOR.addOut(0);
        lista.add(sampleXOR);MLP rede = new MLP();
        rede.createIn(2);
        rede.addHiddenLayer(1, 3, Perceptron.AlgorithmSimoid.TANGEN);
        rede.addOut(1, 1, Perceptron.AlgorithmSimoid.LOGISTIC);
        rede.connect();
        Training treino = new Training(false);
        treino.withBackPropagation(rede, lista, 0.5, 10000);
        
        lista.forEach(sample -> {
            rede.updateIn(sample);
            OutPerceptron[] ret = rede.process();
            StringBuilder sb = new StringBuilder(" ");
            for (OutPerceptron value : ret) {
                sb.append(String.format("%.30f ", value.getOut()));
            }

            System.out.printf("Exemplo in[%s] out esperado [%s] out real [%s]\n", sample.toStringIn(), sample.toStringOut(), sb.toString().trim());
        });
        
    }

    @Test
    public void testWithBackPropagationDecoder() {
        final int NUMEX = 8;
        List<Sample> samples = new ArrayList<>();
        for (int j = 0; j < NUMEX; j++) {
            Sample sample = new Sample();
            for (int i = 0; i < NUMEX; i++) {
                sample.addIn(i == j ? 1 : 0);
            }
            samples.add(sample);
        }
        int pos = 0;
        for (Sample sample : samples) {
            for (int i = 0; i < NUMEX; i++) {
                sample.addOut(i == pos ? 1 : 0);
            }
            pos++;
        }

        MLP rede = new MLP();
        rede.createIn(NUMEX);
        rede.addHiddenLayer(1, NUMEX/2, 0, Perceptron.AlgorithmSimoid.LOGISTIC);
        rede.addOut(NUMEX, 0, Perceptron.AlgorithmSimoid.LOGISTIC);
        rede.connect();
        Training treino = new Training(false);
        treino.withBackPropagation(rede, samples, 0.005, 600000);

        samples.forEach(sample -> {
            rede.updateIn(sample);
            OutPerceptron[] ret = rede.process();
            StringBuilder sb = new StringBuilder(" ");
            for (OutPerceptron value : ret) {
                sb.append(String.format("%.30f ", value.getOut()));
            }

            System.out.printf("Exemplo in[%s] out esperado [%s] out real [%s]\n", sample.toStringIn(), sample.toStringOut(), sb.toString().trim());
        });

    }

}
