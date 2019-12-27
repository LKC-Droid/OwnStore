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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.lkcdroid.ownstore.R;
import com.lkcdroid.ownstore.dal.TiendaDAL;
import com.lkcdroid.ownstore.dto.Tienda;

import java.util.HashMap;
import java.util.Map;


public class MyShopActivity extends AppCompatActivity {
    private EditText editNombre;
    private EditText editDescripcion;
    private EditText editDireccion;
    private ImageButton imgBtnLogo;
    private Button btnIngresar;
    private TiendaDAL tiendaDAL;
    private DatabaseReference mRootReference;
    private StorageReference mStorage;
    private static final int GALLERY_INTENT=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shop);

        mRootReference= FirebaseDatabase.getInstance().getReference();
        mStorage= FirebaseStorage.getInstance().getReference();

        this.editNombre=(EditText) findViewById(R.id.txtNombre);
        this.editDescripcion=(EditText) findViewById(R.id.txtDecripcion);
        this.editDireccion=(EditText) findViewById(R.id.txtDireccion);
        this.imgBtnLogo=(ImageButton) findViewById(R.id.imBtnLogo);
        this.btnIngresar=(Button) findViewById(R.id.btnCrear);

        imgBtnLogo.setOnClickListener(new View.OnClickListener() {
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
                Tienda t = new Tienda(editNombre.getText().toString(), editDescripcion.getText().toString(),
                        editDireccion.getText().toString());

                Map<String,Object> datosTienda= new HashMap<>();
                datosTienda.put("Nombre_tienda",t.getNombre());
                datosTienda.put("Descripcion_tienda",t.getDescripcion());
                datosTienda.put("Direccion_tienda",t.getDireccion());

                mRootReference.child("Tiendas").push().setValue(datosTienda);

                //Plan B
                /*tiendaDAL = new TiendaDAL(getApplicationContext(), t);
                boolean r = tiendaDAL.insertar();

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
            StorageReference filePath= mStorage.child("Logos").child(uri.getLastPathSegment());

            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(MyShopActivity.this,"El logo se agrego exitosamente",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
