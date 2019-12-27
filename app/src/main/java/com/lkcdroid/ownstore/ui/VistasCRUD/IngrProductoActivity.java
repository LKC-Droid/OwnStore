package com.lkcdroid.ownstore.ui.VistasCRUD;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.lkcdroid.ownstore.MainActivity;
import com.lkcdroid.ownstore.R;
import com.lkcdroid.ownstore.dal.ProductoDAL;
import com.lkcdroid.ownstore.dto.Producto;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

public class IngrProductoActivity extends AppCompatActivity {
    private EditText editNombre;
    private EditText editDescripcion;
    private Button btnIngresar;
    private ImageButton imgBtnImage;
    private ProductoDAL productoDAL;
    private DatabaseReference mRootReference;
    private StorageReference mStorage;
    private static final int GALLERY_INTENT=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingr_producto);

        mRootReference= FirebaseDatabase.getInstance().getReference();
        mStorage= FirebaseStorage.getInstance().getReference();

        this.editNombre=(EditText) findViewById(R.id.txtNombre);
        this.editDescripcion=(EditText) findViewById(R.id.txtDecripcion);
        this.imgBtnImage=(ImageButton) findViewById(R.id.imBtnImagen);
        this.btnIngresar=(Button) findViewById(R.id.btnIngresar);

        imgBtnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);
            }
        });


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Producto p = new Producto(editNombre.getText().toString(), editDescripcion.getText().toString());
                //Plan B
                //productoDAL = new ProductoDAL(getApplicationContext(), p);

                Map<String,Object> datosProducto= new HashMap<>();
                datosProducto.put("Nombre_producto",p.getNombre());
                datosProducto.put("Descripcion_producto",p.getDescripcion());


                mRootReference.child("Producto").push().setValue(datosProducto);


                //Plan B
                /*boolean r = productoDAL.insertar();

                if (r) {
                    Toast.makeText(getApplicationContext(), "OK! Insertó", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "MAL! NO Insertó", Toast.LENGTH_LONG).show();
                }*/
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GALLERY_INTENT && requestCode==RESULT_OK){

            Uri uri= data.getData();
            StorageReference filePath= mStorage.child("Imagenes").child(uri.getLastPathSegment());

            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(IngrProductoActivity.this,"La imagen se agrego exitosamente",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
