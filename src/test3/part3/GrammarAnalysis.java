package test3.part3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import test3.part1.VNSymbol;
import test3.part1.GrammarTree;
import test3.part1.Production;

public class GrammarAnalysis {

    private VNProcedure procedure;
    private Collection<VNProcedure> procedures;

    private GrammarAnalysis() {
        procedure = new VNProcedure("");
    }

    public String test(String input) {
        List<Character> list = stringToCharacters(input);
        list.add('#');
        String result = procedure.test(list.listIterator());
        return result.equals("") ? "运算不合法" : result;
    }

    public static GrammarAnalysis loadFromFile(String filename) throws IOException {
        GrammarAnalysis ga = getAnalysis(GrammarTree.loadFromFile(filename));
        ga.writeMD(filename + "-analysis.md");
        return ga;
    }

    public static GrammarAnalysis getAnalysis(GrammarTree tree) {
        GrammarAnalysis analysis = new GrammarAnalysis();
        Map<String, Production> productions = tree.getProductions();
        Map<String, VNProcedure> procedures = new HashMap<>();
        tree.getProductions().keySet().forEach(vn -> {
            procedures.put(vn, new VNProcedure(vn));
        });
        tree.getProductions().forEach((vn, production) -> {
            VNProcedure procedure = procedures.get(vn);
            productions.get(vn).getRight().forEach(list -> {
                List<Procedure> matchList = new ArrayList<>();
                list.forEach(symbol -> {
                    if (symbol instanceof VNSymbol) {
                        VNProcedure vnProcedure = procedures.get(symbol.getSymbol());
                        matchList.add(vnProcedure);
                    } else {
                        char symbolChar = symbol.getSymbol().charAt(0);
                        if (symbolChar == 'ε') matchList.add(EpsilonMatchFunction.getInstance());
                        else matchList.add(new VTProcedure(symbolChar));
                    }
                });
                procedure.add(matchList);
            });
        });
        analysis.procedure = procedures.get(tree.getRoot().getSymbol());
        Set<Character> follow = new HashSet<>();
        follow.add('#');
        analysis.procedure.getFirst();
        analysis.procedure.setFollow(follow);
        analysis.procedures = procedures.values();
        analysis.procedures.forEach(pro -> pro.setEpsilon());
        return analysis;
    }

    private List<Character> stringToCharacters(String s) {
        return s.chars().mapToObj(ch -> (char) ch).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        Set<Character> symbols = getSymbols();
        StringBuilder sb = new StringBuilder("|语法变量|");
        symbols.forEach(ch -> sb.append(ch).append('|'));
        sb.append("\n|:-:|");
        int n = symbols.size();
        for (int i = 0; i < n; i++) {
            sb.append(":-:|");
        }
        sb.append('\n');
        procedures.forEach(pro -> {
            String symbol = pro.getSymbol();
            sb.append('|').append(symbol).append('|');
            Map<Character, List<Procedure>> table = pro.getAnalysisTable();
            symbols.forEach(ch -> {
                List<Procedure> list = table.get(ch);
                if (list != null) {
                    sb.append(symbol).append("→");
                    list.forEach(p -> sb.append(p.getSymbol()));
                }
                sb.append('|');
            });
            sb.append('\n');
        });
        return sb.toString();
    }

    public void writeMD(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename, Charset.forName("UTF-8")));
        bw.write(this.toString());
        bw.close();
    }

    public Set<Character> getSymbols() {
        Set<Character> set = new HashSet<>();
        procedures.forEach(pro -> {
            set.addAll(pro.getFirst());
            set.addAll(pro.getFollow());
        });
        set.remove('ε');
        return set;
    }

    public static void main(String[] args) {
        String[] filenames = {
            "src/test3/grammar1.txt",
            "src/test3/grammar2.txt"
        };
        String[] tests = {
            "i+i*i",
            "i+i*i*(i*(i+i))",
            "i+i*i/(i-i/i)+i/i*(i/i+(i-i))",
            "i+-i",
            "i+(i-i"
        };
        for (String filename : filenames) {
            try {
                GrammarAnalysis analysis = loadFromFile(filename);
                for (String test : tests) {
                    System.out.println(analysis.test(test));
                }
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}