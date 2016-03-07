package com.example.calculator;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity 
{
	//0~9
	private Button[]btn=new Button[10];
	//显示器
	private EditText input;
	//记忆器
	private TextView mem;
	private TextView _drg;
	private TextView tip;
	private Button div,mul,sub,add,equal,
	               sin,cos,tan,log,ln,
	               sqrt,square,factorial,bksp,
	               left,right,dot,exit,drg,
	               mc,c;
	public String str_old,str_new;
	public boolean vbegin=true;
	public boolean drg_flag=true;
	public double pi=4*Math.atan(1);
	public boolean tip_lock=true;
	public boolean equals_flag=true;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		input=(EditText)findViewById(R.id.input);
		mem=(TextView)findViewById(R.id.mem);
		_drg=(TextView)findViewById(R.id._drg);
		tip=(TextView)findViewById(R.id.tip);
		mc=(Button)findViewById(R.id.mc);
		c=(Button)findViewById(R.id.c);
		drg=(Button)findViewById(R.id.drg);
		sin=(Button)findViewById(R.id.sin);
		cos=(Button)findViewById(R.id.cos);
		tan=(Button)findViewById(R.id.tan);
		factorial=(Button)findViewById(R.id.factorial);
		bksp=(Button)findViewById(R.id.bksp);
		btn[0]=(Button)findViewById(R.id.zero);
		btn[1]=(Button)findViewById(R.id.one);
		btn[2]=(Button)findViewById(R.id.two);
		btn[3]=(Button)findViewById(R.id.three);
		btn[4]=(Button)findViewById(R.id.four);
		btn[5]=(Button)findViewById(R.id.five);
		btn[6]=(Button)findViewById(R.id.six);
		btn[7]=(Button)findViewById(R.id.seven);
		btn[8]=(Button)findViewById(R.id.eight);
		btn[9]=(Button)findViewById(R.id.nine);
		add=(Button)findViewById(R.id.add);
		sub=(Button)findViewById(R.id.sub);
		mul=(Button)findViewById(R.id.mul);
		div=(Button)findViewById(R.id.div);
		left=(Button)findViewById(R.id.left);
		right=(Button)findViewById(R.id.right);
		sqrt=(Button)findViewById(R.id.sqrt);
		log=(Button)findViewById(R.id.log);
		ln=(Button)findViewById(R.id.ln);
		dot=(Button)findViewById(R.id.dot);
		equal=(Button)findViewById(R.id.equal);
		exit=(Button)findViewById(R.id.exit);
		
		
		for(int i=0;i<10;i++){
			btn[i].setOnClickListener(actionPerformed);
		}
		div.setOnClickListener(actionPerformed);
		mul.setOnClickListener(actionPerformed);
		sub.setOnClickListener(actionPerformed);
		add.setOnClickListener(actionPerformed);
		equal.setOnClickListener(actionPerformed);
		left.setOnClickListener(actionPerformed);
		right.setOnClickListener(actionPerformed);
		sqrt.setOnClickListener(actionPerformed);
		log.setOnClickListener(actionPerformed);
		ln.setOnClickListener(actionPerformed);
		dot.setOnClickListener(actionPerformed);
		exit.setOnClickListener(actionPerformed);
		input.setOnClickListener(actionPerformed);
		_drg.setOnClickListener(actionPerformed);
		mem.setOnClickListener(actionPerformed);
		mc.setOnClickListener(actionPerformed);
		c.setOnClickListener(actionPerformed);
		drg.setOnClickListener(actionPerformed);
		sin.setOnClickListener(actionPerformed);
		cos.setOnClickListener(actionPerformed);
		tan.setOnClickListener(actionPerformed);
		factorial.setOnClickListener(actionPerformed);
		bksp.setOnClickListener(actionPerformed);
	}
	
	String[]Tipcommand=new String[500];
	int tip_i=0;
	private OnClickListener actionPerformed=new OnClickListener() {
		
		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub
			String command=((Button)v).getText().toString();
			String str=input.getText().toString();
			if((equals_flag==false)&&"0123456789.()sincostanlnlogn!+-*/√^".indexOf(command)!=-1){
				if(right(str)){
					if("+-*/√^".indexOf(command)!=-1){
						for(int i=0;i<str.length();i++){
							Tipcommand[tip_i]=String.valueOf(str.charAt(i));
							tip_i++;
						}
						vbegin=false;
					}
				}else {
					input.setText("0");
					vbegin=true;
					tip_i=0;
					tip_lock=true;
				}
				equals_flag=true;
			}
			if(tip_i>0){
				TipChecker(Tipcommand[tip_i-1],command);
			}else if(tip_i==0){
				TipChecker("#", command);
			}
			if("0123456789.()sincostanlnlogn!+-*/√^".indexOf(command)!=-1&&tip_lock){
				Tipcommand[tip_i]=command;
				tip_i++;
			}
			if("0123456789.()sincostanlnlogn!+-*/√^".indexOf(command)!=-1&&tip_lock){
				print(command);
			}else if(command.compareTo("DRG")==0&&tip_lock){
				if(drg_flag=true){
					drg_flag=false;
					_drg.setText(" RAD");
				}else {
					drg_flag=true;
					_drg.setText(" DEG");
				}
			}else if(command.compareTo("Bksp")==0&&equals_flag){
				if(TTO(str)==3){
					if(str.length()>3){
						input.setText(str.substring(0,str.length()-3));
					}else if(str.length()==3){
						input.setText("0");
						vbegin=true;
						tip_i=0;
					}
				}else if(TTO(str)==2){
					if(str.length()>2){
						input.setText(str.substring(0,str.length()-2));
					}else if(str.length()==2){
						input.setText("0");
						vbegin=true;
						tip_i=0;
					}
				}else if(TTO(str)==1){
					if(right(str)){
						if(str.length()>1){
							input.setText(str.substring(0,str.length()-1));
						}else if(str.length()==1){
							input.setText("0");
							vbegin=true;
							tip_i=0;
						}
					}else{
						input.setText("0");
						vbegin=true;
						tip_i=0;
					}
				}
			}else if(command.compareTo("Bksp")==0&&equals_flag==false){
				input.setText("0");
				vbegin=true;
				tip_i=0;
				tip_lock=true;
			}else if(command.compareTo("C")==0){
				input.setText("0");
				vbegin=true;
				tip_i=0;
				tip_lock=true;
				equals_flag=true;
			}else if(command.compareTo("MC")==0){
				mem.setText("0");
			}else if(command.compareTo("exit")==0){
				System.exit(0);
			}else if(command.compareTo("=")==0&&equals_flag&&tip_lock&&right(str)){
				tip_i=0;
				tip_lock=false;
				equals_flag=false;
				str_old=str;
				str=str.replaceAll("sin", "s");
				str=str.replaceAll("cos", "c");
				str=str.replaceAll("tan", "t");
				str=str.replaceAll("log", "g");
				str=str.replaceAll("ln", "l");
				str=str.replaceAll("n!", "!");
				vbegin=true;
				str_new=str.replaceAll("-", "-1*");
				new calc().process(str_new);
			}
			tip_lock=true;
		}
	};
	
	
	private void print(String str){
		if(vbegin)
			input.setText(str);
		else 
			input.append(str);
		vbegin=false;
	}
	
	
	private boolean right(String str){
		int i=0;
		for(i=0;i<str.length();i++){
			if((str.charAt(i)!='0')&&(str.charAt(i)!='1')&&(str.charAt(i)!='2')&&(str.charAt(i)!='3')
					&&(str.charAt(i)!='4')&&(str.charAt(i)!='5')&&(str.charAt(i)!='6')&&(str.charAt(i)!='7')
					&&(str.charAt(i)!='8')&&str.charAt(i)!='9'&&str.charAt(i)!='+'&&str.charAt(i)!='-'&&str.charAt(i)!='*'
					&&str.charAt(i)!='/'&&str.charAt(i)!='('&&str.charAt(i)!=')'&&str.charAt(i)!='^'&&str.charAt(i)!='√'
					&&str.charAt(i)!='s'&&str.charAt(i)!='i'&&str.charAt(i)!='n'&&str.charAt(i)!='c'&&str.charAt(i)!='o'
					&&str.charAt(i)!='t'&&str.charAt(i)!='a'&&str.charAt(i)!='l'&&str.charAt(i)!='g'&&str.charAt(i)!='!'){
				break;
			}
		}
		if(i==str.length())return true;
		else return false;
	}
	
	
	private int TTO(String str){
		if((str.charAt(str.length()-3)=='s'&&str.charAt(str.length()-2)=='i'&&str.charAt(str.length()-1)=='n')
				||(str.charAt(str.length()-3)=='c'&&str.charAt(str.length()-2)=='o'&&str.charAt(str.length()-1)=='s')
				||(str.charAt(str.length()-3)=='t'&&str.charAt(str.length()-2)=='a'&&str.charAt(str.length()-1)=='n')
				||(str.charAt(str.length()-3)=='l'&&str.charAt(str.length()-2)=='o'&&str.charAt(str.length()-1)=='g')){
			return 3;
		}
		if((str.charAt(str.length()-2)=='l'&&str.charAt(str.length()-1)=='n')||(str.charAt(str.length()-2)=='n'&&str.charAt(str.length()-1)=='!')){
			return 2;
		}
		return 1;
	}
	
	
	private void TipChecker(String tipcommand1,String tipcommand2){
		int tipcode1=0,tipcode2=0;
		int tiptype1=0,tiptype2=0;
		int bracket=0;
		if(tipcommand1.compareTo("#")==0&&(tipcommand2.compareTo("+")==0||tipcommand2.compareTo("*")==0)||tipcommand2.compareTo("/")==0
	                               ||tipcommand2.compareTo("(")==0||tipcommand2.compareTo("^")==0){
			tipcode1=-1;
		}
		else if(tipcommand1.compareTo("#")!=0){
			if(tipcommand1.compareTo("(")==0){
				tiptype1=1;
			}
			else if(tipcommand1.compareTo(")")==0){
				tiptype1=2;
			}else if(tipcommand1.compareTo(".")==0){
				tiptype1=3;
			}else if("0123456789".indexOf(tipcommand1)!=-1){
				tiptype1=4;
			}else if("+-*/".indexOf(tipcommand1)!=-1){
				tiptype1=5;
			}else if("√^".indexOf(tipcommand1)!=-1){
				tiptype1=6;
			}else if("sincostanlnlogn!".indexOf(tipcommand1)!=-1){
				tiptype1=7;
			}
			
			if(tipcommand2.compareTo("(")==0){
				tiptype2=1;
			}
			else if(tipcommand2.compareTo(")")==0){
				tiptype2=2;
			}else if(tipcommand2.compareTo(".")==0){
				tiptype2=3;
			}else if("0123456789".indexOf(tipcommand2)!=-1){
				tiptype2=4;
			}else if("+-*/".indexOf(tipcommand2)!=-1){
				tiptype2=5;
			}else if("√^".indexOf(tipcommand2)!=-1){
				tiptype2=6;
			}else if("sincostanlnlogn!".indexOf(tipcommand2)!=-1){
				tiptype2=7;
			}
			
			
			switch(tiptype1){
				case 1:
					if(tiptype2==2||(tiptype2==5&&tipcommand2.compareTo("-")!=0)||tiptype2==6)
						tipcode1=1;break;
				
			}
		}
	}
	public class calc{
		public calc(){
			
		};
		final int MAXLEN=500;
		public void process(String str){
			int weightplius=0,topop=0,topnum=0,flag=1,weighttemp=0;
			int weight[];
			double number[];char ch,ch_gai,operator[];
			String num;
			weight=new int[MAXLEN];
			number=new double[MAXLEN];
			operator=new char[MAXLEN];
			String expression=str;
			StringTokenizer token=new StringTokenizer(expression,"+-*/sctlg!^√");
			int i=0;
			while(i<expression.length()){
				ch=expression.charAt(i);
				if(i==0){
					if(ch=='-')
						flag=-1;
				}else {
					if(expression.charAt(i-1)=='('&&ch=='-'){
						flag=-1;
					}
				}
				if(ch<='9'&&ch>='0'||ch=='.'||ch=='E'){
					num=token.nextToken();
					ch_gai=ch;
					while(i<expression.length()&&(ch_gai<='9'&&ch_gai>='0'||ch_gai=='.'||ch_gai=='E')){
						ch_gai=expression.charAt(i++);
					}
					if(i>=expression.length())i-=1;
					else i-=2;
					if(num.compareTo(".")==0)number[topnum++]=0;
					else {
						number[topnum++]=Double.parseDouble(num)*flag;
						flag=1;
					}
				}
				if(ch=='(')weightplius+=4;
				if(ch==')')weightplius-=4;
				if(ch=='-'&&flag==1||ch=='+'||ch=='*'||ch=='/'||ch=='s'||ch=='c'||ch=='t'||ch=='l'||ch=='g'
						||ch=='√'||ch=='^'||ch=='!'){
					switch(ch){
						case '+':
						case '-':
							weighttemp=1+weightplius;
							break;
						case '*':
						case '/':
							weighttemp=2+weightplius;
							break;
						case's':
						case'c':
						case't':
						case'g':
						case'l':
						case'!':
							weighttemp=3+weightplius;
							break;
						case'^':
						case'√':
							default:
								weighttemp=4+weightplius;
								break;
					}
					if(topop==0||weight[topop-1]<weighttemp){
						weight[topop]=weighttemp;
						operator[topop]=ch;
						topop++;
					}else{
						while(topop>0&&weight[topop-1]>=weighttemp){
							switch(operator[topop-1]){
								case'+':
									number[topnum-2]+=number[topnum-1];
									break;
								case'-':
									number[topnum-2]-=number[topnum-1];
									break;
								case'*':
									number[topnum-2]*=number[topnum-1];
									break;
								case'/':
									if(number[topnum-1]==0){
										showerrors(1, "str_old");
										return;
									}
									number[topnum-2]/=number[topnum-1];
									break;
								case'√':
									if(number[topnum-1]==0||(number[topnum-2]<0&&number[topnum-1]%2==0)){
										showerrors(2, str_old);
									}
									number[topnum-2]=Math.pow(number[topnum-2], 1/number[topnum-1]);
									break;
								case'^':
									number[topnum-2]=Math.pow(number[topnum-2], number[topnum-1]);
									break;
								case's':
									if(drg_flag==true){
										number[topnum-1]=Math.sin((number[topnum-1]/180)*pi);
									}else{
										number[topnum-1]=Math.sin(number[topnum-1]);
									}
									topnum++;
									break;
								case'c':
									if(drg_flag==true){
										number[topnum-1]=Math.cos((number[topnum-1]/180)*pi);
									}else{
										number[topnum-1]=Math.cos(number[topnum-1]);
									}
									topnum++;
									break;
								case't':
									if(drg_flag==true){
										if((Math.abs(number[topnum-1])/90)%2==1){
											showerrors(2, str_old);
											return;
										}
										number[topnum-1]=Math.tan((number[topnum-1]/180)*pi);
									}else{
										if((Math.abs(number[topnum-1])/(pi/2))%2==1){
											showerrors(2, str_old);
											return;
										}
										number[topnum-1]=Math.tan(number[topnum-1]);
									}
									topnum++;
									break;
							case'g':
								if(number[topnum-1]<=0){
									showerrors(2, str_old);
									return;
								}
								number[topnum-1]=Math.log10(number[topnum-1]);
								topnum++;
								break;
							case'l':
								if(number[topnum-1]<=0){
									showerrors(2, str_old);
									return;
								}
								number[topnum-1]=Math.log(number[topnum-1]);
								topnum++;
								break;
							case'!':
								if(number[topnum-1]>170){
									showerrors(3, str_old);
									return;
								}else if(number[topnum-1]<0){
									showerrors(2, str_old);
									return;
								}
								number[topnum-1]=N(number[topnum-1]);
								topnum++;
								break;
						}
							topnum--;
							topop--;
					}
						weight[topop]=weighttemp;
						operator[topop]=ch;
						topop++;
				}
			}
				i++;
			}
			while(topop>0){
				switch(operator[topop-1]){
					case'+':
						number[topnum-2]+=number[topnum-1];
						break;
					case'-':
						number[topnum-2]-=number[topnum-1];
						break;
					case'*':
						number[topnum-2]*=number[topnum-1];
						break;
					case'/':
						if(number[topnum-1]==0){
							showerrors(1, "str_old");
							return;
						}
						number[topnum-2]/=number[topnum-1];
						break;
					case'√':
						if(number[topnum-1]==0||(number[topnum-2]<0&&number[topnum-1]%2==0)){
							showerrors(2, str_old);
						}
						number[topnum-2]=Math.pow(number[topnum-2], 1/number[topnum-1]);
						break;
					case'^':
						number[topnum-2]=Math.pow(number[topnum-2], number[topnum-1]);
						break;
					case's':
						if(drg_flag==true){
							number[topnum-1]=Math.sin((number[topnum-1]/180)*pi);
						}else{
							number[topnum-1]=Math.sin(number[topnum-1]);
						}
						topnum++;
						break;
					case'c':
						if(drg_flag==true){
							number[topnum-1]=Math.cos((number[topnum-1]/180)*pi);
						}else{
							number[topnum-1]=Math.cos(number[topnum-1]);
						}
						topnum++;
						break;
					case't':
						if(drg_flag==true){
							if((Math.abs(number[topnum-1])/90)%2==1){
								showerrors(2, str_old);
								return;
							}
							number[topnum-1]=Math.tan((number[topnum-1]/180)*pi);
						}else{
							if((Math.abs(number[topnum-1])/(pi/2))%2==1){
								showerrors(2, str_old);
								return;
							}
							number[topnum-1]=Math.tan(number[topnum-1]);
						}
						topnum++;
						break;
				case'g':
					if(number[topnum-1]<=0){
						showerrors(2, str_old);
						return;
					}
					number[topnum-1]=Math.log10(number[topnum-1]);
					topnum++;
					break;
				case'l':
					if(number[topnum-1]<=0){
						showerrors(2, str_old);
						return;
					}
					number[topnum-1]=Math.log(number[topnum-1]);
					topnum++;
					break;
				case'!':
					if(number[topnum-1]>170){
						showerrors(3, str_old);
						return;
					}else if(number[topnum-1]<0){
						showerrors(2, str_old);
						return;
					}
					number[topnum-1]=N(number[topnum-1]);
					topnum++;
					break;
			}
				topnum--;
				topop--;
			}
			if(number[0]>7.3E306){
				showerrors(3, str_old);
				return;
			}
			input.setText(String.valueOf(FP(number[0])));	
			tip.setText("计算完毕，要继续请按归零键C");
			mem.setText(str_old+"="+String.valueOf(FP(number[0])));
		}
		
		public double FP(double n){
			DecimalFormat format=new DecimalFormat("0.#############");
			return Double.parseDouble(format.format(n));
		}
		
		public double N(double n){
			
			int sum=1;
			for(int i=1;i<=n;i++){
				sum*=i;
			}
			return sum;
		}
		
		public void showerrors(int code,String str){
			String message="";
			switch(code){
				case 1:
					message="零不能做除数";
					break;
				case 2:
					message="函数格式错误";
					break;
				case 3:
					message="值太大了，超出范围";
					break;
			}
			input.setText("\""+str+"\""+": "+message);
			tip.setText(message+"\n"+"计算完毕，要继续请按归零键");
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
