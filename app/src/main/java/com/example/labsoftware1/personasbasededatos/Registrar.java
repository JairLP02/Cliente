package com.example.labsoftware1.personasbasededatos;

import android.content.DialogInterface;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Registrar extends AppCompatActivity {
    private EditText cajaCedula;
    private EditText cajaNombre;
    private EditText cajaApellido;
    private EditText cajaTelefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        cajaCedula = (EditText)findViewById(R.id.txtcedula);
        cajaNombre = (EditText)findViewById(R.id.txtNombre);
        cajaApellido = (EditText)findViewById(R.id.txtApellido);
        cajaTelefono = (EditText)findViewById(R.id.txtTelefono);

    }

    public boolean validarTodo(){
        if(cajaCedula.getText().toString().isEmpty()){
            cajaCedula.setError("Digite la cédula");
            cajaCedula.requestFocus();
            return false;
        }
        if(cajaNombre.getText().toString().isEmpty()){
            cajaNombre.setError("Digite el Nombre");
            cajaNombre.requestFocus();
            return false;
        }
        if(cajaApellido.getText().toString().isEmpty()){
            cajaApellido.setError("Digite el Apellido");
            cajaApellido.requestFocus();
            return false;
        }
        if(cajaTelefono.getText().toString().isEmpty()){
            cajaTelefono.setError("Digite el Telefono");
            cajaTelefono.requestFocus();
            return false;

        }
        return true;
    }

    public void guardar(View v){
        String foto,cedula,nombre,apellido,telefono;
        Persona p;

        if(validarTodo()){
            foto = String.valueOf(fotoAleatoria());
            cedula = cajaCedula.getText().toString();
            nombre = cajaNombre.getText().toString();
            apellido=cajaApellido.getText().toString();
            telefono=cajaTelefono.getText().toString();
            p = new Persona(foto,cedula,nombre,apellido,telefono);
            p.guardar(getApplicationContext());

            Toast.makeText(getApplicationContext(), "Persona Guardada Exitosamente",
                    Toast.LENGTH_SHORT).show();



            limpiar();

        }
    }

    public int fotoAleatoria(){
        int fotos[] = {R.drawable.images,R.drawable.images2,R.drawable.images3};
        int numero = (int)(Math.random() * 3);
        return fotos[numero];
    }
    public boolean validarCedula() {
        if (cajaCedula.getText().toString().isEmpty()) {
            cajaCedula.setError("Digite la cédula");
            cajaCedula.requestFocus();
            return false;
        }
        return true;
    }

    public void limpiar(){
        cajaCedula.setText("");
        cajaNombre.setText("");
        cajaApellido.setText("");
        cajaTelefono.setText("");

        cajaCedula.requestFocus();

    }

    public void limpiar(View v){
        limpiar();
    }

    public void buscar(View v){
        Persona p;

        if(validarCedula()) {
            p = Datos.buscarPersona(getApplicationContext(), cajaCedula.getText().toString());
            if(p!=null){
                cajaNombre.setText(p.getNombre());
                cajaApellido.setText(p.getApellido());
                cajaApellido.setText(p.getApellido());
                cajaTelefono.setText(p.getTelefono());
            }
        }
        }

    public void modificar(View v){
        Persona p,p2;
        String nombre,apellido,telefono;
        if(validarCedula()) {
            p = Datos.buscarPersona(getApplicationContext(), cajaCedula.getText().toString());
            if(p!=null){

                nombre = cajaNombre.getText().toString();
                apellido=cajaApellido.getText().toString();
                telefono=cajaTelefono.getText().toString();

                p2 = new Persona(p.getFoto(),p.getCedula(),nombre,apellido,telefono);
                p2.modificar(getApplicationContext());

                Toast.makeText(getApplicationContext(), "Persona Modificada Exitosamente",
                        Toast.LENGTH_SHORT).show();


                limpiar();




            }
        }
    }

    public void eliminar(View v){
        Persona p;
        if(validarCedula()) {
            p = Datos.buscarPersona(getApplicationContext(), cajaCedula.getText().toString());
            if(p!=null){
               AlertDialog.Builder ventana = new AlertDialog.Builder(this);
                ventana.setTitle("Confirmación");
                ventana.setMessage("¿Está seguro que desea eliminar esta persona?");
                ventana.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Persona p;
                        p = Datos.buscarPersona(getApplicationContext(), cajaCedula.getText().toString());

                        p.eliminar(getApplicationContext());
                        limpiar();
                        Toast.makeText(getApplicationContext(), "Persona Eliminada Exitosamente",
                                Toast.LENGTH_SHORT).show();

                    }
                });

                ventana.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cajaCedula.requestFocus();
                    }
                });

                   ventana.show();
            }
        }
    }





}
