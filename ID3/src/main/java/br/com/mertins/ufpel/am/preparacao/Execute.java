package br.com.mertins.ufpel.am.preparacao;

import br.com.mertins.ufpel.am.id3.Entropy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author mertins
 */
public class Execute {

    public static void main(String[] args) {
        try {
            Sample sample = new Sample(5);
            sample.addDiscardedColumns(0);
            try (FileReader arq = new FileReader("beach.csv")) {
                BufferedReader lerArq = new BufferedReader(arq);
                sample.process(lerArq);
            }

            System.out.println("******");
            sample.getAttributes().stream().forEach((attribute) -> {
                System.out.println(attribute.toString());
                attribute.getAttributesInstance().stream().forEach((attributeInstance) -> {
                    System.out.printf("\t\t%s\n", attributeInstance.toString());
                });

            });
            System.out.println("******");
            Entropy entropy=new Entropy(sample.getRegisters(), sample.getLabels());
            entropy.process();
//            sample.getRotulos().stream().forEach((rotulo) -> {
//                System.out.printf("%s\n", rotulo.toString());
//            });
//            System.out.println("******");
//
//            System.out.println("******");
//            sample.getRegisters().stream().forEach((register) -> {
//                System.out.printf("%d   %s  %s\n", register.getLine(), register.getAttributesInstance().get(0).toString(), register.getLabel().toString());
//            });
//            System.out.println("******");

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

    }
}