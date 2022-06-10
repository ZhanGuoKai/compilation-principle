package test1.part3;

/**
 * 非终止符类
 */
public class VNSymbol extends Symbol {

    /**
     * {@code VNSymbol} 构造方法。
     * 
     * @param symbol 符号。
     */
    public VNSymbol(char symbol) {
        super(symbol);
    }

    @Override
    public boolean isVT() {
        return false;
    }

}
