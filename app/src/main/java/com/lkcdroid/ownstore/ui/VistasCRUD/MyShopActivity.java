package com.lkcdroid.ownstore.ui.VistasCRUD;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lkcdroid.ownstore.R;
import com.lkcdroid.ownstore.dal.TiendaDAL;
import com.lkcdroid.ownstore.dto.Tienda;



public class MyShopActivity extends AppCompatActivity {
    private EditText editNombre;
    private EditText editDescripcion;
    private EditText editDireccion;
    private ImageButton imBtnLogo;
    private Button btnIngresar;
    private TiendaDAL tiendaDAL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shop);

        this.editNombre=(EditText) findViewById(R.id.txtNombre);
        this.editDescripcion=(EditText) findViewById(R.id.txtDecripcion);
        this.editDireccion=(EditText) findViewById(R.id.txtDireccion);
        this.imBtnLogo=(ImageButton) findViewById(R.id.imBtnLogo);
        this.btnIngresar=(Button) findViewById(R.id.btnCrear);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tienda t = new Tienda(editNombre.getText().toString(), editDescripcion.getText().toString(),
                        editDireccion.getText().toString());
                tiendaDAL = new TiendaDAL(getApplicationContext(), t);
                boolean r = tiendaDAL.insertar();

                if (r) {
                    Toast.makeText(getApplicationContext(), "OK! Insertó", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "MAL! NO Insertó", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
