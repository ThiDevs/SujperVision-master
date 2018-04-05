package alves.thiago.supervision;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.R.layout.simple_list_item_activated_2;



public class MainActivity extends Activity  {
    private Socket socket;
    Button botao;
    String message;
    Thread thread;
    EditText editText;
    TextView superV;
    int SERVERPORT = 9999;
    ListView listview;
    public String SERVER_IP;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    List<String> value;
    List<String> separador;
    Thread a;
    ArrayAdapter<String> adapter;
    boolean mudou = true;
    private GestureDetector myGestDetector;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview  = (ListView) findViewById(R.id.list2);
        editText = (EditText) findViewById(R.id.EditText01);
        botao= (Button) findViewById(R.id.botao);
        superV = (TextView) findViewById(R.id.superV);
        myGestDetector = new GestureDetector(this, new GestureListener());


        //editText
        superV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)            {
                myGestDetector.onTouchEvent(event);
                if(mudou == true){

                    Thread animation = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ObjectAnimator animation = ObjectAnimator.ofFloat(superV, "translationX", 1090f);
                                    animation.setDuration(2000);
                                    animation.start();
                                }});
                        }});
                    animation.start();

                    Thread animation1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                a.sleep(2000);
                            } catch (Exception e){

                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    superV.setText("SuperVision 86");
                                    ObjectAnimator animation = ObjectAnimator.ofFloat(superV, "translationX", 0);
                                    animation.setDuration(2000);
                                    animation.start();

                                }});
                        }});
                    animation1.start();


                    SERVERPORT = 9998;
                    mudou = false;



                } else {
                    Thread animation = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ObjectAnimator animation = ObjectAnimator.ofFloat(superV, "translationX", 1090f);
                                    animation.setDuration(2000);
                                    animation.start();
                                }});
                        }});
                    animation.start();

                    Thread animation1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                a.sleep(2000);
                            } catch (Exception e){

                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    superV.setText("SuperVision 82");
                                    ObjectAnimator animation = ObjectAnimator.ofFloat(superV, "translationX", 0);
                                    animation.setDuration(2000);
                                    animation.start();

                                }});
                        }});
                    animation1.start();











                    SERVERPORT = 9999;
                    mudou = true;
                }
                return false;            }

        });


        value = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, value);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listview.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),"Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });

        botao.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Thread ab = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ObjectAnimator animation = ObjectAnimator.ofFloat(editText, "translationX", 1090f);
                                animation.setDuration(2000);
                                animation.start();
                                ObjectAnimator animation2 = ObjectAnimator.ofFloat(botao, "translationX", 1000f);
                                animation2.setDuration(2000);
                                animation2.start();
                            }});
                    }});
                ab.start();

                a = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            a.sleep(2000);
                        } catch (Exception e){

                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                editText.setVisibility(View.INVISIBLE);

                                botao.setVisibility(View.INVISIBLE);
                                superV.setVisibility(View.VISIBLE);
                            }});
                    }});
                a.start();

              //  connect();
            }
        });

    }
    void connect(){

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        SERVER_IP = editText.getText().toString();
                        InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

                        socket = new Socket(serverAddr, SERVERPORT);

                        InputStream is = socket.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr);

                        message = br.readLine();

                        System.out.println("Message received from the server : " + message);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    value.clear();
                                } catch(Exception e) {
                                    Log.e("ue","error");
                                }
                                separador = Arrays.asList(message.split(";"));
                                for(String elem : separador) {
                                    value.add(elem);
                                }

                                adapter.notifyDataSetChanged();

                            }
                        });


                    } catch (UnknownHostException e1) {
                        e1.printStackTrace();
                        Log.e("Ue", "Unknown");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        Log.e("Ue", "Fail");

                    } finally {
                        try {
                            socket.close();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        thread.sleep(2000);
                    } catch (Exception e){

                    }
                }

            }
        });
        thread.start();




    };
}