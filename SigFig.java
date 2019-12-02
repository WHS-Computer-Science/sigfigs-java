import java.util.Arrays;

public class SigFig {
    private String n;
    private int l;
    private char[] sf;
    private int totalSF;
    private Integer[] rep;

    public SigFig(String value) {
        this.n = value;
        this.l = value.length();
        this.sf = sigFigger();
        this.totalSF = sigFigs();
        this.rep = reposition();
    }

    public SigFig(String value, char[] override) {
        this.n = value;
        this.l = value.length();
        this.sf = override;
        this.totalSF = sigFigs();
        this.rep = reposition();
    }

    private boolean isIn(char x, char[] y) {
        for (char i : y) {
            if (x == i) {
                return true;
            }
        }
        return false;
    }

    private boolean isIn(char[] x, char[] y) {
        for (char i : x) {
            for (char j : y) {
                if (i == j) {
                    return true;
                }
            }
        }
        return false;
    }

    private int findIn(char x, char[] y) {
        for (int i = 0; i < y.length; i++) {
            if (x == y[i]) return i;
        }
        return -1;
    }

    private int findIn(int x, Integer[] y) {
        for (int i = 0; i < y.length; i++) {
            if ((Integer) x == y[i]) {
                return i;
            }
        }
        return -1;
    }

    private char[] sigFigger() {
        char[] natNums = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] dNums = new char[]{'.', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] output = new char[l];
        for (int i = 0; i < l; i++) {
            char v = n.charAt(i);
            char[] preN = n.substring(i + 1).toCharArray();
            char[] postN = n.substring(0, i).toCharArray();
            if (isIn(v, natNums)) output[i] = 's';
            else if (v == '0' && isIn(preN, natNums) && isIn(postN, dNums)) output[i] = 's';
            else if (v == '0' && isIn('.', preN) && !isIn(postN, natNums)) output[i] = 's';
            else if (v == '.') output[i] = 'd';
            else output[i] = 'n';
        }
        return output;
    }

    private int sigFigs() {
        int count = 0;
        for (char x : sf) {
            if (x == 's') count++;
        }
        return count;
    }

    private char[] decimalize() {
        char[] output;
        if (isIn('d', sf)) {
            return sf;
        } else {
            output = Arrays.copyOf(sf, l + 1);
            output[output.length - 1] = 'd';
        }
        return output;
    }

    private char[] decimalize(char[] sf) {
        char[] output;
        if (isIn('.', sf)) {
            return sf;
        } else {
            output = Arrays.copyOf(sf, sf.length + 1);
            output[output.length - 1] = '.';
        }
        return output;
    }

    private Integer[] reposition() {
        char[] d = decimalize();
        Integer[] output = new Integer[l];
        int dP = findIn('d', d);
        for (int i = 0; i < l; i++) {
            char v = d[i];
            if (v == 'd') output[i] = null;
            else if (i < dP) output[i] = (dP - (i + 1));
            else output[i] = (dP - i);
        }
        return output;
    }

    private Integer[] reposition(String sf) {
        char[] d = decimalize(sf.toCharArray());
        Integer[] output = new Integer[sf.length()];
        int dP = findIn('.', d);
        for (int i = 0; i < sf.length(); i++) {
            char v = d[i];
            if (v == '.') output[i] = null;
            else if (i < dP) output[i] = (dP - (i + 1));
            else output[i] = (dP - i);
        }
        return output;
    }

    private char[] removeDecimal(char[] y){
        if(findIn('.', y) == -1 && findIn('d', y) == -1){
            return y;
        } else{
            char[] output = new char[y.length - 1];
            int count = 0;
            for(char i : y){
                if(i != '.' && i!= 'd'){
                    output[count] = i;
                }
                count++;
            }
            return output;
        }
    }
