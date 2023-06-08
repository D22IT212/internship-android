package com.example.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.email.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.emailAddress.getText().toString();
                String subject = binding.emailAddress.getText().toString();
                String message = binding.emailAddress.getText().toString();

                String[] address = email.split(",");

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL,address);
                intent.putExtra(Intent.EXTRA_EMAIL,subject);
                intent.putExtra(Intent.EXTRA_EMAIL,message);


                if (subject.equals("")&&message.equals("")&&email.equals("")){
                    Toast.makeText(MainActivity.this,"ALL FIELDS ARE REQUIRED",Toast.LENGTH_SHORT).show();
                }
                else {
                    sendmail(subject,message,email);
                }
            }
        });
    }
    public void sendmail(String Subject,String Message, String emailAddress){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
        intent.putExtra(Intent.EXTRA_SUBJECT, Subject);
        intent.putExtra(Intent.EXTRA_TEXT, Message);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose email client:"));

    }
}