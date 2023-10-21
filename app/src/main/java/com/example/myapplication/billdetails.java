package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class billdetails extends AppCompatActivity {

    private String[][] bill1 =
    {
        {"name:   jio","number:9141xxxx52","amount:rs259","type:Monthly"},
        {"name:   vi","number:9011xxxx31","amount:rs599","type:Monthly"},
        {"name:   airtel","number:9251xxxx73","amount:rs1259","type:yearly"}
    };

    private String[][] bill2 =
            {
                    {"name:   APSPDCL","number:301526301215","amount:rs540","type:Monthly"},
                    {"name:   APSPDCL","number:304521648215","amount:rs1020","type:Monthly"},
                    {"name:   APSPDCL","number:3015142546895","amount:rs2599","type:Monthly"}
            };

    private String[][] bill3 =
            {
                    {"name:   INDANE","number:3015ASDF75","amount:rs980","type:Monthly"},
                    {"name:   HP","number:3045ZXCD12","amount:rs1020","type:Monthly"},
                    {"name:   BharatGas","number:3115QWFG15","amount:rs1099","type:Monthly"}
            };

    private String[][] bill4 =
            {
                    {"name:   Home1","number:#112415AL55","amount:rs120","type:Monthly"},
                    {"name:   Home2","number:#128428Au60","amount:rs210","type:Monthly"},
                    {"name:   Home3","number:#322515AG15","amount:rs450","type:Monthly"}
            };

    private String[][] bill5 =
            {
                    {"name:   ACT","number:ASDQWEZXC123","amount:rs339","type:Monthly"},
                    {"name:   RR","number:RTYFGHCVB456","amount:rs350","type:Monthly"},
                    {"name:   Jio","number:UIOJKLHNM789","amount:rs330","type:Monthly"}
            };

    private String[][] bill6 =
            {
                    {"name:   LIC Health","number:123456789120","amount:rs939","type:Monthly"},
                    {"name:   LIC Life","number:152426348521","amount:rs1550","type:Monthly"},
                    {"name:   Bike Insurance","number:UIO457812KJ","amount:rs129","type:Monthly"}
            };
    TextView tv , back;
    String[][] bill_details ={};
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billdetails);


        tv=findViewById(R.id.billdetail);
        back=findViewById(R.id.backtext);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Recharge Bills")==0)
            bill_details=bill1;
        else
        if(title.compareTo("Electricity Bills")==0)
            bill_details=bill2;
        else
        if(title.compareTo("LPG Cylinder Bills")==0)
            bill_details=bill3;
        else
        if(title.compareTo("Water Bills")==0)
            bill_details=bill4;
        else
        if(title.compareTo("DTH Cable Bills")==0)
            bill_details=bill5;
        else
            bill_details=bill6;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(billdetails.this , bills.class));
            }
        });

            list =new ArrayList();
            for (int i=0 ; i<bill_details.length ; i++){
                item = new HashMap<String , String>();
                item.put("line1",bill_details[i][0]);
                item.put("line2",bill_details[i][1]);
                item.put("line3",bill_details[i][2]+"/-");
                item.put("line4",bill_details[i][3]);
                list.add(item);
            }
            sa=new SimpleAdapter(this,list,R.layout.multi_lines,
                    new String[]{"line1","line2","line3","line4"},
                    new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d});

        ListView lst =findViewById(R.id.listofbills);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it =new Intent(billdetails.this , paymentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",bill_details[i][0]);
                it.putExtra("text3",bill_details[i][1]);
                it.putExtra("text4" , bill_details[i][2]);
                startActivity(it);
            }
        });
    }
}