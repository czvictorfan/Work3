
package com.example.administrator.work3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/*
* 项目名称：个人标准身高计算器。
* 项目目标：
* （1）开发输入界面。
* （2）进行事件处理。
* （3）处理计算结果。
* （4）发布到手机。
*
* ***********************************************************/


public class MainActivity extends Activity {
    //计算按钮
    private Button calculatorButton;
    //体重输入框
    private EditText weightEditText;
    //男性选择框
    private RadioButton manRadioButton;
    //女性选择框
    private RadioButton womanRadioButton;
    //显示结果
    private TextView resultTextView;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面布局
        setContentView(R.layout.activity_main);
        //从main.xml页面布局中获得对应的UI控件
        calculatorButton = (Button) findViewById(R.id.calculator);
        weightEditText = (EditText) findViewById(R.id.weight);
        manRadioButton = (RadioButton) findViewById(R.id.man);
        womanRadioButton = (RadioButton) findViewById(R.id.woman);
        resultTextView = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册事件
        registerEvent();
    }

    /*
    * 注册事件
    * */
    private void registerEvent() {
        //注册按钮事件
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否已经填写体重数据
                if (!weightEditText.getText().toString().trim().equals("")) {
                    //判断是否已经选择性别
                    if (manRadioButton.isChecked() || womanRadioButton.isChecked()) {
                        Double weight = Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("------评估结果------ \n");
                        if (manRadioButton.isChecked()) {
                            sb.append("男性标准身高：");
                            //执行运算
                            double result = evaluateHeight(weight, "男");
                            sb.append((int) result + "（厘米）");
                        }
                        if (womanRadioButton.isChecked()) {
                            sb.append("女性标准身高：");
                            //执行运算
                            double result = evaluateHeight(weight, "女");
                            sb.append((int) result + "(厘米)");
                        }
                        //输出页面显示结果
                        resultTextView.setText(sb.toString());
                    } else {
                        showMessage("请输入体重！");
                    }
                }
            }
        });

    }

    /*
    * 计算处理执行代码事件
    * */
    private double evaluateHeight(double weight, String sex) {
        double height;
        if (sex == "男") {
            height = 170 - (62 - weight) / 0.6;
        } else {
            height = 158 - (52 - weight) / 0.5;
        }
        return height;
    }

    /*
    * 消息提示
    * */

    private void showMessage(String message) {

        //提示框
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //可在此方法内编写按下确定按钮的处理代码，但在本项目中不需要编写处理代码
            }
        });
        alert.show();//显示窗口
    }
    /*
    * 创建菜单*/
    public boolean onCreateOptionMenu(Menu menu){
        menu.add("退出");
        return super.onCreateOptionsMenu(menu);
    }

    /*菜单事件*/
    public boolean onOptionItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1://退出
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }





}
