import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class homework6 {
    private Frame f; 
    private FlowLayout my_layout;
    private JTextField tf;   //编辑框组件
    private JButton bt[];   //按钮组件
    private int num1,num2,result,mode=0;
    private String input="",output="";

    class event_window extends WindowAdapter {    //窗口关闭事件监听
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    class event_action implements ActionListener {  //按钮点击事件监听
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            switch(s) {
            case "0": tf.setText(input+="0"); break;
            case "1": tf.setText(input+="1"); break;
            case "2": tf.setText(input+="2"); break;
            case "3": tf.setText(input+="3"); break;
            case "4": tf.setText(input+="4"); break;
            case "5": tf.setText(input+="5"); break;
            case "6": tf.setText(input+="6"); break;
            case "7": tf.setText(input+="7"); break;
            case "8": tf.setText(input+="8"); break;
            case "9": tf.setText(input+="9"); break;
            case "+": {
                if(input!=""&&mode!=0&&input.charAt(input.length()-2)==mode){
                    int n = input.length() - 3;
                    input = input.substring(0, n);
                    mode='+';
                    tf.setText(input+=" + ");
                }
                else {
                    num1=Integer.valueOf(input);
                    mode='+';
                    tf.setText(input+=" + "); 
                }
                break;
            }
            case "-": {
                mode=0;
                if(input==""&&mode==0&&output==""){  //输入的第一个整数为负数
                    tf.setText(input+="-");
                }
                else if(input!=""&&mode!=0&&input.charAt(input.length()-2)==mode){ //输入的第二个数字为负数
                    tf.setText(input+="-");
                }
                else if(input!=""&&output==""){  //正在输入计算符号
                    num1=Integer.valueOf(input);
                    mode='-';
                    tf.setText(input+=" - ");  
                    break;
                }
            }
            case "*": {
                if(input!=""&&mode!=0&&input.charAt(input.length()-2)==mode){
                    int n = input.length() - 3;
                    input = input.substring(0, n);
                    mode='*';
                    tf.setText(input+=" * ");
                }
                else {
                    num1=Integer.valueOf(input);
                    mode='*';
                    tf.setText(input+=" * "); 
                }
                break;
            }
            case "/": {
                if(input!=""&&mode!=0&&input.charAt(input.length()-2)==mode) {
                    int n = input.length() - 3;
                    input = input.substring(0, n);
                    mode='/';
                    tf.setText(input+=" / ");
                }
                else {
                    num1=Integer.valueOf(input);
                    mode='/';
                    tf.setText(input+=" / "); 
                }
                break;
            }
            case "Del": {
                if(input==""&&output==""&&mode!=0) { //已经计算过结果则清空整个输出框
                    input="";
                    output="";
                    mode=0;   //运算符清空
                    tf.setText("0");
                }
                else {
                    int n = input.length() - 1;
                    input = input.substring(0, n);
                    tf.setText(input); 
                }
                break;
            }
            case "C": {
                tf.setText("0");
                input="";
                output="";
                mode=0;         //运算符清空
                break;
            }
            case "=": {
                if(input==""&&output==""&&mode!=0){  //已经计算过一次，这是再次按下=
                    switch(mode){
                    case '+': output = Integer.toString(result+=num2); break;
                    case '-': output = Integer.toString(result-=num2); break;
                    case '*': output = Integer.toString(result*=num2); break;
                    case '/': output = Integer.toString(result/=num2); break;
                    default: break;
                    }
                }
                else if(input!=""&&output==""&&mode!=0){  //否则进行第一次计算
                    int n = input.indexOf(mode);
                    num2 = Integer.valueOf(input.substring(n+2));
                    switch(mode){
                    case '+': output = Integer.toString(result=num1+num2); break;
                    case '-': output = Integer.toString(result=num1-num2); break;
                    case '*': output = Integer.toString(result=num1*num2); break;
                    case '/': output = Integer.toString(result=num1/num2); break;
                    default: break;
                    }
                }
                tf.setText(output);
                input="";
                output="";
                break;
            }
            default: break;
            }
        }
    }

    public void init() {      //初始化整个窗口
        //容器和布局定义
        f= new Frame("计算器");
        my_layout = new FlowLayout(FlowLayout.LEFT,20,10);

        //组件初始化
        tf=new JTextField(13);
        tf.setFont(new Font("TIMESNEWROMAN",Font.BOLD,30));
        tf.setText("0");
        
        bt = new JButton[17];
        bt[0]=new JButton("C");
        bt[1]=new JButton("Del");
        bt[2]=new JButton("="); 
        bt[3]=new JButton("+");
        bt[4]=new JButton("1");
        bt[5]=new JButton("2"); 
        bt[6]=new JButton("3");
        bt[7]=new JButton("-");
        bt[8]=new JButton("4");
        bt[9]=new JButton("5");
        bt[10]=new JButton("6");
        bt[11]=new JButton("*");
        bt[12]=new JButton("7");
        bt[13]=new JButton("8");
        bt[14]=new JButton("9");
        bt[15]=new JButton("/");
        bt[16]=new JButton("0");
        
        for(int i=0;i<17;i++){   //设置按钮样式
            bt[i].setFont(new Font("TIMESNEWROMAN",Font.BOLD,20));
            bt[i].setPreferredSize(new Dimension(70,50));
        }

        //设置布局、添加组件
        f.setLayout(my_layout);
        f.add(tf,"North"); 
        for(int i=0;i<17;i++){
            f.add(bt[i]);
        }

        //添加事件监听
        event_window e_w = new event_window();
        event_action e_c = new event_action();
        f.addWindowListener(e_w);
        for(int i=0;i<17;i++) {
            bt[i].addActionListener(e_c);
        }
    }

    public void display() {   //显示窗口
        f.setSize(400,400);
        f.setLocation(700,200);
        f.setVisible(true);
        f.setResizable(false);
    }

    public static void main(String args[]){
        homework6 a = new homework6();
        a.init();
        a.display();
    }
}