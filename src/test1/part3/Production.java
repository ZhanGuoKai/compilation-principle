package test1.part3;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 产生式类
 */
public class Production {

    private static final int RG  = 0b11111;     // 正则文法
    private static final int LRG = 0b10111;     // 正则文法（左线性）
    private static final int RRG = 0b1111;      // 正则文法（右线性）
    private static final int CFG = 0b111;       // 上下文无关文法
    private static final int CSG = 0b11;        // 上下文有关文法
    private static final int PSG = 1;           // 短语结构文法
    private static final int NG  = 0;           // 非文法

    private ArrayList<VNSymbol> left;           // 产生式左边的符号
    private ArrayList<Symbol> right;            // 产生式右边的符号

    /**
     * {@code Production} 的无参构造方法。
     */
    public Production() {
        this(new ArrayList<>(), new ArrayList<>());
    }
    /**
     * {@code Production} 的有参构造方法。
     * 
     * @param left  产生式左边的符号。
     * @param right 产生式右边的符号。
     */
    public Production(ArrayList<VNSymbol> left, ArrayList<Symbol> right) {
        this.left = left;
        this.right = right;
    }

    /**
     * 获取非终结符的数量。
     * 
     * @param symbols 符号。
     * @return 非终结符的数量。
     */
    private static int getVNNum(ArrayList<? extends Symbol> symbols) {
        Iterator<? extends Symbol> it = symbols.iterator();
        int num = 0;
        while (it.hasNext()) {
            if (Symbol.isVN(it.next().getSymbol())) ++num;
        }
        return num;
    }

    /**
     * 获取表达式类型。
     * 
     * @return  如果是非文法，返回{@code NG = 0}；
     *          如果是短语结构文法，返回{@code PSG = 1}；
     *          如果是上下文相关文法，返回{@code CSG = 0b11}；
     *          如果是上下文无关文法，返回{@code CFG = 0b111}；
     *          如果是左线性正则文法，返回{@code LGR = 0b10111}；
     *          如果是右线性正则文法，返回{@code RRG = 0b1111}；
     *          如果是正则文法，返回{@code RG = 0b11111}；
     */
    public int getType() {
        if (left.size() < 1 || (left.size() == 1
            && Symbol.isVT(left.get(0).getSymbol())))
            return NG;
        if (left.size() > right.size()) return PSG;
        if (left.size() != 1) return CSG;
        if (getVNNum(right) > 1) return CFG;
        if (right.size() <= 1) return RG;
        if (Symbol.isVN(right.get(0).getSymbol())) return LRG;
        return Symbol.isVN(right.get(right.size() - 1).getSymbol()) ?
                RRG : CFG;
    }

    /**
     * 获取产生式左边的符号。
     * 
     * @return 产生式左边的符号。
     */
    public ArrayList<VNSymbol> getLeft() {
        return left;
    }

    /**
     * 获取产生式右边的符号。
     * 
     * @return 产生式右边的符号。
     */
    public ArrayList<Symbol> getRight() {
        return right;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<? extends Symbol> it = left.iterator();
        while (it.hasNext()) {
            sb.append(it.next().getSymbol());
        }
        sb.append('-').append('>');
        it = right.iterator();
        while (it.hasNext()) {
            sb.append(it.next().getSymbol());
        }
        return sb.toString();
    }

}
