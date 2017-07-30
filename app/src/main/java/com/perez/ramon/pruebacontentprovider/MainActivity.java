package com.perez.ramon.pruebacontentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button obtenerContactos;
    private int contador;
    private int contador2;
    private TextView txtIdName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         txtIdName = (TextView)findViewById(R.id.txtIdName);
        obtenerContactos = (Button)findViewById(R.id.btnInicio);

        obtenerContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                access();
            }
        });
    }



    public void access(){
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);

        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                imprimirContactos(id,name);
            }
        }
    }

    public void imprimirContactos(String id, String name){
        txtIdName.setText("ID   "+ id +"  Nombre"+ name +"\n");
    }
}
