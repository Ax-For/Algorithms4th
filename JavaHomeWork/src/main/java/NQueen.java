import java.util.*;

/** 使用回溯法解决N皇后问题
 * @author dongyafei
 * @date 2021/11/23
 */
public class NQueen {
    private int N;  // N值-皇后数目
    private List<String> results;   // 求解结果
    private int[] columns;  // 皇后在每行的所在列
    private Set<Integer> processedColumn;   // 已确认的皇后所在列
    private Set<Integer> processedDiagLeft;     // 已确认的皇后所在的【左上-右下】对角线
    private Set<Integer> processedDiagRight;    // 已确认的皇后所在的【右上-左下】对角线

    // 初始化
    public NQueen(int n) {
        this.N = n;
        this.results = new ArrayList<>();
        this.columns = new int[n];
        Arrays.fill(columns, -1);
        this.processedColumn = new HashSet<>();
        this.processedDiagLeft = new HashSet<>();
        this.processedDiagRight = new HashSet<>();
        // 逐行确定皇后所在列
        solve(0);
    }

    /**
     * 确定指定行及之后各行皇后所在位置
     * @param row 起始行
     */
    private void solve(int row){
        if (row == this.N){     // 处理完毕，获得一个合法结果，进行保存
            results.add(getMatrixStr());
        }else{
            for (int i = 0; i < this.N; i++) {  // 逐列排除不合法的位置
                if (processedColumn.contains(i)){   // 该列已有皇后
                    continue;
                }
                int diagLeft = row - i;
                if (processedDiagLeft.contains(diagLeft)){  // 该位置所在【左上-右下】对角线已有皇后
                    continue;
                }
                int diagRight = row + i;
                if (processedDiagRight.contains(diagRight)){    // 该位置所在【右上-左下】对角线已有皇后
                    continue;
                }
                // 该位置合法予以保存
                columns[row] = i;
                processedColumn.add(i);
                processedDiagLeft.add(diagLeft);
                processedDiagRight.add(diagRight);
                // 确定下一行的皇后位置
                solve(row + 1);
                // 回溯结果，判断下一列的可行性
                columns[row] = -1;
                processedColumn.remove(i);
                processedDiagLeft.remove(diagLeft);
                processedDiagRight.remove(diagRight);
            }
        }
    }

    // 将每个合法结果以矩阵形式表示 'Q' 代表皇后
    private String getMatrixStr(){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.N; i++) {
            s.append("+---");
        }
        s.append("+\n");
        StringBuilder res = new StringBuilder(s);
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                if (this.columns[i] == j){
                    res.append("| Q ");
                }else{
                    res.append("|   ");
                }
            }
            res.append("|\n").append(s);
        }
        return res.toString();
    }

    // 结果输出
    public void showResult(){
        System.out.println("共有" + results.size() + "个结果");
        for (String result : results) {
            System.out.println(result);
        }
    }

    // 主函数测试结果
    public static void main(String[] args) {
        NQueen nQueen = new NQueen(5);
        nQueen.showResult();
    }
}
