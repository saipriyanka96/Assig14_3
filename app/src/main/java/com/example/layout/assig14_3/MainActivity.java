package com.example.layout.assig14_3;
//Package objects contain version information about the implementation and specification of a Java package
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    ////here i have created main class
//public keyword is used in the declaration of a class,method or field;public classes,method and fields can be accessed by the members of any class.
//extends is for extending a class. implements is for implementing an interface
//AppCompatActivity is a class from e v7 appcompat library. This is a compatibility library that back ports some features of recent versions of
// Android to older devices.
    //creating objects
    EditText editText;
    //A user interface element for entering and modifying text.
    TextView textView;
    //A user interface element that displays text to the user.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Variables, methods, and constructors, which are declared protected in a superclass can be accessed only by the subclasses
        // in other package or any class within the package of the protected members class.
        //void is a Java keyword.  Used at method declaration and definition to specify that the method does not return any type,
        // the method returns void.
        //onCreate Called when the activity is first created. This is where you should do all of your normal static set up: create views,
        // bind data to lists, etc. This method also provides you with a Bundle containing the activity's previously frozen state,
        // if there was one.Always followed by onStart().
        //Bundle is most often used for passing data through various Activities.
// This callback is called only when there is a saved instance previously saved using onSaveInstanceState().
// We restore some state in onCreate() while we can optionally restore other state here, possibly usable after onStart() has
// completed.The savedInstanceState Bundle is same as the one used in onCreate().

        super.onCreate(savedInstanceState);
// call the super class onCreate to complete the creation of activity like the view hierarchy
        setContentView(R.layout.activity_main);
        //R means Resource
        //layout means design
        //  main is the xml you have created under res->layout->main.xml
        //  Whenever you want to change your current Look of an Activity or when you move from one Activity to another .
        // The other Activity must have a design to show . So we call this method in onCreate and this is the second statement to set
        // the design
        //INITIALIZING
        editText= (EditText) findViewById(R.id.edittext);
        textView= (TextView) findViewById(R.id.textview);
        textView.setVisibility(View.GONE);
        //Set the visibility state of this view.
        //GONE:This view is invisible, and it doesn't take any space for layout purposes.
    }
    //Method to write messege in file
    public void writeMessage(View view){
        String message= editText.getText().toString();
        //String is a set of characters and with variable msg we will take the text which need to be a string
        //declaring a file and saving with a name
        String file_Name="hello_file";
        try {
            //FileOutputStream is an output stream used for writing data to a file.
            //Open a private file associated with this Context's application package for writing. Creates the file if it doesn't already exist
            //name	String: The name of the file to open; can not contain path separators.
          //  mode	int: Operating mode.
          //  Value is either 0 or combination of MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITEABLE or MODE_APPEND.
            //MODE_PRIVATE:File creation mode: the default mode, where the created file can only be accessed by the calling
            // application (or all applications sharing the same user ID)
            FileOutputStream fileOutputStream= openFileOutput(file_Name,MODE_PRIVATE);
            fileOutputStream.write(message.getBytes());
            //Writing the msg which will be bytes
            //then we will close the file after writing it
            fileOutputStream.close();
            // toast:A toast provides simple feedback about an operation in a small popup
            //Make a standard toast that just contains a text view with the text from a resource.

            // Parameters
            //context	Context: The context to use. Usually your Application or Activity object.
            //resId	int: The resource id of the string resource to use. Can be formatted text.
            //duration	int: How long to display the message. Either LENGTH_SHORT or LENGTH_LONG
//show(): it show the toast
            Toast.makeText(getApplicationContext(),"message saved",Toast.LENGTH_LONG).show();
            editText.setText("");
            //giving the text to it
        } catch (FileNotFoundException e) {//if file is not found
            e.printStackTrace();
            //Prints this throwable and its backtrace to the standard error stream
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //Method to read message from file
    public void readMessage(View view){
        try {
            String Message ;
            //Java FileInputStream class obtains input bytes from a file
            //here it contains the file
            FileInputStream fileInputStream=openFileInput("hello_file");
            //FileInputStream is meant for reading streams of raw bytes such as image data
            InputStreamReader inputStreamReader= new InputStreamReader(fileInputStream);
            //An InputStreamReader is a bridge from byte streams to character streams
            //it takes the file from fileinputstream
            //BufferedReader class is used to read the text from a character-based input stream.
            //The StringBuffer class is used to create mutable string
            BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer= new StringBuffer();
            textView.setText(stringBuffer.toString());
            //set the text from string buffer and get it into string buffer
            try {
                while ((Message = bufferedReader.readLine())!=null) {
                    //if msg is not equal to null
                    //is used to append the specified string with this string.
                    stringBuffer.append(Message + "\n");
                }
                textView.setText(stringBuffer.toString());
                textView.setVisibility(View.VISIBLE);
                //here the view will be visible
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}