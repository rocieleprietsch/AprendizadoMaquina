package br.com.mertins.ufpel.am.bayes;

import com.sun.media.sound.InvalidFormatException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

/**
 * http://www.programcreek.com/2012/05/opennlp-tutorial/
 *
 * @author mertins
 */
public class Execute {

    public static void SentenceDetect() throws InvalidFormatException, IOException {
        String paragraph = "Hi. How are you? This is Mike.";

        // always start with a model, a model is learned from training data
        InputStream is = new FileInputStream("src/main/resource/en-sent.bin");
        SentenceModel model = new SentenceModel(is);
        SentenceDetectorME sdetector = new SentenceDetectorME(model);

        String sentences[] = sdetector.sentDetect(paragraph);

        System.out.println(sentences[0]);
        System.out.println(sentences[1]);
        is.close();
    }

    public static void Tokenize() throws InvalidFormatException, IOException {
        InputStream is = new FileInputStream("src/main/resource/en-token.bin");

        TokenizerModel model = new TokenizerModel(is);

        Tokenizer tokenizer = new TokenizerME(model);

        String tokens[] = tokenizer.tokenize("Hi. How are you? This is Mike.");

        for (String a : tokens) {
            System.out.println(a);
        }

        is.close();
    }

    public static void findName() throws IOException {
        InputStream is = new FileInputStream("src/main/resource/en-ner-person.bin");
        TokenNameFinderModel model = new TokenNameFinderModel(is);
        is.close();
        NameFinderME nameFinder = new NameFinderME(model);
        String[] sentence = new String[]{
            "Mike",
            "Smith",
            "is",
            "a",
            "good",
            "person"
        };
        Span nameSpans[] = nameFinder.find(sentence);
        for (Span s : nameSpans) {
            System.out.println(s.toString());
        }
    }

    public static void POSTag() throws IOException {
//        POSModel model = new POSModelLoader()
//                .load(new File("src/main/resource/en-pos-maxent.bin"));
//        PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
//        POSTaggerME tagger = new POSTaggerME(model);
//
//        String input = "Hi. How are you? This is Mike.";
//        ObjectStream<String> lineStream = new PlainTextByLineStream(
//                new StringReader(input));
//
//        perfMon.start();
//        String line;
//        while ((line = lineStream.read()) != null) {
//
//            String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
//                    .tokenize(line);
//            String[] tags = tagger.tag(whitespaceTokenizerLine);
//
//            POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
//            System.out.println(sample.toString());
//
//            perfMon.incrementCounter();
//        }
//        perfMon.stopAndPrintFinalResult();
    }

    public static void chunk() throws IOException {
//        POSModel model = new POSModelLoader()
//                .load(new File("src/main/resource/en-pos-maxent.bin"));
//        PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
//        POSTaggerME tagger = new POSTaggerME(model);
//
//        String input = "Hi. How are you? This is Mike.";
//        ObjectStream<String> lineStream = new PlainTextByLineStream(
//                new StringReader(input));
//
//        perfMon.start();
//        String line;
//        String whitespaceTokenizerLine[] = null;
//
//        String[] tags = null;
//        while ((line = lineStream.read()) != null) {
//            whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE
//                    .tokenize(line);
//            tags = tagger.tag(whitespaceTokenizerLine);
//
//            POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
//            System.out.println(sample.toString());
//            perfMon.incrementCounter();
//        }
//        perfMon.stopAndPrintFinalResult();
//        // chunker
//        InputStream is = new FileInputStream("src/main/resource/en-chunker.bin");
//        ChunkerModel cModel = new ChunkerModel(is);
//        ChunkerME chunkerME = new ChunkerME(cModel);
//        String result[] = chunkerME.chunk(whitespaceTokenizerLine, tags);
//        for (String s : result) {
//            System.out.println(s);
//        }
//        Span[] span = chunkerME.chunkAsSpans(whitespaceTokenizerLine, tags);
//        for (Span s : span) {
//            System.out.println(s.toString());
//        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("*****");
        Execute.SentenceDetect();
        System.out.println("---");
        Execute.Tokenize();
        System.out.println("---");
        Execute.findName();
        System.out.println("---");
//        Execute.POSTag();
//        System.out.println("---");
//        Execute.chunk();
    }
}
