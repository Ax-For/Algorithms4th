import java.util.ArrayList;

/**
 * 使用深度优先搜索判断无向图是否被双色染色（二分图）,若可染色，输出染色结果
 * @author dongyafei
 * @date 2021/11/23
 */
public class DFSTwoColor {
    private final int V;    // 顶点数
    private ArrayList<Integer>[] adj;   // 图的邻接矩阵表示
    private boolean[] marked;   // 点是否被搜索过
    private boolean[] color;    // 点的涂色
    private boolean isTwoColorable = true;      // 是否可双色染色

    public DFSTwoColor(int v) {
        this.V = v;
        // 初始化
        this.marked = new boolean[v];
        this.color = new boolean[v];
        this.adj = (ArrayList<Integer>[]) new ArrayList[v];
        for (int i = 0; i < v; i++) {
            this.adj[i] = new ArrayList<>();
        }
    }

    /**
     * 向无向图中添加一条边 (v, w)
     * @param v
     * @param w
     */
    public void addEdge(int v, int w){
        if (!this.adj[v].contains(w)){  // 不存在该边则增加
            this.adj[v].add(w);
            this.adj[w].add(v);
        }
    }

    // 计算图是否可二分
    public void solve(){
        for (int i = 0; i < this.V; i++) {
            if (! this.marked[i]){
                dfs(i);
            }
        }
    }

    // 对顶点v进行深度优先搜索
    public void dfs(int v){
        marked[v] = true;   // 标记为该点已被搜索
        for (Integer w: this.adj[v]){   // 遍历邻接点
            if (!isTwoColorable){
                break;
            }
            if (!this.marked[w]){      // 未被搜索过的点，涂色处理 + 继续搜索
                this.color[w] = !this.color[v];
                dfs(w);
            }else if (this.color[w] == this.color[v]){  // 该点已被涂色且与该点相同
                this.isTwoColorable = false;
            }
        }
    }

    public boolean isTwoColorable(){
        return this.isTwoColorable;
    }

    // 展示涂色结果
    public void showTwoColor(){
        if (isTwoColorable){
            for (int i = 0; i < this.V; i++) {
                if (color[i]){
                    System.out.println(i + " : white");
                }else{
                    System.out.println(i + " : black");
                }
            }
        }
    }

    // 主函数测试执行结果
    public static void main(String[] args) {
        DFSTwoColor dfsTwoColor = new DFSTwoColor(9);
        dfsTwoColor.addEdge(1, 3);
        dfsTwoColor.addEdge(0, 3);
        dfsTwoColor.addEdge(2, 8);
        dfsTwoColor.addEdge(6, 0);
        dfsTwoColor.addEdge(7, 5);
        dfsTwoColor.addEdge(4, 6);
        dfsTwoColor.addEdge(3, 2);
        dfsTwoColor.addEdge(8, 7);

        dfsTwoColor.solve();
        if (dfsTwoColor.isTwoColorable()){
            System.out.println("该图可被两种颜色区分");
            dfsTwoColor.showTwoColor();
        }else {
            System.out.println("该图不可被两种颜色区分");
        }
    }
}
