package com.example.whoisthesinger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static class Singer {
        public Singer(String name, int imageIndex) {
            this.name = name;
            this.imageIndex = imageIndex;
        }

        public String name;
        public int imageIndex;
    }

    ImageView SingerImage;
    Button option1, option2, option3, option4, PlayAgain;
    Button[] options;

    Singer currentSinger;
    ArrayList<Singer> singers = new ArrayList<>(), availableSingers = new ArrayList<>();
    ArrayList<String> choices;
    int numQuestionsDisplayed = 0, numCorrectAnswers = 0, correctIndex, questionsToDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTitle("Who is the Singer ?");
        SingerImage = findViewById(R.id.Singer);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        options = new Button[]{option1, option2, option3, option4};
        PlayAgain = findViewById(R.id.playagain);
        PlayAgain.setOnClickListener(this);

        //initialize singers
        singers.add(new Singer("Sidhu Moosewala", R.drawable.s1));
        singers.add(new Singer("Karan Aujla", R.drawable.s2));
        singers.add(new Singer("Deep Jandu", R.drawable.s3));
        singers.add(new Singer("Akhil", R.drawable.s4));
        singers.add(new Singer("Guru Randhawa", R.drawable.s5));
        singers.add(new Singer("Jordan Sandhu", R.drawable.s6));
        singers.add(new Singer("Dilpreet Dhillon", R.drawable.s7));
        singers.add(new Singer("A-Kay", R.drawable.s8));
        singers.add(new Singer("Ammy Virk", R.drawable.s9));
        singers.add(new Singer("Prabh Gill", R.drawable.s10));


        if (savedInstanceState != null) {
            updateButtonFunctions();

        } else {
            NewGame();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
        //Save Current Singer
        outState.putInt("current_Singer_ImageIndex", currentSinger.imageIndex);
        outState.putString("current_Singer_Name", currentSinger.name);

        //save game related ints
        outState.putInt("num_questions_displayed", numQuestionsDisplayed);
        outState.putInt("num_correct_answers", numCorrectAnswers);
        outState.putInt("correct_index", correctIndex);
        outState.putInt("questions_to_display", questionsToDisplay);

        //save current options
        outState.putString("option_1", option1.getText().toString());
        outState.putString("option_2", option2.getText().toString());
        outState.putString("option_3", option3.getText().toString());
        outState.putString("option_4", option4.getText().toString());

        //save available singers count
        outState.putInt("available_singer_count", availableSingers.size());

        //save available singers
        for(int i = 0; i<availableSingers.size(); i++){

            outState.putInt("available_Singers_"+i+"_imageIndex", availableSingers.get(i).imageIndex);
            outState.putString("available_Singers_"+i+"_name", availableSingers.get(i).name);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
    super.onRestoreInstanceState(savedInstanceState);

        //restore current singer
        currentSinger = new Singer(savedInstanceState.getString("current_Singer_Name"),savedInstanceState.getInt("current_Singer_ImageIndex"));

        //restore current singer image
        SingerImage.setImageResource(currentSinger.imageIndex);
        //restore current options
        option1.setText(savedInstanceState.getString("option_1"));
        option2.setText(savedInstanceState.getString("option_2"));
        option3.setText(savedInstanceState.getString("option_3"));
        option4.setText(savedInstanceState.getString("option_4"));



        //restore all singers
        singers.add(new Singer("Sidhu Moosewala", R.drawable.s1));
        singers.add(new Singer("Karan Aujla", R.drawable.s2));
        singers.add(new Singer("Deep Jandu", R.drawable.s3));
        singers.add(new Singer("Akhil", R.drawable.s4));
        singers.add(new Singer("Guru Randhawa", R.drawable.s5));
        singers.add(new Singer("Jordan Sandhu", R.drawable.s6));
        singers.add(new Singer("Dilpreet Dhillon", R.drawable.s7));
        singers.add(new Singer("A-Kay", R.drawable.s8));
        singers.add(new Singer("Ammy Virk", R.drawable.s9));
        singers.add(new Singer("Prabh Gill", R.drawable.s10));
        //restore available singers
        int count = savedInstanceState.getInt("available_singer_count");

        //save available singers
        for(int i = 0; i<count; i++){
            availableSingers.add(new Singer(savedInstanceState.getString("available_Singers_"+i+"_name"), savedInstanceState.getInt("available_Singers_"+i+"_imageIndex")));
        }

        //restore game related ints
        numQuestionsDisplayed = savedInstanceState.getInt("num_questions_displayed");
        numCorrectAnswers = savedInstanceState.getInt("num_correct_answers");
        correctIndex = savedInstanceState.getInt("correct_index");
        questionsToDisplay = savedInstanceState.getInt("questions_to_display");
     }






    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        if (menu instanceof MenuBuilder) {
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
                finish(); // add this line
                return true;
            case R.id.help:
                Intent intent1 = new Intent(MainActivity.this, Help.class);
                startActivity(intent1);
                finish(); // add this line
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {

        numQuestionsDisplayed = 0;
        numCorrectAnswers = 0;

        // Disable all button after showing all images
        for (Button option : options)
            option.setEnabled(true);

        PlayAgain.setVisibility(View.INVISIBLE);
        NewGame(); //this is where we restart the game
    }

    public void NewGame() {
        //clear out list of available singers and add the full list of singers back.
        availableSingers.clear();
        availableSingers.addAll(singers);
        Log.d("test","size: "+availableSingers.size());
        resetButtonColors();
        numQuestionsDisplayed = 0;
        numCorrectAnswers = 0;

        questionsToDisplay = singers.size();

        //Create a question
        CreateNewQuestion();
    }

    public void CreateNewQuestion() {
        // CreateOptions();
        displaySingerQuestion();
        updateButtonFunctions();
    }

    public void CreateOptions(){


        //select a random available singer
        int singerIndex = ThreadLocalRandom.current().nextInt(availableSingers.size()-1);
        Log.d("tag", "singerindex: "+singerIndex);
        currentSinger = new Singer(availableSingers.get(singerIndex).name,availableSingers.get(singerIndex).imageIndex);

        Log.d("tag", "current singer: "+currentSinger.name+", "+currentSinger.imageIndex);
        Log.d("tag", "available singers: "+availableSingers.size()+", "+availableSingers.get(0).name+", "+availableSingers.get(0).imageIndex);


        availableSingers.remove(singerIndex);
        choices.add(currentSinger.name);

        for (int i = 0; i < 3; i++)
            choices.add(singers.get(ThreadLocalRandom.current().nextInt(singers.size())).name) ;
        Collections.shuffle(choices);
    }

    public void updateButtonFunctions() {

        for (Button button : options) {
            button.setBackgroundResource(android.R.drawable.btn_default);
            button.setOnClickListener(v -> {
                if (button.getText().toString().equals(currentSinger.name)) {
                    numCorrectAnswers++;
                    button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green)));

                    // Display a success toast message
                    Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                } else {
                    // Change the button color to red
                    button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red2)));

                    // Display a failure toast message
                    Toast.makeText(MainActivity.this, "Wrong! The correct answer is " + currentSinger.name, Toast.LENGTH_SHORT).show();
                }

                questionsToDisplay--;

                if (questionsToDisplay <= 0) {
                    //All questions have been displayed, show final score message
                    Toast.makeText(MainActivity.this, "You scored " + numCorrectAnswers + " out of 10", Toast.LENGTH_LONG).show();

                    // Disable buttons so user cannot answer any more questions
                    for (Button option : options)
                        option.setEnabled(false);

                    PlayAgain.setVisibility(View.VISIBLE);
                } else
                    //trigger a new question to show up
                    new Handler().postDelayed(() -> {
                        resetButtonColors();
                        CreateNewQuestion();
                    }, 1000);
            });
        }
    }

    public void displaySingerQuestion() {
        Random random = new Random();
        Button[] options = {option1, option2, option3, option4};


        //select a random available singer index
        int singerIndex = random.nextInt((availableSingers.size()));

        Log.d("myTag", "correct image: "+availableSingers.get(singerIndex));
        currentSinger = availableSingers.remove(singerIndex);
        Log.d("myTag", "displayed image: "+currentSinger);

        for(int i=0; i<= availableSingers.size(); i++){

            Log.d("myTag", "test available singer:"+i);
        }
        for(int i=0; i<= singers.size(); i++){

            Log.d("myTag", "test singer:"+i);
        }

        // Step 1: Choose a random index for the correct answer in options
        correctIndex = random.nextInt(options.length);

        // Step 3: Set the singer image to the corresponding image
        SingerImage.setImageResource(currentSinger.imageIndex);

        // Step 4: Set the text for each button option
        for (int i = 0; i < options.length; i++) {
            if (i == correctIndex) {
                // Set the text of the button with the correct answer
                options[i].setText(currentSinger.name);
            } else {
                // Choose a random name for the other options, but make sure it's not the correct answer
                int randomIndex;
                randomIndex = random.nextInt(singers.size());

                boolean isAvailableName = false;
                //keep randomizing until we get a singer that is not the correct option and also not already in use
                do{
                    randomIndex = random.nextInt(singers.size());


                    boolean isADuplicate = false;
                    //loop through all options and make sure it is not already in use
                    for (int j = 0 ; j <options.length; j++){

                        if(options[j].getText() == singers.get(randomIndex).name){
                            isADuplicate = true;
                        }
                    }

                    boolean isTheCorrectOption = false;
                    //Make sure that it is not the already in use correct answer
                    if(singers.get(randomIndex).name == currentSinger.name){
                        isTheCorrectOption=true;
                    }

                    //if either of those conditions are not met, return false and generate a new random index
                    if(isTheCorrectOption || isADuplicate){ //
                        isAvailableName = false;
                    }else{
                        isAvailableName = true;
                    }

                }while(!isAvailableName);

                options[i].setText(singers.get(randomIndex).name);
            }
        }
    }



    private void resetButtonColors() {
        for (Button button : options)
            button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange)));
    }
}