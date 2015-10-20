
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
* ��Ŀ���ƣ����˱�׼��߼�������
* ��ĿĿ�꣺
* ��1������������档
* ��2�������¼�����
* ��3�������������
* ��4���������ֻ���
*
* ***********************************************************/


public class MainActivity extends Activity {
    //���㰴ť
    private Button calculatorButton;
    //���������
    private EditText weightEditText;
    //����ѡ���
    private RadioButton manRadioButton;
    //Ů��ѡ���
    private RadioButton womanRadioButton;
    //��ʾ���
    private TextView resultTextView;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //����ҳ�沼��
        setContentView(R.layout.activity_main);
        //��main.xmlҳ�沼���л�ö�Ӧ��UI�ؼ�
        calculatorButton = (Button) findViewById(R.id.calculator);
        weightEditText = (EditText) findViewById(R.id.weight);
        manRadioButton = (RadioButton) findViewById(R.id.man);
        womanRadioButton = (RadioButton) findViewById(R.id.woman);
        resultTextView = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //ע���¼�
        registerEvent();
    }

    /*
    * ע���¼�
    * */
    private void registerEvent() {
        //ע�ᰴť�¼�
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //�ж��Ƿ��Ѿ���д��������
                if (!weightEditText.getText().toString().trim().equals("")) {
                    //�ж��Ƿ��Ѿ�ѡ���Ա�
                    if (manRadioButton.isChecked() || womanRadioButton.isChecked()) {
                        Double weight = Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("------�������------ \n");
                        if (manRadioButton.isChecked()) {
                            sb.append("���Ա�׼��ߣ�");
                            //ִ������
                            double result = evaluateHeight(weight, "��");
                            sb.append((int) result + "�����ף�");
                        }
                        if (womanRadioButton.isChecked()) {
                            sb.append("Ů�Ա�׼��ߣ�");
                            //ִ������
                            double result = evaluateHeight(weight, "Ů");
                            sb.append((int) result + "(����)");
                        }
                        //���ҳ����ʾ���
                        resultTextView.setText(sb.toString());
                    } else {
                        showMessage("���������أ�");
                    }
                }
            }
        });

    }

    /*
    * ���㴦��ִ�д����¼�
    * */
    private double evaluateHeight(double weight, String sex) {
        double height;
        if (sex == "��") {
            height = 170 - (62 - weight) / 0.6;
        } else {
            height = 158 - (52 - weight) / 0.5;
        }
        return height;
    }

    /*
    * ��Ϣ��ʾ
    * */

    private void showMessage(String message) {

        //��ʾ��
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("ϵͳ��Ϣ");
        alert.setMessage(message);
        alert.setButton("ȷ��", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //���ڴ˷����ڱ�д����ȷ����ť�Ĵ�����룬���ڱ���Ŀ�в���Ҫ��д�������
            }
        });
        alert.show();//��ʾ����
    }
    /*
    * �����˵�*/
    public boolean onCreateOptionMenu(Menu menu){
        menu.add("�˳�");
        return super.onCreateOptionsMenu(menu);
    }

    /*�˵��¼�*/
    public boolean onOptionItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1://�˳�
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }





}
