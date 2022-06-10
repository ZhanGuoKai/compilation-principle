package test1.part3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 文法类
 */
public class Grammar {

    private static final int RG  = 0b11111;    // 正则文法
    private static final int LRG = 0b10111;    // 正则文法（左线性）
    private static final int RRG = 0b1111;     // 正则文法（右线性）
    private static final int CFG = 0b111;       // 上下文无关文法
    private static final int CSG = 0b11;        // 上下文有关文法
    private static final int PSG = 1;           // 短语结构文法
    private static final int NG  = 0;           // 非文法

    private VNSymbol start;                     // 开始符号
    private ArrayList<Production> productions;  // 产生式

    /**
     * {@code Grammar} 无参构造
     */
    public Grammar() {
        productions = new ArrayList<>();
    }

    /**
     * {@code Grammar} 的有参构造
     * 
     * @param productions   产生式
     */
    public Grammar(VNSymbol start, ArrayList<Production> productions) {
        this.start = start;
        this.productions = productions;
    }

    /**
     * 从文件读取文法。
     * 
     * @param fileName 文件路径。
     * @return 读取的文法。
     * @throws IOException 如果路径错误或者文件打开错误会抛出 {@code IOException} 异常。
     */
    public static Grammar loadFrom(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        VNSymbol start;
        int next = 0;
        ArrayList<Production> productions = new ArrayList<>();
        while (next != 'G') next = br.read();
        while (next != '[') next = br.read();
        while (!Symbol.isVN((char) next)) next = br.read();
        start = new VNSymbol((char) next);
        while (next != ']') next = br.read();
        read:
        while (next != -1) {
            while (!Symbol.isSymbol((char) next)) {
                if (next == -1) break read;
                next = br.read();
            }
            ArrayList<VNSymbol> left = new ArrayList<>();
            ArrayList<Symbol> right = new ArrayList<>();
            while (Symbol.isSymbol((char) next)) {
                left.add(new VNSymbol((char) next));
                next = br.read();
            }
            while (!Symbol.isSymbol((char) next)) {
                next = br.read();
            }
            while (Symbol.isSymbol((char) next)) {
                right.add(Symbol.isVT((char) next) ? 
                        new VTSymbol((char) next) :
                        new VNSymbol((char) next));
                next = br.read();
            }
            productions.add(new Production(left, right));
        }
        return new Grammar(start, productions);
    }

    /**
     * 判断是否包含开始符号。
     * 
     * @param symbols 
     * @return
     */
    public boolean containStart(ArrayList<? extends Symbol> symbols) {
        Iterator<? extends Symbol> it = symbols.iterator();
        while (it.hasNext()) {
            if (it.next().getSymbol() == start.getSymbol()) return true;
        }
        return false;
    }

    /**
     * 获取文法的类型。
     * 
     * @return  如果是正则文法，则返回 {@code "RG"} ;
     *          如果是上下文无关文法，则返回 {@code "CFG"} ;
     *          如果是上下文有关文法，则返回 {@code "CSG"} ;
     *          如果是短语结构文法，则返回 {@code "PSG"} ;
     */
    public String getGrammarType() {
        int type = RG;
        Iterator<Production> it = productions.iterator();
        Production pro;
        while (it.hasNext()) {
            pro = it.next();
            type &= pro.getType();
            if (type == NG) return "NG";
            if (type > PSG && containStart(pro.getRight())) type = PSG;
        }
        switch (type) {
            case RG:
            case LRG:
            case RRG:
                return "RG";
            case CFG:
                return "CFG";
            case CSG:
                return "CSG";
            case PSG:
                return "PSG";
        }
        return "NG";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Production> it = productions.iterator();
        while (it.hasNext()) {
            sb.append(it.next()).append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            System.out.println(Grammar.loadFrom("./src/test1/RG1.txt").getGrammarType());
            System.out.println(Grammar.loadFrom("./src/test1/RG2.txt").getGrammarType());
            System.out.println(Grammar.loadFrom("./src/test1/CFG1.txt").getGrammarType());
            System.out.println(Grammar.loadFrom("./src/test1/CFG2.txt").getGrammarType());
            System.out.println(Grammar.loadFrom("./src/test1/CSG.txt").getGrammarType());
            System.out.println(Grammar.loadFrom("./src/test1/PSG1.txt").getGrammarType());
            System.out.println(Grammar.loadFrom("./src/test1/PSG2.txt").getGrammarType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
