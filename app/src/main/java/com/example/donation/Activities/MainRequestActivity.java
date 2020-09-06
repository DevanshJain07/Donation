package com.example.donation.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.donation.R;

import java.net.URI;

public class MainRequestActivity extends AppCompatActivity {

    EditText messageText;
    TextView chooseImageText;
    ImageView postImage;
    Button submit_button;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_request);
        messageText=findViewById(R.id.message);
        chooseImageText=findViewById(R.id.choose_text);
        postImage=findViewById(R.id.post_image);
        submit_button=findViewById(R.id.submit_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()){
                    uploadRequest(messageText.getText().toString());
                }
            }
        });

        chooseImageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,101);
            }
        });
    }

    private void uploadRequest(String message){

    }

    private void uploadImage(){

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==101 && requestCode==RESULT_OK){
            if(data!=null){
                imageUri=data.getData();
                Glide.with(getApplicationContext()).load(imageUri).into(postImage);
            }
        }
    }

    private boolean isValid(){
        if(messageText.getText().toString().isEmpty()){
            showMessage("Message should not be empty");
            return false;
        }
        return true;
    }
    private void showMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
