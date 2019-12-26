package com.lkcdroid.ownstore.ui.VistasCRUD;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lkcdroid.ownstore.R;
import com.lkcdroid.ownstore.dal.ProductoDAL;
import com.lkcdroid.ownstore.dto.Imagen;
import com.lkcdroid.ownstore.dto.Producto;

public class IngrProductoActivity extends AppCompatActivity {
    private EditText editNombre;
    private EditText editDescripcion;
    private Button btnIngresar;
    private ImageButton imgBtnImage;
    private ProductoDAL productoDAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingr_producto);

        this.editNombre=(EditText) findViewById(R.id.txtNombre);
        this.editDescripcion=(EditText) findViewById(R.id.txtDecripcion);
        this.imgBtnImage=(ImageButton) findViewById(R.id.imBtnImagen);
        this.btnIngresar=(Button) findViewById(R.id.btnIngresar);


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto p = new Producto(editNombre.getText().toString(), editDescripcion.getText().toString());
                productoDAL = new ProductoDAL(getApplicationContext(), p);
                boolean r = productoDAL.insertar();

                if (r) {
                    Toast.makeText(getApplicationContext(), "OK! Insertó", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "MAL! NO Insertó", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
