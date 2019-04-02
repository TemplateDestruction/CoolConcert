package com.breakout.myapplication.concerts.concert.meeting.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.breakout.myapplication.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ChatActivity extends AppCompatActivity {
    private static final int SIGN_IN_REQUEST_CODE = 100;


    private FirebaseListAdapter<ChatMessage> adapter;
    FloatingActionButton fab;

    @BindView(R.id.fun_img)
    ImageView funImg;

    @BindView(R.id.sad_img)
    ImageView sadImg;

    @BindView(R.id.input)
    EditText textInput;

    @BindView(R.id.dish_img)
    ImageView dishImg;

    @BindView(R.id.home_img)
    ImageView homeImg;

    @BindView(R.id.exit_img)
    ImageView exitImg;

    @BindView(R.id.imageView9)
    ImageView yesImg;

    @BindView(R.id.no_img)
    ImageView noImg;


    @BindView(R.id.imageView10)
    ImageView beerImg;

    @BindView(R.id.cant_img)
    ImageView cantImg;

    @BindView(R.id.imageView11)
    ImageView getCantImg;

    ListView listOfMessages;

    String[] fun;
    String[] sad;
    String[] dish;
    String[] home;
    String[] exit;
    String[] ok;
    String[] no;
    String[] beer;
    String[] cant;
    String[] can;

    Disposable disposable;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_register_layout);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {
            // User is already signed in. Therefore, display
            // a welcome Toast
            Toast.makeText(this,
                    "Welcome " + FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getDisplayName(),
                    Toast.LENGTH_LONG)
                    .show();

            // Load chat room contents
            displayChatMessages();
        }

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(view -> {
            EditText input = findViewById(R.id.input);

            // Read the input field and push a new instance
            // of ChatMessage to the Firebase database
            FirebaseDatabase.getInstance()
                    .getReference()
                    .push()
                    .setValue(new ChatMessage(input.getText().toString(),
                            FirebaseAuth.getInstance()
                                    .getCurrentUser()
                                    .getDisplayName())
                    );

            // Clear the input
            input.setText("");
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }

    private void displayChatMessages() {
        initTextArrays();

        listOfMessages = findViewById(R.id.list_of_messages);


        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = v.findViewById(R.id.message_text);
                TextView messageUser = v.findViewById(R.id.message_user);
                TextView messageTime = v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };
        listOfMessages.setAdapter(adapter);
        listOfMessages.setSelection(adapter.getCount() - 1);
//
//        disposable =
//                Observable.just(adapter.getCount())
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .repeatWhen(completed -> {
//                        })
//                        .subscribe(this::scrollToItem);
    }

    private void scrollToItem(Integer count) {
        listOfMessages.setSelection(count);
    }

    private void initTextArrays() {
        fun = getResources().getStringArray(R.array.fun);
        sad = getResources().getStringArray(R.array.sad);
        dish = getResources().getStringArray(R.array.dish);
        home = getResources().getStringArray(R.array.home);
        exit = getResources().getStringArray(R.array.exit);
        ok = getResources().getStringArray(R.array.yes);
        no = getResources().getStringArray(R.array.no);
        beer = getResources().getStringArray(R.array.beer);
        cant = getResources().getStringArray(R.array.cant);
        can = getResources().getStringArray(R.array.can);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_IN_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(this,
                        "Successfully signed in. Welcome!",
                        Toast.LENGTH_LONG)
                        .show();
                displayChatMessages();
            } else {
                Toast.makeText(this,
                        "We couldn't sign you in. Please try again later.",
                        Toast.LENGTH_LONG)
                        .show();

                // Close the app
                finish();
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_sign_out) {
            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(task -> {
                        Toast.makeText(getApplicationContext(),
                                "You have been signed out.",
                                Toast.LENGTH_LONG)
                                .show();

                        // Close activity
                        finish();
                    });
        }
        return true;
    }

    public void onImgClick(View view) {
        Random random = new Random();
        switch (view.getId()) {
            case R.id.fun_img:
                textInput.setText(fun[random.nextInt(fun.length)]);
                fab.performClick();
                listOfMessages.setSelection(adapter.getCount() + 1);
                break;
            case R.id.sad_img:
                textInput.setText(sad[random.nextInt(sad.length)]);
                fab.performClick();
                listOfMessages.setSelection(adapter.getCount() + 1);                break;
            case R.id.dish_img:
                textInput.setText(dish[random.nextInt(dish.length)]);
                fab.performClick();
                listOfMessages.setSelection(adapter.getCount());                break;
            case R.id.home_img:
                textInput.setText(home[random.nextInt(home.length)]);
                fab.performClick();
                listOfMessages.setSelection(adapter.getCount());                break;
            case R.id.exit_img:
                textInput.setText(exit[random.nextInt(exit.length)]);
                fab.performClick();
                listOfMessages.setSelection(adapter.getCount());                break;
            case R.id.imageView9:
                textInput.setText(ok[random.nextInt(ok.length)]);
                fab.performClick();
                listOfMessages.setSelection(adapter.getCount());                break;
            case R.id.no_img:
                textInput.setText(no[random.nextInt(no.length)]);
                fab.performClick();
                listOfMessages.setSelection(adapter.getCount());                break;
            case R.id.imageView10:
                textInput.setText(beer[random.nextInt(beer.length)]);
                fab.performClick();
                listOfMessages.setSelection(adapter.getCount());                break;
            case R.id.cant_img:
                textInput.setText(cant[random.nextInt(cant.length)]);
                fab.performClick();
                listOfMessages.setSelection(adapter.getCount());                break;
            case R.id.imageView11:
                textInput.setText(can[random.nextInt(can.length)]);
                fab.performClick();
                listOfMessages.setSelection(adapter.getCount());
                break;
        }
    }
}
