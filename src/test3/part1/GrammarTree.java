package test3.part1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrammarTree {

    private static final Pattern PRODUCTION_PATTERN =
                    Pattern.compile("([A-Z']+)\\s*->\\s*([a-zA-Z+\\-*/\\(\\)']+)");
    private static final Pattern SYMBOL_PATTERN =
                    Pattern.compile("epsilon|[a-zA-Z]'?|\\+|-|\\*|/|\\(|\\)");

    private VNSymbol root;
    private Map<String, Production> productions;

    public GrammarTree(String root) {
        this.root = new VNSymbol(root);
        productions = new HashMap<>();
    }

    public VNSymbol getRoot() {
        return root;
    }

    public Map<String, Production> getProductions() {
        return productions;
    }

    public static GrammarTree loadFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename + "-tree.md",
                                Charset.forName("UTF-8")));
        String line, vn, right, singleSymbol;
        line = br.readLine();;
        if (line == null) {
            br.close();
            bw.close();
            return null;
        }
        Matcher productionMatcher, symbolMatcher;
        symbolMatcher = SYMBOL_PATTERN.matcher(line);
        symbolMatcher.find();
        GrammarTree tree = new GrammarTree(symbolMatcher.group(0));
        Stack<VNSymbol> stack = new Stack<>();
        Stack<VNSymbol> symbols = new Stack<>();
        Production production;
        List<Symbol> rightSymbols;
        VNSymbol left;
        Symbol symbol;
        left = tree.root;
        stack.add(left);
        bw.write("```mermaid\ngraph TB\n");
        bw.write(left.getMDString());
        read:
        while (line != null) {
            productionMatcher = PRODUCTION_PATTERN.matcher(line);
            if (!productionMatcher.find()) continue;
            if (stack.empty()) break;
            vn = productionMatcher.group(1);
            right = productionMatcher.group(2);
            while (!(left = stack.pop()).getSymbol().equals(vn)) {
                if (stack.empty()) break read;
            }
            production = tree.productions.get(vn);
            if (production == null) {
                production = new Production(left);
            }
            rightSymbols = new ArrayList<>();
            symbolMatcher = SYMBOL_PATTERN.matcher(right);
            while (symbolMatcher.find()) {
                singleSymbol = symbolMatcher.group(0);
                symbol = Symbol.getSymbol(singleSymbol);
                bw.write(symbol.getMDString());
                left.addSymbol(symbol);
                rightSymbols.add(symbol);
                bw.write(left.getId() + " --> " + symbol.getId() + "\n");
                if (symbol instanceof VNSymbol) {
                    symbols.add((VNSymbol) symbol);
                }
            }
            production.add(rightSymbols);
            tree.productions.put(vn, production);
            while (!symbols.empty()) stack.add(symbols.pop());
            line = br.readLine();
        }
        bw.write("```\n");
        br.close();
        bw.close();
        return tree;
    }

    public static void main(String[] args) {
        String[] filenames = {
            "src/test3/part1/test/test1.txt",
            "src/test3/grammar1.txt",
            "src/test3/grammar2.txt"
        };
        for (String filename : filenames) {
            try {
                GrammarTree tree = loadFromFile(filename);
                System.out.println(tree.root);
                System.out.println(tree.root.getLeafString());
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}